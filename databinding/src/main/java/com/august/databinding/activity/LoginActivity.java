package com.august.databinding.activity;

import android.app.Activity;
import android.os.Bundle;

import com.august.databinding.BR;
import com.august.databinding.R;
import com.august.databinding.databinding.LoginBinding;
import com.august.databinding.viewmodel.LoginViewModel;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;

public class LoginActivity extends BaseActivity<LoginBinding, LoginViewModel> {
    @Override
    public int initContentView(Bundle bundle) {
        return R.layout.activity_login;
    }

    @Override
    public int initVariableId() {
        return BR.loginViewModel;
    }

    @Override
    public LoginViewModel initViewModel() {
        return new LoginViewModel(this);
    }
}
