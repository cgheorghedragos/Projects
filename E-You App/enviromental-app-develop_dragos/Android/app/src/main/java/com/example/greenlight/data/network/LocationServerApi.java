package com.example.greenlight.data.network;

import com.example.greenlight.data.requests.IncidentRequest;
import com.example.greenlight.data.requests.SolveIncidentRequest;
import com.example.greenlight.data.requests.UserLoginRequest;
import com.example.greenlight.data.requests.UserModel;
import com.example.greenlight.data.responses.IncidentGetResponse;
import com.example.greenlight.data.responses.IncidentPostResponse;
import com.example.greenlight.data.responses.RankingGetResponse;
import com.example.greenlight.data.responses.SendPhotoResponse;
import com.example.greenlight.data.responses.SolveIncidentResponse;
import com.example.greenlight.data.responses.UserLoginResponse;
import com.example.greenlight.data.responses.UserRegisterResponse;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface LocationServerApi {
    @POST("users/login")
    Observable<UserLoginResponse> loginRequest(@Body UserLoginRequest loginRequest);

    @POST("users/signup")
    Observable<UserRegisterResponse> registerRequest(@Body UserModel registerRequest);

    @Multipart
    @POST("/images/profile/pic")
    Observable<SendPhotoResponse> uploadPhoto(@Part MultipartBody.Part file);

    @Multipart
    @POST("/images/incidents/pics")
    Observable<SendPhotoResponse> uploadPhotos(@Part List<MultipartBody.Part> files,
                                               @Header("Authorization") String authHeader);

    @POST("/incidents/add")
    Observable<IncidentPostResponse> uploadIncident(@Body IncidentRequest incidentRequest,
                                                    @Header("Authorization") String authHeader);

    @GET("/incidents/get")
    Observable<IncidentGetResponse> getAllIncidents(@Header("Authorization") String authHeader);

    @GET("/users/ranking")
    Observable<RankingGetResponse> getRanking(@Query("n") int length);

    @POST("incidents/solve")
    Observable<SolveIncidentResponse> solveIncident(@Header("Authorization") String authHeader,
                                                    @Body SolveIncidentRequest solveIncidentRequest);

}
