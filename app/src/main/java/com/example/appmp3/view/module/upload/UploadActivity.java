package com.example.appmp3.view.module.upload;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.example.appmp3.R;
import com.example.appmp3.databinding.ActivityUploadBinding;
import com.example.appmp3.model.entity.Song;
import com.example.appmp3.view.base.BaseActivity;
import com.example.appmp3.viewmodel.UploadViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class UploadActivity extends BaseActivity<ActivityUploadBinding, UploadViewModel> {

    private static String[] permissions = {
            Manifest.permission.READ_EXTERNAL_STORAGE
    };
    private static final int REQUEST_CODE_PERMISSION = 1;

    private static final int PICK_SOUND_FILE = 200;
    private static final int PICK_IMAGE_FILE = 100;
    private Uri mUriImage;
    private Uri mUriMp3;

    @Override
    protected void addEvent() {
        getBinding().btnUpload.setOnClickListener(v -> {
            onUploadClick();
        });

        getBinding().imgBtnUploadImage.setOnClickListener(v -> {

            if (checkPermission(this)) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(intent, PICK_IMAGE_FILE);
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(permissions, REQUEST_CODE_PERMISSION);
                }
            }
        });

        getBinding().tvUploadMp3.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.setType("audio/mp3");
            startActivityForResult(intent, PICK_SOUND_FILE);
        });
    }

    private boolean checkPermission(Context context) {
        boolean isWriteStorageGranted =
                ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_GRANTED;
        return isWriteStorageGranted;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSION) {
            Intent intent = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            startActivityForResult(intent, PICK_IMAGE_FILE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE_FILE) {
            mUriImage = data.getData();
            getBinding().imgBtnUploadImage.setImageURI(mUriImage);
        }
        if (resultCode == RESULT_OK && requestCode == PICK_SOUND_FILE) {
            mUriMp3 = data.getData();
            getBinding().tvUploadMp3.setText(mUriMp3.toString());
        }
    }

    @Override
    protected void obsViewModel() {
        getViewModel().songLiveData.observe(this, aBoolean -> finish());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_upload;
    }

    @Override
    protected Class<UploadViewModel> getViewModelClass() {
        return UploadViewModel.class;
    }

    @Override
    protected void init() {

    }

    private void onUploadClick() {
        String songName = getBinding().edtSongName.getText().toString().trim();
        String singerName = getBinding().edtSingerName.getText().toString().trim();
        String artistName = getBinding().edtArtistName.getText().toString().trim();
        String postName = getBinding().edtPostName.getText().toString().trim();

        Song song = new Song(songName, singerName, artistName, postName, null, null);
        getViewModel().uploadSongInfo(mUriImage, mUriMp3, song);
    }
}
