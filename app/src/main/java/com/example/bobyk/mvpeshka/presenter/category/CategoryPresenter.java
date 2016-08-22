package com.example.bobyk.mvpeshka.presenter.category;

import com.example.bobyk.mvpeshka.model.Model;
import com.example.bobyk.mvpeshka.model.ModelImpl;
import com.example.bobyk.mvpeshka.model.category.data.Categories;
import com.example.bobyk.mvpeshka.view.category.View;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by bobyk on 18.08.16.
 */
public class CategoryPresenter implements ICategoryPresenter {

    private Model categoryModel = new ModelImpl();

    private View view;
    private Call<Categories> categoryCB;

    public CategoryPresenter(View view){
        this.view = view;
    }

    @Override
    public void getAllCategories() {
        categoryCB = categoryModel.getCategoryList();
        categoryCB.enqueue(new Callback<Categories>() {

            @Override
            public void onResponse(Call<Categories> call, Response<Categories> response) {

            }

            @Override
            public void onFailure(Call<Categories> call, Throwable t) {

            }
        });
    }

    @Override
    public void onStop() {
        if (categoryCB != null) {
            categoryCB.cancel();
        }
    }
}
