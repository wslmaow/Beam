package com.august.databinding.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

import com.august.databinding.R;
import com.qihoo.safekeyboard.PasswdEditText;

import butterknife.Bind;
import butterknife.ButterKnife;

/*public class LoginActivity extends BaseActivity<LoginBinding, LoginViewModel> {
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
}*/
public class LoginActivity extends AppCompatActivity {
    @Bind(R.id.qihoo_keyboard_et_passwd)
    PasswdEditText qihooKeyboardEtPasswd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        qihooKeyboardEtPasswd.setCursorVisible(true);
    }
}
