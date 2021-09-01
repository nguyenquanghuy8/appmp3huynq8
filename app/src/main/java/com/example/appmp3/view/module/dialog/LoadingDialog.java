package com.example.appmp3.view.module.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.example.appmp3.databinding.DialogLoadingBinding;

public class LoadingDialog extends Dialog {
    public LoadingDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DialogLoadingBinding dialogLoadingBinding = DialogLoadingBinding.inflate(getLayoutInflater());

        setContentView(dialogLoadingBinding.getRoot());
    }
}
