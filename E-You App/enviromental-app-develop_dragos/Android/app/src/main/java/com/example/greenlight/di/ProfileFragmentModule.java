package com.example.greenlight.di;

import com.example.greenlight.presentation.views.application.profile.ProfileFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ProfileFragmentModule {
    @ContributesAndroidInjector
    abstract ProfileFragment contributeProfileFragmentModule ();
}
