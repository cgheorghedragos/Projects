package com.example.greenlight.di;

import com.example.greenlight.presentation.views.application.map.MapContainerFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MapContainerFragmentModule {
    @ContributesAndroidInjector
    abstract MapContainerFragment contributeMapContainerFragmentModule ();
}
