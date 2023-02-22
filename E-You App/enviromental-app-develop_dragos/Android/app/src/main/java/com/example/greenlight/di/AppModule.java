package com.example.greenlight.di;

import android.app.Application;
import android.content.Context;

import com.example.greenlight.data.SessionManager;
import com.example.greenlight.data.network.LocationServerApi;
import com.example.greenlight.data.network.RemoteDataSource;
import com.example.greenlight.data.repository.ServerRepoImpl;
import com.example.greenlight.data.repository.ServerRepository;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private Application application;

    public AppModule(Application application){
        this.application = application;
    }

    @Provides
    @Singleton
    public Application providesApplication(){
        return application;
    }

    @Provides
    @Singleton
    public Context provideContext(){
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    public LocationServerApi provideLocationServerApi(RemoteDataSource remoteDataSource, Context context){
        return remoteDataSource.buildApi(LocationServerApi.class,context);
    }

    @Provides
    @Singleton
    public ServerRepository provideLocationRepository(LocationServerApi locationAPI){
        return new ServerRepoImpl(locationAPI);
    }

    @Provides
    @Singleton
    public Gson providesGson() {
        return new Gson();
    }

    @Provides
    @Singleton
    public SessionManager provideSessionManager(Context context) {
        return new SessionManager(context);
    }

}
