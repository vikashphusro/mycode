package com.condivision.rentongo.fragment;

import com.condivision.rentongo.R;
import com.condivision.rentongo.interfaces.OnMessageListner;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class RateUsFragment extends Fragment {

	private static final String TAG = "RateUsFragment";
	private static final RateUsFragment mRateUsFragment = new RateUsFragment();
	private OnMessageListner mOnMessageListner;

	private RateUsFragment() {

	}

	public static RateUsFragment getInstance() {
		return mRateUsFragment;
	}
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
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

		View view = inflater.inflate(R.layout.fragment_rate_us, container,
				false);
		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		mOnMessageListner.setTitle("Rate Us");
		mOnMessageListner.setEnableBackButton(true);
		mOnMessageListner.setEnableProfileButton(true);
		mOnMessageListner.setEnableFilterPanel(false);
		
	}
}
