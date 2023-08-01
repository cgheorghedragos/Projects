package com.ciurezu.gheorghe.dragos.greenlight.di

import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.home.view.FragmentHomeScreen
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentHomeScreenModule {
    @ContributesAndroidInjector
    abstract fun contributeFragmentHomeScreenModule() : FragmentHomeScreen
}