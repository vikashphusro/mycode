package com.condivision.rentongo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.PorterDuff.Mode;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.condivision.rentongo.https.CommonAsync;
import com.condivision.rentongo.interfaces.OnMessageListner;

/**
 * This class used to confirmation from user for Location Selection.
 * 
 * @author rentongo
 *
 */
public class UserConfirmationScreenActivity extends Activity implements
		OnClickListener, OnTouchListener {
	private static final String TAG = "UserConfirmationScreen";
	private Button mBtnOk;
	private Button mBtnDont;
	private TextView mTitleConfirm1, mTitleConfirm2;
	private ProgressDialog mPdialoge;
	private CommonAsync mCommonAsync;
	com.condivision.rentongo.service.GPSTracker gps;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate");
		setContentView(R.layout.activity_user_confirmation_screen);

		// set notification bar color in lollipop and above version
		int currentapiVersion = android.os.Build.VERSION.SDK_INT;
		if (currentapiVersion >= android.os.Build.VERSION_CODES.LOLLIPOP) {
			Window window = UserConfirmationScreenActivity.this.getWindow();
			window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			window.setStatusBarColor(getResources().getColor(
					R.color.notification_baf_color));
		}

		// get view
		mBtnDont = (Button) findViewById(R.id.btnDont);
		mBtnOk = (Button) findViewById(R.id.btnOk);
		Typeface font = Typeface.createFromAsset(getAssets(),
				"proximanova-regular-webfont.ttf");
		mTitleConfirm1 = (TextView) findViewById(R.id.txtConfirmTitle1);
		mTitleConfirm2 = (TextView) findViewById(R.id.txtConfirmTitle2);

		mTitleConfirm1.setTypeface(font);
		mTitleConfirm2.setTypeface(font);
		mBtnDont.setTypeface(font);
		mBtnOk.setTypeface(font);
		// click listener on view
		mBtnDont.setOnClickListener(this);
		mBtnDont.setOnTouchListener(this);

		mBtnOk.setOnClickListener(this);
		mBtnOk.setOnTouchListener(this);

		mPdialoge = new ProgressDialog(UserConfirmationScreenActivity.this);
		mPdialoge.setMessage("Please wait...");
		mPdialoge.setCancelable(true);

	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Log.d(TAG, "onRestart");

	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.d(TAG, "onStart");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d(TAG, "onResume");

		gps = new com.condivision.rentongo.service.GPSTracker(this);
		if (gps.canGetLocation()) {

			Intent intent = new Intent(this, SplashScreenActivity.class);
			startActivity(intent);
			finish();

		}

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
		case R.id.btnDont:
			Log.d(TAG, "Dont button click");

			Intent intent = new Intent(UserConfirmationScreenActivity.this,
					SearchScreenActivity.class);
			intent.putExtra("address", "");
			startActivity(intent);

			break;
		case R.id.btnOk:
			Log.d(TAG, "Ok button click");
			Intent intent1 = new Intent(
					Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			UserConfirmationScreenActivity.this.startActivity(intent1);

			break;
		default:
			break;
		}
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
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
