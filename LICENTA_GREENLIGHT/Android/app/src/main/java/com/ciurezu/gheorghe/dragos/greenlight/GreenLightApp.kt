package com.ciurezu.gheorghe.dragos.greenlight

import android.app.Application
import com.ciurezu.gheorghe.dragos.greenlight.di.AppModule
import com.ciurezu.gheorghe.dragos.greenlight.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class GreenLightApp : Application(), HasAndroidInjector{

    @Inject
    lateinit var injector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
            .inject(this)

    }

    override fun androidInjector(): AndroidInjector<Any> = injector
}