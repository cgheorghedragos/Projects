package com.example.greenlight.di;

import com.example.greenlight.presentation.views.application.ApplicationActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ApplicationActivityModule {
    @ContributesAndroidInjector
    abstract ApplicationActivity contributeApplicationActivityModule();
}
