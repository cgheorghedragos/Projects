package com.example.greenlight.di;

import com.example.greenlight.presentation.views.application.map.SolveIncidentFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class SolveIncidentFragmentModule{
    @ContributesAndroidInjector
    abstract SolveIncidentFragment contributeSolveIncidentFragmentModule();
}
