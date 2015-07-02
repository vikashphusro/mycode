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
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This Class used to verify Booking i.e Bike Name, Date, Amount, Location and
 * Vendor Name.
 *
 */
public class BookingVerificationFragment extends Fragment implements
		OnClickListener, OnTouchListener {
	private static final String TAG = "BookingVerificationFragment";
	private OnMessageListner mOnMessageListner = null;
	private TextView mTxtBikeName, mTxtBikePerDayAmount, mTxtFromDate,
			mTxtToDate, mTxtNumberOfDayForRent, mTxtAmountToPay,
			mTxtAmountToPayAmount, mTxtPayebleAmount, mTxtPayebleAmountTotal;
	private TextView mTxtBookingVerification, mVendorLocationHeader;
	private TextView mTxtKm, mTxtVendorName, mTxtVendorSubAddress,
			mTxtVendorAddress, mTxtTermAndCondition, mTxtTermAndCondition1;
	private Button mBtnProceedToPayement;
	private CheckBox mChKTermAndcondition;
	private LinearLayout mLayoutAddress;
	

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
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		Log.d(TAG, "onCreateView");
		View view = inflater.inflate(R.layout.fragment_booking_verification, container, false);
		// Get View
		mTxtBikeName = (TextView) view
				.findViewById(R.id.txtBookingVerificationBikeName);
		mTxtBikePerDayAmount = (TextView) view
				.findViewById(R.id.txtBookingVerificationBikePricePerDay);
		mTxtFromDate = (TextView) view
				.findViewById(R.id.txtBookingConfirmationFromDate);
		mTxtToDate = (TextView) view
				.findViewById(R.id.txtBookingConfirmationToDate);
		mTxtNumberOfDayForRent = (TextView) view
				.findViewById(R.id.txtBookingNumberOfDays);
		mTxtAmountToPay = (TextView) view
				.findViewById(R.id.txtBookingVerificationAmountToPay);
		mTxtAmountToPayAmount = (TextView) view
				.findViewById(R.id.txtBookingVerificationAmout);
		mTxtPayebleAmount = (TextView) view
				.findViewById(R.id.txtBookingVerificatioPayableAmount);
		mTxtPayebleAmountTotal = (TextView) view
				.findViewById(R.id.txtBookingVerificationPayableAmountTotal);
		mTxtBookingVerification = (TextView) view
				.findViewById(R.id.txtBookingVerificationRefundable);
		mVendorLocationHeader = (TextView) view
				.findViewById(R.id.txtBookingVerification2);
		mTxtKm = (TextView) view.findViewById(R.id.txtBookingVerificationKM);
		mTxtVendorName = (TextView) view
				.findViewById(R.id.txtBookingVerificationBikeVendorName);
		mTxtVendorSubAddress = (TextView) view
				.findViewById(R.id.txtBookingVerificationBikeVendorPlace);
		mTxtVendorAddress = (TextView) view
				.findViewById(R.id.txtBookingVerificationBikeVendorCity);
		mTxtTermAndCondition = (TextView) view
				.findViewById(R.id.txtBookingVerificationTerm);
		mTxtTermAndCondition1 = (TextView)view.findViewById(R.id.txtBookingVerificationTerm2);
		mChKTermAndcondition = (CheckBox) view
				.findViewById(R.id.chkBookingVerificationTerm);
		mBtnProceedToPayement = (Button) view
				.findViewById(R.id.btnBookingVerification);
		mLayoutAddress = (LinearLayout) view
				.findViewById(R.id.linLayoutBookingVerificationAddress);

		// Set Font in View
		mTxtBikeName.setTypeface(Constant.getFontSemiBold(getActivity()));
		mTxtBikePerDayAmount.setTypeface(Constant.getFontNormal(getActivity()));
		mTxtFromDate.setTypeface(Constant.getFontSemiBold(getActivity()));
		mTxtToDate.setTypeface(Constant.getFontSemiBold(getActivity()));
		mTxtNumberOfDayForRent.setTypeface(Constant
				.getFontNormal(getActivity()));
		mTxtAmountToPay.setTypeface(Constant.getFontSemiBold(getActivity()));
		mTxtAmountToPayAmount
				.setTypeface(Constant.getFontNormal(getActivity()));
		mTxtPayebleAmount.setTypeface(Constant.getFontSemiBold(getActivity()));
		mTxtPayebleAmountTotal.setTypeface(Constant
				.getFontSemiBold(getActivity()));
		mTxtBookingVerification.setTypeface(Constant
				.getFontNormal(getActivity()));
		mVendorLocationHeader
				.setTypeface(Constant.getFontNormal(getActivity()));
		mTxtKm.setTypeface(Constant.getFontNormal(getActivity()));
		mTxtVendorName.setTypeface(Constant.getFontSemiBold(getActivity()));
		mTxtVendorSubAddress.setTypeface(Constant.getFontNormal(getActivity()));
		mTxtVendorAddress.setTypeface(Constant.getFontNormal(getActivity()));
		mTxtTermAndCondition.setTypeface(Constant.getFontNormal(getActivity()));
		mTxtTermAndCondition1.setTypeface(Constant.getFontNormal(getActivity()));
		mBtnProceedToPayement.setTypeface(Constant
				.getFontSemiBold(getActivity()));
		// Set Listener
		mChKTermAndcondition.setOnClickListener(this);
		mBtnProceedToPayement.setOnClickListener(this);
		mBtnProceedToPayement.setOnTouchListener(this);
		mLayoutAddress.setOnClickListener(this);
		mTxtTermAndCondition1.setOnClickListener(this);

		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		mOnMessageListner.setEnableBackButton(true);
		mOnMessageListner.setEnableFilterPanel(false);
		mOnMessageListner.setEnableProfileButton(true);
		mOnMessageListner.setTitle(getActivity().getResources().getString(
				R.string.booking_verification));
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.txtBookingVerificationTerm2:
			Log.d(TAG, "Term and Condition");
			
		
		break;
		case R.id.chkBookingVerificationTerm:
			Log.d(TAG, "Checkbox Booking Verification");

			break;
		case R.id.linLayoutBookingVerificationAddress:
			Fragment mapFragment = new MapViewFragment();
			FragmentManager mapFragmentManager = getActivity()
					.getFragmentManager();
			FragmentTransaction mapFragmentTransaction = mapFragmentManager
					.beginTransaction();
			mapFragmentTransaction.replace(R.id.content_frame, mapFragment);
			mapFragmentTransaction.addToBackStack(null);
			mapFragmentTransaction.commit();
			break;

		case R.id.btnBookingVerification:
			Log.d(TAG, "Button Booking Verification");
			if (!mChKTermAndcondition.isChecked()) {
				Toast.makeText(getActivity(),
						"Please accept term and condition.", Toast.LENGTH_SHORT)
						.show();
			} else {
				Fragment fragment = new UserDetailsFragment();
				// Fragment fragment = new MapViewFragment();
				FragmentManager fragmentManager = getActivity()
						.getFragmentManager();
				FragmentTransaction fragmentTransaction = fragmentManager
						.beginTransaction();
				fragmentTransaction.replace(R.id.content_frame, fragment);
				fragmentTransaction.addToBackStack(null);
				fragmentTransaction.commit();

			}

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
		}
		return false;
	}

}
