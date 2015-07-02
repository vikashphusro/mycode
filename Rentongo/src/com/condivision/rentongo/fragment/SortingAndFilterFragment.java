package com.condivision.rentongo.fragment;

import com.condivision.rentongo.R;
import com.condivision.rentongo.customviews.RangeSeekBar;
import com.condivision.rentongo.customviews.RangeSeekBar.OnRangeSeekBarChangeListener;
import com.condivision.rentongo.interfaces.OnMessageListner;
import com.condivision.rentongo.util.Constant;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class SortingAndFilterFragment extends Fragment implements
		OnClickListener, OnTouchListener {

	private static final String TAG = "SortingAndFilter";
	private static final int RENT_MIN_VALUE = 100;
	private static final int RENT_MAX_VALUE = 9999;
	private static final int DISTANCE_MIN_VALUE = 0;
	private static final int DISTANCE_MAX_VALUE = 100;
	private static final SortingAndFilterFragment mSortingAndFilter = new SortingAndFilterFragment();
	private RangeSeekBar<Integer> mRangeSeekBar;
	private TextView mTxtMinRent, mTxtMaxRent, mTxtMinDistanse,
			mTxtMaxDistanse;
	private EditText mEdtDistanse, mEdtMinRent, mEdtMaxRent;
	private OnMessageListner mOnMessageListner;
	private Button mBtnPrice, mBtnProximity, mBtnApplyFilter;
	private TextView mTxtSelectBrand, mTxtSelectEngine;
	private RelativeLayout mLayoutBrand, mLayoutEngine;
	private SeekBar mSeekBarDistance;

	private SortingAndFilterFragment() {

	}

	public static SortingAndFilterFragment getInstance() {
		return mSortingAndFilter;
	}

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
		Log.d(TAG, "onCreate");
		mRangeSeekBar = new RangeSeekBar<Integer>(RENT_MIN_VALUE,
				RENT_MAX_VALUE, getActivity());
	}

	@SuppressLint("NewApi")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		Log.d(TAG, "onCreateView");
		
		View view = inflater.inflate(R.layout.fragment_sorting_and_searching,
				container, false);
		TextView mText1 = (TextView) view.findViewById(R.id.txtSorting1);
		TextView mText2 = (TextView) view.findViewById(R.id.txtSorting2);
		TextView mText3 = (TextView) view.findViewById(R.id.txtSorting3);
		TextView mText4 = (TextView) view.findViewById(R.id.txtSorting4);
		TextView mText5 = (TextView) view.findViewById(R.id.txtSorting5);
		TextView mText6 = (TextView) view.findViewById(R.id.txtSorting6);
		TextView mText7 = (TextView) view.findViewById(R.id.txtSorting7);
		TextView mText8 = (TextView) view.findViewById(R.id.txtSorting8);
		TextView mText9 = (TextView) view.findViewById(R.id.txtSorting9);
		mSeekBarDistance = (SeekBar) view
				.findViewById(R.id.seekSortingDistance);

		mText1.setTypeface(Constant.getFontSemiBold(getActivity()));
		mText2.setTypeface(Constant.getFontSemiBold(getActivity()));
		mText3.setTypeface(Constant.getFontSemiBold(getActivity()));
		mText4.setTypeface(Constant.getFontNormal(getActivity()));
		mText5.setTypeface(Constant.getFontSemiBold(getActivity()));
		mText6.setTypeface(Constant.getFontNormal(getActivity()));
		mText7.setTypeface(Constant.getFontNormal(getActivity()));
		mText8.setTypeface(Constant.getFontSemiBold(getActivity()));
		mText9.setTypeface(Constant.getFontSemiBold(getActivity()));
		mEdtDistanse = (EditText) view.findViewById(R.id.edtSortingEnterKm);
		mEdtDistanse.setTypeface(Constant.getFontNormal(getActivity()));
		mEdtMinRent = (EditText) view
				.findViewById(R.id.edtSortingEnterMinPrice);
		mEdtMinRent.setTypeface(Constant.getFontNormal(getActivity()));
		mEdtMaxRent = (EditText) view
				.findViewById(R.id.edtSortingEnterMaxPrice);
		mEdtMaxRent.setTypeface(Constant.getFontNormal(getActivity()));
		mTxtMinDistanse = (TextView) view.findViewById(R.id.txtDistanceMinKm);
		mTxtMinDistanse.setTypeface(Constant.getFontNormal(getActivity()));
		mTxtMaxDistanse = (TextView) view.findViewById(R.id.txtDistanceMAxKm);
		mTxtMaxDistanse.setTypeface(Constant.getFontNormal(getActivity()));
		mTxtMinRent = (TextView) view.findViewById(R.id.txtPriceMin);
		mTxtMinRent.setTypeface(Constant.getFontNormal(getActivity()));
		mTxtMaxRent = (TextView) view.findViewById(R.id.txtPriceMax);
		mTxtMaxRent.setTypeface(Constant.getFontNormal(getActivity()));

		mBtnPrice = (Button) view.findViewById(R.id.btnSortingPrice);
		mBtnPrice.setTypeface(Constant.getFontSemiBold(getActivity()));
		mBtnProximity = (Button) view.findViewById(R.id.btnSortingProximity);
		mBtnProximity.setTypeface(Constant.getFontSemiBold(getActivity()));
		mBtnApplyFilter = (Button) view.findViewById(R.id.btnFilter);
		mBtnApplyFilter.setTypeface(Constant.getFontSemiBold(getActivity()));
		mTxtSelectBrand = (TextView) view.findViewById(R.id.txtSelectBrand);
		mTxtSelectBrand.setTypeface(Constant.getFontNormal(getActivity()));
		mTxtSelectEngine = (TextView) view.findViewById(R.id.txtSelectEngine);
		mTxtSelectEngine.setTypeface(Constant.getFontNormal(getActivity()));
		mLayoutBrand = (RelativeLayout) view
				.findViewById(R.id.relSortingSpinnerBrand);
		mLayoutEngine = (RelativeLayout) view
				.findViewById(R.id.relSortingSpinnerEngineCapacity);
		mBtnApplyFilter.setOnClickListener(this);
		mBtnPrice.setOnClickListener(this);
		mBtnProximity.setOnClickListener(this);
		mLayoutBrand.setOnClickListener(this);
		mLayoutEngine.setOnClickListener(this);

		mBtnApplyFilter.setOnTouchListener(this);
		mBtnPrice.setOnTouchListener(this);
		mBtnProximity.setOnTouchListener(this);
		mLayoutBrand.setOnTouchListener(this);
		mLayoutEngine.setOnTouchListener(this);

		LinearLayout rangeSeekBarLayout = (LinearLayout) view
				.findViewById(R.id.LinLayoutForRangeSeekBar);

		rangeSeekBarLayout.addView(mRangeSeekBar);

		mTxtMinRent.setText(""
				+ String.valueOf(mRangeSeekBar.getAbsoluteMinValue()));
		mTxtMaxRent.setText(""
				+ String.valueOf(mRangeSeekBar.getAbsoluteMaxValue()));
		mRangeSeekBar.setBackgroundColor(getActivity().getResources().getColor(
				R.color.notification_baf_color));

		mRangeSeekBar
				.setOnRangeSeekBarChangeListener(new OnRangeSeekBarChangeListener<Integer>() {

					@Override
					public void onRangeSeekBarValuesChanged(
							RangeSeekBar<?> bar, Integer minValue,
							Integer maxValue) {
						mTxtMinRent.setText("" + String.valueOf(minValue));

						mTxtMaxRent.setText("" + String.valueOf(maxValue));

					}

				});

		mSeekBarDistance.setMax(DISTANCE_MAX_VALUE);

		// Listner for Distance Seek bar.
		mSeekBarDistance
				.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
					int progressValue = 0;

					@Override
					public void onStopTrackingTouch(SeekBar seekBar) {

					}

					@Override
					public void onStartTrackingTouch(SeekBar seekBar) {

					}

					@Override
					public void onProgressChanged(SeekBar seekBar,
							int progress, boolean fromUser) {

						mTxtMaxDistanse.setText("" + progress + " " + "KM");

					}
				});
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Log.d(TAG, "onActivityCreated");
	}

	@Override
	public void onStart() {
		super.onStart();
		Log.d(TAG, "onStart");
	}

	@Override
	public void onResume() {
		super.onResume();
		mOnMessageListner.setEnableBackButton(false);
		mOnMessageListner.setEnableProfileButton(true);
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onStop() {
		super.onStop();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onDetach() {
		super.onDetach();
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
		case R.id.btnFilter:
			Log.d(TAG, "filter button is click");
			mOnMessageListner.closeOpenDrawer(true);
			break;

		default:
			break;
		}

	}

}
