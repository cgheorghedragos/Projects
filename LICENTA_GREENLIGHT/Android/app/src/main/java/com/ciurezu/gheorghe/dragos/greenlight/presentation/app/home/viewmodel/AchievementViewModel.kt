package com.ciurezu.gheorghe.dragos.greenlight.presentation.app.home.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ciurezu.gheorghe.dragos.greenlight.data.SharedPrefs
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.GreenLightApiResponse
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.Resource
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.get_achievement.ActiveAchResponse
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.get_achievement.InactiveAchResponse
import com.ciurezu.gheorghe.dragos.greenlight.data.repository.GreenLightRepository
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.util.stream.Collectors
import javax.inject.Inject

class AchievementViewModel @Inject constructor(
    private val greenLightRepository: GreenLightRepository,
    private val sharedPrefs: SharedPrefs
) : ViewModel() {
    val achActiveResponse: MutableLiveData<Resource<GreenLightApiResponse<List<ActiveAchResponse>>>> =
        MutableLiveData()
    val achInactiveResponse: MutableLiveData<Resource<GreenLightApiResponse<List<InactiveAchResponse>>>> =
        MutableLiveData()

    fun getAllAchievements() {
        val username: String = sharedPrefs.getUsername()
        viewModelScope.launch {
            greenLightRepository.getActiveAchByUser(username).collect {
                achActiveResponse.postValue(it)
            }
        }

        viewModelScope.launch {
            greenLightRepository.getInactiveAchByUser(username).collect {
                if (it is Resource.Success) {
                    it.data.payload.stream().map { inactiveAch ->
                        inactiveAch.createdOn = formatDateTime(inactiveAch.createdOn)
                    }.collect(Collectors.toList())
                }
                achInactiveResponse.postValue(it)
            }
        }
    }

    private fun formatDateTime(dateTimeString: String): String {
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss[.SSSSSS][.SSSSS][.SSSS][.SSS][.SS][.S]")
        val outputFormatter = DateTimeFormatter.ofPattern("d MMM yy, HH:mm", Locale.ENGLISH)
        val dateTime = LocalDateTime.parse(dateTimeString, inputFormatter)
        return dateTime.format(outputFormatter)
    }

}