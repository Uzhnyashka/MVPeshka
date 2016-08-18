package com.example.bobyk.mvpeshka.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.bobyk.mvpeshka.R;
import com.example.bobyk.mvpeshka.model.data.Category;
import com.example.bobyk.mvpeshka.presenter.CategoryPresenter;
import com.example.bobyk.mvpeshka.presenter.ICategoryPresenter;
import com.example.bobyk.mvpeshka.view.adapters.RecyclerViewAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View{

    @Bind(R.id.rv)
    RecyclerView rv;

    private RecyclerViewAdapter adapter;

    private ICategoryPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        rv.setLayoutManager(new LinearLayoutManager(this));
        presenter = new CategoryPresenter(this);
        presenter.getAllCategories();
        adapter = new RecyclerViewAdapter();
        rv.setAdapter(adapter);
    }

    @Override
    public void showData(List<Category> list) {
        adapter.setCategoryList(this, list);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showEmptyList() {
        Toast.makeText(this, "List", Toast.LENGTH_SHORT).show();
    }
}
