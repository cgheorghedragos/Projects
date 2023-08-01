package com.ciurezu.gheorghe.dragos.greenlight.presentation.app.home.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ciurezu.gheorghe.dragos.greenlight.data.SharedPrefs
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.GreenLightApiResponse
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.Resource
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.add_voucher.AddVoucherReq
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.add_voucher.CompReq
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.get_user.GetUserResponse
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.transfer_money.TransferMoney
import com.ciurezu.gheorghe.dragos.greenlight.data.repository.GreenLightRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeScreenViewModel @Inject constructor(
    private val greenLightRepository: GreenLightRepository,
    private val sharedPrefs: SharedPrefs
) : ViewModel() {
    val messageResponse: MutableLiveData<String> = MutableLiveData()
    val userResponse: MutableLiveData<Resource<GreenLightApiResponse<GetUserResponse>>> =
        MutableLiveData()

    fun getUser() {
        val user = sharedPrefs.getUsername()

        viewModelScope.launch {
            greenLightRepository.getUser(user).collect {
                userResponse.postValue(it)
            }
        }
    }

    fun isAdmin(): Boolean {
        return sharedPrefs.getIsAdmin()
    }

    fun isShopper(): Boolean {
        return sharedPrefs.getIsShopper()
    }

    fun transferMoney(money: Long, user: String) {
        val transfer = TransferMoney(
            money = money.toInt(),
            userThatTransfer = sharedPrefs.getUsername(),
            userThatReceive = user
        )

        viewModelScope.launch {
            greenLightRepository.transferMoney(transfer).collect {
                when (it) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        messageResponse.postValue("Success")
                    }

                    is Resource.Error -> {
                        messageResponse.postValue(it.errorMessage)
                    }
                }
            }
        }
    }

    fun addVoucher(price: Int, quantity: Int, description: String) {
        val company = CompReq("blank", sharedPrefs.getUsername())
        val addVoucher = AddVoucherReq(
            quantity = quantity,
            price = price,
            description = description,
            company = company
        )

        viewModelScope.launch {
            greenLightRepository.addVoucher(addVoucher).collect {
                when (it) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        messageResponse.postValue("Success")
                    }

                    is Resource.Error -> {
                        messageResponse.postValue(it.errorMessage)
                    }
                }
            }
        }
    }
}