package com.example.bobyk.mvpeshka.view;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.bobyk.mvpeshka.R;
import com.example.bobyk.mvpeshka.view.comments.CommentFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openCommentFragment();
    }

    private void openCommentFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment, CommentFragment.newInstance());
        ft.addToBackStack(null);
        ft.commit();
    }
}
