package com.ciurezu.gheorghe.dragos.greenlight.presentation.app.home.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ciurezu.gheorghe.dragos.greenlight.data.SharedPrefs
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.GreenLightApiResponse
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.Resource
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.TrashResponse
import com.ciurezu.gheorghe.dragos.greenlight.data.repository.GreenLightRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class TrashViewModel @Inject constructor(
    private val greenLightRepository: GreenLightRepository,
    private val sharedPrefs: SharedPrefs
) : ViewModel() {
    val allTrashResponse: MutableLiveData<Resource<GreenLightApiResponse<List<TrashResponse>>>> =
        MutableLiveData()

    val booleanResponse: MutableLiveData<Resource<GreenLightApiResponse<Boolean>>> =
        MutableLiveData()

    val stringResponse: MutableLiveData<String> = MutableLiveData()


    fun addTrash(trash: TrashResponse) {
        viewModelScope.launch {
            greenLightRepository.addTrash(trash).collect {
                if (it is Resource.Success) {
                    getAllTrashes()
                } else if(it is Resource.Error){
                    stringResponse.postValue(it.errorMessage)
                    Log.e("ERROR",it.errorMessage.toString())
                }


            }
        }
    }
    fun deleteTrash(barcode: String) {
        viewModelScope.launch {
            greenLightRepository.deleteTrash(barcode).collect {
                if (it is Resource.Success) {
                    getAllTrashes()
                } else {
                    booleanResponse.postValue(it)
                }
            }
        }
    }

    fun updateTrash(trash: TrashResponse) {
        viewModelScope.launch {
            greenLightRepository.updateTrash(trash).collect {
                if (it is Resource.Success) {
                    getAllTrashes()
                } else {
                    booleanResponse.postValue(it)
                }
            }
        }
    }

    fun getAllTrashes() {
        viewModelScope.launch {
            greenLightRepository.getAllTrashes().collect {
                allTrashResponse.postValue(it)
            }
        }
    }

    fun isAdmin(): Boolean {
        return sharedPrefs.getIsAdmin()
    }
}