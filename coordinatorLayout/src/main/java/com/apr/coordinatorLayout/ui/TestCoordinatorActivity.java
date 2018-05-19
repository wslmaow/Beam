package com.apr.coordinatorLayout.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.apr.coordinatorLayout.R;
import com.apr.coordinatorLayout.adapter.OptimizedRecyclerAdapter;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.jude.easyrecyclerview.EasyRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    List<String> data;
    OptimizedRecyclerAdapter adapter;
    @Bind(R.id.float_btn)
    FloatingActionButton floatBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        initParams();
        initViews();
    }

    void initViews() {
       // collapsingToolbar.setTitle(getResources().getString(R.string.app_name));
        collapsingToolbar.setCollapsedTitleTextColor(Color.parseColor("#ffffff"));
        collapsingToolbar.setExpandedTitleColor(Color.parseColor("#ff0000"));

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(TestCoordinatorActivity.this,"ok "+position,Toast.LENGTH_SHORT).show();
            }
        });
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(TestCoordinatorActivity.this,"no "+position,Toast.LENGTH_SHORT).show();

            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        floatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                collapsingToolbar.setTitle(getResources().getString(R.string.app_name));
            }
        });
    }

    void initParams() {
        data = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            data.add("test data " + i);
        }
        adapter = new OptimizedRecyclerAdapter(R.layout.item_recycler, data);
    }

    @OnClick(R.id.float_btn)
    public void onViewClicked() {
    }
}
