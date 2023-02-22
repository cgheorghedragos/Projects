package com.example.greenlight.di;

import com.example.greenlight.presentation.views.application.map.GoogleMapFragment;
import com.example.greenlight.presentation.views.starting.DatePickerFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class GoogleMapFragmentModule {
    @ContributesAndroidInjector
    abstract GoogleMapFragment contributeGoogleMapFragmentModule();
}
