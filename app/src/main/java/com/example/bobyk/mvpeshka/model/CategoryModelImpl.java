package com.example.bobyk.mvpeshka.model;

import com.example.bobyk.mvpeshka.model.CategoryModel;
import com.example.bobyk.mvpeshka.model.api.ApiInterface;
import com.example.bobyk.mvpeshka.model.api.ApiModule;
import com.example.bobyk.mvpeshka.model.data.Categories;
import com.example.bobyk.mvpeshka.model.data.Category;

import java.util.List;

import retrofit.Call;

/**
 * Created by bobyk on 18.08.16.
 */
public class CategoryModelImpl implements CategoryModel {

    ApiInterface apiInterface = ApiModule.getApiInterface();

    @Override
    public Call<Categories> getCategoryList() {
        return apiInterface.getRepositories();
    }
}
