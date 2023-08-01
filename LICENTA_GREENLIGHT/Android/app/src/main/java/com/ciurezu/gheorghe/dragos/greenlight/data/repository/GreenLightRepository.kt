package com.ciurezu.gheorghe.dragos.greenlight.data.repository

import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.GreenLightApiResponse
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.Resource
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.TrashResponse
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.add_company.AddCompanyRequest
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.add_user.request.UserSignUpRequest
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.add_user.response.UserSignUpResponse
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.add_user_address.request.AddUserAddressRequest
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.add_user_address.response.AddUserAddressResponse
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.add_user_photo.request.AddUserPhotoRequest
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.add_user_photo.response.AddUserPhotoResponse
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.add_voucher.AddVoucherReq
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.check_username.check_email.UserWithEmail
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.faq.FaqResponse
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.faq.SimpleFAQ
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.get_achievement.ActiveAchResponse
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.get_achievement.InactiveAchResponse
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.get_company.CompanyResponse
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.get_incident.IncidentResponse
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.get_user.GetUserResponse
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.get_user.GetUserWithAddress
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.get_users_by_coins.response.GetAllUsersPctResponse
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.get_voucher.UserVoucher
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.get_voucher.Voucher
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.login_user.request.UserSignInRequest
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.login_user.response.UserSignInResponse
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.rb.AddRecycleBin
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.rb.GetRecycleBin
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.solve_incident.SolveIncident
import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.transfer_money.TransferMoney
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface GreenLightRepository {
    suspend fun loginUser(user: UserSignInRequest): Flow<Resource<UserSignInResponse>>
    suspend fun registerUser(user: UserSignUpRequest): Flow<Resource<GreenLightApiResponse<Boolean>>>
    suspend fun getAllUsersByPoints(): Flow<Resource<GreenLightApiResponse<List<GetAllUsersPctResponse>>>>
    suspend fun getAllFAQ(): Flow<Resource<GreenLightApiResponse<List<FaqResponse>>>>
    suspend fun addUserAddress(address: AddUserAddressRequest): Flow<Resource<GreenLightApiResponse<Boolean>>>
    suspend fun addUserPhoto(photo: AddUserPhotoRequest): Flow<Resource<GreenLightApiResponse<Boolean>>>
    suspend fun postPhotoLink(photo: MultipartBody.Part): Flow<Resource<GreenLightApiResponse<String>>>
    suspend fun addIncident(
        file: List<MultipartBody.Part>,
        json: RequestBody
    ): Flow<Resource<GreenLightApiResponse<String>>>

    suspend fun getUser(username: String): Flow<Resource<GreenLightApiResponse<GetUserResponse>>>
    suspend fun updateFAQ(faqList: List<SimpleFAQ>): Flow<Resource<GreenLightApiResponse<Boolean>>>
    suspend fun addFaq(faq: SimpleFAQ): Flow<Resource<GreenLightApiResponse<Boolean>>>
    suspend fun deleteFAQ(title: String): Flow<Resource<GreenLightApiResponse<Boolean>>>
    suspend fun getActiveAchByUser(username: String): Flow<Resource<GreenLightApiResponse<List<ActiveAchResponse>>>>
    suspend fun getInactiveAchByUser(username: String): Flow<Resource<GreenLightApiResponse<List<InactiveAchResponse>>>>
    suspend fun getIncidents(): Flow<Resource<GreenLightApiResponse<List<IncidentResponse>>>>
    suspend fun getAllVouchers(): Flow<Resource<GreenLightApiResponse<List<Voucher>>>>
    suspend fun getUserVoucher(): Flow<Resource<GreenLightApiResponse<List<UserVoucher>>>>
    suspend fun getAllTrashes(): Flow<Resource<GreenLightApiResponse<List<TrashResponse>>>>
    suspend fun updateTrash(trashResponse: TrashResponse): Flow<Resource<GreenLightApiResponse<Boolean>>>
    suspend fun deleteTrash(string: String): Flow<Resource<GreenLightApiResponse<Boolean>>>
    suspend fun addTrash(trashResponse: TrashResponse): Flow<Resource<GreenLightApiResponse<Boolean>>>
    suspend fun buyVoucher(
        voucher: Voucher,
        quantity: Int
    ): Flow<Resource<GreenLightApiResponse<Boolean>>>

    suspend fun getUserOnly(username: String): Flow<Resource<GreenLightApiResponse<GetUserWithAddress>>>
    suspend fun updatePassword(
        username: String,
        oldPassword: String,
        newPassword: String
    ): Flow<Resource<GreenLightApiResponse<Boolean>>>

    suspend fun updateEmail(
        username: String,
        email: String
    ): Flow<Resource<GreenLightApiResponse<Boolean>>>

    suspend fun updatePhoneNumber(
        username: String,
        phoneNumber: String
    ): Flow<Resource<GreenLightApiResponse<Boolean>>>

    suspend fun updateTown(
        username: String,
        town: String
    ): Flow<Resource<GreenLightApiResponse<Boolean>>>

    suspend fun updateStreet(
        username: String,
        street: String
    ): Flow<Resource<GreenLightApiResponse<Boolean>>>

    suspend fun updateStreetNr(
        username: String,
        streetNr: String
    ): Flow<Resource<GreenLightApiResponse<Boolean>>>

    suspend fun solveIncident(
        solveIncident: SolveIncident
    ): Flow<Resource<GreenLightApiResponse<Boolean>>>

    suspend fun checkUserAvailability(
        username: String
    ): Flow<Resource<GreenLightApiResponse<String>>>

    suspend fun addCompany(
        company: AddCompanyRequest
    ): Flow<Resource<GreenLightApiResponse<Boolean>>>

    suspend fun addVoucher(
        addV: AddVoucherReq
    ): Flow<Resource<GreenLightApiResponse<Boolean>>>

    suspend fun getCompanies(
    ): Flow<Resource<GreenLightApiResponse<List<CompanyResponse>>>>

    suspend fun updateCompany(
        company: CompanyResponse
    ): Flow<Resource<GreenLightApiResponse<Boolean>>>

    suspend fun checkUserEmail(
        @Body userWithEmail: UserWithEmail
    ): Flow<Resource<GreenLightApiResponse<Boolean>>>


    suspend fun transferMoney(@Body transferMoney: TransferMoney): Flow<Resource<GreenLightApiResponse<Boolean>>>

    suspend fun addRecycleBin(@Body addRB: AddRecycleBin): Flow<Resource<GreenLightApiResponse<Boolean>>>

    suspend fun getRB() : Flow<Resource<GreenLightApiResponse<List<GetRecycleBin>>>>

    suspend fun deleteRB(@Query("delete_id") id : Long) : Flow<Resource<GreenLightApiResponse<Boolean>>>

}
