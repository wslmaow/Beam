package com.feb.demo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.feb.demo.R;
import com.feb.demo.utils.CommonUtils;

import butterknife.Bind;

/**
 * Created by Administrator on 2018/3/30.
 */

public class MProgressDialog extends Dialog {

    @Bind(R.id.tv_progress_text)
    TextView tvProgressText;

    public MProgressDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.dialog_progress);
    }

    public void show(String msg){
        if (CommonUtils.isEmpty(msg)){
            tvProgressText.setVisibility(View.GONE);
        }else {
            tvProgressText.setVisibility(View.VISIBLE);
            tvProgressText.setText(msg);
        }
        show();
    }
}
