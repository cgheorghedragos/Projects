package com.example.greenlight.di;

import com.example.greenlight.presentation.views.application.map.ReportDetailFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ReportDetailFragmentModule {
    @ContributesAndroidInjector
    abstract ReportDetailFragment contributeReportDetailFragment();
}
