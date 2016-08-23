package com.example.bobyk.mvpeshka.view.upload;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.example.bobyk.mvpeshka.R;
import com.example.bobyk.mvpeshka.global.Constants;
import com.example.bobyk.mvpeshka.presenter.upload.IUploadPresenter;
import com.example.bobyk.mvpeshka.presenter.upload.UploadPresenter;

import org.w3c.dom.Text;

import butterknife.OnClick;

/**
 * Created by bobyk on 22.08.16.
 */
public class UploadFragment extends Fragment implements UploadView, View.OnClickListener{

    private IUploadPresenter presenter;

    public static UploadFragment newInstance() {
        Bundle args = new Bundle();
        UploadFragment fragment = new UploadFragment();
        fragment.setArguments(args);
        return fragment;
    }

    TextView tvProgress;
    Button btnChooseFile;
    Button btnUploadFile;
    Button btnDownloadFile;
    ImageView imgField;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.upload_layout, null);
        presenter = new UploadPresenter(getActivity(), this, this);
        tvProgress = (TextView) view.findViewById(R.id.tv_progress_loading);
        btnUploadFile = (Button) view.findViewById(R.id.btn_upload_file);
        btnChooseFile = (Button) view.findViewById(R.id.btn_choose_file);
        btnDownloadFile = (Button) view.findViewById(R.id.btn_download_file);
        imgField = (ImageView) view.findViewById(R.id.img_field);

        btnChooseFile.setOnClickListener(this);
        btnUploadFile.setOnClickListener(this);
        btnDownloadFile.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_choose_file:
                presenter.performFileSearch();
                break;
            case R.id.btn_upload_file:
                presenter.uploadFile();
                break;
            case R.id.btn_download_file:
                imgField.setImageBitmap(null);
                presenter.downloadFile();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.getLoadFile(requestCode, resultCode, data);
    }


    @Override
    public void errorLoading() {
        Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void successLoading(String result) {
        tvProgress.setText(result);
    }

    @Override
    public void showImage(Bitmap bitmap) {
        imgField.setImageBitmap(bitmap);
    }
}
