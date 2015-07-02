package com.condivision.rentongo.fragment;

import com.condivision.rentongo.R;
import com.condivision.rentongo.interfaces.OnMessageListner;
import com.condivision.rentongo.util.Constant;
import com.nostra13.universalimageloader.core.ImageLoader;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * This class used to describe Bike Details.
 * 
 * @author rentongo
 *
 */
public class BikeDetailsFragment extends Fragment implements OnClickListener, OnTouchListener {
	private static final String TAG = "BikeDetailsFragment";
	private OnMessageListner mOnMessageListner = null;
	private TextView mBikeName, mVendorName, mKm, mViewOnMap, mRentalCharges,
			mSecurityDeposite, mVendorReview, mRentalChargesAmount,
			mSecurityDepositAmount, mBikeDetailsSummary;
	private RatingBar mVendorReviewRating;
	private Button mBookMyRide;
	private TextView mProductDes, mTermAndCondition;
	private LinearLayout mLinearLayoutClickForMapView;
	private boolean isProductDesEnable = true, isTermAndConditionEnable = true;
	private ImageView mBikeImage;

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
		View view = inflater.inflate(R.layout.fragment_bike_details, container,
				false);
		// get View
		mBikeName = (TextView) view.findViewById(R.id.txtBikeDatilsBikeName);
		mBikeImage=(ImageView)view.findViewById(R.id.imgBikeDetailsSlide);
		mVendorName = (TextView) view.findViewById(R.id.txtBikeVendorDetails);
		mKm = (TextView) view.findViewById(R.id.txtBikeDatilsKM);
		mViewOnMap = (TextView) view.findViewById(R.id.txtBikeVendorDetailsAdd);
		mRentalCharges = (TextView) view
				.findViewById(R.id.txtBikeDetailsRentalCharge);
		mRentalChargesAmount = (TextView) view
				.findViewById(R.id.txtBikeDetailsRentalChargeAmount);
		mSecurityDeposite = (TextView) view
				.findViewById(R.id.txtBikeDetailsSecurityDeposite);
		mSecurityDepositAmount = (TextView) view
				.findViewById(R.id.txtBikeDetailsSecurityDepositeAmount);
		mVendorReview = (TextView) view
				.findViewById(R.id.txtBikeDetailsVendorReview);
		mVendorReviewRating = (RatingBar) view
				.findViewById(R.id.ratingBikeDetails);
		mProductDes = (TextView) view.findViewById(R.id.txtBikeProductDetails);
		mTermAndCondition = (TextView) view
				.findViewById(R.id.txtBikeDatilsTermCondition);
		mBookMyRide = (Button) view.findViewById(R.id.btnBikeDetails);
		mLinearLayoutClickForMapView = (LinearLayout) view
				.findViewById(R.id.linLayoutBikeDetailsViewOnMap);
		mBikeDetailsSummary = (TextView) view
				.findViewById(R.id.txtBikeDetailsSummary);
		// Set Font of View
		mBikeName.setTypeface(Constant.getFontSemiBold(getActivity()));
		mVendorName.setTypeface(Constant.getFontNormal(getActivity()));
		mKm.setTypeface(Constant.getFontNormal(getActivity()));
		mViewOnMap.setTypeface(Constant.getFontNormal(getActivity()));
		mRentalCharges.setTypeface(Constant.getFontSemiBold(getActivity()));
		mRentalChargesAmount.setTypeface(Constant.getFontNormal(getActivity()));
		mSecurityDeposite.setTypeface(Constant.getFontSemiBold(getActivity()));
		mSecurityDepositAmount.setTypeface(Constant
				.getFontNormal(getActivity()));
		mVendorReview.setTypeface(Constant.getFontSemiBold(getActivity()));
		mProductDes.setTypeface(Constant.getFontSemiBold(getActivity()));
		mTermAndCondition.setTypeface(Constant.getFontSemiBold(getActivity()));
		mBikeDetailsSummary.setTypeface(Constant.getFontNormal(getActivity()));
		mBookMyRide.setTypeface(Constant.getFontSemiBold(getActivity()));
		// Set Listner in View
		mLinearLayoutClickForMapView.setOnClickListener(this);
		mProductDes.setOnClickListener(this);
		mTermAndCondition.setOnClickListener(this);
		mBookMyRide.setOnClickListener(this);
		
