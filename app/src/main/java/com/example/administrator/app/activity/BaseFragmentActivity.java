package com.example.administrator.app.activity;

import android.support.v4.app.FragmentActivity;
import android.view.View;

/**
 * Created by pengsihai@yy.com on 2016/12/13.
 */

public abstract class BaseFragmentActivity extends FragmentActivity {
    protected  boolean isDestory;
    protected abstract void initView();
    protected abstract void initData();
    protected abstract void initListener();
    @Override
    protected void onDestroy() {
        super.onDestroy();
        isDestory = true;
    }

    protected boolean isActivityDestoryed() {
        if(isFinishing() || isDestory) {
            return true;
        }
        return false;
    }

    public <T extends View> T findView(int resId) {
        return (T)findViewById(resId);
    }
}
