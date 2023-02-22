package com.example.greenlight.presentation.viewmodel;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.greenlight.data.SessionManager;
import com.example.greenlight.data.repository.ServerRepository;
import com.example.greenlight.data.requests.UserLoginRequest;
import com.example.greenlight.data.requests.UserModel;
import com.example.greenlight.data.responses.Resource;
import com.example.greenlight.data.responses.UserLoginResponse;

import javax.inject.Inject;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class LoginViewModel extends ViewModel {
    private MutableLiveData<String> Username = new MutableLiveData<>();
    private MutableLiveData<String> Password = new MutableLiveData<>();
    private MutableLiveData<Resource<UserLoginResponse>> responseLiveData= new MutableLiveData<>();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private ServerRepository repository;
    private SessionManager sessionManager;

    @Inject
    public LoginViewModel(ServerRepository repository, SessionManager sessionManager){
        this.repository = repository;
        this.sessionManager = sessionManager;

        responseLiveData.setValue(Resource.initial());
    }

    public LiveData<Resource<UserLoginResponse>> getLoginResponse(){
        return responseLiveData;
    }

    public void updateUserName(String userName) {
        Username.postValue(userName);
    }

    public void updatePassword(String password) {
        Password.postValue(password);
    }

    public void onClick() {
        UserLoginRequest loginUser = new UserLoginRequest(Username.getValue(), Password.getValue());

        loginRequest(loginUser);

    }
    private void loginRequest(UserLoginRequest user){
        responseLiveData.setValue(Resource.loading());

        compositeDisposable.add(repository
                .loginRequest(user)
                .subscribe( result -> responseLiveData.postValue(Resource.success(result)),
                            error -> responseLiveData.postValue(Resource.error(error)),
                            () -> Log.d("onCompleted", "Login request Completed")));
    }

    public void saveAuthToken(String token){
        sessionManager.saveAuthToken(token);
    }

    public void saveUser(UserModel userModel){
        sessionManager.saveUser(userModel);
    }

    @Override
    protected void onCleared() {
        super.onCleared();

        compositeDisposable.clear();
    }
}
