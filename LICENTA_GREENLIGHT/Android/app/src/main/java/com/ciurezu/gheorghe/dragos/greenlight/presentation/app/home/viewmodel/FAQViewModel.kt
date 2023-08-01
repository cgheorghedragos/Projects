package com.ciurezu.gheorghe.dragos.greenlight.presentation.app.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ciurezu.gheorghe.dragos.greenlight.data.SharedPrefs
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.GreenLightApiResponse
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.Resource
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.faq.FaqResponse
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.faq.SimpleFAQ
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.login_user.request.UserSignInRequest
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.login_user.response.UserSignInResponse
import com.ciurezu.gheorghe.dragos.greenlight.data.repository.GreenLightRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class FAQViewModel @Inject constructor(
    private val greenLightRepository: GreenLightRepository,
    private val sharedPrefs: SharedPrefs
) : ViewModel() {
    val faqResponse: MutableLiveData<Resource<GreenLightApiResponse<List<FaqResponse>>>> =
        MutableLiveData()
    val faqUpdateResponse: MutableLiveData<Resource<GreenLightApiResponse<Boolean>>> =
        MutableLiveData()
    val faqDeleteResponse: MutableLiveData<Resource<GreenLightApiResponse<Boolean>>> =
        MutableLiveData()

    fun getAllFaq() {
        viewModelScope.launch {
            greenLightRepository.getAllFAQ().collect {
                faqResponse.postValue(it)
            }
        }
    }
    fun addFaq(faq: SimpleFAQ) {
        viewModelScope.launch {
            greenLightRepository.addFaq(faq).collect {
                faqUpdateResponse.postValue(it)
            }
        }
    }

    fun updateFaq(oldTitle: String, newTitle: String, newDescription: String) {
        val list = ArrayList<SimpleFAQ>()
        list.add(SimpleFAQ(oldTitle,""))
        list.add(SimpleFAQ(newTitle,newDescription))

        viewModelScope.launch {
            greenLightRepository.updateFAQ(list).collect {
                faqUpdateResponse.postValue(it)
            }
        }
    }

    fun deleteFaq(title:String){

        viewModelScope.launch {
            greenLightRepository.deleteFAQ(title).collect{
                faqDeleteResponse.postValue(it)
            }
        }
    }

    fun isAdmin(): Boolean {
        return sharedPrefs.getIsAdmin()
    }

}