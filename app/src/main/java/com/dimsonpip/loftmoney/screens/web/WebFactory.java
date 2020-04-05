package com.dimsonpip.loftmoney.screens.web;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebFactory {

    private WebFactory instance = null;

    public  WebFactory getInstance() {
        if (instance == null) {
            instance = new WebFactory();
        }

        return instance;

    }

    private Retrofit retrofit;
    private OkHttpClient okHttpClient;

    public  WebFactory() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://verdant-violet.glitch.me/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

    }

    public GetItemRequest getItemRequest() {
        return retrofit.create(GetItemRequest.class);
    }

    public PostItemRequest postItemRequest() {
        return retrofit.create(PostItemRequest.class);
    }
}
