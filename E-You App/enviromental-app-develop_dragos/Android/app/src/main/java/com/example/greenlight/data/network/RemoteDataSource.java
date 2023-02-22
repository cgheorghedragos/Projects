package com.example.greenlight.data.network;

import android.content.Context;

import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteDataSource {
    private  String BASE_URL = "http://172.20.10.4:8081/";
    private Gson gson;

    @Inject
    public RemoteDataSource(Gson gson){
        this.gson = gson;
    }


    public <Api> Api buildApi(Class <Api> api, Context context){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(300, TimeUnit.SECONDS)
                .writeTimeout(300,TimeUnit.SECONDS)
                .connectTimeout(300,TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor).build();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(api);
    }
}
