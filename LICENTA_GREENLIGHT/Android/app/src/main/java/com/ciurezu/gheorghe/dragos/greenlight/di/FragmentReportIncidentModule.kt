package com.ciurezu.gheorghe.dragos.greenlight.di

import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.map.view.FragmentReportIncident
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentReportIncidentModule {
    @ContributesAndroidInjector
    abstract fun contributeFragmentReportIncidentModule() : FragmentReportIncident
}