package com.ciurezu.gheorghe.dragos.greenlight.di

import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.home.view.FragmentTrashScreen
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentTrashScreenModule {
    @ContributesAndroidInjector
    abstract fun contributeFragmentTrashScreenModule(): FragmentTrashScreen
}