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
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UserDetailsFragment extends Fragment implements OnClickListener, OnTouchListener {
	private static final String TAG = "UserDetailsFragment";
	private OnMessageListner mOnMessageListner;
	private EditText mEdtName, mEdtPhoneNumber, mEdtEmailAddress;
	private Button mBtnContinue;
	private TextView mTxtLoginNow, mTxtSignUpNow;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mOnMessageListner = (OnMessageListner) activity;
			// mActivity = (FragmentActivity) activity;
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
		View view = inflater.inflate(R.layout.fragment_user_details, container,
				false);
		TextView txt1 = (TextView) view.findViewById(R.id.txtUserDetails1);
		TextView txt2 = (TextView) view.findViewById(R.id.txtUserDetails2);
		TextView txt3 = (TextView) view.findViewById(R.id.txtUserDetails3);

		mEdtName = (EditText) view.findViewById(R.id.edtUserDetailsName);
		mEdtEmailAddress = (EditText) view
				.findViewById(R.id.edtUserDetailsEmail);
		mEdtPhoneNumber = (EditText) view
				.findViewById(R.id.edtUserDetailsPhoneNum);
		mBtnContinue = (Button) view.findViewById(R.id.btnUserDetails);

		mTxtLoginNow = (TextView) view.findViewById(R.id.txtLoginNow);
		mTxtSignUpNow = (TextView) view.findViewById(R.id.txtUserDetailSignUP);

		txt1.setTypeface(Constant.getFontSemiBold(getActivity()));
		txt2.setTypeface(Constant.getFontSemiBold(getActivity()));
		txt3.setTypeface(Constant.getFontSemiBold(getActivity()));
		mEdtName.setTypeface(Constant.getFontNormal(getActivity()));
		mEdtEmailAddress.setTypeface(Constant.getFontNormal(getActivity()));
		mEdtPhoneNumber.setTypeface(Constant.getFontNormal(getActivity()));
		mTxtLoginNow.setTypeface(Constant.getFontSemiBold(getActivity()));
		mTxtSignUpNow.setTypeface(Constant.getFontSemiBold(getActivity()));
		mBtnContinue.setTypeface(Constant.getFontSemiBold(getActivity()));

		mTxtLoginNow.setOnClickListener(this);
		mTxtSignUpNow.setOnClickListener(this);
		mBtnContinue.setOnClickListener(this);
		mBtnContinue.setOnTouchListener(this);
		return view;
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mOnMessageListner.setEnableBackButton(true);
		mOnMessageListner.setEnableFilterPanel(false);
		mOnMessageListner.setEnableProfileButton(false);
		mOnMessageListner.setTitle("User Details");
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnUserDetails:
			String name = mEdtName.getText().toString().trim();
			String phoneNumber = mEdtPhoneNumber.getText().toString().trim();
			String emailAddress = mEdtEmailAddress.getText().toString().trim();
			
			if (name.isEmpty()) {
				mEdtName.setError("Please Enter Name");
			}else if(phoneNumber.isEmpty()||!Constant.validatePhoneNumber(phoneNumber)){
				mEdtPhoneNumber.setError("Please Enter Valid Phone Number");
			}else if (emailAddress.isEmpty()||!Constant.validateEmailId(emailAddress)) {
				mEdtEmailAddress.setError("Please Enter Valid Email Address");
			}else{
				//To for if above correct.
			}

			break;
		case R.id.txtLoginNow:
			Fragment loginFragment = new LoginFragment();
			// Fragment fragment = new MapViewFragment();
			FragmentManager loginFragmentManager = getActivity()
					.getFragmentManager();
			FragmentTransaction loginFragmentTransaction = loginFragmentManager
					.beginTransaction();
			loginFragmentTransaction.replace(R.id.content_frame, loginFragment);
			loginFragmentTransaction.addToBackStack(null);
			loginFragmentTransaction.commit();
			break;
		case R.id.txtUserDetailSignUP:
			Fragment signUpFragment = new SignUpFragment();
			// Fragment fragment = new MapViewFragment();
			FragmentManager signUpFragmentManager = getActivity()
					.getFragmentManager();
			FragmentTransaction signUpFragmentTransaction = signUpFragmentManager
					.beginTransaction();
			signUpFragmentTransaction.replace(R.id.content_frame,
					signUpFragment);
			signUpFragmentTransaction.addToBackStack(null);
			signUpFragmentTransaction.commit();
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
		}		return false;
	}

}
