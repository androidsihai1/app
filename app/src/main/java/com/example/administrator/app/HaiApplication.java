package com.example.administrator.app;

import android.app.Application;
import android.content.Context;

import com.example.administrator.app.db.DbManager;

/**
 * Created by pengsihai@yy.com on 2016/12/12.
 */

public class HaiApplication extends Application {

    public static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    public void init() {
        mContext = this;
        DbManager.getInstance().init(); //初始化数据库
        HaiSharePreference.initSp(mContext); //初始化SharePrefrence
    }




}
