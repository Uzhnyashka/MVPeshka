package com.example.bobyk.mvpeshka.presenter.video;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;

import com.example.bobyk.mvpeshka.global.Constants;
import com.example.bobyk.mvpeshka.view.video.UploadVideoView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bobyk on 23.08.16.
 */
public class UploadVideoPresenter implements IUploadVideoPresenter {

    private Activity mContext;
    private Fragment mFragment;
    private UploadVideoView mView;

    private File file;

    public UploadVideoPresenter(Activity context, Fragment fragment, UploadVideoView view) {
        mContext = context;
        mFragment = fragment;
        mView = view;
    }

    @Override
    public void performFileSearch() {
        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        mFragment.startActivityForResult(intent, Constants.READ_VIDEO_REQUEST_CODE);
    }

    @Override
    public void uploadVideo() {

    }

    @Override
    public void downloadVideo() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data, List<File> list) {
        List<File> mList = new ArrayList<>();
        mList.addAll(list);
        if (requestCode == Constants.READ_VIDEO_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Uri uri = null;
            if (data != null) {
                uri = data.getData();
                file = new File(getRealPathFromURI(uri));
                mList.add(file);
                mView.updateVideoList(mList);
            }
        } else {
            file = null;
        }
    }

    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = mContext.getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

}
