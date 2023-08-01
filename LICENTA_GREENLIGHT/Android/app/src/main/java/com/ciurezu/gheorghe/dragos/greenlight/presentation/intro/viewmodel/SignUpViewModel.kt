package com.ciurezu.gheorghe.dragos.greenlight.presentation.intro.viewmodel


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ciurezu.gheorghe.dragos.greenlight.data.SharedPrefs
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.Resource
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.add_user.request.UserSignUpRequest
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.add_user_address.request.AddUserAddressRequest
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.add_user_address.request.SimpleAdress
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.add_user_address.request.SimpleUser
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.add_user_photo.request.AddUserPhotoRequest
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.check_username.check_email.UserWithEmail
import com.ciurezu.gheorghe.dragos.greenlight.data.repository.GreenLightRepository
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SignUpViewModel @Inject constructor(
    private val greenLightRepository: GreenLightRepository,
    private val sharedPrefs: SharedPrefs
) : ViewModel() {
    val currentUser: MutableLiveData<UserSignUpRequest> = MutableLiveData()
    val errorResponse: MutableLiveData<String> = MutableLiveData()
    val checkUserResponse: MutableLiveData<Boolean> = MutableLiveData()
    val singUpResponse: MutableLiveData<Boolean> = MutableLiveData()
    val updateResponse: MutableLiveData<Boolean> = MutableLiveData()


    fun checkUserEmail(userEm: UserWithEmail) {
        viewModelScope.launch {
            greenLightRepository.checkUserEmail(userEm).collect {
                when (it) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        it.data.payload.let { it2 ->
                            checkUserResponse.postValue(it2)
                            Log.e("check_email_response", "" + it2)
                        }
                    }

                    is Resource.Error -> {
                        errorResponse.postValue(it.errorMessage)
                    }
                }
            }
        }
    }

    fun signUpUser(user: UserSignUpRequest) {
        checkUserResponse.postValue(false)
        viewModelScope.launch {
            greenLightRepository.registerUser(user).collect {
                when (it) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        it.data?.payload?.let { bool ->
                            currentUser.postValue(user)
                            singUpResponse.postValue(bool) }
                    }

                    is Resource.Error -> {
                        errorResponse.postValue(it.errorMessage)
                    }
                }
            }
        }
    }


    fun updateUser(town: String, street: String, streetNr: Int, photo: File?) {
        val user = SimpleUser(currentUser.value!!.username)
        val address = SimpleAdress(town = town, street = street, streetNr = streetNr)
        val userAdress = AddUserAddressRequest(user = user, address = address)
        viewModelScope.launch {
            greenLightRepository.addUserAddress(userAdress).collect {
                when (it) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        Log.e("success_add_user","_");
                        addProfilePhotoRequest(photo)
                    }

                    is Resource.Error -> {
                        Log.e("erroare_add_user","_");
                        errorResponse.postValue(it.errorMessage)
                    }
                }
            }
        }


    }

    private suspend fun addProfilePhotoRequest(photo: File?) {
        if (photo != null) {
            greenLightRepository.postPhotoLink(prepareFile(photo)).collect { photoResponse ->
                when (photoResponse) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        Log.e("success_add_photo_link","_");
                        addUserProfilePhotoRequest(photoResponse.data.payload)
                    }

                    is Resource.Error -> {
                        Log.e("erroare_add_photo_link","_");
                        errorResponse.postValue(photoResponse.errorMessage)
                    }
                }
            }
        } else {
            updateResponse.postValue(true)
        }
    }

    private suspend fun addUserProfilePhotoRequest(photoUrl: String) {
        val userPhotoRequest =
            AddUserPhotoRequest(user = currentUser.value!!.username, photoUrl = photoUrl)

        greenLightRepository.addUserPhoto(userPhotoRequest).collect {
            when (it) {
                is Resource.Loading -> {}
                is Resource.Success -> {
                    Log.e("success_add_user_photo","_");
                    updateResponse.postValue(true)
                }

                is Resource.Error -> {
                    Log.e("eroare_add_user_photo","_");
                    errorResponse.postValue(it.errorMessage)
                }
            }
        }
    }

    private fun prepareFile(file: File): MultipartBody.Part {
        val requestFile = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
        val body = MultipartBody.Part.createFormData("photo", file.name, requestFile)
        return body;
    }
}