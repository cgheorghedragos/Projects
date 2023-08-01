package com.ciurezu.gheorghe.dragos.greenlight.data.network

import com.ciurezu.gheorghe.dragos.greenlight.data.network_model.GreenLightApiResponse
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
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface GreenLightApi {

    @GET("api/v1/user/all/desc-by-points")
    suspend fun getAllUsersDescByPct(): GreenLightApiResponse<List<GetAllUsersPctResponse>>

    @POST("api/login")
    suspend fun loginUser(@Body user: UserSignInRequest): UserSignInResponse

    @POST("api/v1/user/save2")
    suspend fun registerUser(@Body user: UserSignUpRequest): GreenLightApiResponse<Boolean>

    @PUT("api/v1/user/add-address")
    suspend fun addUserAddress(@Body user: AddUserAddressRequest): GreenLightApiResponse<Boolean>


    @PUT("api/v1/user/update-user-photo")
    suspend fun addProfilePhoto(@Body userPhotoRequest: AddUserPhotoRequest): GreenLightApiResponse<Boolean>

    @Multipart
    @POST("api/v1/image/save")
    suspend fun postPhotoLink(@Part photo: MultipartBody.Part): GreenLightApiResponse<String>

    @GET("api/v1/faq/get-all-faq")
    suspend fun getAllFAQ(): GreenLightApiResponse<List<FaqResponse>>

    @Multipart
    @POST("api/v1/incidents/add-incident")
    suspend fun addIncident(
        @Part file: List<MultipartBody.Part>,
        @Part("json") json: RequestBody
    ): GreenLightApiResponse<String>

    @GET("api/v1/incidents/get-incidents")
    suspend fun getIncidents(): GreenLightApiResponse<List<IncidentResponse>>

    @GET("api/v1/user/get-user-money")
    suspend fun getUser(@Query("username") username: String): GreenLightApiResponse<GetUserResponse>

    @PUT("api/v1/faq/edit-faq")
    suspend fun updateFAQ(@Body faqList: List<SimpleFAQ>,@Header("Authorization") token: String): GreenLightApiResponse<Boolean>

    @POST("api/v1/faq/add-faq")
    suspend fun addFAQ(@Body faq: SimpleFAQ,@Header("Authorization") token: String): GreenLightApiResponse<Boolean>

    @DELETE("api/v1/faq/delete-faq")
    suspend fun deleteFAQ(@Query("title") title: String,@Header("Authorization") token: String): GreenLightApiResponse<Boolean>

    @GET("api/v1/achievement/get-by-user")
    suspend fun getActiveAchByUser(@Query("user") username: String): GreenLightApiResponse<List<ActiveAchResponse>>

    @GET("api/v1/achievement/get-inactive-by-user")
    suspend fun getInactiveAchByUser(@Query("user") username: String): GreenLightApiResponse<List<InactiveAchResponse>>

    @GET("api/v1/voucher/get-all")
    suspend fun getAllVouchers(): GreenLightApiResponse<List<Voucher>>

    @GET("api/v1/voucher/get-user-voucher")
    suspend fun getUserVoucher(@Query("user") username: String): GreenLightApiResponse<List<UserVoucher>>

    @POST("api/v1/voucher/buy-voucher")
    suspend fun buyVoucher(@Body buyVoucher: BuyVoucher): GreenLightApiResponse<Boolean>

    @GET("api/v1/trash_classifier/get-trash-categories")
    suspend fun getAllTrashes(): GreenLightApiResponse<List<TrashResponse>>

    @PUT("api/v1/trash_classifier/edit-trash-category")
    suspend fun updateTrash(@Body trashResponse: TrashResponse,@Header("Authorization") token: String): GreenLightApiResponse<Boolean>

    @DELETE("api/v1/trash_classifier/delete-trash-category")
    suspend fun deleteTrash(@Query("bar_code") string: String,@Header("Authorization") token: String): GreenLightApiResponse<Boolean>

    @POST("api/v1/trash_classifier/add-trash-category")
    suspend fun addTrash(@Body trashResponse: TrashResponse,@Header("Authorization") token: String): GreenLightApiResponse<Boolean>

    @GET("api/v1/user/get-user")
    suspend fun getUserOnly(@Query("user") username: String): GreenLightApiResponse<GetUserWithAddress>

    @PUT("api/v1/user/transfer-money")
    suspend fun transferMoney(@Body transferMoney: TransferMoney): GreenLightApiResponse<Boolean>

    @POST("/api/v1/incidents/add-recycle-bin")
    suspend fun addRecycleBin(@Body addRB: AddRecycleBin): GreenLightApiResponse<Boolean>

    @GET("/api/v1/incidents/get-recycle-bin")
    suspend fun getRB(): GreenLightApiResponse<List<GetRecycleBin>>

    @DELETE("/api/v1/incidents/delete-recycle-bin")
    suspend fun deleteRB(@Query("delete_id") id: Long): GreenLightApiResponse<Boolean>

    @PUT("api/v1/user/update-user-email")
    suspend fun updateEmail(
        @Query("user") username: String,
        @Query("email") email: String
    ): GreenLightApiResponse<Boolean>

    @PUT("api/v1/user/update-password")
    suspend fun updatePassword(
        @Query("user") username: String,
        @Query("old_password") oldPass: String,
        @Query("new_Password") newPass: String
    ): GreenLightApiResponse<Boolean>

    @PUT("api/v1/user/update-phone-number")
    suspend fun updatePhoneNumber(
        @Query("user") username: String,
        @Query("phone_number") phoneNumber: String
    ): GreenLightApiResponse<Boolean>

    @PUT("api/v1/user/update-user-town")
    suspend fun updateTown(
        @Query("user") username: String,
        @Query("town") town: String
    ): GreenLightApiResponse<Boolean>

    @PUT("api/v1/user/update-user-street")
    suspend fun updateStreet(
        @Query("user") username: String,
        @Query("street") street: String
    ): GreenLightApiResponse<Boolean>

    @PUT("api/v1/user/update-user-street-nr")
    suspend fun updateStreetNr(
        @Query("user") username: String,
        @Query("street_nr") streetNr: String
    ): GreenLightApiResponse<Boolean>

    @PUT("api/v1/incidents/solve-incident")
    suspend fun solveIncident(
        @Body solveIncident: SolveIncident
    ): GreenLightApiResponse<Boolean>

    @GET("api/v1/user/check-user")
    suspend fun checkUserAvailability(
        @Query("user") username: String
    ): GreenLightApiResponse<String>

    @POST("api/v1/voucher/save-company")
    suspend fun addCompany(
        @Body company: AddCompanyRequest
    ): GreenLightApiResponse<Boolean>

    @PUT("api/v1/voucher/update-company")
    suspend fun updateCompany(
        @Body company: CompanyResponse
    ): GreenLightApiResponse<Boolean>

    @GET("api/v1/voucher/get-companies")
    suspend fun getCompanies(
    ): GreenLightApiResponse<List<CompanyResponse>>

    @POST("api/v1/voucher/add-voucher")
    suspend fun addVoucher(
        @Body voucher: AddVoucherReq,
        @Header("Authorization") token: String
    ): GreenLightApiResponse<Boolean>

    @POST("api/v1/user/check-username-and-email")
    suspend fun checkUserEmail(
        @Body userWithEmail: UserWithEmail
    ): GreenLightApiResponse<Boolean>

}
