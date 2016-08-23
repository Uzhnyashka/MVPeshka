package com.example.bobyk.mvpeshka.presenter.upload;

import android.content.Intent;

/**
 * Created by bobyk on 22.08.16.
 */
public interface IUploadPresenter {
    void performFileSearch();
    void getLoadFile(int requestCode, int resultCode, Intent data);
    void uploadFile();
}
