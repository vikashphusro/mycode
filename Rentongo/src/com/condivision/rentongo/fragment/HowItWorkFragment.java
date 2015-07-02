package com.condivision.rentongo.fragment;

import com.condivision.rentongo.R;
import com.condivision.rentongo.interfaces.OnMessageListner;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * This class used for to give working details of application
 * 
 * @author rentongo
 *
 */
public class HowItWorkFragment extends Fragment {
	private static final String TAG = "HowItWorkFragment";
	private static final HowItWorkFragment mHowItWorkFragment = new HowItWorkFragment();
	private OnMessageListner mOnMessageListner;

	private HowItWorkFragment() {
		// TODO Auto-generated constructor stub
	}

	public static HowItWorkFragment getInstance() {
		return mHowItWorkFragment;
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
		View view = inflater.inflate(R.layout.fragment_how_it_works, container,
				false);
		return view;
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mOnMessageListner.setTitle("How It Work");
		mOnMessageListner.setEnableBackButton(true);
		mOnMessageListner.setEnableProfileButton(true);
		mOnMessageListner.setEnableFilterPanel(false);
		
	}
}
