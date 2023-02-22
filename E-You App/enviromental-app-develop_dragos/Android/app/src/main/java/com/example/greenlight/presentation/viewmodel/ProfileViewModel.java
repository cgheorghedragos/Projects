package com.example.greenlight.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.greenlight.data.SessionManager;
import com.example.greenlight.data.repository.ServerRepository;
import com.example.greenlight.data.requests.UserModel;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ProfileViewModel extends ViewModel {

    private ServerRepository serverRepository;
    private SessionManager sessionManager;
    private MutableLiveData<UserModel>  myAccount = new MutableLiveData<>();


    @Inject
    public ProfileViewModel(ServerRepository serverRepository, SessionManager sessionManager){
        this.serverRepository = serverRepository;
        this.sessionManager = sessionManager;
    }

    public void requestMyAccount(){
        myAccount.postValue(sessionManager.fetchUser());
    }

    public LiveData<UserModel> getMyAccount(){
        return myAccount;
    }

}
