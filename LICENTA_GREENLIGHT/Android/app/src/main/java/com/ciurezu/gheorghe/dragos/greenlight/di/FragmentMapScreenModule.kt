package com.ciurezu.gheorghe.dragos.greenlight.di

import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.map.view.FragmentMapScreen
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentMapScreenModule {
    @ContributesAndroidInjector
    abstract fun contributeFragmentMapScreenModule() : FragmentMapScreen
}