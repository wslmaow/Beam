package com.feb.recycle.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.feb.recycle.R;
import com.jude.beam.expansion.BeamBaseActivity;
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MyTestActivity extends BeamBaseActivity {

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    @Bind(R.id.swipy_refresh_layout)
    SwipyRefreshLayout swipyRefreshLayout;
    List<String> data;
    BaseQuickAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);

        initView();
        initListener();
    }

    void initView(){
        data = new ArrayList<>();
        for (int i=0;i<22;i++){
            data.add("test"+i);
        }
        adapter=new BaseQuickAdapter<String,BaseViewHolder>(R.layout.recycler_view_item,data) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                 helper.setText(R.id.text,item);
            }
        };

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        swipyRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.blue));
        swipyRefreshLayout.setDirection(SwipyRefreshLayoutDirection.BOTH);
    }

    void initListener(){
        swipyRefreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                if (direction == SwipyRefreshLayoutDirection.TOP){
                    data.set(0,"top_pull_down");
                    adapter.notifyItemChanged(0);

                }else {
                    data.set(data.size()-1,"bottom_pull_up");
                    adapter.notifyItemChanged(data.size()-1);
                }
                swipyRefreshLayout.setRefreshing(false);
            }
        });
    }
}
