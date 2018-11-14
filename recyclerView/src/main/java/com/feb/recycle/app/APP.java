package com.feb.recycle.app;

import android.app.Application;

import com.bumptech.glide.Glide;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.feb.recycle.R;
import com.feb.recycle.view.LoadingState;
import com.jude.beam.Beam;
import com.jude.beam.expansion.BeamBaseActivity;
import com.jude.beam.expansion.list.ListConfig;
import com.jude.beam.expansion.overlay.ViewExpansionDelegate;
import com.jude.beam.expansion.overlay.ViewExpansionDelegateProvider;
import com.feb.recycle.utils.RetrofitUtils;
import com.jude.utils.JUtils;
import com.tqzhang.stateview.core.LoadState;

import java.io.InputStream;

/**
 * Created by Mr.Jude on 2015/7/28.
 */
public class APP extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        JUtils.initialize(this);
        JUtils.setDebug(true, "BeamTest");
        Beam.init(this);
        Beam.setViewExpansionDelegateProvider(new ViewExpansionDelegateProvider() {
            @Override
            public ViewExpansionDelegate createViewExpansionDelegate(BeamBaseActivity activity) {
                return null;
            }
        });
        ListConfig.setDefaultListConfig(
                new ListConfig().
                        setRefreshAble(true).
                        setContainerLayoutRes(R.layout.activity_recyclerview).
                        setLoadmoreAble(true));

        new LoadState.Builder()
                .register(new LoadingState())
                .setDefaultCallback(LoadingState.class)
                .build();

    }
}
