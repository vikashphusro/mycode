package com.condivision.rentongo.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * Application default sharedprefrance class.
 * 
 * @author rentongo
 *
 */
public class AppPreferences {
	private static final String TAG = "AppPreferences";
	public static String firstLaunch = null;
	private static final String APP_SHARED_PREFS = "com.condivision.rentongo";
	private SharedPreferences appSharedPrefs;
	private static Editor prefsEditor;
	private static AppPreferences instance = null;
	private Context mContext;

	private AppPreferences(Context context) {
		this.appSharedPrefs = context.getSharedPreferences(APP_SHARED_PREFS,
				Activity.MODE_PRIVATE);
		this.prefsEditor = appSharedPrefs.edit();
	}

	public static AppPreferences getInstance(Context context) {
		if (instance != null) {
			return instance;
		} else {
			instance = new AppPreferences(context);
			return instance;
		}

	}

	/**
	 * get Editor
	 * 
	 * @return
	 */

	public Editor getEditor() {
		return prefsEditor;
	}

	/**
	 * set Latitude
	 */
	public void setLatitude(double latitude) {
		prefsEditor.putFloat("latitude", (float) latitude);
		prefsEditor.commit();
	}

	public void setLongitude(double longitude) {
		prefsEditor.putFloat("longitude", (float) longitude);
		prefsEditor.commit();
	}

	public double getLatitude() {
		return appSharedPrefs.getFloat("latitude", 0.0f);
	}

	public double getLongitude() {
		return appSharedPrefs.getFloat("longitude", 0.0f);
	}

	public void setMotorcycleStatus(boolean status) {
		prefsEditor.putBoolean("motorcyclestatus", status);
		prefsEditor.commit();
	}

	public void setScooterStatus(boolean status) {
		prefsEditor.putBoolean("scooterstatus", status);
		prefsEditor.commit();
	}

	public void setBycycleStatus(boolean status) {
		prefsEditor.putBoolean("bycyclestatus", status);
		prefsEditor.commit();

	}

	public boolean getMotorCycleStatus() {
		return appSharedPrefs.getBoolean("motorcyclestatus", false);
	}

	public boolean getScooterStatus() {
		return appSharedPrefs.getBoolean("scooterstatus", false);
	}

	public boolean getBicycleStatus() {
		return appSharedPrefs.getBoolean("bycyclestatus", false);
	}

	public void setLoginStatus(boolean status) {
		prefsEditor.putBoolean("login", status);
		prefsEditor.commit();

	}

	public boolean getLoginStatus() {
		return appSharedPrefs.getBoolean("login", false);
	}

}
