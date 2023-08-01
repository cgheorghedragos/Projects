package com.ciurezu.gheorghe.dragos.greenlight.presentation.app.map.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.ciurezu.gheorghe.dragos.greenlight.R
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.get_incident.IncidentResponse
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.rb.GetRecycleBin
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.map.viewmodel.MapViewModel
import com.ciurezu.gheorghe.dragos.greenlight.util.ImageConverter
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapView
import com.mapbox.maps.Style
import com.mapbox.maps.plugin.annotation.annotations
import com.mapbox.maps.plugin.annotation.generated.OnPointAnnotationClickListener
import com.mapbox.maps.plugin.annotation.generated.OnPointAnnotationLongClickListener
import com.mapbox.maps.plugin.annotation.generated.PointAnnotation
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions
import com.mapbox.maps.plugin.annotation.generated.createPointAnnotationManager
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class FragmentMapScreen : Fragment() {

    @Inject
    lateinit var mapVM: MapViewModel

    var mapView: MapView? = null
    private lateinit var addIncident: ImageView
    private lateinit var userLocation: PointAnnotation
    private var markerMap = HashMap<IncidentResponse, PointAnnotation>()
    private var markerMapinverse = HashMap<PointAnnotation, IncidentResponse>()
    private var rbMarkerMapInv = HashMap<PointAnnotation, GetRecycleBin>()
    private var pointAnnotationNonClickManager: PointAnnotationManager? = null
    private var pointAnnotationOnClickManager: PointAnnotationManager? = null
    private var recycleBinManager: PointAnnotationManager? = null

    private lateinit var addRecycleBin: ImageView

    companion object {
        const val DEFAULT_ZOOM = 15.0

        const val DEFAULT_LAT_NO_LOCATION = 46.770920
        const val DEFAULT_LNG_NO_LOCATION = 23.589920
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        initListeners(view)
        mapVM.getIncidents()
        mapVM.getAllRB()
    }

    private fun initListeners(view: View) {

        addIncident.setOnClickListener {
            mapView?.visibility = View.GONE
            val nanOptions = NavOptions.Builder().setEnterAnim(R.anim.slide_in_left).build()
            it.findNavController().navigate(
                R.id.action_fragmentGoogleMap_to_fragmentReportDashboard, null, nanOptions
            )
        }

        addRecycleBin.setOnClickListener {
            mapVM.putRB()
        }
    }

    private fun initViews(view: View) {


        addIncident = view.findViewById(R.id.add_incident_action_button)
        initMap(view)
        initClickableManager()
        initUserLocation()
        initIncidents()
        initObservers(view)
        addRecycleBin = view.findViewById(R.id.addRecycleBin)

        addRecycleBin.visibility = if(mapVM.isAdmin()) View.VISIBLE else View.GONE
    }


    private fun initUserLocation() {
        ImageConverter.bitmapFromDrawableRes(
            requireContext(), R.drawable.ic_my_location
        )?.let {
            val annotationApi = mapView?.annotations
            pointAnnotationNonClickManager = annotationApi?.createPointAnnotationManager()
            val pointAnnotationOptions: PointAnnotationOptions = PointAnnotationOptions().withPoint(
                Point.fromLngLat(
                    mapVM.getLocation()!!.longitude, mapVM.getLocation()!!.latitude
                )
            ).withIconImage(it)

            userLocation = pointAnnotationNonClickManager?.create(pointAnnotationOptions)!!
        }
    }

    private fun initClickableManager() {
        val annotationApi = mapView?.annotations
        pointAnnotationOnClickManager = annotationApi?.createPointAnnotationManager()
        recycleBinManager = annotationApi?.createPointAnnotationManager()
        recycleBinManager?.addLongClickListener(onLongClickListener)
        pointAnnotationOnClickManager?.addClickListener(onItemClickListener)
    }

    private val onLongClickListener = OnPointAnnotationLongClickListener {
        val item = rbMarkerMapInv[it]
        if(mapVM.isAdmin())   mapVM.deleteRB(item!!.id)

        true
    }


    private val onItemClickListener = OnPointAnnotationClickListener {
        mapVM.currentIncidentResponse.postValue(markerMapinverse[it])
        findNavController().navigate(R.id.action_fragmentGoogleMap_to_fragmentInfoMarker)
        true
    }


    private fun initObservers(view: View) {
        initUserLocationWatcher()
        initDeleteWatcher()
        initUpdateWatcher()

        mapVM.errorResponses.observe(viewLifecycleOwner) {
            if (it != null) {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                mapVM.errorResponses.postValue(null)
            }
        }

        observeRB()
    }

    private fun initUserLocationWatcher() {
        mapVM.currentLocation.observe(viewLifecycleOwner) {
            userLocation.point = Point.fromLngLat(it.longitude, it.latitude)
            pointAnnotationNonClickManager?.update(userLocation)
        }
    }


    private fun initDeleteWatcher() {
        mapVM.shouldBeDeletedResponse.observe(viewLifecycleOwner) { itemsDeleted ->
            itemsDeleted?.forEach { item ->
                val currentPoint = markerMap[item]
                currentPoint?.let {
                    pointAnnotationOnClickManager?.delete(it)
                    markerMap.remove(item)
                    markerMapinverse.remove(currentPoint)
                }

            }
            mapVM.shouldBeDeletedResponse.postValue(null)
        }
    }

    private fun initUpdateWatcher() {
        mapVM.shouldBeAddedResponse.observe(viewLifecycleOwner) { itemsAdded ->
            itemsAdded?.forEach { item ->
                addIncidentToMap(item)
            }
            mapVM.shouldBeAddedResponse.postValue(null)
        }
    }

    private fun addIncidentToMap(item: IncidentResponse) {
        val photo = mapVM.getPhoto(requireContext(), item.type)
        photo?.let {
            val pointAnnotationOptions: PointAnnotationOptions =
                PointAnnotationOptions().withPoint(Point.fromLngLat(item.longitude, item.latitude))
                    .withIconImage(photo)

            val pointAnnotation: PointAnnotation =
                pointAnnotationOnClickManager?.create(pointAnnotationOptions)!!
            markerMap[item] = pointAnnotation
            markerMapinverse[pointAnnotation] = item
        }
    }

    private fun observeRB() {
        mapVM.rbResponse.observe(viewLifecycleOwner) {
            val allKeys = rbMarkerMapInv.keys
            recycleBinManager?.delete(allKeys.toList())

            it.forEach { rb ->
                val trashPhoto = ImageConverter.bitmapFromDrawableRes(
                    requireContext(),
                    R.drawable.recyclebin
                )

                trashPhoto?.let {
                    val pointAnnotationOptions: PointAnnotationOptions =
                        PointAnnotationOptions().withPoint(
                            Point.fromLngLat(
                                rb.longitude,
                                rb.latitude
                            )
                        )
                            .withIconImage(it)

                    val pointAnnotation: PointAnnotation =
                        recycleBinManager?.create(pointAnnotationOptions)!!

                    rbMarkerMapInv[pointAnnotation] = rb
                }
            }
        }
    }


    private fun initMap(view: View) {
        mapView = view.findViewById(R.id.mapView)
        mapView?.getMapboxMap()?.loadStyleUri(Style.MAPBOX_STREETS)
        val cameraPosition = CameraOptions.Builder().zoom(DEFAULT_ZOOM).center(
            Point.fromLngLat(mapVM.getLocation()!!.longitude, mapVM.getLocation()!!.latitude)
        ).build()

        // set camera position
        mapView?.getMapboxMap()?.setCamera(cameraPosition)

    }

    private fun initIncidents() {
        mapVM.incidentsResponse.value?.forEach { item ->
            addIncidentToMap(item)
        }
    }

    override fun onStart() {
        super.onStart()
        mapVM.startLocationUpdates(requireContext())
    }

    override fun onStop() {
        super.onStop()
        mapVM.stopLocationUpdates()
    }

}