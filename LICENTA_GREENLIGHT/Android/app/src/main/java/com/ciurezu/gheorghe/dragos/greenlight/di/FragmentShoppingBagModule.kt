package com.ciurezu.gheorghe.dragos.greenlight.di

import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.shopping.view.FragmentShoppingBag
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentShoppingBagModule {
    @ContributesAndroidInjector
    abstract fun contributeFragmentShoppingBagModule(): FragmentShoppingBag
}