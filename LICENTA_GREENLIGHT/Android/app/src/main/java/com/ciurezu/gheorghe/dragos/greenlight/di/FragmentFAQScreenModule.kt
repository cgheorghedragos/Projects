package com.ciurezu.gheorghe.dragos.greenlight.di

import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.home.view.FragmentFAQScreen
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentFAQScreenModule {
    @ContributesAndroidInjector
    abstract fun contributeFragmentFAQScreen(): FragmentFAQScreen
}