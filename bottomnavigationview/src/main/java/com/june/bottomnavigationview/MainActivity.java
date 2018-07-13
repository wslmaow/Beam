package com.june.bottomnavigationview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TabHost;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.bottom_navigation)
    BottomNavigationView bottomNavigation;
    @Bind(R.id.realtabcontent)
    FrameLayout realtabcontent;
    @Bind(android.R.id.tabhost)
    FragmentTabHost tabhost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initView();
        initListener();
    }

    void initView() {
        BottomNavigationViewHelper.disableShiftMode(bottomNavigation);

        tabhost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        for (int i=0;i<4;i++){
            TabHost.TabSpec spec = tabhost.newTabSpec(i+"").setIndicator(i+"yy");
            Bundle bundle = new Bundle();
            bundle.putInt("tag",i);
            tabhost.addTab(spec,FragmentOne.class,bundle);
        }
    }

    void initListener() {
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_favorites:
                        tabhost.setCurrentTab(0);
                        break;
                    case R.id.action_schedules:
                        tabhost.setCurrentTab(1);
                        break;
                    case R.id.action_music:
                        tabhost.setCurrentTab(2);
                        break;
                    case R.id.action_message:
                        tabhost.setCurrentTab(3);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }
}
