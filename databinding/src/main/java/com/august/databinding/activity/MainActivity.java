package com.august.databinding.activity;

import android.os.Bundle;

import com.august.databinding.R;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;

public class MainActivity extends BaseActivity{
    @Override
    public BaseViewModel initViewModel() {
        return null;
    }

    @Override
    public int initVariableId() {
        return 0;
    }

    @Override
    public int initContentView(Bundle bundle) {
        return R.layout.activity_main;
    }
}
