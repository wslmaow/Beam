package com.jude.beamdemo.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;

import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.BeamBaseActivity;
import com.jude.beamdemo.itemDecoration.SimplePaddingDecoration;
import com.jude.beamdemo.model.MyModel;
import com.jude.beamdemo.model.ResponseBean;
import com.jude.beamdemo.presenter.MyRecyclerViewPresener;
import com.jude.beamdome.R;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/2/11.
 */
@RequiresPresenter(MyRecyclerViewPresener.class)
public class MyRecyclerViewActivity extends BeamBaseActivity<MyRecyclerViewPresener> {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.recycler)
    EasyRecyclerView recycler;

    public RecyclerArrayAdapter<ResponseBean> myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    void initData(){
        myAdapter=new RecyclerArrayAdapter<ResponseBean>(this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                return new MyViewHolder(parent);
            }
        };

        if (getPresenter()!=null){
            getPresenter().setAdapterData();
        }
    }

    void initView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);
        recycler.setAdapterWithProgress(myAdapter);
        recycler.addItemDecoration(new SimplePaddingDecoration(this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
