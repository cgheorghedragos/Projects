package com.ciurezu.gheorghe.dragos.greenlight.data.repository

import android.util.Log
import com.ciurezu.gheorghe.dragos.greenlight.data.SharedPrefs
import com.ciurezu.gheorghe.dragos.greenlight.data.local.dao.FaqDAO
import com.ciurezu.gheorghe.dragos.greenlight.data.local.mappers.toErrorResponse
import com.ciurezu.gheorghe.dragos.greenlight.data.local.mappers.toFaq
import com.ciurezu.gheorghe.dragos.greenlight.data.local.mappers.toFaqResponse
import com.ciurezu.gheorghe.dragos.greenlight.data.local.mappers.toGreenLightResponse
import com.ciurezu.gheorghe.dragos.greenlight.data.network.GreenLightApi
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.Resource
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.TrashResponse
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.add_company.AddCompanyRequest
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.add_user.request.UserSignUpRequest
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.add_user_address.request.AddUserAddressRequest
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.add_user_photo.request.AddUserPhotoRequest
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.add_voucher.AddVoucherReq
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.buy_voucher.BuyVoucher
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.check_username.check_email.UserWithEmail
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.faq.FaqResponse
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.faq.SimpleFAQ
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.get_company.CompanyResponse
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.get_voucher.Voucher
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.login_user.request.UserSignInRequest
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.rb.AddRecycleBin
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.solve_incident.SolveIncident
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.transfer_money.TransferMoney
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.HttpException
import javax.inject.Inject
import kotlin.streams.toList

