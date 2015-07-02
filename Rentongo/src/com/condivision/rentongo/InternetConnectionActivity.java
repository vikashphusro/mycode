package com.condivision.rentongo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.PorterDuff.Mode;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.condivision.rentongo.util.Constant;

/**
 * This Class used to show Internet Status.
 * 
 * @author rentongo
 *
 */
public class InternetConnectionActivity extends Activity implements
		OnClickListener, OnTouchListener {
	private static final String TAG = "InternetConnectionActivity";
	private Button mOk, mTryAgain;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_no_internet);
		Log.d(TAG, "onCreate");
		
		// set notification bar color in lollipop and above version
		int currentapiVersion = android.os.Build.VERSION.SDK_INT;
		if (currentapiVersion >= android.os.Build.VERSION_CODES.LOLLIPOP) {
			Window window = InternetConnectionActivity.this.getWindow();
			window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			window.setStatusBarColor(getResources().getColor(
					R.color.notification_baf_color));
		}
		//get view of this screen.
		mOk = (Button) findViewById(R.id.btnInternetOk);
		mTryAgain = (Button) findViewById(R.id.btnInternetTryAgain);
		TextView txt1 = (TextView) findViewById(R.id.txtInternet1);
		TextView txt2 = (TextView) findViewById(R.id.txtInternet2);
		//set custom font on all view.
		mOk.setTypeface(Constant.getFontSemiBold(this));
		mTryAgain.setTypeface(Constant.getFontSemiBold(this));
		txt1.setTypeface(Constant.getFontSemiBold(this));
		txt2.setTypeface(Constant.getFontNormal(this));
		//set listener to button
		mOk.setOnClickListener(this);
		mOk.setOnTouchListener(this);
		mTryAgain.setOnClickListener(this);
		mTryAgain.setOnTouchListener(this);

	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.d(TAG,"onStart" );
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d(TAG, "onResume");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.d(TAG, "onPause");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.d(TAG, "onStop");
	}
	

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "onDestroy");
	}

	@Override
	public void onClick(View v) {
		Log.d(TAG, "onClick");

		switch (v.getId()) {
		case R.id.btnInternetOk:
			// Kill this screen because user want to exit from Application.
			finish();

			break;
		case R.id.btnInternetTryAgain:
			//start a splash screen to search condition for Internet connection.
			Intent intent = new Intent(InternetConnectionActivity.this,
					SplashScreenActivity.class);
			startActivity(intent);
			finish();

			break;

		default:
			break;
		}
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		Log.d(TAG, "onTouch");
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN: {
			v.getBackground().setColorFilter(0xf0f47521, Mode.SRC_ATOP);
			v.invalidate();
			break;
		}
		case MotionEvent.ACTION_UP: {
			v.getBackground().clearColorFilter();
			v.invalidate();
			break;
		}
		}

		return false;
	}

}
