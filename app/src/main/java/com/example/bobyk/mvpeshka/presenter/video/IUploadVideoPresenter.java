package com.example.bobyk.mvpeshka.presenter.video;

import android.content.Intent;

import java.io.File;
import java.util.List;

/**
 * Created by bobyk on 23.08.16.
 */
public interface IUploadVideoPresenter {
    void performFileSearch();
    void uploadVideo();
    void downloadVideo();
    void onActivityResult(int requestCode, int resultCode, Intent intent, List<File> list);
}
