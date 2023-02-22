package com.example.greenlight.di;

import com.example.greenlight.EYouApp;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        MainActivityModule.class,
        AppModule.class,
        ExploreFragmentModule.class,
        SplashScreenModule.class,
        ProfileFragmentModule.class,
        SignUpFragmentModule.class,
        DatePickerFragmentModule.class,
        SignInFragmentModule.class,
        MapContainerFragmentModule.class,
        ApplicationActivityModule.class,
        GoogleMapFragmentModule.class,
        ReportActionFragmentModule.class,
        ReportDetailFragmentModule.class,
        SolveIncidentFragmentModule.class,
        RankingFragmentModule.class,
        InfoMarkerFragmentModule.class,
        AndroidSupportInjectionModule.class
})


public interface AppComponent {
    void inject(EYouApp instance);
}
