package com.apr.coordinatorLayout.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.apr.coordinatorLayout.R;
import com.apr.coordinatorLayout.adapter.MyPagerAdapter;
import com.apr.coordinatorLayout.adapter.OptimizedRecyclerAdapter;
import com.apr.coordinatorLayout.holder.MyViewHolder;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyCoordinatorLayoutActivity extends AppCompatActivity {

    @Bind(R.id.float_btn)
    FloatingActionButton floatBtn;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tabs)
    TabLayout tabs;
    @Bind(R.id.appbar)
    AppBarLayout appbar;
    @Bind(R.id.viewpager)
    ViewPager viewpager;
    @Bind(R.id.main_content)
    CoordinatorLayout mainContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    void initView(){
        List<View> pagerViews = new ArrayList<>();
        RecyclerView viewHolder;
        RecyclerView.Adapter adapter=new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new MyViewHolder(parent);
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                TextView textView = (TextView) holder.itemView.findViewById(R.id.item_text);
                textView.setText("item : "+position);
                /*textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MyCoordinatorLayoutActivity.this,"ok",Toast.LENGTH_SHORT).show();
                    }
                });*/
            }

            @Override
            public int getItemCount() {
                return 21;
            }
        };
        List<String> data=new ArrayList<>();
        for (int i=0;i<30;i++){
            data.add("test data "+i);
        }
        OptimizedRecyclerAdapter optimizedRecyclerAdapter=new OptimizedRecyclerAdapter(R.layout.item_recycler,data);
        optimizedRecyclerAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(MyCoordinatorLayoutActivity.this,"ok",Toast.LENGTH_SHORT).show();

            }
        });
        optimizedRecyclerAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(MyCoordinatorLayoutActivity.this,"no",Toast.LENGTH_SHORT).show();

            }
        });

        for (int i=0;i<3;i++){
            viewHolder = new RecyclerView(this);
            //viewHolder.setAdapter(adapter);
            viewHolder.setAdapter(optimizedRecyclerAdapter);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            viewHolder.setLayoutManager(layoutManager);
            pagerViews.add(viewHolder);
            tabs.addTab(tabs.newTab());
        }
        PagerAdapter PagerAdapter = new MyPagerAdapter(pagerViews);
        viewpager.setAdapter(PagerAdapter);
        tabs.setupWithViewPager(viewpager);
        tabs.setTabTextColors(Color.parseColor("#ff4499"),Color.parseColor("#239911"));
        for (int i=0;i<3;i++) {
            tabs.getTabAt(i).setText("tab : "+i);
        }
    }

    @OnClick(R.id.float_btn)
    public void onViewClicked() {
        Snackbar.make(floatBtn, "hello!", Snackbar.LENGTH_SHORT).setAction("cancel", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MyCoordinatorLayoutActivity.this,TestCoordinatorActivity.class));
            }
        }).show();
    }
}
