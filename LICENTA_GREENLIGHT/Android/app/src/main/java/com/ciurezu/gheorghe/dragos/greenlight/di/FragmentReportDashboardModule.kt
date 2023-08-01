package com.ciurezu.gheorghe.dragos.greenlight.di

import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.map.view.FragmentReportDashboard
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentReportDashboardModule {
    @ContributesAndroidInjector
    abstract fun contributeFragmentReportDashboardModule() : FragmentReportDashboard
}