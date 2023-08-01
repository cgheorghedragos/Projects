package com.ciurezu.gheorghe.dragos.greenlight.di

import com.ciurezu.gheorghe.dragos.greenlight.MainActivity
import com.ciurezu.gheorghe.dragos.greenlight.presentation.intro.view.FragmentIntro
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentIntroModule {
    @ContributesAndroidInjector
    abstract fun contributeFragmentIntro(): FragmentIntro
}