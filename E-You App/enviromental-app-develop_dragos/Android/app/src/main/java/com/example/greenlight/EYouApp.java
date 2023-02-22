package com.example.greenlight;

import android.app.Application;

import androidx.annotation.NonNull;

import com.example.greenlight.di.AppModule;
import com.example.greenlight.di.DaggerAppComponent;


import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;

public class EYouApp extends Application implements HasAndroidInjector {

    @Inject
    public DispatchingAndroidInjector<Object> injector ;

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build().inject(this);
    }


    @Override

    public AndroidInjector<Object> androidInjector() {
        return  injector;
    }
}
