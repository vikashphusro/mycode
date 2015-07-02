package com.condivision.rentongo.fragment;

import com.condivision.rentongo.R;
import com.condivision.rentongo.interfaces.OnMessageListner;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * This class used to describe about rentongo details.
 * 
 * @author rentongo
 *
 */
public class AboutRentOnGoFragment extends Fragment {

	private static final String TAG = "AboutRentOnGoFragment";
	private static final AboutRentOnGoFragment mAboutRentOnGoFragment = new AboutRentOnGoFragment();
	private OnMessageListner mOnMessageListner = null;

	private AboutRentOnGoFragment() {
		// TODO Auto-generated constructor stub
	}

	public static AboutRentOnGoFragment getInstance() {
		return mAboutRentOnGoFragment;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		try {
			mOnMessageListner = (OnMessageListner) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnMessageListner");
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		Log.d(TAG, "onCreateView");
		View view = inflater.inflate(R.layout.fragment_about_rent_on_go,
				container, false);
		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		// Set title of Screen
		mOnMessageListner.setTitle("About Rentongo");
		mOnMessageListner.setEnableBackButton(true);
		mOnMessageListner.setEnableProfileButton(true);
		mOnMessageListner.setEnableFilterPanel(false);
	}

}
