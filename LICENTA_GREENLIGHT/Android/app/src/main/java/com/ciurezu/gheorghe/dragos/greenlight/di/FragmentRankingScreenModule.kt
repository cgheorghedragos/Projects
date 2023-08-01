package com.ciurezu.gheorghe.dragos.greenlight.di

import com.ciurezu.gheorghe.dragos.greenlight.presentation.app.ranking.view.FragmentRankingScreen
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentRankingScreenModule {
@ContributesAndroidInjector
abstract fun contributeFragmentRankingScreenModule() : FragmentRankingScreen
}