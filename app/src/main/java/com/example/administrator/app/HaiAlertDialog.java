package com.example.administrator.app;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.dbdemo.R;

/*
* sihai
*/
public class HaiAlertDialog {
	
	public Dialog mDialog;
	public Context mContext;
	public Button okBtn;
	public Button cancelBtn;
	public TextView titleTv;
	public OnClickListener mOkOnClickListener;
	public HaiAlertDialog(Context context) {
		this.mContext = context;
		mDialog = new Dialog(context , R.style.my_dialog_style);
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.dialog_item, null);
		okBtn = (Button) view.findViewById(R.id.ok_btn);
		cancelBtn = (Button) view.findViewById(R.id.cancel_btn);
		titleTv = (TextView) view.findViewById(R.id.title_tv);
		okBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mDialog.dismiss();
				if(mOkOnClickListener != null) {
					mOkOnClickListener.onClick(v);
				}
			}
		});
		cancelBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mDialog.dismiss();
			}
		});
		int width = mContext.getResources().getDisplayMetrics().widthPixels;
		mDialog.setContentView(view);
		LayoutParams lp = mDialog.getWindow().getAttributes();
		lp.width = 4 * width / 5;
		mDialog.getWindow().setAttributes(lp);
		mDialog.setCanceledOnTouchOutside(false);
		mDialog.setCancelable(false);
	}
	
	public void dismiss() {
		if(mDialog != null && mDialog.isShowing()) {
			mDialog.dismiss();
		}
	}
	
	public void show() {
		if(mDialog != null && !mDialog.isShowing()) {
			mDialog.show();
		}
	}
	
	public void setOnOkClickListener(OnClickListener onClickListener) {
		this.mOkOnClickListener = onClickListener;
	}
	
	

}
