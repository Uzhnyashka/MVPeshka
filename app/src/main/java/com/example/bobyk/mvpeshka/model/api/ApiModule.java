package com.example.bobyk.mvpeshka.model.api;

import com.example.bobyk.mvpeshka.global.Constants;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by bobyk on 18.08.16.
 */
public class ApiModule {

    public static ApiInterface getApiInterface() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Constants.BASE_API)
                .addConverterFactory(GsonConverterFactory.create());

        ApiInterface apiInterface = builder.build().create(ApiInterface.class);
        return apiInterface;
    }
}
