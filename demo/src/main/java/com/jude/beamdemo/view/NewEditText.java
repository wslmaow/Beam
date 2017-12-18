package com.jude.beamdemo.view;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;


/**
 * Created by Administrator on 2017/12/8.
 */

public class NewEditText extends FrameLayout implements TextWatcher {
    EditText editText;
    TextView textView;
    public NewEditText(@NonNull Context context) {
        this(context,null);

    }

    public NewEditText(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public NewEditText(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        editText=new EditText(context);
        textView=new TextView(context);
        init();
    }

    private void init(){
        textView.setText("请输入");
        editText.addTextChangedListener(this);
        editText.setGravity(Gravity.RIGHT);
        textView.setGravity(Gravity.RIGHT);
        addView(editText);
        addView(textView);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (TextUtils.isEmpty(charSequence)){
            textView.setVisibility(VISIBLE);
        }else {
            textView.setVisibility(INVISIBLE);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
