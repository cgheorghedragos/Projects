package com.ciurezu.gheorghe.dragos.greenlight.presentation.app.shopping.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.GreenLightApiResponse
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.Resource
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.get_voucher.UserVoucher
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.get_voucher.Voucher
import com.ciurezu.gheorghe.dragos.greenlight.data.repository.GreenLightRepository
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShoppingViewModel @Inject constructor(
    private val greenLightRepository: GreenLightRepository
) : ViewModel() {
    val fetchVouchersResponse: MutableLiveData<Resource<GreenLightApiResponse<List<Voucher>>>> =
        MutableLiveData()

    val fetchUserVoucherResponse: MutableLiveData<Resource<GreenLightApiResponse<List<UserVoucher>>>> =
        MutableLiveData()
    val buyVoucherResponse: MutableLiveData<Resource<GreenLightApiResponse<Boolean>>> =
        MutableLiveData()


    fun fetchVouchers() {
        viewModelScope.launch {
            greenLightRepository.getAllVouchers().collect {
                fetchVouchersResponse.postValue(it)
            }
        }
    }

    fun getUserVouchers() {
        viewModelScope.launch {
            greenLightRepository.getUserVoucher().collect {
                fetchUserVoucherResponse.postValue(it)
            }
        }
    }

    fun buyVoucher(voucher: Voucher, quantity: Int) {
        viewModelScope.launch {
            greenLightRepository.buyVoucher(voucher, quantity).collect {
                buyVoucherResponse.postValue(it)
            }
        }
    }
}