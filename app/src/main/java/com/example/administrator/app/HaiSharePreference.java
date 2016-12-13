package com.example.administrator.app;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by pengsihai@yy.com on 2016/12/13.
 */

public class HaiSharePreference {

    private static final String SP_NAME = "app_sp";
    public static SharedPreferences mSp;

    public  static SharedPreferences initSp(Context context) {
            if(mSp == null) {
                synchronized (HaiSharePreference.class) {
                    if(mSp == null) {
                        mSp =  context.getSharedPreferences(SP_NAME , Context.MODE_PRIVATE);
                    }
                }
        }
        return  mSp;
    }
}
