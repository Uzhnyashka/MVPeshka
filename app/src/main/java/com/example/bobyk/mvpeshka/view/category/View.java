package com.example.bobyk.mvpeshka.view.category;

import com.example.bobyk.mvpeshka.model.category.data.Category;

import java.util.List;

/**
 * Created by bobyk on 18.08.16.
 */
public interface View {
    void showData(List<Category> list);

    void showError(String error);

    void showEmptyList();
}
