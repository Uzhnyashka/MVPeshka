package com.example.bobyk.mvpeshka.view;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.bobyk.mvpeshka.R;
import com.example.bobyk.mvpeshka.view.comments.CommentFragment;
import com.example.bobyk.mvpeshka.view.upload.UploadFragment;
import com.example.bobyk.mvpeshka.view.video.UploadVideoFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      //  openCommentFragment();
      //  openUploadFragment();
        openUploadVideoFragment();
    }

    private void openCommentFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment, CommentFragment.newInstance());
        ft.addToBackStack(null);
        ft.commit();
    }

    private void openUploadFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment, UploadFragment.newInstance());
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void openUploadVideoFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment, UploadVideoFragment.newInstance());
        ft.addToBackStack(null);
        ft.commit();
    }
}
