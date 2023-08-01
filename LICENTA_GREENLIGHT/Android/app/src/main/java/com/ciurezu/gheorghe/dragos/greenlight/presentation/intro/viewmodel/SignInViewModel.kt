package com.ciurezu.gheorghe.dragos.greenlight.presentation.intro.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ciurezu.gheorghe.dragos.greenlight.data.SharedPrefs
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.Resource
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.login_user.request.UserSignInRequest
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.login_user.response.UserSignInResponse
import com.ciurezu.gheorghe.dragos.greenlight.data.repository.GreenLightRepository
import kotlinx.coroutines.launch
import java.util.Base64
import javax.inject.Inject


class SignInViewModel @Inject constructor(
    private val greenLightRepository: GreenLightRepository,
    private val sharedPref: SharedPrefs
) : ViewModel() {
    val signInResponse: MutableLiveData<Resource<UserSignInResponse>> = MutableLiveData()

    fun signInUser(username: String, password: String) {
        sharedPref.saveUser(username)

        val user = UserSignInRequest(username, password)
        viewModelScope.launch {
            greenLightRepository.loginUser(user).collect {
                signInResponse.postValue(it)
            }
        }
    }

    fun saveAccessToken(token: String) {
        sharedPref.saveAccessToken(token)
        sharedPref.saveIsAdmin(checkIsAdmin(token))
        sharedPref.saveIsShopper(checkIsShopper(token))
    }

    private fun checkIsAdmin(token: String): Boolean {
        val jwtParts = token.split('.')

        if (jwtParts.size == 3) {
            val payloadBase64 = jwtParts[1]
            val decodedBytes = Base64.getDecoder().decode(payloadBase64)
            val decodedPayload = String(decodedBytes)

            return decodedPayload.contains("ROLE_ADMIN")
        }
        return false
    }

    private fun checkIsShopper(token: String): Boolean {
        val jwtParts = token.split('.')

        if (jwtParts.size == 3) {
            val payloadBase64 = jwtParts[1]
            val decodedBytes = Base64.getDecoder().decode(payloadBase64)
            val decodedPayload = String(decodedBytes)

            return decodedPayload.contains("ROLE_SHOPPER")
        }
        return false
    }


}