package com.condivision.rentongo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.condivision.rentongo.db.DatabaseHelper;
import com.condivision.rentongo.service.GPSTracker;
import com.condivision.rentongo.util.AppPreferences;
import com.condivision.rentongo.util.LocationAddress;
import com.condivision.rentongo.util.NetworkUtility;

/**
 * Splashscreen is a first screen dismiss after few second
 * 
 * @author rentongo
 * @date 10th June
 *
 */
public class SplashScreenActivity extends Activity {
	private static final String TAG = "SplashScreenActivity";
	private static final int mId = 2;
	private static final int mInternetId = 3;
	private static final int mTimeOut = 2000;
	private ProgressDialog mPdialoge;
	private GPSTracker gps;
	private DatabaseHelper mDatabaseHelper = null;

	// A Handler allows you to send and process Message and Runnable objects
	// associated with a thread's MessageQueue.
	private final Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {

			switch (msg.what) {
			case mId:
				Log.d(TAG, "after 2 second");
				finish();
				Intent intent = new Intent(SplashScreenActivity.this,
						UserConfirmationScreenActivity.class);
				startActivity(intent);
				break;

			default:
				break;
			}
		};

	};

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate");
		// Set the activity content from a layout resource. The resource will be
		// inflated, adding all top-level views to the activity.
		setContentView(R.layout.activity_splash_screen);
		// set notification bar color in lollipop and above version
		int currentapiVersion = android.os.Build.VERSION.SDK_INT;
		if (currentapiVersion >= android.os.Build.VERSION_CODES.LOLLIPOP) {
			Window window = SplashScreenActivity.this.getWindow();
			window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			window.setStatusBarColor(getResources().getColor(
					R.color.notification_baf_color));
		}
		// Dialog for to show progress
		mPdialoge = new ProgressDialog(SplashScreenActivity.this);
		mPdialoge.setMessage("Please wait...");
		mPdialoge.setIndeterminateDrawable(getResources().getDrawable(R.drawable.my_progress_indeterminate));
		mPdialoge.setCancelable(false);
		mDatabaseHelper = DatabaseHelper.getInstacne(SplashScreenActivity.this);
		// check the Network connection
		if (NetworkUtility.checkConnectivity(SplashScreenActivity.this)) {
			gps = new com.condivision.rentongo.service.GPSTracker(this);
			if (gps.canGetLocation()) {
				// Show dialog
				mPdialoge.show();
				getLocationAddress();

			} else {
				mHandler.sendEmptyMessageDelayed(mId, mTimeOut);
			}

		} else {

			// If Internet is not available then start activity to show error.
			Intent intent1 = new Intent(SplashScreenActivity.this,
					InternetConnectionActivity.class);
			startActivity(intent1);
			finish();

		}

	}

	private void getLocationAddress() {
		double latitude = gps.getLatitude();
		double longitude = gps.getLongitude();
		AppPreferences.getInstance(SplashScreenActivity.this).setLatitude(
				latitude);
		AppPreferences.getInstance(SplashScreenActivity.this).setLongitude(
				longitude);
		// Get Address from given latitude and longitude
		LocationAddress.getAddressFromLocation(latitude, longitude,
				getApplicationContext(), new GeocoderHandler());
	}

	private void displayAlert() {
		Log.d(TAG, "displaytAlert");
		AlertDialog.Builder builder1 = new AlertDialog.Builder(
				SplashScreenActivity.this);
		builder1.setTitle("Alert");
		builder1.setMessage("Please Check Your Internet Connection. ");
		builder1.setCancelable(false);
		builder1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				// displayAlert();
				dialog.cancel();
				SplashScreenActivity.this.finish();

			}
		});

		AlertDialog alert11 = builder1.create();
		alert11.show();
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

	private class GeocoderHandler extends Handler {
		@Override
		public void handleMessage(Message message) {
			String locationAddress;
			switch (message.what) {
			case 1:
				Bundle bundle = message.getData();
				locationAddress = bundle.getString("address");
				break;
			default:
				locationAddress = null;
			}
			if (mPdialoge != null && mPdialoge.isShowing()) {

				Log.d(TAG, "Address : " + locationAddress);
				if (locationAddress != null) {
					mPdialoge.dismiss();
					Intent intent = new Intent(SplashScreenActivity.this,
							SearchScreenActivity.class);
					intent.putExtra("address", locationAddress);
					startActivity(intent);
					finish();
				} else {
					gps = new com.condivision.rentongo.service.GPSTracker(
							SplashScreenActivity.this);
					if (gps.canGetLocation()) {
						getLocationAddress();
					}
				}

			}

		}
	}
}
