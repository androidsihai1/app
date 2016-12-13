package com.example.administrator.app.utils;

import com.example.administrator.app.HaiApplication;

/**
 * Created by pengsihai@yy.com on 2016/12/13.
 */

public class ViewUtils {

    public static float dp2px(int dp) {
        float scale = HaiApplication.mContext.getResources().getDisplayMetrics().density;
        return dp * scale + 0.5f;
    }

    public static float px2dp(int px) {
        float scale = HaiApplication.mContext.getResources().getDisplayMetrics().density;
        return px / scale;
    }

    public static float sp2px( float sp) {
       float scale =  HaiApplication.mContext.getResources().getDisplayMetrics().scaledDensity;
        return sp * scale;
    }
}
