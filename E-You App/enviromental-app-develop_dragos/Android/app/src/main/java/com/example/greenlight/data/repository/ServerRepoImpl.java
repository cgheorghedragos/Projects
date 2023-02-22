package com.example.greenlight.data.repository;

import com.example.greenlight.data.models.MarkerModel;
import com.example.greenlight.data.network.LocationServerApi;
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
import com.example.greenlight.utils.MarkersType;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.MultipartBody;

public class ServerRepoImpl implements ServerRepository{
    private LocationServerApi locationServerApi;

    @Inject
    public ServerRepoImpl(LocationServerApi locationServerApi) {
        this.locationServerApi = locationServerApi;
    }

    public LocationServerApi getLocationServerApi() {
        return locationServerApi;
    }

    public void setLocationServerApi(LocationServerApi locationServerApi) {
        this.locationServerApi = locationServerApi;
    }

    @Override
    public Observable<UserLoginResponse> loginRequest(UserLoginRequest user) {
        return locationServerApi.loginRequest(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<UserRegisterResponse> registerRequest(UserModel user) {
        return locationServerApi.registerRequest(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<SendPhotoResponse> uploadPhoto(MultipartBody.Part file) {
        return locationServerApi.uploadPhoto(file)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<SendPhotoResponse> uploadPhotos(List<MultipartBody.Part> files, String authHeader) {
        return locationServerApi.uploadPhotos(files, authHeader)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<IncidentPostResponse> uploadIncident(IncidentRequest incidentRequest, String authToken) {
        return locationServerApi.uploadIncident(incidentRequest, authToken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<IncidentGetResponse> getAllIncidents(String authHeader) {
        return locationServerApi.getAllIncidents(authHeader)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    @Override
    public List<MarkerModel> requestMarkers() {
        return LoadData();
    }

    @Override
    public @NonNull Observable<RankingGetResponse> getRanking(int length) {
        return locationServerApi.getRanking(length)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<SolveIncidentResponse> solveIncident(String authHeader,
                                                           SolveIncidentRequest solveIncidentRequest) {
        return locationServerApi.solveIncident(authHeader,solveIncidentRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private List<MarkerModel> LoadData() {
        List<MarkerModel> markerModels = new ArrayList<>();
        String markerType;
        String description;

        markerType = MarkersType.ANIMALS;
        description = "Animal in Danger";
        markerModels.add(new MarkerModel(markerType, description));


        markerType = MarkersType.BIOLOGICAL_HAZARD;
        description = "Biological Hazard";
        markerModels.add(new MarkerModel(markerType, description));

        markerType = MarkersType.DEFORESTING;
        description = "Deforesting spotted!";
        markerModels.add(new MarkerModel(markerType, description));

        markerType = MarkersType.FIRE;
        description = "Fire spotted!";
        markerModels.add(new MarkerModel(markerType, description));

        markerType = MarkersType.FISHING;
        description = "FISHING";
        markerModels.add(new MarkerModel(markerType, description));

        markerType = MarkersType.FLOOD;
        description = "Flood";
        markerModels.add(new MarkerModel(markerType, description));

        markerType = MarkersType.GARBAGE;
        description = "Garbage";
        markerModels.add(new MarkerModel(markerType, description));

        markerType = MarkersType.RADIOACTIVITY;
        description = "Radio Activity";
        markerModels.add(new MarkerModel(markerType, description));

        return markerModels;
    }
}
