package com.example.administrator.app;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.dbdemo.R;

/*
* sihai
*/
public class HaiToast {
	
	public static Toast mToast;
	public static Handler mUiHandler;
	public static Context mContext;
	public static LayoutInflater mInflater;
	
	public static void init(Context context) {
		mContext = context;
		mInflater = LayoutInflater.from(context);
		mUiHandler = new Handler(Looper.getMainLooper());
	}
	
	public static void checkInit() {
		if(mContext == null) {
			throw new RuntimeException("you need call init()");
		}
	}
	
	public static void makeText(String str , int icon , int duration) {
		checkInit();
		if(mToast == null) {
			mToast = new Toast(mContext);
		}
		View view = mInflater.inflate(R.layout.toast_item, null);
		ImageView iv = (ImageView) view.findViewById(R.id.toast_iv);
		TextView tv = (TextView) view.findViewById(R.id.toast_title_tv);
		mToast.cancel();
		mToast.setView(view);
		mToast.setGravity(Gravity.CENTER, 0, 0);
		iv.setImageResource(icon);
		tv.setText(str);
		mToast.setDuration(duration);
		mUiHandler.post(new Runnable() {
			
			@Override
			public void run() {
				mToast.show();
			}
		});
	}
	
	public static void makeText(int textId , int icon , int duration) {
		String str = mContext.getResources().getString(textId);
		makeText(str, icon, duration);
	}
	
	public static void sucess(String str) {
		makeText(str, R.mipmap.box_toast_success_face, Toast.LENGTH_LONG);
	}
	
	public static void error(String str) {
		makeText(str, R.mipmap.box_toast_error_face, Toast.LENGTH_LONG);
	}
}
