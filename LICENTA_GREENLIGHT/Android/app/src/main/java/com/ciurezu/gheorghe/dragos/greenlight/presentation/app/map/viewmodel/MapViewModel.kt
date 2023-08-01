package com.ciurezu.gheorghe.dragos.greenlight.presentation.app.map.viewmodel


import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.location.Location
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ciurezu.gheorghe.dragos.greenlight.R
import com.ciurezu.gheorghe.dragos.greenlight.data.SharedPrefs
import com.ciurezu.gheorghe.dragos.greenlight.data.local.model.DashboardData
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.Resource
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.add_incident.AddIncidentRequest
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.get_incident.IncidentResponse
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.rb.AddRecycleBin
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.rb.GetRecycleBin
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.solve_incident.SolveIncident
import com.ciurezu.gheorghe.dragos.greenlight.data.repository.GreenLightRepository
import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.map.LocationHandler
import com.ciurezu.gheorghe.dragos.greenlight.util.ImageConverter
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MapViewModel @Inject constructor(
    private val greenLightRepository: GreenLightRepository,
    private val sharedPrefs: SharedPrefs
) : ViewModel() {
    companion object {
        private const val GARBAGE_DESCRIPTION = "garbage"
        private const val FIRE_DESCRIPTION = "fire";
        private const val ANIMALS_DESCRIPTION = "animals";
        private const val FLOOD_DESCRIPTION = "flood";
        private const val BIOLOGICAL_HAZARD_DESCRIPTION = "biological_hazard";
        private const val FISHING_DESCRIPTION = "fishing";
        private const val DEFORESTING_DESCRIPTION = "deforesting";
        private const val RADIOACTIVITY_DESCRIPTION = "radioactivity";

        private const val GARBAGE_TYPE = "garbage";
        private const val FIRE_TYPE = "fire";
        private const val ANIMALS_TYPE = "animals";
        private const val FLOOD_TYPE = "flood";
        private const val BIOLOGICAL_HAZARD_TYPE = "biological_hazard";
        private const val FISHING_TYPE = "fishing";
        private const val DEFORESTING_TYPE = "deforesting";
        private const val RADIOACTIVITY_TYPE = "radioactivity";

    }

    val currentLocation = MutableLiveData<Location>()
    val currentIncidentResponse: MutableLiveData<IncidentResponse> = MutableLiveData()
    val incidentsResponse: MutableLiveData<List<IncidentResponse>> =
        MutableLiveData()
    val shouldBeDeletedResponse: MutableLiveData<List<IncidentResponse>> =
        MutableLiveData()
    val shouldBeAddedResponse: MutableLiveData<List<IncidentResponse>> =
        MutableLiveData()
    val rbResponse: MutableLiveData<List<GetRecycleBin>> =
        MutableLiveData()
    val shouldFireStart: MutableLiveData<Boolean> =
        MutableLiveData()
    var currentDataSelected: DashboardData = DashboardData(
        R.drawable.ic_trash, GARBAGE_DESCRIPTION, GARBAGE_TYPE
    )

    val usersToAdd = MutableLiveData<String>()
    val errorResponses = MutableLiveData<String>()

    fun getLocation(): LatLng? = sharedPrefs.getLastUserLocation();

    fun calculateDistance(
        lat1: Double,
        lon1: Double,
        lat2: Double,
        lon2: Double
    ): Double {
        var distance = FloatArray(1)
        Location.distanceBetween(
            lat1,
            lon1,
            lat2,
            lon2,
            distance
        )
        return distance[0].toDouble()
    }

    fun fetchDashboardData(): ArrayList<DashboardData> {
        val garbage = DashboardData(R.drawable.ic_trash, GARBAGE_DESCRIPTION, GARBAGE_TYPE)
        val fire = DashboardData(R.drawable.ic_forest_fire, FIRE_DESCRIPTION, FIRE_TYPE)
        val animals = DashboardData(R.drawable.ic_injured_animal, ANIMALS_DESCRIPTION, ANIMALS_TYPE)
        val deforesting = DashboardData(
            R.drawable.ic_deforesting, DEFORESTING_DESCRIPTION, DEFORESTING_TYPE
        )
        val radioactivyty = DashboardData(
            R.drawable.ic_radiocativity, RADIOACTIVITY_DESCRIPTION, RADIOACTIVITY_TYPE
        )
        val biological_hazard = DashboardData(
            R.drawable.ic_biological_hazard, BIOLOGICAL_HAZARD_DESCRIPTION, BIOLOGICAL_HAZARD_TYPE
        )
        val flood = DashboardData(R.drawable.ic_flood, FLOOD_DESCRIPTION, FLOOD_TYPE)
        val fishing = DashboardData(R.drawable.ic_water_trash, FISHING_DESCRIPTION, FISHING_TYPE)

        return ArrayList(
            listOf(
                garbage,
                fire,
                animals,
                deforesting,
                radioactivyty,
                biological_hazard,
                flood,
                fishing
            )
        )
    }

    fun getPhoto(context: Context, type: String): Bitmap? {
        val element = fetchDashboardData().find { it.type == type }
        element?.let {
            return ImageConverter.bitmapFromDrawableRes(
                context,
                element.icon
            )
        }
        return null
    }

    @SuppressLint("MissingPermission")
    fun startLocationUpdates(context: Context) {
        LocationHandler.startWatchingUserLocation(context) { location ->
            currentLocation.value = location
            sharedPrefs.saveUserLocation(location)
        }
    }

    fun isAdmin() :Boolean{
        return sharedPrefs.getIsAdmin()
    }

    fun stopLocationUpdates() {
        LocationHandler.stopWatchingUserLocation()
    }

    fun uploadImage(files: List<File>, context: Context, description: String) {
        val body: MutableList<MultipartBody.Part> = ArrayList()
        for (file in files) {
            body.add(prepareFile(file))
        }

        val incidentRequest = AddIncidentRequest(
            sharedPrefs.getLastUserLocation()!!.latitude,
            sharedPrefs.getLastUserLocation()!!.longitude,
            description,
            currentDataSelected.type
        )

        val json = prepareJson(incidentRequest)

        viewModelScope.launch {
            greenLightRepository.addIncident(body, json).collect {
//                postPhotoLinkLiveData.postValue(it)
            }
        }
    }

    fun getIncidents() {
        viewModelScope.launch {
            greenLightRepository.getIncidents().collect { resourse ->
                when (resourse) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        val oldList = incidentsResponse.value
                        val newList = resourse.data.payload

                        val shouldBeDeleted = oldList?.minus(newList)
                        var shouldBeAdded: List<IncidentResponse>? = ArrayList()

                        oldList?.let { shouldBeAdded = newList.minus(it) }

                        shouldBeDeleted?.let {
                            this@MapViewModel.shouldBeDeletedResponse.postValue(
                                it
                            )
                        }
                        shouldBeAdded?.let { shouldBeAddedResponse.postValue(it) }

                        shouldBeAdded?.let {
                            val found = it.any { incidentResponse ->
                                val distance = calculateDistance(
                                    lat1 = incidentResponse.latitude,
                                    lat2 = sharedPrefs.getLastUserLocation()!!.latitude,
                                    lon1 = incidentResponse.longitude,
                                    lon2 = sharedPrefs.getLastUserLocation()!!.longitude
                                )
                                distance < 1000&&incidentResponse.type=="fire"
                            }

                            if (found) {
                                shouldFireStart.postValue(true)
                            }
                        }
                        incidentsResponse.postValue(newList)
                    }

                    is Resource.Error -> {}
                }

            }
        }
    }


    private fun prepareFile(file: File): MultipartBody.Part {
        val requestFile = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
        val body = MultipartBody.Part.createFormData("file", file.name, requestFile)
        return body;
    }

    private fun prepareJson(incidentRequest: AddIncidentRequest): RequestBody {
        val gson: Gson = GsonBuilder().create()
        val jsonString: String = gson.toJson(incidentRequest)

        val json = RequestBody.create("application/json".toMediaTypeOrNull(), jsonString)

        return json;
    }

    fun checkUserAvailability(user: String) {

        viewModelScope.launch {
            if (sharedPrefs.getUsername().equals(user)) {
                errorResponses.postValue("You can't include you");
            } else {
                greenLightRepository.checkUserAvailability(user).collect {
                    when (it) {
                        is Resource.Loading -> {}
                        is Resource.Error -> {
                            errorResponses.postValue(it.errorMessage)
                        }

                        is Resource.Success -> {
                            it.data.payload.let { usersToAdd.postValue(it) }
                        }
                    }
                }
            }
        }
    }

    fun solveMarker(users: List<String>) {
        viewModelScope.launch {
            val solveIncident = SolveIncident(0, HashSet())
            solveIncident.incidentId = currentIncidentResponse.value!!.id
            solveIncident.users = users.toHashSet()
            solveIncident.users.add(sharedPrefs.getUsername())

            greenLightRepository.solveIncident(solveIncident).collect {
                when (it) {
                    is Resource.Loading -> {}
                    is Resource.Error -> {
                        errorResponses.postValue(it.errorMessage)
                    }

                    is Resource.Success -> {
                        errorResponses.postValue("Solved successfully!")
                    }
                }
            }
        }
    }

    fun getAllRB() {
        viewModelScope.launch {

            greenLightRepository.getRB().collect {
                when (it) {
                    is Resource.Success -> rbResponse.postValue(it.data.payload.toList())
                    is Resource.Error -> {}
                    is Resource.Loading -> {}
                }
            }
        }
    }

    fun putRB() {

        val rb = AddRecycleBin(
            sharedPrefs.getLastUserLocation()!!.latitude,
            sharedPrefs.getLastUserLocation()!!.longitude,
            "recyclebin"
        )
        viewModelScope.launch {
            greenLightRepository.addRecycleBin(rb).collect {
                when (it) {
                    is Resource.Success -> getAllRB()
                    is Resource.Error -> {
                        errorResponses.postValue(it.errorMessage)
                    }

                    is Resource.Loading -> {}
                }
            }
        }
    }

    fun deleteRB(id: Long) {
        viewModelScope.launch {
            greenLightRepository.deleteRB(id).collect {
                when (it) {
                    is Resource.Success -> getAllRB()
                    is Resource.Error -> {
                        errorResponses.postValue(it.errorMessage)
                    }

                    is Resource.Loading -> {}
                }
            }
        }
    }

}