		mProductDes.setOnTouchListener(this);
		mTermAndCondition.setOnTouchListener(this);
		mBookMyRide.setOnTouchListener(this);

		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		mOnMessageListner.setEnableBackButton(true);
		mOnMessageListner.setTitle("Bike Details");
		mOnMessageListner.setEnableProfileButton(true);
		mOnMessageListner.setEnableFilterPanel(false);
		//set Image of Bike
		ImageLoader.getInstance().displayImage("http://api.androidhive.info/images/sample.jpg", mBikeImage);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.linLayoutBikeDetailsViewOnMap:
			Log.d(TAG, "Click for Map ");
			Fragment mapFragment = new MapViewFragment();
			FragmentManager mapFragmentManager = getActivity().getFragmentManager();
			FragmentTransaction mapFragmentTransaction = mapFragmentManager
					.beginTransaction();
			mapFragmentTransaction.replace(R.id.content_frame, mapFragment);
			mapFragmentTransaction.addToBackStack(null);
			mapFragmentTransaction.commit();

			break;
		case R.id.btnBikeDetails:
			Log.d(TAG, "Button Bike Details");
			// Fragment fragment = new SearchingFragment(locationBin);
			Fragment fragment = new BookingVerificationFragment();
			FragmentManager fragmentManager = getActivity()
					.getFragmentManager();
			FragmentTransaction fragmentTransaction = fragmentManager
					.beginTransaction();
			fragmentTransaction.replace(R.id.content_frame, fragment);
			fragmentTransaction.addToBackStack(null);
			fragmentTransaction.commit();
			break;

		case R.id.txtBikeDatilsTermCondition:
			Log.d(TAG, "Term and Condition");
			/*if (isTermAndConditionEnable) {
				mTermAndCondition
						.setBackgroundResource(R.drawable.bike_details_term_condition);
				mTermAndCondition.setCompoundDrawablesWithIntrinsicBounds(0, 0,
						R.drawable.arrow_down_icon, 0);
				mTermAndCondition.setTextColor(getActivity().getResources()
						.getColor(R.color.black));
				isTermAndConditionEnable = false;
				mProductDes
						.setBackgroundResource(R.drawable.bike_details_term_condition);
				mProductDes.setCompoundDrawablesWithIntrinsicBounds(0, 0,
						R.drawable.arrow_down_icon, 0);
				mProductDes.setTextColor(getActivity().getResources().getColor(
						R.color.black));
				isProductDesEnable = false;

			} else {
				mTermAndCondition
						.setBackgroundResource(R.drawable.bike_product_details);
				mTermAndCondition.setCompoundDrawablesWithIntrinsicBounds(0, 0,
						R.drawable.arrow_up_icon, 0);
				mTermAndCondition.setTextColor(getActivity().getResources()
						.getColor(R.color.white));
				isTermAndConditionEnable = true;
				mBikeDetailsSummary.setText("Term And Conditions");
			}*/

			break;
		case R.id.txtBikeProductDetails:
			Log.d(TAG, "Bike Product Details");
		/*	if (isProductDesEnable) {
				mProductDes
						.setBackgroundResource(R.drawable.bike_product_details);
				mProductDes.setCompoundDrawablesWithIntrinsicBounds(0, 0,
						R.drawable.arrow_down_icon, 0);
				mProductDes.setTextColor(getActivity().getResources().getColor(
						R.color.black));
				isProductDesEnable = false;

			} else {
				mProductDes
						.setBackgroundResource(R.drawable.bike_product_details);
				mProductDes.setCompoundDrawablesWithIntrinsicBounds(0, 0,
						R.drawable.arrow_up_icon, 0);
				mProductDes.setTextColor(getActivity().getResources().getColor(
						R.color.white));
				isProductDesEnable = true;
				mBikeDetailsSummary.setText("Product Decription");
				mTermAndCondition
						.setBackgroundResource(R.drawable.bike_details_term_condition);
				mTermAndCondition.setCompoundDrawablesWithIntrinsicBounds(0, 0,
						R.drawable.arrow_down_icon, 0);
				mTermAndCondition.setTextColor(getActivity().getResources()
						.getColor(R.color.black));
				isTermAndConditionEnable = false;
			}*/
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
