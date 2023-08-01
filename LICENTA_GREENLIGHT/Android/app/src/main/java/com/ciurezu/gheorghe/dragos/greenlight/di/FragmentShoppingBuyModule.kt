package com.ciurezu.gheorghe.dragos.greenlight.di

import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.shopping.view.FragmentShoppingBuy
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentShoppingBuyModule {
    @ContributesAndroidInjector
    abstract fun contributeFragmentShoppingBuyModule() : FragmentShoppingBuy
}