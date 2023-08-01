package com.ciurezu.gheorghe.dragos.greenlight.di

import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.map.view.FragmentSolveMarker
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentSolveMarkerModule {
    @ContributesAndroidInjector
    abstract fun contributeFragmentSolveMarkerModule(): FragmentSolveMarker
}