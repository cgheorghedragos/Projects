package com.example.greenlight.di;


import com.example.greenlight.presentation.views.application.map.ReportActionFragment;
import com.example.greenlight.presentation.views.starting.DatePickerFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ReportActionFragmentModule {
    @ContributesAndroidInjector
    abstract ReportActionFragment contributeReportActionFragmentModule();
}
