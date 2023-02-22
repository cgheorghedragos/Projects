package com.example.greenlight.di;

import com.example.greenlight.presentation.views.starting.DatePickerFragment;
import com.example.greenlight.presentation.views.starting.SignInFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class SignInFragmentModule {

    @ContributesAndroidInjector
    abstract SignInFragment contributeSignInFragmentModule();
}
