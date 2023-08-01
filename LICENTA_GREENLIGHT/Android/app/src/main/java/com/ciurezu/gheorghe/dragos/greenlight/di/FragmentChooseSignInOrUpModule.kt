package com.ciurezu.gheorghe.dragos.greenlight.di

import com.ciurezu.gheorghe.dragos.greenlight.presentation.intro.view.FragmentChooseSignInOrUp
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentChooseSignInOrUpModule {
    @ContributesAndroidInjector
    abstract fun contributeFragmentChooseSignInOrUp() :FragmentChooseSignInOrUp
}