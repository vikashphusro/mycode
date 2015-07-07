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

public class PrivacyPolicyFragment extends Fragment {

	private static final String TAG = "PrivacyPolicyFragment";
	private OnMessageListner mOnMessageListner;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		Log.d(TAG, "onAttach");
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

		View view = inflater.inflate(R.layout.fragment_privacy_policy,
				container, false);

		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		mOnMessageListner.closeOpenDrawer(true);
		mOnMessageListner.setTitle("Privacy Policy");
		mOnMessageListner.setEnableBackButton(true);
		mOnMessageListner.setEnableProfileButton(true);
		mOnMessageListner.setEnableFilterPanel(false);
		
	}
}
