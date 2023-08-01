package com.ciurezu.gheorghe.dragos.greenlight.di

import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.home.view.FragmentCompany
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentCompanyScreenModule {
    @ContributesAndroidInjector
    abstract fun contributeFragmentCompanyScreenModule(): FragmentCompany
}