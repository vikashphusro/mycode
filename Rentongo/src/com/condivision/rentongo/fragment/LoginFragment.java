package com.condivision.rentongo.fragment;

import com.condivision.rentongo.R;
import com.condivision.rentongo.interfaces.OnMessageListner;
import com.condivision.rentongo.util.Constant;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginFragment extends Fragment implements OnClickListener,
		OnTouchListener {
	private static final String TAG = "LoginFragment";
	private EditText mEdtPhoneNumber, mEdtPassword;
	private TextView mTxtForgotPassword, mTxtSignUp;
	private Button mLogIn;
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
		View view = inflater.inflate(R.layout.fragment_login, container, false);

		TextView txt1 = (TextView) view.findViewById(R.id.txtLogin1);
		TextView txt2 = (TextView) view.findViewById(R.id.txtLogin2);
		mEdtPassword = (EditText) view.findViewById(R.id.edtLoginPassword);
		mEdtPhoneNumber = (EditText) view
				.findViewById(R.id.edtLoginPhoneNumber);
		mLogIn = (Button) view.findViewById(R.id.btnLogin);
		mTxtForgotPassword = (TextView) view
				.findViewById(R.id.txtLoginForgotPass);
		mTxtSignUp = (TextView) view.findViewById(R.id.txtLoginSignUp);

		txt1.setTypeface(Constant.getFontNormal(getActivity()));
		txt2.setTypeface(Constant.getFontSemiBold(getActivity()));
		mEdtPassword.setTypeface(Constant.getFontNormal(getActivity()));
		mEdtPhoneNumber.setTypeface(Constant.getFontNormal(getActivity()));
		mLogIn.setTypeface(Constant.getFontSemiBold(getActivity()));
		mTxtForgotPassword.setTypeface(Constant.getFontSemiBold(getActivity()));
		mTxtSignUp.setTypeface(Constant.getFontSemiBold(getActivity()));

		mLogIn.setOnClickListener(this);
		mTxtForgotPassword.setOnClickListener(this);
		mTxtSignUp.setOnClickListener(this);
		mLogIn.setOnTouchListener(this);

		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		mOnMessageListner.setEnableBackButton(true);
		mOnMessageListner.setEnableFilterPanel(false);
		mOnMessageListner.setTitle("Login");
		mOnMessageListner.setEnableProfileButton(false);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnLogin:
			Log.d(TAG, "Login");
			String PhoneNumber = mEdtPhoneNumber.getText().toString().trim();
			String password = mEdtPassword.getText().toString().trim();
			if (!PhoneNumber.isEmpty()
					&& Constant.validatePhoneNumber(PhoneNumber)) {

				if (!password.isEmpty()) {

				} else {
					Toast.makeText(getActivity(), "Plaese Enter Password",
							Toast.LENGTH_SHORT).show();

				}

			} else {
				Toast.makeText(getActivity(),
						"Plaese Enter Valid Phone Number", Toast.LENGTH_SHORT)
						.show();
			}

			break;
		case R.id.txtLoginForgotPass:
			Log.d(TAG, "Forgot Password");
			displayForgotPasswordDialog();

			break;
		case R.id.txtLoginSignUp:
			Log.d(TAG, "Login SignUp");
			Fragment fragment = new SignUpFragment();
			FragmentManager fragmentManager = getActivity()
					.getFragmentManager();
			FragmentTransaction fragmentTransaction = fragmentManager
					.beginTransaction();
			fragmentTransaction.replace(R.id.content_frame, fragment);
			fragmentTransaction.addToBackStack(null);
			fragmentTransaction.commit();
			break;

		default:
			break;
		}
	}

	private void displayForgotPasswordDialog() {
		final Dialog dialog = new Dialog(getActivity());
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.getWindow().setBackgroundDrawable(
				new ColorDrawable(android.graphics.Color.TRANSPARENT));
		dialog.setContentView(R.layout.dialoge_forgot_password);
		dialog.setCancelable(false);

		TextView txtTitle = (TextView) dialog
				.findViewById(R.id.txtForgotPasswordTitle);
		TextView txtForgotPass = (TextView) dialog
				.findViewById(R.id.txtForgotPass);
		final EditText edtPhoneNumber = (EditText) dialog
				.findViewById(R.id.edtForgotPassPhoneNumber);
		Button btnResetPass = (Button) dialog
				.findViewById(R.id.btnForgotPasswordResetPass);
		Button btnCancle = (Button) dialog
				.findViewById(R.id.btnForgotPasswordCancle);

		txtTitle.setTypeface(Constant.getFontNormal(getActivity()));
		txtForgotPass.setTypeface(Constant.getFontNormal(getActivity()));
		edtPhoneNumber.setTypeface(Constant.getFontNormal(getActivity()));
		//edtPhoneNumber.setSelection(edtPhoneNumber.getHint().length());
		btnResetPass.setTypeface(Constant.getFontSemiBold(getActivity()));
		btnCancle.setTypeface(Constant.getFontSemiBold(getActivity()));
		btnResetPass.setOnTouchListener(this);
		btnCancle.setOnTouchListener(this);

		btnResetPass.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String phoneNum = edtPhoneNumber.getText().toString().trim();
				if (!phoneNum.isEmpty()
						&& Constant.validatePhoneNumber(phoneNum)) {

				} else {
					Toast.makeText(getActivity(),
							"Please Enter Valid Phone Number",
							Toast.LENGTH_SHORT).show();
				}

			}
		});

		btnCancle.setOnClickListener(new OnClickListener() {

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
