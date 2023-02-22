package com.example.greenlight.di;

import com.example.greenlight.presentation.views.application.explore.RankingFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class RankingFragmentModule {

    @ContributesAndroidInjector
    abstract RankingFragment contributeRankingFragmentModule();
}
