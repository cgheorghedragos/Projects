package com.ciurezu.gheorghe.dragos.greenlight.di

import com.ciurezu.gheorghe.dragos.greenlight.presentation.intro.view.FragmentSplashScreen
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentSplashScreenModule {
    @ContributesAndroidInjector
    abstract fun contributeFragmentSplashScreen(): FragmentSplashScreen
}