package com.example.greenlight.di;

import com.example.greenlight.presentation.views.starting.DatePickerFragment;
import com.example.greenlight.presentation.views.starting.SplashScreen;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class SplashScreenModule {

    @ContributesAndroidInjector
    abstract SplashScreen contributeSplashScreenModule ();
}