class GreenLightRepositoryImpl
@Inject constructor(
    private val faqDAO: FaqDAO,
    private val greenLightApi: GreenLightApi,
    private val sharedPrefs: SharedPrefs
) : GreenLightRepository {


    override suspend fun loginUser(user: UserSignInRequest) = flow {
        emit(Resource.Loading(true))
        val response = greenLightApi.loginUser(user)
        emit(Resource.Success(response))
    }.catch { e ->
        if (e is HttpException) {
            val body = e.response()!!.errorBody()
            val errorResponse = body?.toErrorResponse(body)

            if (errorResponse != null && errorResponse.message != null) {
                emit(Resource.Error(errorResponse.message))
            } else {
                emit(Resource.Error("An error has occured"))
            }
        }
    }

    override suspend fun registerUser(user: UserSignUpRequest) = flow {
        emit(Resource.Loading(true))
        val response = greenLightApi.registerUser(user)
        emit(Resource.Success(response))
    }.catch { e ->
        if (e is HttpException) {
            val body = e.response()!!.errorBody()
            val errorResponse = body.toErrorResponse(body!!)

            emit(Resource.Error(errorResponse.message))
        }
    }

    override suspend fun getAllUsersByPoints() = flow {
        emit(Resource.Loading(true))
        val response = greenLightApi.getAllUsersDescByPct()
        emit(Resource.Success(response))
    }.catch { e ->
        if (e is HttpException) {
            val body = e.response()!!.errorBody()
            val errorResponse = body.toErrorResponse(body!!)

            emit(Resource.Error(errorResponse.message))
        }
    }

    override suspend fun getAllFAQ() = flow {
        emit(Resource.Loading(true))
        val response = greenLightApi.getAllFAQ()
        emit(Resource.Success(response))
        cacheFaqs(response.payload)
    }.catch { e ->
        val cachedValue = faqDAO.getFaqs().stream().map { it.toFaqResponse() }.toList()

        emit(Resource.Success(cachedValue.toGreenLightResponse()))
//        emit(Resource.Error(e.toString()))
    }

    override suspend fun addUserAddress(address: AddUserAddressRequest) = flow {
        emit(Resource.Loading(true))
        val response = greenLightApi.addUserAddress(address)
        emit(Resource.Success(response))
    }.catch { e ->
        if (e is HttpException) {
            val body = e.response()!!.errorBody()
            val errorResponse = body.toErrorResponse(body!!)

            emit(Resource.Error(errorResponse.message))
        }
    }

    override suspend fun addUserPhoto(photo: AddUserPhotoRequest) = flow {
        emit(Resource.Loading(true))
        val response = greenLightApi.addProfilePhoto(photo)
        emit(Resource.Success(response))
    }.catch { e ->
        if (e is HttpException) {
            val body = e.response()!!.errorBody()
            val errorResponse = body.toErrorResponse(body!!)

            emit(Resource.Error(errorResponse.message))
        }
    }

    override suspend fun postPhotoLink(photo: MultipartBody.Part) = flow {
        emit(Resource.Loading(true))
        val response = greenLightApi.postPhotoLink(photo)
        emit(Resource.Success(response))
    }.catch { e ->
        if (e is HttpException) {
            val body = e.response()!!.errorBody()
            val errorResponse = body.toErrorResponse(body!!)

            emit(Resource.Error(errorResponse.message))
        }
    }

    override suspend fun addIncident(
        file: List<MultipartBody.Part>,
        json: RequestBody
    ) = flow {
        emit(Resource.Loading(true))
        val response = greenLightApi.addIncident(file, json)
        emit(Resource.Success(response))
    }.catch { e ->
        if (e is HttpException) {
            val body = e.response()!!.errorBody()
            val errorResponse = body.toErrorResponse(body!!)

            emit(Resource.Error(errorResponse.message))
        } else {
            emit(Resource.Error("An error has occurred"))
        }
    }

    override suspend fun getUser(username: String) = flow {
        emit(Resource.Loading(true))
        val response = greenLightApi.getUser(username)
        emit(Resource.Success(response))
    }.catch { e ->
        if (e is HttpException) {
            val body = e.response()!!.errorBody()
            val errorResponse = body.toErrorResponse(body!!)

            emit(Resource.Error(errorResponse.message))
        }
    }

    override suspend fun updateFAQ(faqList: List<SimpleFAQ>) = flow {
        emit(Resource.Loading(true))
        val bearerToken = "Bearer "+sharedPrefs.getAccessToken()
        val response = greenLightApi.updateFAQ(faqList,bearerToken)
        emit(Resource.Success(response))
    }.catch { e ->
        if (e is HttpException) {
            val body = e.response()!!.errorBody()
            val errorResponse = body.toErrorResponse(body!!)

            emit(Resource.Error(errorResponse.message))
        }
    }

    override suspend fun addFaq(faq: SimpleFAQ) = flow {
        emit(Resource.Loading(true))
        val bearerToken = "Bearer "+sharedPrefs.getAccessToken();

        val response = greenLightApi.addFAQ(faq,bearerToken)
        emit(Resource.Success(response))
    }.catch { e ->
        if (e is HttpException) {
            val body = e.response()!!.errorBody()
            val errorResponse = body.toErrorResponse(body!!)

            emit(Resource.Error(errorResponse.message))
        }
    }

    override suspend fun deleteFAQ(title: String) = flow {
        emit(Resource.Loading(true))
        val bearerToken = "Bearer "+sharedPrefs.getAccessToken()
        val response = greenLightApi.deleteFAQ(title,bearerToken)
        emit(Resource.Success(response))
    }.catch { e ->
        if (e is HttpException) {
            val body = e.response()!!.errorBody()
            val errorResponse = body.toErrorResponse(body!!)

            emit(Resource.Error(errorResponse.message))
        }
    }

    override suspend fun getActiveAchByUser(username: String) = flow {
        emit(Resource.Loading(true))
        val response = greenLightApi.getActiveAchByUser(username)
        emit(Resource.Success(response))
    }.catch { e ->
        if (e is HttpException) {
            val body = e.response()!!.errorBody()
            val errorResponse = body.toErrorResponse(body!!)

            emit(Resource.Error(errorResponse.message))
        }
    }

    override suspend fun getInactiveAchByUser(username: String) = flow {
        emit(Resource.Loading(true))
        val response = greenLightApi.getInactiveAchByUser(username)
        emit(Resource.Success(response))
    }.catch { e ->
        if (e is HttpException) {
            val body = e.response()!!.errorBody()
            val errorResponse = body.toErrorResponse(body!!)

            emit(Resource.Error(errorResponse.message))
        }
    }

    override suspend fun getIncidents() = flow {
        emit(Resource.Loading(true))
        val response = greenLightApi.getIncidents()
        emit(Resource.Success(response))
    }.catch { e ->
        if (e is HttpException) {
            val body = e.response()!!.errorBody()
            val errorResponse = body.toErrorResponse(body!!)

            emit(Resource.Error(errorResponse.message))
        }
    }

    override suspend fun getAllVouchers() = flow {
        emit(Resource.Loading(true))
        val response = greenLightApi.getAllVouchers()
        emit(Resource.Success(response))
    }.catch { e ->
        if (e is HttpException) {
            val body = e.response()!!.errorBody()
            val errorResponse = body.toErrorResponse(body!!)

            emit(Resource.Error(errorResponse.message))
        }
    }

    override suspend fun getUserVoucher() = flow {
        emit(Resource.Loading(true))
        val response = greenLightApi.getUserVoucher(sharedPrefs.getUsername())
        emit(Resource.Success(response))
    }.catch { e ->
        if (e is HttpException) {
            val body = e.response()!!.errorBody()
            val errorResponse = body.toErrorResponse(body!!)

            emit(Resource.Error(errorResponse.message))
        }
    }

    override suspend fun getAllTrashes() = flow {
        emit(Resource.Loading(true))
        val response = greenLightApi.getAllTrashes()
        emit(Resource.Success(response))
    }.catch { e ->
        if (e is HttpException) {
            val body = e.response()!!.errorBody()
            val errorResponse = body.toErrorResponse(body!!)

            emit(Resource.Error(errorResponse.message))
        }
    }

    override suspend fun updateTrash(trashResponse: TrashResponse) = flow {
        emit(Resource.Loading(true))
        val bearerToken = "Bearer "+sharedPrefs.getAccessToken();
        val response = greenLightApi.updateTrash(trashResponse,bearerToken)
        emit(Resource.Success(response))
    }.catch { e ->
        if (e is HttpException) {
            val body = e.response()!!.errorBody()
            val errorResponse = body.toErrorResponse(body!!)

            emit(Resource.Error(errorResponse.message))
        }
    }

    override suspend fun deleteTrash(string: String) = flow {
        emit(Resource.Loading(true))
        val bearerToken = "Bearer "+sharedPrefs.getAccessToken();
        val response = greenLightApi.deleteTrash(string,bearerToken)
        emit(Resource.Success(response))
    }.catch { e ->
        if (e is HttpException) {
            val body = e.response()!!.errorBody()
            val errorResponse = body.toErrorResponse(body!!)

            emit(Resource.Error(errorResponse.message))
        }
    }

    override suspend fun addTrash(trashResponse: TrashResponse) = flow {
        emit(Resource.Loading(true))
        val bearerToken = "Bearer "+sharedPrefs.getAccessToken();

        val response = greenLightApi.addTrash(trashResponse,bearerToken)
        emit(Resource.Success(response))
    }.catch { e ->
        if (e is HttpException) {
            val body = e.response()!!.errorBody()
            val errorResponse = body.toErrorResponse(body!!)

            emit(Resource.Error(errorResponse.message))
        }
    }

    override suspend fun buyVoucher(voucher: Voucher, quantity: Int) = flow {
        val voucherToBuy = BuyVoucher(
            amount = quantity.toLong(),
            id = voucher.id,
            username = sharedPrefs.getUsername()
        )
        emit(Resource.Loading(true))
        val response = greenLightApi.buyVoucher(voucherToBuy)
        emit(Resource.Success(response))
    }.catch { e ->
        if (e is HttpException) {
            val body = e.response()!!.errorBody()
            val errorResponse = body.toErrorResponse(body!!)

            emit(Resource.Error(errorResponse.message))
        }
    }

    override suspend fun getUserOnly(username: String) = flow {
        emit(Resource.Loading(true))
        val response = greenLightApi.getUserOnly(username)
        emit(Resource.Success(response))
    }.catch { e ->
        if (e is HttpException) {
            val body = e.response()!!.errorBody()
            val errorResponse = body.toErrorResponse(body!!)

            emit(Resource.Error(errorResponse.message))
        }
    }

    override suspend fun updatePassword(
        username: String,
        oldPassword: String,
        newPassword: String
    ) = flow {
        emit(Resource.Loading(true))
        val response = greenLightApi.updatePassword(username, oldPassword, newPassword)
        emit(Resource.Success(response))
    }.catch { e ->
        if (e is HttpException) {
            val body = e.response()!!.errorBody()
            val errorResponse = body.toErrorResponse(body!!)

            emit(Resource.Error(errorResponse.message))
        }
    }

    override suspend fun updateEmail(
        username: String,
        email: String
    ) = flow {
        emit(Resource.Loading(true))
        val response = greenLightApi.updateEmail(username, email)
        emit(Resource.Success(response))
    }.catch { e ->
        if (e is HttpException) {
            val body = e.response()!!.errorBody()
            val errorResponse = body.toErrorResponse(body!!)

            emit(Resource.Error(errorResponse.message))
        }
    }

    override suspend fun updatePhoneNumber(
        username: String,
        phoneNumber: String
    ) = flow {
        emit(Resource.Loading(true))
        val response = greenLightApi.updatePhoneNumber(username, phoneNumber)
        emit(Resource.Success(response))
    }.catch { e ->
        if (e is HttpException) {
            val body = e.response()!!.errorBody()
            val errorResponse = body.toErrorResponse(body!!)

            emit(Resource.Error(errorResponse.message))
        }
    }

    override suspend fun updateTown(
        username: String,
        town: String
    ) = flow {
        emit(Resource.Loading(true))
        val response = greenLightApi.updateTown(username, town)
        emit(Resource.Success(response))
    }.catch { e ->
        if (e is HttpException) {
            val body = e.response()!!.errorBody()
            val errorResponse = body.toErrorResponse(body!!)

            emit(Resource.Error(errorResponse.message))
        }
    }

    override suspend fun updateStreet(
        username: String,
        street: String
    ) = flow {
        emit(Resource.Loading(true))
        val response = greenLightApi.updateStreet(username, street)
        emit(Resource.Success(response))
    }.catch { e ->
        if (e is HttpException) {
            val body = e.response()!!.errorBody()
            val errorResponse = body.toErrorResponse(body!!)

            emit(Resource.Error(errorResponse.message))
        }
    }

    override suspend fun updateStreetNr(
        username: String,
        streetNr: String
    ) = flow {
        emit(Resource.Loading(true))
        val response = greenLightApi.updateStreetNr(username, streetNr)
        emit(Resource.Success(response))
    }.catch { e ->
        if (e is HttpException) {
            val body = e.response()!!.errorBody()
            val errorResponse = body.toErrorResponse(body!!)

            emit(Resource.Error(errorResponse.message))
        }
    }

    override suspend fun solveIncident(solveIncident: SolveIncident) = flow {
        emit(Resource.Loading(true))
        val response = greenLightApi.solveIncident(solveIncident)
        emit(Resource.Success(response))
    }.catch { e ->
        if (e is HttpException) {
            val body = e.response()!!.errorBody()
            val errorResponse = body.toErrorResponse(body!!)

            emit(Resource.Error(errorResponse.message))
        }
    }

    override suspend fun checkUserAvailability(username: String) = flow {
        emit(Resource.Loading(true))
        val response = greenLightApi.checkUserAvailability(username)
        emit(Resource.Success(response))
    }.catch { e ->
        if (e is HttpException) {
            val body = e.response()!!.errorBody()
            val errorResponse = body.toErrorResponse(body!!)
            Log.e("error_check_user", e.toString())
            Log.e("error", e.response().toString())
            emit(Resource.Error(errorResponse.message))
        }
    }

    override suspend fun addCompany(company: AddCompanyRequest) = flow {
        emit(Resource.Loading(true))
        val response = greenLightApi.addCompany(company)
        emit(Resource.Success(response))
    }.catch { e ->
        if (e is HttpException) {
            val body = e.response()!!.errorBody()
            val errorResponse = body.toErrorResponse(body!!)

            emit(Resource.Error(errorResponse.message))
        }
    }

    override suspend fun addVoucher(addV: AddVoucherReq) = flow {
        emit(Resource.Loading(true))
        val bearerToken = "Bearer "+sharedPrefs.getAccessToken();

        val response = greenLightApi.addVoucher(addV,bearerToken)
        emit(Resource.Success(response))
    }.catch { e ->
        if (e is HttpException) {
            val body = e.response()!!.errorBody()
            val errorResponse = body.toErrorResponse(body!!)
            emit(Resource.Error(errorResponse.message))
        }
    }

    override suspend fun getCompanies() = flow {
        emit(Resource.Loading(true))
        val response = greenLightApi.getCompanies()
        emit(Resource.Success(response))
    }.catch { e ->
        if (e is HttpException) {
            val body = e.response()!!.errorBody()
            val errorResponse = body.toErrorResponse(body!!)

            emit(Resource.Error(errorResponse.message))
        }
    }

    override suspend fun updateCompany(company: CompanyResponse) = flow {
        emit(Resource.Loading(true))
        val response = greenLightApi.updateCompany(company)
        emit(Resource.Success(response))
    }.catch { e ->
        if (e is HttpException) {
            val body = e.response()!!.errorBody()
            val errorResponse = body.toErrorResponse(body!!)

            emit(Resource.Error(errorResponse.message))
        }
    }

    override suspend fun checkUserEmail(userWithEmail: UserWithEmail) = flow {
        emit(Resource.Loading(true))
        val response = greenLightApi.checkUserEmail(userWithEmail)
        emit(Resource.Success(response))
    }.catch { e ->
        if (e is HttpException) {
            val body = e.response()!!.errorBody()
            val errorResponse = body.toErrorResponse(body!!)

            emit(Resource.Error(errorResponse.message))
        }
    }

    override suspend fun transferMoney(transferMoney: TransferMoney)= flow {
        emit(Resource.Loading(true))
        val response = greenLightApi.transferMoney(transferMoney)
        emit(Resource.Success(response))
    }.catch { e ->
        if (e is HttpException) {
            val body = e.response()!!.errorBody()
            val errorResponse = body.toErrorResponse(body!!)

            emit(Resource.Error(errorResponse.message))
        }
    }

    override suspend fun addRecycleBin(addRB: AddRecycleBin)= flow {
        emit(Resource.Loading(true))
        val response = greenLightApi.addRecycleBin(addRB)
        emit(Resource.Success(response))
    }.catch { e ->
        if (e is HttpException) {
            val body = e.response()!!.errorBody()
            val errorResponse = body.toErrorResponse(body!!)

            emit(Resource.Error(errorResponse.message))
        }
    }

    override suspend fun getRB()= flow {
        emit(Resource.Loading(true))
        val response = greenLightApi.getRB()
        emit(Resource.Success(response))
    }.catch { e ->
        if (e is HttpException) {
            val body = e.response()!!.errorBody()
            val errorResponse = body.toErrorResponse(body!!)

            emit(Resource.Error(errorResponse.message))
        }
    }

    override suspend fun deleteRB(id: Long)= flow {
        emit(Resource.Loading(true))
        val response = greenLightApi.deleteRB(id)
        emit(Resource.Success(response))
    }.catch { e ->
        if (e is HttpException) {
            val body = e.response()!!.errorBody()
            val errorResponse = body.toErrorResponse(body!!)

            emit(Resource.Error(errorResponse.message))
        }
    }

    private suspend fun cacheFaqs(faqList: List<FaqResponse>) {
        faqDAO.deleteAllFaqs()
        faqList.forEach { faq -> faqDAO.insertFaqs(faq.toFaq()) }
    }
}