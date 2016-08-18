package com.example.bobyk.mvpeshka.model.api;

import com.example.bobyk.mvpeshka.global.Constants;
import com.example.bobyk.mvpeshka.model.data.Categories;
import com.example.bobyk.mvpeshka.model.data.Category;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by bobyk on 18.08.16.
 */
public interface ApiInterface {

    @GET(Constants.API_CATEGORY)
    Call<Categories> getRepositories();
}
