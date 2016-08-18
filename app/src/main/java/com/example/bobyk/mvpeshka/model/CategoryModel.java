package com.example.bobyk.mvpeshka.model;

import com.example.bobyk.mvpeshka.model.data.Categories;
import com.example.bobyk.mvpeshka.model.data.Category;

import java.util.List;

import retrofit.Call;

/**
 * Created by bobyk on 18.08.16.
 */
public interface CategoryModel {

    Call<Categories> getCategoryList();
}
