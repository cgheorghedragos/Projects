package com.ciurezu.gheorghe.dragos.greenlight.di

import com.ciurezu.gheorghe.dragos.greenlight.presentation.intro.view.FragmentSignUpIdentification
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentSignUpIdentificationModule {
    @ContributesAndroidInjector
    abstract fun contributeFragmentSignUpIdentificationModule(): FragmentSignUpIdentification
}