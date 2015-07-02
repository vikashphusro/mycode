package com.condivision.rentongo.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * This class contain all constant of Network and check for connection.
 * 
 * @author rentongo
 *
 */
public class NetworkUtility {
	private static final String TAG = "NetworkUtility";

	public static String BASE_URL = "";

	public static boolean checkConnectivity(Context context) {
		Log.d(TAG, "checkConnectivity");
		boolean isConnected = false;
		try {
			ConnectivityManager connService = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo network = connService.getActiveNetworkInfo();
			if (network != null) {
				if (network.isConnected()) {
					isConnected = true;
				}
			} else {
				isConnected = false;

			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return isConnected;
	}

}
