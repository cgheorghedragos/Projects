package com.ciurezu.gheorghe.dragos.greenlight.di

import com.ciurezu.gheorghe.dragos.greenlight.presentation.intro.view.FragmentSignIn
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentSignInModule {
    @ContributesAndroidInjector
    abstract fun contributeFragmentSignInModule() : FragmentSignIn
}