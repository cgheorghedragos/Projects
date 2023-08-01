package com.ciurezu.gheorghe.dragos.greenlight.di

import com.ciurezu.gheorghe.dragos.greenlight.presentation.intro.view.FragmentSignUpName

import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class FragmentSignUpNameModule {
    @ContributesAndroidInjector
    abstract fun contributeFragmentSignUpName(): FragmentSignUpName
}