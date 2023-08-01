package com.ciurezu.gheorghe.dragos.greenlight.presentation.app.settings.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ciurezu.gheorghe.dragos.greenlight.data.SharedPrefs
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.Resource
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.get_user.GetUserWithAddress
import com.ciurezu.gheorghe.dragos.greenlight.data.repository.GreenLightRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val greenLightRepository: GreenLightRepository, private val sharedPrefs: SharedPrefs
) : ViewModel() {
    val userResponse: MutableLiveData<GetUserWithAddress> = MutableLiveData()
    val errorMessageResponse: MutableLiveData<String> =
        MutableLiveData()

    fun getUser() {
        val username: String = sharedPrefs.getUsername()
        viewModelScope.launch {
            greenLightRepository.getUserOnly(username).collect {
                when (it) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        it.data.payload.let { it2 ->
                            userResponse.postValue(it2)
                            errorMessageResponse.postValue("Success")
                        }
                    }

                    is Resource.Error -> {
                        errorMessageResponse.postValue(it.errorMessage)
                    }
                }
            }
        }
    }

    fun updateEmail(email: String) {
        val username: String = sharedPrefs.getUsername()
        if (!isEmailValid(email)) {
            errorMessageResponse.postValue("Email not valid")
            return
        }
        viewModelScope.launch {
            greenLightRepository.updateEmail(username, email).collect {
                when (it) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        getUser()
                    }

                    is Resource.Error -> {
                        errorMessageResponse.postValue(it.errorMessage)
                    }
                }
            }
        }
    }

    fun updatePhoneNumber(phoneNr: String) {
        val username: String = sharedPrefs.getUsername()

        viewModelScope.launch {
            greenLightRepository.updatePhoneNumber(username, phoneNr).collect {
                when (it) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        getUser()
                    }

                    is Resource.Error -> {
                        errorMessageResponse.postValue(it.errorMessage)
                    }
                }
            }
        }
    }

    fun updateTown(town: String) {
        val username: String = sharedPrefs.getUsername()

        viewModelScope.launch {
            greenLightRepository.updateTown(username, town).collect {
                when (it) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        getUser()
                    }

                    is Resource.Error -> {
                        errorMessageResponse.postValue(it.errorMessage)
                    }
                }
            }
        }
    }

    fun updateStreet(street: String) {
        val username: String = sharedPrefs.getUsername()

        viewModelScope.launch {
            greenLightRepository.updateStreet(username, street).collect {
                when (it) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        getUser()
                    }

                    is Resource.Error -> {
                        errorMessageResponse.postValue(it.errorMessage)
                    }
                }
            }
        }
    }

    fun updateStreetNr(streetNr: String) {
        val username: String = sharedPrefs.getUsername()

        viewModelScope.launch {
            greenLightRepository.updateStreetNr(username, streetNr).collect {
                when (it) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        getUser()
                    }

                    is Resource.Error -> {
                        errorMessageResponse.postValue(it.errorMessage)
                    }
                }
            }
        }
    }


    private fun isEmailValid(email: String): Boolean {
        val emailRegex = Regex("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}")
        return emailRegex.matches(email)
    }

}