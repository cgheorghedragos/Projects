package com.example.greenlight.presentation.viewmodel;

import android.content.Context;
import android.location.Location;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.greenlight.R;
import com.example.greenlight.data.SessionManager;
import com.example.greenlight.data.models.IncidentsModel;
import com.example.greenlight.data.models.MarkerModel;
import com.example.greenlight.data.repository.ServerRepository;
import com.example.greenlight.data.requests.IncidentRequest;
import com.example.greenlight.data.requests.SolveIncidentRequest;
import com.example.greenlight.data.responses.IncidentGetResponse;
import com.example.greenlight.data.responses.IncidentPostResponse;
import com.example.greenlight.data.responses.Resource;
import com.example.greenlight.data.responses.SendPhotoResponse;
import com.example.greenlight.data.responses.SolveIncidentResponse;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import okhttp3.MultipartBody;

@Singleton
public class MapViewModel extends ViewModel {
    private ServerRepository repository;
    private SessionManager sessionManager;

    private MutableLiveData<List<IncidentsModel>> incidents = new MutableLiveData<>();
    private MutableLiveData<MarkerModel> currentPlantMarker = new MutableLiveData<>();
    private MutableLiveData<String> description = new MutableLiveData<>();
    private MutableLiveData<String> selectedIncidentId = new MutableLiveData<>();
    private MutableLiveData<Location> myLocation = new MutableLiveData<>();
    private MutableLiveData<List<MarkerModel>> eventIcons = new MutableLiveData<>();
    public MutableLiveData<Resource<SendPhotoResponse>> sendPhotoResponse = new MutableLiveData<>();
    public MutableLiveData<Resource<IncidentPostResponse>> incidentResponse = new MutableLiveData<>();
    public MutableLiveData<Resource<IncidentGetResponse>> getAllIncidents = new MutableLiveData<>();
    public MutableLiveData<Resource<SolveIncidentResponse>> solveIncidentResponse = new MutableLiveData<>();
    private MutableLiveData<List<String>> currentPathPhotoIncidents = new MutableLiveData<>();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private MutableLiveData<List<String>> collaborationUsers = new MutableLiveData<>();
    private MutableLiveData<String> currentCollaboration = new MutableLiveData<>();

    @Inject
    public MapViewModel(ServerRepository serverRepository,SessionManager sessionManager) {
        this.repository = serverRepository;
        this.sessionManager = sessionManager;
        eventIcons.postValue(repository.requestMarkers());
        incidents.postValue(new ArrayList<>());
        collaborationUsers.setValue(new ArrayList<>());
    }

    public void updateCurrentCollaborationUser(String string) {
        currentCollaboration.postValue(string);
    }

    public LiveData<List<String>> getAddedCollaborationUsers() {
        return collaborationUsers;
    }

    public void addCollaborationUser() {
        List<String> collaborations = new ArrayList<>(collaborationUsers.getValue());
        if (!collaborations.contains(currentCollaboration.getValue())) {
            collaborations.add(currentCollaboration.getValue());
            collaborationUsers.postValue(collaborations);
        }
    }

    public void removeCollaborationUser(int position) {
        List<String> users = new ArrayList<>(collaborationUsers.getValue());
        users.remove(position);
        collaborationUsers.setValue(users);
    }

    public LiveData<Resource<IncidentPostResponse>> getPostedIncidentResponse() {
        return incidentResponse;
    }

    public void updateDescription(String description) {
        this.description.postValue(description);
    }

    public void updateIcons(List<MarkerModel> eventIcons) {
        this.eventIcons.postValue(eventIcons);
    }

    public LiveData<List<MarkerModel>> getEventIcons() {
        return eventIcons;
    }

    public void updateMarker(MarkerModel marker) {
        currentPlantMarker.postValue(marker);
    }

    public LiveData<MarkerModel> getMarker() {
        return currentPlantMarker;
    }

    public void addIncidents(IncidentsModel incidentsModel) {
        incidents.getValue().add(incidentsModel);
    }


    public void setIncidents(ArrayList<IncidentsModel> incidents) {
        this.incidents.postValue(incidents);
    }

