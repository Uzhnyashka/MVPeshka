package com.example.bobyk.mvpeshka.model.category.response;

import com.example.bobyk.mvpeshka.model.category.data.Category;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by bobyk on 18.08.16.
 */
public class Categories {

    @SerializedName("categories")
    @Expose
    public List<Category> categoryList;

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }
}
