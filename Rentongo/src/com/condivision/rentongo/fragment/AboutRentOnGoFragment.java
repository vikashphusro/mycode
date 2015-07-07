package com.condivision.rentongo.fragment;

import com.condivision.rentongo.R;
import com.condivision.rentongo.interfaces.OnMessageListner;
import com.condivision.rentongo.util.Constant;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.PorterDuff.Mode;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * This class used to describe about rentongo details.
 * 
 * @author rentongo
 *
 */
public class AboutRentOnGoFragment extends Fragment implements OnClickListener,
		OnTouchListener {

	private static final String TAG = "AboutRentOnGoFragment";
	private static final AboutRentOnGoFragment mAboutRentOnGoFragment = new AboutRentOnGoFragment();
	private OnMessageListner mOnMessageListner = null;

	private Button mTermAndCondition, mPrivacyPolicy;

	private AboutRentOnGoFragment() {
	}

	public static AboutRentOnGoFragment getInstance() {
		return mAboutRentOnGoFragment;
	}

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
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		Log.d(TAG, "onCreateView");
		View view = inflater.inflate(R.layout.fragment_about_rent_on_go,
				container, false);
		mTermAndCondition = (Button) view
				.findViewById(R.id.btnTermandCondition);
		mPrivacyPolicy = (Button) view.findViewById(R.id.btnPrivacyPolicy);
		mTermAndCondition.setTypeface(Constant.getFontSemiBold(getActivity()));
		mPrivacyPolicy.setTypeface(Constant.getFontSemiBold(getActivity()));

		mTermAndCondition.setOnClickListener(this);
		mPrivacyPolicy.setOnClickListener(this);
		mTermAndCondition.setOnTouchListener(this);
		mPrivacyPolicy.setOnTouchListener(this);
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

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnTermandCondition:
			Log.d(TAG, "term and condition button click");
			Fragment fragmentTermAndContion = new TermAndCondition();
			FragmentManager fragmentManagerTermAndContion = getFragmentManager();
			FragmentTransaction fragmentTransactionTermAndContion = fragmentManagerTermAndContion
					.beginTransaction();
			fragmentTransactionTermAndContion.replace(R.id.content_frame,
					fragmentTermAndContion);
			fragmentTransactionTermAndContion
					.addToBackStack("termandcondition");
			fragmentTransactionTermAndContion.commit();

			break;

		case R.id.btnPrivacyPolicy:
			Log.d(TAG, "privacy button click");
			Fragment fragmentPolicy= new PrivacyPolicyFragment();
			FragmentManager fragmentManagerPolicy = getFragmentManager();
			FragmentTransaction fragmentTransactionPolicy = fragmentManagerPolicy
					.beginTransaction();
			fragmentTransactionPolicy.replace(R.id.content_frame,
					fragmentPolicy);
			fragmentTransactionPolicy
					.addToBackStack("privacypolicy");
			fragmentTransactionPolicy.commit();

			break;

		default:
			break;
		}

	}

}
