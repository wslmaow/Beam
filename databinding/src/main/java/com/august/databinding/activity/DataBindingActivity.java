package com.august.databinding.activity;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.august.databinding.MyRecyclerAdapter;
import com.august.databinding.R;
import com.august.databinding.databinding.MainBinding;
import com.blankj.utilcode.util.ToastUtils;


import com.bumptech.glide.Glide;
import com.may.coomon.beans.UserBean;

import java.util.ArrayList;
import java.util.List;

public class DataBindingActivity extends AppCompatActivity {
    MainBinding mainBinding;
    List<UserBean> userBeanList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_binding);

        mainBinding= DataBindingUtil.setContentView(this,R.layout.activity_data_binding);

        userBean=new UserBean();
        userBean.userName.set("tom");
        mainBinding.setUser(userBean);
        mainBinding.setImageUrl("https://avatar.csdn.net/6/C/5/1_qq_36304617.jpg");
        initRecyclerView();
    }

    UserBean userBean;

    @BindingAdapter({"url","placeHolder"})
    public static void setImage(ImageView view, String url, Drawable placeHolder){
        Glide.with(view.getContext()).load(url).into(view);
    }

    public void click(View view){
        ToastUtils.showShort("!!!");
        userBean.userName.set("lora");
        for (UserBean userBean:userBeanList){
            userBean.userName.set("long");
        }
    }

    private void initRecyclerView(){
        userBeanList=new ArrayList<>();
        for (int i=0;i<22;i++){
            userBeanList.add(new UserBean());
            userBeanList.get(i).userName.set("userName "+i);
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL, false);
        mainBinding.recyclerView.setLayoutManager(layoutManager);
        mainBinding.recyclerView.setAdapter(new MyRecyclerAdapter(this,userBeanList));
    }

}
