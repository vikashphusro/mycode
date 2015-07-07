package com.condivision.rentongo.fragment;

import com.condivision.rentongo.R;
import com.condivision.rentongo.https.bins.LocationBin;
import com.condivision.rentongo.interfaces.OnMessageListner;
import com.condivision.rentongo.util.Constant;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This Class to show data for searching.
 * 
 * @author rentongo
 *
 */
public class SearchingFragment extends Fragment {
	private static final String TAG = "SearchingFragment";
	private OnMessageListner mOnMessageListner;
	LocationBin mLocationBin;

	public SearchingFragment(LocationBin locationBin) {
		Log.d(TAG, "Searching fragment constructor");
		this.mLocationBin = locationBin;
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
		View view = inflater.inflate(R.layout.fragment_searching, container,
				false);
		// Get View
		TextView txtView1 = (TextView) view.findViewById(R.id.txtSearching1);
		TextView txtView2 = (TextView) view.findViewById(R.id.txtSearching2);
		EditText edtLocation = (EditText) view
				.findViewById(R.id.edtSearchingLocation);
		TextView txtFrom = (TextView) view.findViewById(R.id.txtSearchingFrom);
		TextView txtTo = (TextView) view.findViewById(R.id.txtSearchingTo);
		TextView txtDayFrom = (TextView) view
				.findViewById(R.id.txtDaySearchingFrom);
		TextView txtDayTo = (TextView) view
				.findViewById(R.id.txtDaySearchingTo);
		TextView txtMonthAndYearFrom = (TextView) view
				.findViewById(R.id.txtSearchingDateAndYearFrom);
		TextView txtMonthAndYearTo = (TextView) view
				.findViewById(R.id.txtSearchingDateAndYearTo);

		edtLocation.setText("" + mLocationBin.getLocation());
		int dayFromCount = mLocationBin.getDayFrom();
		int dayToCount = mLocationBin.getDayTo();
		txtDayFrom.setText("" + Constant.getDay(dayFromCount));
		txtMonthAndYearFrom.setText(" "
				+ Constant.getMonth(mLocationBin.getMonthFrom()) + " "
				+ mLocationBin.getDateFrom() + ", "
				+ mLocationBin.getYearFrom());
		txtDayTo.setText("" + Constant.getDay(dayToCount));
		txtMonthAndYearTo.setText(" "
				+ Constant.getMonth(mLocationBin.getMonthTo()) + " "
				+ mLocationBin.getDateTo() + ", " + mLocationBin.getYearTo());

		// Set Font
		txtView1.setTypeface(Constant.getFontNormal(getActivity()));
		txtView2.setTypeface(Constant.getFontNormal(getActivity()));
		edtLocation.setTypeface(Constant.getFontSemiBold(getActivity()));
		txtFrom.setTypeface(Constant.getFontSemiBold(getActivity()));
		txtTo.setTypeface(Constant.getFontSemiBold(getActivity()));
		txtDayFrom.setTypeface(Constant.getFontNormal(getActivity()));
		txtDayTo.setTypeface(Constant.getFontNormal(getActivity()));
		txtMonthAndYearFrom
				.setTypeface(Constant.getFontSemiBold(getActivity()));
		txtMonthAndYearTo.setTypeface(Constant.getFontSemiBold(getActivity()));

		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.d(TAG, "onResume");
		mOnMessageListner.setTitle("Searching...");
		mOnMessageListner.setEnableFilterPanel(false);
		mOnMessageListner.setEnableBackButton(false);
		mOnMessageListner.setEnableProfileButton(true);
		
		// call search result fragment
		Fragment fragment = new SearchResultFragment();
		FragmentManager fragmentManager = getActivity()
				.getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		fragmentTransaction.replace(R.id.content_frame, fragment);
		//fragmentTransaction.addToBackStack(null);
		fragmentTransaction.commit();

	}
}
