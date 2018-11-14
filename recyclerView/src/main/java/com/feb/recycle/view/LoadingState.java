package com.feb.recycle.view;

import com.feb.recycle.R;
import com.tqzhang.stateview.stateview.BaseStateControl;

public class LoadingState extends BaseStateControl {
    @Override
    protected int onCreateView() {
        return R.layout.msv__loading;
    }
}
