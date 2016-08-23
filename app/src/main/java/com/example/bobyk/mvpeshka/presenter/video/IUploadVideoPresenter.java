package com.example.bobyk.mvpeshka.presenter.video;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.io.File;
import java.util.List;

/**
 * Created by bobyk on 23.08.16.
 */
public interface IUploadVideoPresenter {
    void performFileSearch();
    void uploadVideo(int position);
    void downloadVideo();
    void onActivityResult(int requestCode, int resultCode, Intent intent, List<File> list);
}
