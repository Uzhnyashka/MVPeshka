package com.example.bobyk.mvpeshka.view.upload;

import android.graphics.Bitmap;

/**
 * Created by bobyk on 22.08.16.
 */
public interface UploadView {
    void errorLoading();
    void successLoading(String result);
    void showImage(Bitmap bitmap);
}
