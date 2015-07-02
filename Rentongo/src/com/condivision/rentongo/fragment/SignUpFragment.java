package com.condivision.rentongo.fragment;

import com.condivision.rentongo.R;
import com.condivision.rentongo.interfaces.OnMessageListner;
import com.condivision.rentongo.util.Constant;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SignUpFragment extends Fragment implements OnClickListener,
		OnTouchListener {

	private static final String TAG = "SignUpFragment";
	private EditText mEdtName, mEdtPhoneNumber, mEdtEmailId, mEdtPassword,
			mEdtConfirmPassword;
	private Button mBtnSignUp;

	private OnMessageListner mOnMessageListner;

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
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);

		View view = inflater
				.inflate(R.layout.fragment_signup, container, false);

		TextView txt1 = (TextView) view.findViewById(R.id.txtSignUp1);
		TextView txt2 = (TextView) view.findViewById(R.id.txtSignUp2);
		mEdtName = (EditText) view.findViewById(R.id.edtSignUpName);
		mEdtPhoneNumber = (EditText) view
				.findViewById(R.id.edtSignUPhoneNumber);
		mEdtEmailId = (EditText) view.findViewById(R.id.edtSignUpEmail);
		mEdtPassword = (EditText) view.findViewById(R.id.edtSignUpPassword);
		mEdtConfirmPassword = (EditText) view
				.findViewById(R.id.edtSignUpConfirmPassword);
		mBtnSignUp = (Button) view.findViewById(R.id.btnSignUp);

		txt1.setTypeface(Constant.getFontNormal(getActivity()));
		txt2.setTypeface(Constant.getFontSemiBold(getActivity()));
		mEdtName.setTypeface(Constant.getFontNormal(getActivity()));
		mEdtPhoneNumber.setTypeface(Constant.getFontNormal(getActivity()));
		mEdtEmailId.setTypeface(Constant.getFontNormal(getActivity()));
		mEdtPassword.setTypeface(Constant.getFontNormal(getActivity()));
		mEdtConfirmPassword.setTypeface(Constant.getFontNormal(getActivity()));
		mBtnSignUp.setTypeface(Constant.getFontSemiBold(getActivity()));

		mBtnSignUp.setOnClickListener(this);
		mBtnSignUp.setOnTouchListener(this);
		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		mOnMessageListner.setEnableBackButton(true);
		mOnMessageListner.setEnableFilterPanel(false);
		mOnMessageListner.setTitle("Sign Up");
		mOnMessageListner.setEnableProfileButton(false);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnSignUp:
			String name = mEdtName.getText().toString().trim();
			String phoneNo = mEdtPhoneNumber.getText().toString().trim();
			String emailId = mEdtEmailId.getText().toString().trim();
			String password = mEdtPassword.getText().toString().trim();
			String confirmPassword = mEdtConfirmPassword.getText().toString()
					.trim();

			if (name.isEmpty()) {
				mEdtName.setError("Enter Name");
			} else if (phoneNo.isEmpty()
					|| !Constant.validatePhoneNumber(phoneNo)) {
				mEdtPhoneNumber.setError("Enter Valid Phone Number");
			} else if (emailId.isEmpty() || !Constant.validateEmailId(emailId)) {
				mEdtEmailId.setError("Enter Valid Email Id");
			} else if (password.isEmpty()) {
				mEdtPassword.setError("Enter Password");
			} else if (confirmPassword.isEmpty()) {
				mEdtConfirmPassword.setError("Enter Confirm Password");
			} else if (!password.equals(confirmPassword)) {
				Toast.makeText(getActivity(), "Password must be Same",
						Toast.LENGTH_SHORT).show();
				mEdtPassword.setText("");
				mEdtConfirmPassword.setText("");
			} else {
				// To do here
				// displaySignUpSuccessDialog();
				displaySignUpFailureDialog();

			}

			break;

		default:
			break;
		}
	}

	private void displaySignUpFailureDialog() {
		final Dialog dialog = new Dialog(getActivity());
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.getWindow().setBackgroundDrawable(
				new ColorDrawable(android.graphics.Color.TRANSPARENT));
		dialog.setContentView(R.layout.dialoge_failed_registration);
		dialog.setCancelable(false);

		TextView txtTitle = (TextView) dialog
				.findViewById(R.id.txtFailedRegTitle);
		TextView txt1 = (TextView) dialog.findViewById(R.id.txtFaiedReg1);// bold
		TextView txt2 = (TextView) dialog.findViewById(R.id.txtFaiedReg2);
		Button btnSignUpFailure = (Button) dialog
				.findViewById(R.id.btnSignUpFailDialog);

		txtTitle.setTypeface(Constant.getFontNormal(getActivity()));
		txt1.setTypeface(Constant.getFontSemiBold(getActivity()));
		txt2.setTypeface(Constant.getFontNormal(getActivity()));
		btnSignUpFailure.setTypeface(Constant.getFontSemiBold(getActivity()));

		btnSignUpFailure.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});

		btnSignUpFailure.setOnTouchListener(this);
		dialog.show();
	}

	private void displaySignUpSuccessDialog() {
		final Dialog dialog = new Dialog(getActivity());
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.getWindow().setBackgroundDrawable(
				new ColorDrawable(android.graphics.Color.TRANSPARENT));
		dialog.setContentView(R.layout.dialoge_successful_sign);
		dialog.setCancelable(false);

		TextView txtTitle = (TextView) dialog
				.findViewById(R.id.txtSuccessfulTitle);
		TextView txt1 = (TextView) dialog.findViewById(R.id.txtSuccessSign1);
		TextView txt2 = (TextView) dialog.findViewById(R.id.txtSuccessSign2);
		LinearLayout btnLayout = (LinearLayout) dialog
				.findViewById(R.id.btnSuccessfullSignUP);
		TextView txtBtn = (TextView) dialog.findViewById(R.id.txtBtnSign);

		txtTitle.setTypeface(Constant.getFontNormal(getActivity()));
		txt1.setTypeface(Constant.getFontSemiBold(getActivity()));
		txt2.setTypeface(Constant.getFontNormal(getActivity()));
		txtBtn.setTypeface(Constant.getFontSemiBold(getActivity()));
		btnLayout.setOnTouchListener(this);
		btnLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});

		dialog.show();
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

}
