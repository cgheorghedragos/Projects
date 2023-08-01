package com.ciurezu.gheorghe.dragos.greenlight.presentation.app.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ciurezu.gheorghe.dragos.greenlight.data.SharedPrefs
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.Resource
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.add_company.AddCompanyRequest
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.get_company.CompanyResponse
import com.ciurezu.gheorghe.dragos.greenlight.data.repository.GreenLightRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class CompanyViewModel @Inject constructor(
    private val greenLightRepository: GreenLightRepository,
    private val sharedPrefs: SharedPrefs
) : ViewModel() {

    val successResponse: MutableLiveData<Boolean> = MutableLiveData()
    val companiesResponse: MutableLiveData<List<CompanyResponse>> = MutableLiveData()

    val errorResponse: MutableLiveData<String> =
        MutableLiveData()

    fun addCompany(company: AddCompanyRequest) {

        viewModelScope.launch {
            greenLightRepository.addCompany(company).collect {
                when (it) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        it.data.payload.let { it2 -> successResponse.postValue(it2) }
                        getCompanies()
                    }

                    is Resource.Error -> {
                        errorResponse.postValue(it.errorMessage)
                    }
                }
            }
        }
    }

    fun updateCompany(companyResponse: CompanyResponse) {
        viewModelScope.launch {
            greenLightRepository.updateCompany(companyResponse).collect {
                when (it) {
                    is Resource.Loading -> {}
                    is Resource.Success -> { getCompanies() }
                    is Resource.Error -> { errorResponse.postValue(it.errorMessage)
                    }
                }
            }
        }
    }

    fun getCompanies() {
        viewModelScope.launch {
            greenLightRepository.getCompanies().collect {
                when (it) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        it.data.payload.let { it2 -> companiesResponse.postValue(it2) }

                    }

                    is Resource.Error -> {
                        errorResponse.postValue(it.errorMessage)
                    }
                }
            }
        }
    }
}