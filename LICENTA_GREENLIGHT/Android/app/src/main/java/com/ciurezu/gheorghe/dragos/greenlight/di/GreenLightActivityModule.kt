package com.ciurezu.gheorghe.dragos.greenlight.di

import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.GreenLightActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class GreenLightActivityModule {
@ContributesAndroidInjector
abstract fun contributeGreenLightActivityModule() :GreenLightActivity
}