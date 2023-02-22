package com.example.greenlight.di;

import com.example.greenlight.presentation.views.application.explore.ExploreFragment;
import com.example.greenlight.presentation.views.starting.DatePickerFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ExploreFragmentModule {
    @ContributesAndroidInjector
    abstract ExploreFragment contributeExploreFragmentModule();
}
