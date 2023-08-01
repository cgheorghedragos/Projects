package com.ciurezu.gheorghe.dragos.greenlight.di

import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.settings.view.FragmentSettingsScreen
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentSettingsScreenModule {
    @ContributesAndroidInjector
    abstract fun contributeFragmentSettingsScreenModule(): FragmentSettingsScreen
}