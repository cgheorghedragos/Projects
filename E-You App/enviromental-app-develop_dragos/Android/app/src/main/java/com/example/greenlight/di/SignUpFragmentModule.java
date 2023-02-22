package com.example.greenlight.di;

import com.example.greenlight.presentation.views.starting.DatePickerFragment;
import com.example.greenlight.presentation.views.starting.SignUpFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class SignUpFragmentModule {

    @ContributesAndroidInjector
    abstract SignUpFragment contributeSignUpFragmentModule();
}
