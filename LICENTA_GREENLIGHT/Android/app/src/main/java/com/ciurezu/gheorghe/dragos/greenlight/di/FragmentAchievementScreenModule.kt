package com.ciurezu.gheorghe.dragos.greenlight.di

import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.home.view.FragmentAchievementScreen
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentAchievementScreenModule {
    @ContributesAndroidInjector
    abstract fun contributeFragmentAchievementScreen() : FragmentAchievementScreen
}