    public void updateSelectedIncidentId(String id) {
        selectedIncidentId.postValue(id);
    }

    public IncidentsModel getSelectedIncident() {
        if (getAllIncidents.getValue() != null) {
            for (IncidentsModel incidentsModel : getAllIncidents.getValue().getData().getData()) {
                if (incidentsModel.getDocumentId().equals(selectedIncidentId.getValue())) {
                    return incidentsModel;
                }
            }
        }

        return null;
    }

    public LiveData<Resource<SendPhotoResponse>> getSendPhotoResult() {
        return sendPhotoResponse;
    }

    public LiveData<List<IncidentsModel>> getIncidents() {
        return incidents;
    }

    public LiveData<Location> getMyLocation() {
        return myLocation;
    }

    public void updateMyLocation(Location latLng) {
        this.myLocation.postValue(latLng);
    }


    public String getAuthToken() {
        if (sessionManager.fetchAuthToken() != null) {
            return sessionManager.fetchAuthToken();
        }
        return "";
    }

    public LiveData<Resource<IncidentGetResponse>> getAllIncidents() {
        return getAllIncidents;
    }

    public void requestIncidents(String authToken) {
        getAllIncidents.postValue(Resource.loading());

        compositeDisposable.add(repository
                .getAllIncidents(authToken)
                .subscribe(result -> getAllIncidents.postValue(Resource.success(result)),
                        error -> getAllIncidents.postValue(Resource.error(error)),
                        () -> {
                        }));
    }

    public void uploadPhotos(List<MultipartBody.Part> files, String authToken) {
        sendPhotoResponse.postValue(Resource.loading());

        compositeDisposable.add(repository
                .uploadPhotos(files, authToken)
                .subscribe(result -> {
                            sendPhotoResponse.postValue(Resource.success(result));
                        },
                        error -> sendPhotoResponse.postValue(Resource.error(error)),
                        () -> {
                        }));
    }

    public void requestSolveIncident(String authToken){
        ArrayList<String> users = new ArrayList<>(collaborationUsers.getValue());

        if(sessionManager.fetchUser().getUsername() != null){
            String currentAccount = sessionManager.fetchUser().getUsername();
            if(!users.contains(currentAccount)){
                users.add(currentAccount);
            }
        };
        String selectedDoc = selectedIncidentId.getValue();

        SolveIncidentRequest solveIncidentRequest = new SolveIncidentRequest(selectedDoc,users);

        compositeDisposable.add(repository
                        .solveIncident(authToken,solveIncidentRequest)
                .subscribe(result -> {
                            solveIncidentResponse.postValue(Resource.success(result));
                        },
                        error -> solveIncidentResponse.postValue(Resource.error(error)),
                        () -> {
                        }));

    }

    public void uploadIncident(String authToken) {
        try {
            IncidentRequest incidentRequest = new IncidentRequest();
            incidentRequest.setDescription(description.getValue());
            incidentRequest.setLatitude("" + myLocation.getValue().getLatitude());
            incidentRequest.setLongitude("" + myLocation.getValue().getLongitude());
            if (sendPhotoResponse.getValue() != null) {
                incidentRequest.setPhotoPath(sendPhotoResponse.getValue().getData().getResponse());
            } else {
                incidentRequest.setPhotoPath(new ArrayList<>());
            }

            incidentRequest.setUsername(sessionManager.fetchUser().getUsername());
            incidentRequest.setIncident_title(currentPlantMarker.getValue().getMarkerType());
            incidentRequest.setMarkerType(currentPlantMarker.getValue().getMarkerType());

            compositeDisposable.add(repository
                    .uploadIncident(incidentRequest, authToken)
                    .subscribe(result -> {
                                incidentResponse.postValue(Resource.success(result));
                            },
                            error -> incidentResponse.postValue(Resource.error(error)),
                            () -> {
                            }));
        } catch (NullPointerException nullEx) {
            Log.e("UPP_INC", nullEx.getMessage());
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();

        compositeDisposable.clear();
    }
}
