package com.example.greenlight.di;

import com.example.greenlight.presentation.views.starting.DatePickerFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class DatePickerFragmentModule {

    @ContributesAndroidInjector
    abstract DatePickerFragment contributeDatePickerFragmentModule();
}
