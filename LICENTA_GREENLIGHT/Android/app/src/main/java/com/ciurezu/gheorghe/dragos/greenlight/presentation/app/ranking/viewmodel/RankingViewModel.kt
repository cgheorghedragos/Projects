package com.ciurezu.gheorghe.dragos.greenlight.presentation.app.ranking.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.GreenLightApiResponse
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.Resource
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.get_users_by_coins.response.GetAllUsersPctResponse
import com.ciurezu.gheorghe.dragos.greenlight.data.repository.GreenLightRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class RankingViewModel @Inject constructor(
    private val greenLightRepository: GreenLightRepository
) : ViewModel() {
    val fetchUsersResponse: MutableLiveData<Resource<GreenLightApiResponse<List<GetAllUsersPctResponse>>>> =
        MutableLiveData()

    init {
        fetchUsers()
    }
    fun fetchUsers() {
        viewModelScope.launch {
            greenLightRepository.getAllUsersByPoints().collect {
                fetchUsersResponse.postValue(it)
            }
        }
    }

}