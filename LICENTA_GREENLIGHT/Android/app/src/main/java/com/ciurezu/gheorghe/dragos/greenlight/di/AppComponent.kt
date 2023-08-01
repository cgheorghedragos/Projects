package com.ciurezu.gheorghe.dragos.greenlight.di

import com.ciurezu.gheorghe.dragos.greenlight.GreenLightApp
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AndroidInjectionModule::class,
        FragmentSignUpNameModule::class,
        FragmentSplashScreenModule::class,
        MainActivityModule::class,
        FragmentChooseSignInOrUpModule::class,
        FragmentIntroModule::class,
        FragmentMapScreenModule::class,
        FragmentSignInModule::class,
        FragmentRankingScreenModule::class,
        FragmentHomeScreenModule::class,
        GreenLightActivityModule::class,
        FragmentReportDashboardModule::class,
        FragmentReportIncidentModule::class,
        FragmentFAQScreenModule::class,
        FragmentAchievementScreenModule::class,
        FragmentShoppingBagModule::class,
        FragmentShoppingBuyModule::class,
        FragmentShoppingScreenModule::class,
        FragmentTrashScreenModule::class,
        FragmentSettingsScreenModule::class,
        FragmentInfoMarkerModule::class,
        FragmentSolveMarkerModule::class,
        FragmentCompanyScreenModule::class,
        FragmentSignUpIdentificationModule::class,
        AppModule::class
    ]
)
@Singleton
interface AppComponent {
    fun inject(application: GreenLightApp)
}