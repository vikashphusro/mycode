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
 * This class used to feedback from user
 * 
 * @author rentongo
 *
 */
public class FeedbackFragment extends Fragment {
	private static final String TAG = "FeedbackFragment";
	private static final FeedbackFragment mFeedbackFragment = new FeedbackFragment();
	private OnMessageListner mOnMessageListner = null;

	private FeedbackFragment() {
		// TODO Auto-generated constructor stub
	}

	public static FeedbackFragment getInstance() {
		return mFeedbackFragment;
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
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.fragment_feedback, container,
				false);
		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		mOnMessageListner.setTitle("Feedback");
		mOnMessageListner.setEnableBackButton(true);
		mOnMessageListner.setEnableProfileButton(true);
		mOnMessageListner.setEnableFilterPanel(false);
	}

}
