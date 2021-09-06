package com.example.appmp3.view.base;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.appmp3.view.module.dialog.LoadingDialog;

public abstract class BaseActivity<V extends ViewDataBinding, VM extends BaseViewModel> extends AppCompatActivity {
    private LoadingDialog loadingDialog;
    private V binding;
    private VM viewModel;

    protected V getBinding() {
        return binding;
    }

    protected VM getViewModel() {
        return viewModel;
    }

    private Observer<String> errorObserver = error -> Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    private Observer<Boolean> loadingObs = new Observer<Boolean>() {
        @Override
        public void onChanged(Boolean isShowLoading) {
            if (isShowLoading) {
                loadingDialog.show();
            } else {
                loadingDialog.dismiss();
            }
        }
    };

    protected abstract void addEvent();

    protected abstract void obsViewModel();

    protected abstract int getLayoutId();

    protected abstract Class<VM> getViewModelClass();

    protected abstract void init();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, getLayoutId());
        viewModel = new ViewModelProvider(this).get(getViewModelClass());

        loadingDialog = new LoadingDialog(this);

        viewModel.errorLiveData.observe(this, errorObserver);
        viewModel.loadingLiveData.observe(this, loadingObs);

        obsViewModel();
        addEvent();
        init();
    }
}