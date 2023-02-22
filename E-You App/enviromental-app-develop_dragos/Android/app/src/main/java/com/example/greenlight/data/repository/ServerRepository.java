package com.example.greenlight.data.repository;

import com.example.greenlight.data.models.MarkerModel;
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

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ServerRepository {
    Observable<UserLoginResponse> loginRequest(@Body UserLoginRequest user);
    Observable<UserRegisterResponse> registerRequest(@Body UserModel registerRequest);
    Observable<SendPhotoResponse> uploadPhoto(@Part MultipartBody.Part file);
    Observable<SendPhotoResponse> uploadPhotos(@Part List<MultipartBody.Part> files,
                                               @Header("Authorization") String authHeader);
    Observable<IncidentPostResponse> uploadIncident(@Body IncidentRequest incidentRequest,
                                                    @Header("Authorization") String authHeader);
    Observable<IncidentGetResponse> getAllIncidents(@Header("Authorization") String authHeader);

    List<MarkerModel> requestMarkers();

   Observable<RankingGetResponse> getRanking(@Query("n")int length);
    Observable<SolveIncidentResponse> solveIncident(@Header("Authorization") String authHeader,
                                                    @Body SolveIncidentRequest solveIncidentRequest);
}
