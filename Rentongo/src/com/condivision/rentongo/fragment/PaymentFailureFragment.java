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
import android.widget.TextView;

public class PaymentFailureFragment extends Fragment implements OnTouchListener {

	private static final String TAG = "PaymentFailureFragment";
	private Button mBtnRetryTransection;
	private TextView mTxt;
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
		View view = inflater.inflate(R.layout.fragment_payment_failure,
				container, false);

		mBtnRetryTransection = (Button) view
				.findViewById(R.id.btnFailureTransection);
		mTxt = (TextView) view.findViewById(R.id.txtPaymentFail);
		mTxt.setTypeface(Constant.getFontSemiBold(getActivity()));
		mBtnRetryTransection.setTypeface(Constant
				.getFontSemiBold(getActivity()));

		mBtnRetryTransection.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});

		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		mOnMessageListner.setTitle("Payment Failure");
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
