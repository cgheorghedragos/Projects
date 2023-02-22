package com.example.greenlight.presentation.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.greenlight.data.network.LocationServerApi;
import com.example.greenlight.data.requests.UserModel;
import com.example.greenlight.data.responses.Resource;
import com.example.greenlight.data.responses.SendPhotoResponse;
import com.example.greenlight.data.responses.UserRegisterResponse;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import okhttp3.MultipartBody;

@Singleton
public class SignUpViewModel extends ViewModel {
    private LocationServerApi repository;

    private MutableLiveData<String> username = new MutableLiveData<>();
    private MutableLiveData<String> password = new MutableLiveData<>();
    private MutableLiveData<String> firstName = new MutableLiveData<>();
    private MutableLiveData<String> lastName = new MutableLiveData<>();
    private MutableLiveData<String> email = new MutableLiveData<>();
    private MutableLiveData<String> gender = new MutableLiveData<>();
    public MutableLiveData<String> birthday = new MutableLiveData<>();
    private MutableLiveData<String> photoPath = new MutableLiveData<>();
    public MutableLiveData<Resource<UserRegisterResponse>> responseLiveData = new MutableLiveData<>();
    public MutableLiveData<Resource<SendPhotoResponse>> sendPhotoResponse = new MutableLiveData<>();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public SignUpViewModel(LocationServerApi repository) {
        this.repository = repository;
        responseLiveData.postValue(Resource.initial());
        photoPath.postValue("default");
    }

    public LiveData<Resource<UserRegisterResponse>> getRegisterResponse() {
        return responseLiveData;
    }

    public LiveData<Resource<SendPhotoResponse>> getUploadPhotoResponse() {
        return sendPhotoResponse;
    }

    public void updateUserName(String userName) {
        username.postValue(userName);
    }

    public void updatePassword(String password) {
        this.password.postValue(password);
    }

    public void updateGender(String gender) {
        this.gender.postValue(gender);
    }

    public void updateBirthday(String birthday) {
        this.birthday.postValue(birthday);
    }

    public void updateEmail(String email) {
        this.email.postValue(email);
    }

    public void updateFirstName(String firstName) {
        this.firstName.postValue(firstName);
    }

    public void updateLastName(String LastName) {
        this.lastName.postValue(LastName);
    }

    public LiveData<String> getBirthday() {
        return birthday;
    }


    public void onClick() {
        UserModel register = new UserModel();
        register.setDocument_id(username.getValue());
        register.setFirst_name(firstName.getValue());
        register.setLast_name(lastName.getValue());
        register.setUsername(username.getValue());
        register.setEmail(email.getValue());
        register.setPassword(password.getValue());
        register.setGender(gender.getValue());
        register.setBirthday(birthday.getValue());
        register.setRole("[user]");
        if(sendPhotoResponse.getValue() != null){
        register.setPhoto_path(sendPhotoResponse.getValue().getData().getResponse().get(0));
        }
        else{
            register.setPhoto_path("default");
        }

        registerRequest(register);
    }



    private void registerRequest(UserModel user) {
        responseLiveData.postValue(Resource.loading());

        compositeDisposable.add(repository
                .registerRequest(user)
                .subscribe(result -> {
                            responseLiveData.postValue(Resource.success(result));
                        },
                        error -> responseLiveData.postValue(Resource.error(error)),
                        () -> {}));
    }

    public void uploadPhoto(MultipartBody.Part file) {
        sendPhotoResponse.postValue(Resource.loading());

        compositeDisposable.add(repository
                .uploadPhoto(file)
                .subscribe(result -> {
                    Log.e("REsult",result.getResponse().toString());
                            sendPhotoResponse.postValue(Resource.success(result));
                           // photoPath.postValue(result.getResponse().get(0));
                        },
                        error -> sendPhotoResponse.postValue(Resource.error(error)),
                        () ->
                        {
                         //   sendPhotoResponse.postValue(Resource.initial());
                        }));
    }

    public void setToInital(){
        responseLiveData.postValue(Resource.initial());
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }

}
