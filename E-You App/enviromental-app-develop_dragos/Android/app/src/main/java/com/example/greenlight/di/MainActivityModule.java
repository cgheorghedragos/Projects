package com.example.greenlight.di;

import com.example.greenlight.MainActivity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainActivityModule {

    @ContributesAndroidInjector
    abstract MainActivity contributeMainActivityModule();

}
