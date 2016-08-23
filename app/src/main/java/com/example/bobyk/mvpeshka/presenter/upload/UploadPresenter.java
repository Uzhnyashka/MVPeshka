package com.example.bobyk.mvpeshka.presenter.upload;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;


import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.example.bobyk.mvpeshka.global.Constants;
import com.example.bobyk.mvpeshka.view.upload.UploadView;

import java.io.File;

/**
 * Created by bobyk on 22.08.16.
 */
public class UploadPresenter implements IUploadPresenter {

    private Activity mContext;
    private File file;
    private UploadView mView;
    private Fragment mFragment;
    private TransferUtility transferUtility;

    public UploadPresenter(Activity activity, Fragment fragment, UploadView view) {
        this.mContext = activity;
        this.mView = view;
        this.mFragment = fragment;

        init();
    }

    private void init() {
        CognitoCachingCredentialsProvider credentialProvider = new CognitoCachingCredentialsProvider(
                mContext,
                Constants.AMAZON_AUTH,
                Regions.EU_WEST_1
        );
        AmazonS3 s3 = new AmazonS3Client(credentialProvider);
        transferUtility = new TransferUtility(s3, mContext);
    }

    @Override
    public void performFileSearch() {
        Intent intent = new Intent();
        intent.setType("*/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        mFragment.startActivityForResult(intent, Constants.READ_FILE_REQUEST_CODE);
    }

    @Override
    public void getLoadFile(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.READ_FILE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Uri uri = null;
            if (data != null) {
                uri = data.getData();
                file = new File(getRealPathFromURI(uri));
            }
        }
        else {
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


    @Override
    public void uploadFile() {
        TransferObserver observer = transferUtility.upload(
                Constants.AMAZON_BUCKED,
                file.getName(),
                file
        );

        observer.setTransferListener(new TransferListener() {
            @Override
            public void onStateChanged(int id, TransferState state) {
                mView.successLoading(generatePhotoURL(file.getName()));
            }

            @Override
            public void onProgressChanged(int id, long bytesCurrent, long bytesTotal) {
            }

            @Override
            public void onError(int id, Exception ex) {
                mView.errorLoading();

            }
        });
    }

    @Override
    public void downloadFile() {
        final File newFile = new File(mContext.getCacheDir().getAbsolutePath(), "xyj.jpg");

        TransferObserver observer = transferUtility.download(
                Constants.AMAZON_BUCKED,
                file.getName(),
                newFile
        );

        observer.setTransferListener(new TransferListener() {
            @Override
            public void onStateChanged(int id, TransferState state) {
             //   mView.showImage(BitmapFactory.decodeFile(newFile.getAbsolutePath()));
            }

            @Override
            public void onProgressChanged(int id, long bytesCurrent, long bytesTotal) {

            }

            @Override
            public void onError(int id, Exception ex) {
                mView.errorLoading();
            }
        });
    }

    private String generatePhotoURL(final String photoName) {
        return Constants.AMAZON_BASE_URL + photoName;
    }
}
