package com.example.bobyk.mvpeshka.presenter;

import android.widget.ListView;

import com.example.bobyk.mvpeshka.model.CategoryModel;
import com.example.bobyk.mvpeshka.model.CategoryModelImpl;
import com.example.bobyk.mvpeshka.model.data.Categories;
import com.example.bobyk.mvpeshka.model.data.Category;
import com.example.bobyk.mvpeshka.view.View;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by bobyk on 18.08.16.
 */
public class CategoryPresenter implements ICategoryPresenter {

    private CategoryModel categoryModel = new CategoryModelImpl();

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
            public void onResponse(Response<Categories> response, Retrofit retrofit) {
                if (response.body() != null && !response.body().getCategoryList().isEmpty()){
                    view.showData(response.body().getCategoryList());
                } else {
                    view.showEmptyList();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                view.showError(t.getMessage());
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
