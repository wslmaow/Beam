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

import com.apr.coordinatorLayout.R;
import com.apr.coordinatorLayout.adapter.MyPagerAdapter;
import com.apr.coordinatorLayout.holder.MyViewHolder;

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
            }

            @Override
            public int getItemCount() {
                return 21;
            }
        };
        for (int i=0;i<3;i++){
            viewHolder = new RecyclerView(this);
            viewHolder.setAdapter(adapter);
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
