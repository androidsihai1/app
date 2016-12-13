package com.example.administrator.app.fragment;

import android.os.Bundle;

/**
 * Created by pengsihai@yy.com on 2016/12/13.
 */

public abstract class BasePagerFragment extends BaseFragment {

    protected boolean mIsViewInit;
    protected boolean mIsVisibleToUser;
    protected boolean mIsInitData;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        mIsVisibleToUser = isVisibleToUser;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mIsViewInit = true;
    }

    final protected void prepareData(boolean isForceGetData ) {
        if((!mIsInitData && mIsViewInit && mIsVisibleToUser) || isForceGetData) {
            mIsInitData = true;
            initData();
        }
    }

}
