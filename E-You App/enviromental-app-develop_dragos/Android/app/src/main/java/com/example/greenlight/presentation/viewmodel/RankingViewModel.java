package com.example.greenlight.presentation.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.greenlight.data.SessionManager;
import com.example.greenlight.data.repository.ServerRepository;
import com.example.greenlight.data.requests.UserModel;
import com.example.greenlight.data.responses.RankingGetResponse;
import com.example.greenlight.data.responses.Resource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

@Singleton
public class RankingViewModel extends ViewModel {
    private ServerRepository repository;
    private MutableLiveData<Resource<RankingGetResponse>> usersByRank = new MutableLiveData<>();
    private MutableLiveData<List<UserModel>> winnersList = new MutableLiveData<>();
    private MutableLiveData<List<UserModel>> inTopList = new MutableLiveData<>();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private SessionManager sessionManager;

    @Inject
    public RankingViewModel(ServerRepository serverRepository,SessionManager sessionManager){
        this.repository = serverRepository;
        this.sessionManager = sessionManager;

    }

    public void requestUsersRanking(int number) {
        usersByRank.postValue(Resource.loading());

        compositeDisposable.add(repository
                .getRanking(number)
                .subscribe(result -> usersByRank.postValue(Resource.success(result)),
                        error -> usersByRank.postValue(Resource.error(error)),
                        () -> {}));
    }

    public ServerRepository getRepository() {
        return repository;
    }

    public void setRepository(ServerRepository repository) {
        this.repository = repository;
    }

    public MutableLiveData<Resource<RankingGetResponse>> getUsersByRank() {
        return usersByRank;
    }

    public void setUsersByRank(MutableLiveData<Resource<RankingGetResponse>> usersByRank) {
        this.usersByRank = usersByRank;
    }

    public MutableLiveData<List<UserModel>> getWinnersList() {
        return winnersList;
    }

    public void setWinnersList(MutableLiveData<List<UserModel>> winnersList) {
        this.winnersList = winnersList;
    }

    public MutableLiveData<List<UserModel>> getInTopList() {
        return inTopList;
    }

    public void setInTopList(MutableLiveData<List<UserModel>> inTopList) {
        this.inTopList = inTopList;
    }

    public CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }

    public void setCompositeDisposable(CompositeDisposable compositeDisposable) {
        this.compositeDisposable = compositeDisposable;
    }
}
