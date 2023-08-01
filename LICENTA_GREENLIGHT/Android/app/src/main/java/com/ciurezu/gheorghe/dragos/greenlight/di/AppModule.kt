package com.ciurezu.gheorghe.dragos.greenlight.di

import android.app.Application
import android.content.Context
import com.ciurezu.gheorghe.dragos.greenlight.data.SharedPrefs
import com.ciurezu.gheorghe.dragos.greenlight.data.local.dao.FaqDAO
import com.ciurezu.gheorghe.dragos.greenlight.data.local.db.GreenLightDatabase
import com.ciurezu.gheorghe.dragos.greenlight.data.network.GreenLightApi
import com.ciurezu.gheorghe.dragos.greenlight.data.network.RemoteDataSource
import com.ciurezu.gheorghe.dragos.greenlight.data.repository.GreenLightRepository
import com.ciurezu.gheorghe.dragos.greenlight.data.repository.GreenLightRepositoryImpl
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {

    @Provides
    @Singleton
    fun providesApplication(): Application = application

    @Provides
    @Singleton
    fun provideContext(): Context = application.applicationContext


    @Provides
    @Singleton
    fun provideFusedLocationProvider(): FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(application)

    @Singleton
    @Provides
    fun providesGreenLightApi(remoteDataSource: RemoteDataSource, context: Context): GreenLightApi {
        return remoteDataSource.buildApi(GreenLightApi::class.java, context)
    }

    @Singleton
    @Provides
    fun provideSharedPrefs(context: Context): SharedPrefs = SharedPrefs(context)

    @Singleton
    @Provides
    fun providesGson() = Gson()

    @Singleton
    @Provides
    fun providesGreenLightDatabase(context: Context): GreenLightDatabase =
        GreenLightDatabase.getInstance(context)

    @Singleton
    @Provides
    fun providesFaqDAO(greenLightDatabase: GreenLightDatabase): FaqDAO = greenLightDatabase.faqDAO

    @Singleton
    @Provides
    fun providesGreenLightRepository(
        faqDAO: FaqDAO,
        greenLightApi: GreenLightApi,
        sharedPrefs: SharedPrefs
    ): GreenLightRepository {
        return GreenLightRepositoryImpl(faqDAO,greenLightApi,sharedPrefs)
    }
}