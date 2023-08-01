package com.ciurezu.gheorghe.dragos.greenlight.presentation.app.map.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ciurezu.gheorghe.dragos.greenlight.R
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.map.adapter.ImageAdapter
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.map.viewmodel.MapViewModel
import dagger.android.support.AndroidSupportInjection
import java.util.stream.Collectors
import javax.inject.Inject


class FragmentInfoMarker : Fragment() {
    @Inject
    lateinit var mapVM: MapViewModel

    private lateinit var incidentPhoto: ImageView
    private lateinit var descriptionText: TextView
    private lateinit var photoRv: RecyclerView
    private lateinit var photoAdapter: ImageAdapter
    private lateinit var solveThisBtn: Button
    private lateinit var closeBtn: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info_marker, container, false)
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        initListeners()
        initObservers()
    }

    private fun initObservers() {
        mapVM.currentIncidentResponse.observe(viewLifecycleOwner) {

            photoAdapter.updateListAdapter(
                it.photos.stream().map { photo -> photo.photoUrl }.collect(
                    Collectors.toList()
                )
            )
            descriptionText.text = it.description

            val icon =
                mapVM.fetchDashboardData().find { images -> images.type.equals(it.type) }?.icon
            icon?.let { incidentPhoto.setImageResource(icon) }
        }
    }

    private fun initListeners() {
        solveThisBtn.setOnClickListener { findNavController().navigate(R.id.action_fragmentInfoMarker_to_fragmentSolveMarker) }
        closeBtn.setOnClickListener { findNavController().navigate(R.id.action_fragmentInfoMarker_to_fragmentGoogleMap) }
    }

    private fun initViews(view: View) {
        incidentPhoto = view.findViewById(R.id.image_view_rep_incident)
        descriptionText = view.findViewById(R.id.info_description_incident)
        photoRv = view.findViewById(R.id.photo_incident_rv)
        closeBtn = view.findViewById(R.id.close_info_marker)
        solveThisBtn = view.findViewById(R.id.solve_this_incident_btn)
        photoRv.layoutManager = LinearLayoutManager(context)

        photoAdapter = ImageAdapter()
        photoRv.adapter = photoAdapter
    }
}