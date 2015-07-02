package com.condivision.rentongo.adapters;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.condivision.rentongo.R;
import com.condivision.rentongo.fragment.BikeDetailsFragment;
import com.condivision.rentongo.fragment.MapViewFragment;
import com.condivision.rentongo.fragment.SearchingFragment;
import com.condivision.rentongo.util.Constant;
import com.google.android.gms.maps.MapFragment;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Custom adapter for Search result screen 
 * @author rentongo
 *
 */
public class CustomResultAdapter extends BaseAdapter implements OnClickListener {
	private Activity context;
	private ArrayList<String> itemList;

	public CustomResultAdapter(Activity context, ArrayList<String> listItems) {
		this.context = context;
		this.itemList = listItems;

	}

	@Override
	public int getCount() {
		return itemList.size();
	}

	@Override
	public Object getItem(int position) {
		return itemList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ResultHolder resultHolder;
		View view = convertView;
		if (view == null) {
			LayoutInflater inflater = context.getLayoutInflater();
			resultHolder = new ResultHolder();
			view = inflater.inflate(R.layout.list_search_result_item, parent,
					false);
			resultHolder.imgBike = (ImageView) view
					.findViewById(R.id.imgSearchReasultItem);
			resultHolder.txtBikeName = (TextView) view
					.findViewById(R.id.txtBikeName);
			resultHolder.txtVendorNameAndAddress = (TextView) view
					.findViewById(R.id.txtBikeProviderName);
			resultHolder.txtKm = (TextView) view
					.findViewById(R.id.txtSearchResultKm);
			resultHolder.txtRentPerDay = (TextView) view
					.findViewById(R.id.txtSearchResultPricePerDay);
			resultHolder.txtNumberOfVendor = (TextView) view
					.findViewById(R.id.txtNumberOfVendor);
			resultHolder.rtVendorRating = (RatingBar) view
					.findViewById(R.id.ratingBarOfBike);
			
			resultHolder.txtBikeName.setTypeface(Constant.getFontSemiBold(context));
			resultHolder.txtVendorNameAndAddress.setTypeface(Constant.getFontSemiBold(context));
			resultHolder.txtKm.setTypeface(Constant.getFontNormal(context));
			resultHolder.txtNumberOfVendor.setTypeface(Constant.getFontSemiBold(context));
			resultHolder.txtRentPerDay.setTypeface(Constant.getFontNormal(context));
			
			
			view.setTag(resultHolder);

		} else {
			resultHolder = (ResultHolder) view.getTag();

		}
		String name = itemList.get(position);
		resultHolder.txtBikeName.setText("" + name);
		ImageLoader.getInstance().displayImage("http://api.androidhive.info/images/sample.jpg", resultHolder.imgBike);
		resultHolder.txtKm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Fragment fragment = new MapFragment();
				Fragment fragment = new MapViewFragment();
				FragmentManager fragmentManager = context.getFragmentManager();
				FragmentTransaction fragmentTransaction = fragmentManager
						.beginTransaction();
				fragmentTransaction.replace(R.id.content_frame, fragment);
				fragmentTransaction.addToBackStack(null);
				fragmentTransaction.commit();
			}
		});
		/*resultHolder.txtBikeName.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Fragment fragment = new BikeDetailsFragment();
				FragmentManager fragmentManager = context.getFragmentManager();
				FragmentTransaction fragmentTransaction = fragmentManager
						.beginTransaction();
				fragmentTransaction.replace(R.id.content_frame, fragment);
				fragmentTransaction.addToBackStack(null);
				fragmentTransaction.commit();

			}
		});*/
		return view;
	}
	//view for row item of list
	private static class ResultHolder {
		ImageView imgBike;
		TextView txtBikeName;
		TextView txtVendorNameAndAddress;
		TextView txtKm;
		TextView txtRentPerDay;
		TextView txtNumberOfVendor;
		RatingBar rtVendorRating;
	}

	@Override
	public void onClick(View v) {

	}

}
