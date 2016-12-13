package com.example.administrator.app.fragment;

import android.app.Fragment;

/**
 * Created by pengsihai@yy.com on 2016/12/13.
 */

public abstract class BaseFragment extends Fragment{

    protected abstract void initView ();
    protected abstract void initData();
    protected abstract void initListener();

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public boolean isActivityDestory() {
        if(getActivity() == null || getActivity().isFinishing()) {
            return true;
        }
        return false;
    }
}
