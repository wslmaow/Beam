package com.feb.demo.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.feb.demo.ui.MainActivity;
import com.jude.beam.bijection.Presenter;

/**
 * Created by Mr.Jude on 2016/2/22.
 */
public class MainPresenter extends Presenter<MainActivity> {
    @Override
    protected void onCreate(@NonNull MainActivity view, Bundle savedState) {
        super.onCreate(view, savedState);
    }
}
