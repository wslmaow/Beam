package com.feb.demo.ui;

import android.os.Bundle;
import android.widget.Button;

import com.feb.demo.R;
import com.jude.beam.expansion.BeamBaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/4/2.
 */

public class TestActivity extends BeamBaseActivity {
    @Bind(R.id.button1)
    Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_demo);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button1)
    public void onViewClicked() {
        button1.scrollTo(100,20);
    }
}
