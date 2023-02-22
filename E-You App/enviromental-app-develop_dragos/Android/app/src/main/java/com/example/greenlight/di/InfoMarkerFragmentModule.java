package com.example.greenlight.di;

import com.example.greenlight.presentation.views.application.map.InfoMarkerFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class InfoMarkerFragmentModule {
    @ContributesAndroidInjector
    abstract InfoMarkerFragment contributeInfoMarkerFragmentModule();
}
