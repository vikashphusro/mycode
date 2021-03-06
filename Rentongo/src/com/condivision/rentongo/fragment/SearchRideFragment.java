package com.condivision.rentongo.fragment;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.PorterDuff.Mode;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.condivision.rentongo.R;
import com.condivision.rentongo.https.bins.LocationBin;
import com.condivision.rentongo.interfaces.OnMessageListner;
import com.condivision.rentongo.util.AppPreferences;
import com.condivision.rentongo.util.Constant;

/**
 * This Class used to search location, date selection and byke selection.
 * 
 * @author rentongo
 *
 */
public class SearchRideFragment extends Fragment implements OnClickListener,
		OnTouchListener {

	private static final String TAG = "SearchRideFragment";
	private static final SearchRideFragment mSearchRideFragment = new SearchRideFragment();
	// private Typeface fontSemiBold = null, fontNormal = null;
	private OnMessageListner mOnMessageListner = null;
	private EditText edtLocation;
	private DatePickerDialog mDatePickerDialogFrom, mDatePickerDialogTo;
	private Calendar mCalendar;
	private int mDayFrom, mDayTo, mCurrentDay;
	private int mDateFrom, mMonthFrom, mYearFrom, mDateTo, mMonthTo, mYearTo,
			mCurrentDate, mCurrentMonth, mCurrentYear;
	private Switch mSwtMotorBikes, mSwtScooter, mSwtBicycle;
	private Calendar mCalendraFrom, mCalendraTo;
	private TextView txtDayFrom, txtDayTo, txtMonthAndDateFrom,
			txtMonthAndDateTo;

	private SearchRideFragment() {
		Log.d(TAG, "private Constructor");

	}

	public static SearchRideFragment getInstance() {
		return mSearchRideFragment;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		Log.d(TAG, "onAttach");
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
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate");

		// default calendra
		mCalendar = Calendar.getInstance();

		mCurrentYear = mCalendar.get(Calendar.YEAR);
		mCurrentMonth = mCalendar.get(Calendar.MONTH);
		mCurrentDate = mCalendar.get(Calendar.DAY_OF_MONTH);

		mCalendraFrom = Calendar.getInstance();
		mCalendraFrom.add(Calendar.DAY_OF_MONTH, 1);
		mDateFrom = mCalendraFrom.get(Calendar.DATE);
		mDayFrom = mCalendraFrom.get(Calendar.DAY_OF_WEEK);
		mMonthFrom = mCalendraFrom.get(Calendar.MONTH);
		mYearFrom = mCalendraFrom.get(Calendar.YEAR);

		mCalendraTo = Calendar.getInstance();
		mCalendraTo.add(Calendar.DAY_OF_MONTH, 2);
		mDateTo = mCalendraTo.get(Calendar.DATE);
		mDayTo = mCalendraTo.get(Calendar.DAY_OF_WEEK);
		mMonthTo = mCalendraTo.get(Calendar.MONTH);
		mYearTo = mCalendraTo.get(Calendar.YEAR);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.d(TAG, "onCreateView");
		super.onCreateView(inflater, container, savedInstanceState);

		View view = inflater.inflate(R.layout.fragment_search_ride, container,
				false);
		// Get view
		TextView txtLoaction = (TextView) view
				.findViewById(R.id.txtSearchYourLocation);
		TextView txtFrom = (TextView) view.findViewById(R.id.txtSearchFrom);
		TextView txtTo = (TextView) view.findViewById(R.id.txtSearchTo);
		edtLocation = (EditText) view.findViewById(R.id.edtSearchLocation);
		RelativeLayout fromDate = (RelativeLayout) view
				.findViewById(R.id.relLtFrom);
		RelativeLayout toDate = (RelativeLayout) view
				.findViewById(R.id.relLtTo);
		txtDayFrom = (TextView) view.findViewById(R.id.txtSearchDayFrom);
		txtDayTo = (TextView) view.findViewById(R.id.txtSearchDayTo);
		txtMonthAndDateFrom = (TextView) view
				.findViewById(R.id.txtSearchMonthAndDateFrom);
		txtMonthAndDateTo = (TextView) view
				.findViewById(R.id.txtSearchMonthAndDateTo);
		mSwtMotorBikes = (Switch) view.findViewById(R.id.tglMotorbikes);
		mSwtScooter = (Switch) view.findViewById(R.id.tglSccoters);
		mSwtBicycle = (Switch) view.findViewById(R.id.tglBicycles);
		TextView txtMotorCycle = (TextView) view
				.findViewById(R.id.txtMotorcycles);
		TextView txtScooter = (TextView) view.findViewById(R.id.txtSccoters);
		TextView txtBicycle = (TextView) view.findViewById(R.id.txtBicycles);
		Button btnLetRide = (Button) view.findViewById(R.id.btnLetsRide);

		// Set Font to view
		txtLoaction.setTypeface(Constant.getFontSemiBold(getActivity()));
		txtFrom.setTypeface(Constant.getFontSemiBold(getActivity()));
		txtTo.setTypeface(Constant.getFontSemiBold(getActivity()));
		edtLocation.setTypeface(Constant.getFontSemiBold(getActivity()));
		txtDayFrom.setTypeface(Constant.getFontNormal(getActivity()));
		txtDayTo.setTypeface(Constant.getFontNormal(getActivity()));
		txtMonthAndDateFrom
				.setTypeface(Constant.getFontSemiBold(getActivity()));
		txtMonthAndDateTo.setTypeface(Constant.getFontSemiBold(getActivity()));
		txtBicycle.setTypeface(Constant.getFontSemiBold(getActivity()));
		txtMotorCycle.setTypeface(Constant.getFontSemiBold(getActivity()));
		txtScooter.setTypeface(Constant.getFontSemiBold(getActivity()));
		btnLetRide.setTypeface(Constant.getFontSemiBold(getActivity()));

		// Set listener to view
		fromDate.setOnClickListener(this);
		toDate.setOnClickListener(this);
		btnLetRide.setOnClickListener(this);
		// set touch listener to view
		btnLetRide.setOnTouchListener(this);
		edtLocation.setOnTouchListener(this);
		edtLocation.setCursorVisible(true);
		return view;

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Log.d(TAG, "onActivityCreated");
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.d(TAG, "onResume");
		mOnMessageListner.setTitle("Search Ride");
		txtMonthAndDateFrom.setText("" + Constant.getMonth(mMonthFrom) + " "
				+ mDateFrom);
		txtDayFrom.setText("" + Constant.getDay(mDayFrom));

		txtMonthAndDateTo.setText("" + Constant.getMonth(mMonthTo) + " "
				+ mDateTo);

		txtDayTo.setText("" + Constant.getDay(mDayTo));
		mOnMessageListner.setEnableFilterPanel(false);
		mOnMessageListner.setEnableBackButton(false);
		mOnMessageListner.setEnableProfileButton(true);

	}

	@Override
	public void onClick(View v) {
		Log.d(TAG, "onClick");
		switch (v.getId()) {
		case R.id.btnLetsRide:
			// if drawer is open then close it.
			// mOnMessageListner.closeOpenDrawer(true);

			if (mSwtMotorBikes.isChecked()) {
				Log.d(TAG, "MotorCycle is checked");
				AppPreferences.getInstance(v.getContext()).setMotorcycleStatus(
						true);
			} else {
				AppPreferences.getInstance(v.getContext()).setMotorcycleStatus(
						false);
			}
			if (mSwtScooter.isChecked()) {
				Log.d(TAG, "Sccoter is checked");
				AppPreferences.getInstance(v.getContext()).setScooterStatus(
						true);
			} else {
				AppPreferences.getInstance(v.getContext()).setScooterStatus(
						false);
			}
			if (mSwtBicycle.isChecked()) {
				Log.d(TAG, "Bicycle is checked");
				AppPreferences.getInstance(v.getContext()).setBycycleStatus(
						true);
			} else {
				AppPreferences.getInstance(v.getContext()).setBycycleStatus(
						false);
			}

			if (!AppPreferences.getInstance(v.getContext())
					.getMotorCycleStatus()
					&& !AppPreferences.getInstance(v.getContext())
							.getScooterStatus()
					&& !AppPreferences.getInstance(v.getContext())
							.getBicycleStatus()) {
				Toast.makeText(v.getContext(), "Please Select One Item",
						Toast.LENGTH_SHORT).show();
			} else if (edtLocation.getText().toString().isEmpty()) {
				edtLocation.setError("Enter Your Location");
			} else {
				// Set the value in LocationBin
				LocationBin locationBin = new LocationBin();
				locationBin.setLocation(edtLocation.getText().toString());
				locationBin.setDateFrom(mDateFrom);
				locationBin.setDayFrom(mDayFrom);
				locationBin.setMonthFrom(mMonthFrom);
				locationBin.setYearFrom(mYearFrom);
				locationBin.setDateTo(mDateTo);
				locationBin.setDayTo(mDayTo);
				locationBin.setMonthTo(mMonthTo);
				locationBin.setYearTo(mYearTo);

				Fragment fragment = new SearchingFragment(locationBin);
				// Fragment fragment = new MapViewFragment();
				FragmentManager fragmentManager = getActivity()
						.getFragmentManager();
				FragmentTransaction fragmentTransaction = fragmentManager
						.beginTransaction();
				// fragmentTransaction.add(fragment, "searchride");
				// fragmentTransaction.addToBackStack(null);
				fragmentTransaction.replace(R.id.content_frame, fragment);
				fragmentTransaction.commit();
			}

			break;
		case R.id.relLtFrom:
			Log.d(TAG, "click date from");
			showDatePickerDialogFrom(v);
			break;
		case R.id.relLtTo:
			Log.d(TAG, "click date to");
			showDatePickerDialogTo(v);
			break;
		default:
			break;
		}
	}

	public void showDatePickerDialogFrom(View v) {
		DialogFragment newFragment2 = new DatePickerFragmentFrom();
		newFragment2.show(getActivity().getFragmentManager(), "datePicker");
	}

	public void showDatePickerDialogTo(View v) {
		DialogFragment newFragment1 = new DatePickerFragmentTo();
		newFragment1.show(getActivity().getFragmentManager(), "datePicker");
	}

	public class DatePickerFragmentFrom extends DialogFragment implements
			DatePickerDialog.OnDateSetListener {

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {

			return new DatePickerDialog(getActivity(), this, mYearFrom,
					mMonthFrom, mDateFrom);
		}

		public void onDateSet(DatePicker view, int year, int month, int day) {
			System.out.println("year: " + year + "month: " + month + "day: "
					+ day);

			Calendar calendar = Calendar.getInstance();
			calendar.set(year, month, day);
			if (mCalendraFrom.getTime().after(calendar.getTime())
					|| mCalendraFrom.getTime().equals(calendar.getTime())) {
				Toast.makeText(getActivity(), "Please Select a Valid Date",
						Toast.LENGTH_SHORT).show();
			} else {
				txtMonthAndDateFrom.setText("" + Constant.getMonth(month) + " "
						+ day);
				txtDayFrom.setText(""
						+ Constant.getDay(calendar.get(Calendar.DAY_OF_WEEK)));
				mYearFrom = year;
				mMonthFrom = month;
				mDateFrom = day;
				mCalendraFrom = calendar;
			}

		}
	}

	public class DatePickerFragmentTo extends DialogFragment implements
			DatePickerDialog.OnDateSetListener {

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {

			return new DatePickerDialog(getActivity(), this, mYearTo, mMonthTo,
					mDateTo);
		}

		public void onDateSet(DatePicker view, int year, int month, int day) {
			view.setBackgroundColor(getActivity().getResources().getColor(
					R.color.notification_baf_color));
			System.out.println("year: " + year + "month: " + month + "day: "
					+ day);

			Calendar calendar = Calendar.getInstance();
			calendar.set(year, month, day);

			if (calendar.getTime().before(mCalendraFrom.getTime())
					|| calendar.getTime().before(calendar.getTime())) {
				Toast.makeText(getActivity(), "Please Select a Valid Date",
						Toast.LENGTH_SHORT).show();

			} else {
				txtMonthAndDateTo.setText("" + Constant.getMonth(month) + " "
						+ day);

				txtDayTo.setText(""
						+ Constant.getDay(calendar.get(Calendar.DAY_OF_WEEK)));
				mYearTo = year;
				mMonthTo = month;
				mDateTo = day;
				mCalendraTo = calendar;
			}

		}

	}

	public void setAddress(String address) {
		Log.d(TAG, "set address if it null then throw exception");
		if (address != null) {
			if (!address.isEmpty() && edtLocation != null) {
				edtLocation.setText("" + address);
				edtLocation.setSelection(edtLocation.getText().length());
				edtLocation.setCursorVisible(true);
			}

		} else {
			throw new NullPointerException("Value of address : "
					+ String.valueOf(address));
		}
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		Log.d(TAG, "onTouch");
		switch (v.getId()) {
		case R.id.edtSearchLocation:
			v.setFocusable(true);
			v.setFocusableInTouchMode(true);
			break;
		case R.id.btnLetsRide:
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
			break;
		default:
			break;
		}

		return false;
	}
}
