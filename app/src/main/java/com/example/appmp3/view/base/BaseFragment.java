package com.example.appmp3.view.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.appmp3.view.module.dialog.LoadingDialog;
import com.example.appmp3.viewmodel.HomeViewModel;

public abstract class BaseFragment<V extends ViewDataBinding, VM extends BaseViewModel> extends Fragment {
    private LoadingDialog loadingDialog;
    private V binding;
    private VM viewModel;

    protected V getBinding() {
        return binding;
    }

    protected VM getViewModel() {
        return viewModel;
    }

    private Observer<String> errorObserver = error -> Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(getViewModelClass());

        loadingDialog = new LoadingDialog(getContext());

        viewModel.errorLiveData.observe(getViewLifecycleOwner(), errorObserver);
        viewModel.loadingLiveData.observe(getViewLifecycleOwner(), loadingObs);

        obsViewModel();
        addEvent();
        init();
    }
}
