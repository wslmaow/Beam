package com.august.databinding.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;

import com.august.databinding.activity.MainActivity;
import com.blankj.utilcode.util.ToastUtils;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.utils.StringUtils;

public class LoginViewModel extends BaseViewModel{
    public ObservableField<String> userName = new ObservableField<>();
    public ObservableField<String> passWord = new ObservableField<>();

    public LoginViewModel(Context context) {
        super(context);
    }

    //登录按钮的点击事件
    public BindingCommand loginOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if (StringUtils.isEmpty(userName.get()) || StringUtils.isEmpty(passWord.get())){
                ToastUtils.showShort("请输入内容");
                return;
            }
            startActivity(MainActivity.class);
        }
    });
}
