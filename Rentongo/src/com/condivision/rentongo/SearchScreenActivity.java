package com.condivision.rentongo;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.condivision.rentongo.adapters.CustomDrawerAdapter;
import com.condivision.rentongo.fragment.AboutRentOnGoFragment;
import com.condivision.rentongo.fragment.FeedbackFragment;
import com.condivision.rentongo.fragment.HowItWorkFragment;
import com.condivision.rentongo.fragment.LoginFragment;
import com.condivision.rentongo.fragment.RateUsFragment;
import com.condivision.rentongo.fragment.SearchRideFragment;
import com.condivision.rentongo.fragment.SortingAndFilterFragment;
import com.condivision.rentongo.https.bins.DrawerItemBin;
import com.condivision.rentongo.interfaces.OnMessageListner;
import com.condivision.rentongo.util.Constant;

/**
 * This class for base of all fragment.
 * 
 * @author rentongo
 *
 */
public class SearchScreenActivity extends Activity implements OnClickListener,
		OnMessageListner {

	private static final String TAG = "SearchScreenActivity";
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ImageButton mBtnMenu, mPhone, mProfile, mBtnBack;
	private TextView mTitle;
	private ArrayList<DrawerItemBin> drawerList;
	private Intent mIntent;
	private TextView mTxtBikeAvailability;
	private ImageButton mBtnSearch, mBtnFilter;
	private RelativeLayout mBikeAvailableLayout;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_screen);
		Log.d(TAG, "onCreate");
		// get Intent
		mIntent = getIntent();
		// get View of layout
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);
		mTitle = (TextView) findViewById(R.id.txtSerachRide);
		mTitle.setTypeface(Constant.getFontNormal(this));
		mPhone = (ImageButton) findViewById(R.id.btnPhone);
		mProfile = (ImageButton) findViewById(R.id.btnProfile);
		mBtnMenu = (ImageButton) findViewById(R.id.btnMenuIcon);
		mBtnBack = (ImageButton) findViewById(R.id.btnBackIcon);

		// Layout for number of bike available, search icon and filter
		mBikeAvailableLayout = (RelativeLayout) findViewById(R.id.relativeLayoutPanelBikeAvailable);
		mTxtBikeAvailability = (TextView) findViewById(R.id.txtBikeAvilability);
		mBtnFilter = (ImageButton) findViewById(R.id.btnFilter);
		mBtnSearch = (ImageButton) findViewById(R.id.btnSearch);
		mBtnFilter.setOnClickListener(this);
		mBtnSearch.setOnClickListener(this);

		// Set Listener on Button
		mBtnMenu.setOnClickListener(SearchScreenActivity.this);
		mPhone.setOnClickListener(SearchScreenActivity.this);
		mProfile.setOnClickListener(SearchScreenActivity.this);
		mBtnBack.setOnClickListener(SearchScreenActivity.this);

		// Set image and text in Drawer list
		drawerList = new ArrayList<DrawerItemBin>();
		drawerList.add(new DrawerItemBin(getResources().getString(
				R.string.how_it_works), R.drawable.how_it_work_icon));
		drawerList.add(new DrawerItemBin(getResources().getString(
				R.string.rate_us), R.drawable.rate_us_icon));
		drawerList.add(new DrawerItemBin(getResources().getString(
				R.string.feedback), R.drawable.feedback_icon));
		drawerList.add(new DrawerItemBin(getResources().getString(
				R.string.about_rentongo), R.drawable.about_rog_icon));
		drawerList.add(new DrawerItemBin(getResources().getString(
				R.string.share), R.drawable.about_rog_icon));

		// Set Adapter to Drawer List.
		mDrawerList.setAdapter(new CustomDrawerAdapter(
				SearchScreenActivity.this, R.layout.list_drawer_item,
				drawerList));
		mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

		// set notification bar color in lollipop and above version
		int currentapiVersion = android.os.Build.VERSION.SDK_INT;
		if (currentapiVersion >= android.os.Build.VERSION_CODES.LOLLIPOP) {
			Window window = SearchScreenActivity.this.getWindow();
			window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			window.setStatusBarColor(getResources().getColor(
					R.color.notification_baf_color));
		}

		// Display default fragment when this screen is launched.
		displayFragment();

	}

	private void displayFragment() {
		// Display Main content of drawera
		Log.d(TAG, "default fragment left side");
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		fragmentTransaction.replace(R.id.content_frame,
				SearchRideFragment.getInstance());
		fragmentTransaction.commit();

		// Display default of right drawer

		Log.d(TAG, "default right drawer ");
		SortingAndFilterFragment sortingAndFilter = SortingAndFilterFragment
				.getInstance();
		FragmentManager fragmentManager2 = getFragmentManager();
		FragmentTransaction fragmentTransaction2 = fragmentManager2
				.beginTransaction();
		fragmentTransaction2
				.replace(R.id.content_frame_right, sortingAndFilter);
		fragmentTransaction2.commit();

	}

	private class SlideMenuClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// display view for selected nav drawer item
			Log.d(TAG, "display left drawer");
			displayView(position);
		}

		private void displayView(int position) {
			// fragment selection
			Fragment fragment = null;
			switch (position) {
			case 0:
				fragment = HowItWorkFragment.getInstance();
				break;
			case 1:
				fragment = RateUsFragment.getInstance();
				break;
			case 2:
				fragment = FeedbackFragment.getInstance();
				break;
			case 3:
				fragment = AboutRentOnGoFragment.getInstance();
				break;
			case 4:
				// share text to social media App
				shareTextWithSocialMedia();
				break;

			default:
				break;
			}
			if (fragment != null) {
				FragmentManager fragmentManager = getFragmentManager();
				fragmentManager.popBackStackImmediate();
				FragmentTransaction fragmentTransaction = fragmentManager
						.beginTransaction();
				fragmentTransaction.replace(R.id.content_frame, fragment);
				// fragmentTransaction.setCustomAnimations(R.anim.abc_fade_in,
				// R.anim.abc_fade_out);
				fragmentTransaction.addToBackStack(fragment.getClass().getName());
				fragmentTransaction.commit();
				// update selected item and title, then close the drawer
				mDrawerList.setItemChecked(position, true);
				mDrawerList.setSelection(position);
				mDrawerLayout.closeDrawer(mDrawerList);
			} else {
				// error in creating fragment
				Log.e(TAG, "Error in creating fragment");
			}
		}

		private void shareTextWithSocialMedia() {
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_SEND);
			intent.setType("text/plain");
			intent.putExtra(Intent.EXTRA_TEXT, "RentonGo: www.rentongo.com");
			startActivity(Intent.createChooser(intent, "Share"));
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnMenuIcon:
			Log.d(TAG, "menu button click");
			if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
				mDrawerLayout.closeDrawer(Gravity.LEFT);
			} else {
				mDrawerLayout.openDrawer(Gravity.LEFT);

			}

			break;
		case R.id.btnPhone:
			Log.d(TAG, "phone button click");
			Intent callIntent = new Intent(Intent.ACTION_CALL);
			callIntent.setData(Uri.parse("tel:111"));
			startActivity(callIntent);

			break;

		case R.id.btnProfile:
			Log.d(TAG, "profile button click");
			Fragment fragment = new LoginFragment();
			FragmentManager fragmentManagerLogin = this.getFragmentManager();
			FragmentTransaction fragmentTransactionLogin = fragmentManagerLogin
					.beginTransaction();
			fragmentTransactionLogin.replace(R.id.content_frame, fragment);
			fragmentTransactionLogin.addToBackStack(null);
			fragmentTransactionLogin.commit();
			break;

		case R.id.btnBackIcon:
			Log.d(TAG, "app back button click");
			getFragmentManager().popBackStack();
			break;

		case R.id.btnFilter:
			Log.d(TAG, "filter button click");
			if (mDrawerLayout.isDrawerOpen(Gravity.END)) {
				mDrawerLayout.closeDrawer(Gravity.END);
			} else {
				getFragmentManager().popBackStack();
				mDrawerLayout.openDrawer(Gravity.END);
				SortingAndFilterFragment sortingAndFilter = SortingAndFilterFragment
						.getInstance();
				FragmentManager fragmentManager = getFragmentManager();
				FragmentTransaction fragmentTransaction = fragmentManager
						.beginTransaction();
				fragmentTransaction.replace(R.id.content_frame_right,
						sortingAndFilter);

				fragmentTransaction.commit();

			}

			break;
		case R.id.btnSearch:
			Log.d(TAG, "search button click");
			if (mDrawerLayout.isDrawerOpen(Gravity.END)) {
				mDrawerLayout.closeDrawer(Gravity.END);
			} else {
				getFragmentManager().popBackStack();
				mDrawerLayout.openDrawer(Gravity.END);
				SearchRideFragment searchRideFragment = SearchRideFragment
						.getInstance();
				FragmentManager fragmentManager = getFragmentManager();
				FragmentTransaction fragmentTransaction = fragmentManager
						.beginTransaction();
				fragmentTransaction.replace(R.id.content_frame_right,
						searchRideFragment);
				fragmentTransaction.commit();

			}

			break;

		default:
			break;
		}
	}

	@Override
	public void onBackPressed() {
		Log.d(TAG, "onBackPressed");
//		// If Drawer if open then close drawer when back button is clicked.
		if (mDrawerLayout.isDrawerOpen(Gravity.END)
				|| mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
			mDrawerLayout.closeDrawer(Gravity.END);
			mDrawerLayout.closeDrawer(Gravity.LEFT);
		} else {
			super.onBackPressed();
		}
	}

	@Override
	public void setTitle(String title) {
		Log.d(TAG, "settitle");
		if (title != null) {
			mTitle.setText("" + title);
			SearchRideFragment searchRideFragment = SearchRideFragment
					.getInstance();
			String address = mIntent.getStringExtra("address");
			searchRideFragment.setAddress(address);
		} else {
			throw new NullPointerException("Title value is :"
					+ String.valueOf(title));
		}

	}

	@Override
	public void setEnableFilterPanel(boolean b) {
		if (b) {
			mBikeAvailableLayout.setVisibility(View.VISIBLE);
		} else {
			mBikeAvailableLayout.setVisibility(View.GONE);
		}
	}

	@Override
	public void setEnableBackButton(boolean b) {
		if (b) {
			mBtnBack.setVisibility(View.VISIBLE);
		} else {
			mBtnBack.setVisibility(View.GONE);
		}
	}

	@Override
	public void setEnableProfileButton(boolean b) {
		if (b) {
			mProfile.setVisibility(View.VISIBLE);
		} else {
			mProfile.setVisibility(View.GONE);
		}
	}

	@Override
	public void setBikeAvailability(int number) {
		mTxtBikeAvailability.setText("" + number + " " + "Bikes Available");
	}

	@Override
	public void closeOpenDrawer(boolean b) {
		if (b) {
			if (mDrawerLayout.isDrawerOpen(Gravity.END)
					|| mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
				mDrawerLayout.closeDrawer(Gravity.END);
				mDrawerLayout.closeDrawer(Gravity.LEFT);
			} 
		}
	}

}
