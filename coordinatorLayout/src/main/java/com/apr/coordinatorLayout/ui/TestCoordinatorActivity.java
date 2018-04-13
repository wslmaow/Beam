package com.apr.coordinatorLayout.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;

import com.apr.coordinatorLayout.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/4/10.
 */

public class TestCoordinatorActivity extends AppCompatActivity {
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @Bind(R.id.appbar)
    AppBarLayout appbar;
    @Bind(R.id.main_content)
    CoordinatorLayout mainContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        initViews();
    }

    void initViews(){
        collapsingToolbar.setTitle(getResources().getString(R.string.app_name));
        collapsingToolbar.setCollapsedTitleTextColor(Color.parseColor("#ffffff"));
        collapsingToolbar.setExpandedTitleColor(Color.parseColor("#ff0000"));
    }
}
