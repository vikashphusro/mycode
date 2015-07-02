package com.condivision.rentongo.fragment;

import com.condivision.rentongo.R;
import com.condivision.rentongo.interfaces.OnMessageListner;
import com.condivision.rentongo.util.Constant;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Typeface;
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

public class PaymentSuccessFragment extends Fragment implements OnTouchListener {

	private static final String TAG = "PaymentSuccessFragment";
	private OnMessageListner mOnMessageListner;

	@Override
	public void onAttach(Activity activity) {// TODO Auto-generated method stub
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
		View view = inflater.inflate(R.layout.fragment_payment_success,
				container, false);

		TextView txt1 = (TextView) view
				.findViewById(R.id.txtPayment_success_Order_number);
		TextView txt2 = (TextView) view.findViewById(R.id.txtPaymentSuccess1);
		TextView txt3 = (TextView) view.findViewById(R.id.txtPaymentSuccess2);
		TextView txt4 = (TextView) view
				.findViewById(R.id.txtPaymentSuccessPhone);
		TextView txt5 = (TextView) view.findViewById(R.id.txtPaymentSuccess3);
		EditText edtPaymentSuccessPassword = (EditText) view
				.findViewById(R.id.edtPaymentSuccessPass);
		Button btnPaymentSuccess = (Button) view
				.findViewById(R.id.btnPaymentSuccessTrackOrder);
		Button btnPaymentAnotherTrans = (Button) view
				.findViewById(R.id.btnPaymentSuccessTrackOrder);

		txt1.setTypeface(Constant.getFontSemiBold(getActivity()));
		txt2.setTypeface(Constant.getFontNormal(getActivity()));
		txt3.setTypeface(Constant.getFontNormal(getActivity()));
		txt4.setTypeface(Constant.getFontSemiBold(getActivity()));
		txt5.setTypeface(Constant.getFontNormal(getActivity()));
		edtPaymentSuccessPassword.setTypeface(Constant
				.getFontNormal(getActivity()));
		btnPaymentSuccess.setTypeface(Constant.getFontSemiBold(getActivity()));
		btnPaymentAnotherTrans.setTypeface(Constant
				.getFontSemiBold(getActivity()));

		btnPaymentAnotherTrans.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});

		btnPaymentSuccess.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});

		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		mOnMessageListner.setTitle("Payment Success");
		mOnMessageListner.setEnableBackButton(false);
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

}
