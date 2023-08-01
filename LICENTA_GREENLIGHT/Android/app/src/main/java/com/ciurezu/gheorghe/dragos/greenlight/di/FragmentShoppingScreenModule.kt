package com.ciurezu.gheorghe.dragos.greenlight.di

import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.shopping.view.FragmentShoppingScreen
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentShoppingScreenModule {
    @ContributesAndroidInjector
    abstract fun contributeFragmentShoppingScreenModule(): FragmentShoppingScreen
}