package com.condivision.rentongo.fragment;

import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.condivision.rentongo.R;
import com.condivision.rentongo.interfaces.OnMessageListner;
import com.condivision.rentongo.util.AppPreferences;
import com.condivision.rentongo.util.Constant;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

/**
 * This class is used to show location of bike with rentcost, bike name and
 * vendor details on google map.
 * 
 * @author rentongo
 *
 */
public class MapViewFragment extends Fragment {

	private static final String TAG = "MapViewFragment";
	private static final int ZOOM_LEVEL = 14;
	private static final String URL = "http://api.androidhive.info/images/sample.jpg";

	private MapView mMapView;
	private GoogleMap mGoogleMap;
	private OnMessageListner mOnMessageListner;
	private double latitude, longitude;
	private ImageLoader mImageLoader = null;
	private TextView mTxtKm, mTxtRentPerDay, mTxtBikeName;
	private RatingBar mRateBarBike;
	private Dialog mDialog;
	private ImageView mMarkerBikeImage;
	private DisplayImageOptions options;

	public MapViewFragment() {
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
		options = new DisplayImageOptions.Builder()
        .showStubImage(R.drawable.ic_launcher)        //    Display Stub Image
        .showImageForEmptyUri(R.drawable.ic_launcher)    //    If Empty image found
        .cacheInMemory()
        .cacheOnDisc().bitmapConfig(Bitmap.Config.RGB_565).build();
		mImageLoader = ImageLoader.getInstance();

	}

	@SuppressLint("NewApi")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		Log.d(TAG, "onCreateView");
		View view = inflater.inflate(R.layout.fragment_map_view, container,
				false);
		mMapView = (MapView) view.findViewById(R.id.mapView);
		mMapView.onCreate(savedInstanceState);
		mMapView.onResume();// needed to get the map to display immediately

		try {
			MapsInitializer.initialize(getActivity().getApplicationContext());
		} catch (Exception e) {
			e.printStackTrace();
		}
		mGoogleMap = mMapView.getMap();
		// latitude and longitude
		latitude = AppPreferences.getInstance(getActivity()).getLatitude();
		longitude = AppPreferences.getInstance(getActivity()).getLongitude();
		// create marker
		MarkerOptions marker = new MarkerOptions().position(new LatLng(
				latitude, longitude));
		CameraPosition cameraPosition = new CameraPosition.Builder()
				.target(new LatLng(latitude, longitude)).zoom(ZOOM_LEVEL)
				.build();
		mGoogleMap.animateCamera(CameraUpdateFactory
				.newCameraPosition(cameraPosition));
		mGoogleMap.getUiSettings().setZoomControlsEnabled(true);
		// Inflate Layout for marker
		LayoutInflater layoutInflater = (LayoutInflater) getActivity()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View marker1 = layoutInflater.inflate(R.layout.marker_map_view,
				container, false);
		// Get View of Custom marker
		mMarkerBikeImage = (ImageView) marker1
				.findViewById(R.id.imgMapViewItem);
		
		mTxtKm = (TextView) marker1.findViewById(R.id.txtMapViewKm);
		mTxtKm.setText("" + 50 + "KM");
		mTxtRentPerDay = (TextView) marker1
				.findViewById(R.id.txtMapViewPricePerDay);
		mTxtBikeName = (TextView) marker1.findViewById(R.id.txtMapViewBikeName);
		mRateBarBike = (RatingBar) marker1
				.findViewById(R.id.ratingMapViewBarOfBike);
		// Set Font in Custom Marker
		mTxtKm.setTypeface(Constant.getFontNormal(getActivity()));
		mTxtRentPerDay.setTypeface(Constant.getFontNormal(getActivity()));
		mTxtBikeName.setTypeface(Constant.getFontSemiBold(getActivity()));
		marker.icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(
				getActivity(), marker1)));

		// adding marker
		mGoogleMap.addMarker(marker);
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
	//	new ImageDownloader().execute(URL);
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.d(TAG, "onResume");
		mMapView.onResume();
		mOnMessageListner.setEnableBackButton(true);
		mOnMessageListner.setEnableFilterPanel(false);
		mOnMessageListner.setEnableProfileButton(true);
		mOnMessageListner.setTitle("Map View");
		mImageLoader.displayImage(URL, mMarkerBikeImage, options,new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri,
                    View view, Bitmap loadedImage) {
                super.onLoadingComplete(imageUri, view,
                        loadedImage);
                //getInfoContents(marker);
            }});
		/*
		 * ImageLoader.getInstance().displayImage(
		 * "http://api.androidhive.info/images/sample.jpg", mMarkerBikeImage);
		 */
	}

	@Override
	public void onPause() {
		super.onPause();
		Log.d(TAG, "onPause");
		mMapView.onPause();
	}

	@Override
	public void onStop() {
		super.onStop();
		Log.d(TAG, "onStop");

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "onDestroy");
		mMapView.onDestroy();
	}

	@Override
	public void onDetach() {
		super.onDetach();
		Log.d(TAG, "onDetach");

	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();

		mMapView.onLowMemory();
	}

	/**
	 * Convert a view to bitmap
	 * 
	 * @param context
	 * @param view
	 * @return
	 */
	public static Bitmap createDrawableFromView(Context context, View view) {
		// A structure describing general information about a display, such as
		// its size, density, and font scaling.
		DisplayMetrics displayMetrics = new DisplayMetrics();
		((Activity) context).getWindowManager().getDefaultDisplay()
				.getMetrics(displayMetrics);
		view.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		view.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
		view.layout(0, 0, displayMetrics.widthPixels,
				displayMetrics.heightPixels);
		view.buildDrawingCache();
		Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(),
				view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		view.draw(canvas);
		return bitmap;
	}

	private class ImageDownloader extends AsyncTask<String, Void, Bitmap> {

		@Override
		protected Bitmap doInBackground(String... params) {
			// TODO Auto-generated method stub
			return downloadBitmap(params[0]);
		}

		@Override
		protected void onPreExecute() {
			Log.i("Async-Example", "onPreExecute Called");
			mDialog = ProgressDialog.show(getActivity(), "Wait",
					"Downloading Image");

		}

		@Override
		protected void onPostExecute(Bitmap result) {
			Log.i("Async-Example", "onPostExecute Called");
			mMarkerBikeImage.setImageBitmap(result);
			if (mDialog != null && mDialog.isShowing()) {
				mDialog.dismiss();
			}

		}

		private Bitmap downloadBitmap(String url) {
			// initilize the default HTTP client object
			final DefaultHttpClient client = new DefaultHttpClient();

			// forming a HttoGet request
			final HttpGet getRequest = new HttpGet(url);
			try {

				HttpResponse response = client.execute(getRequest);

				// check 200 OK for success
				final int statusCode = response.getStatusLine().getStatusCode();

				if (statusCode != HttpStatus.SC_OK) {
					Log.w("ImageDownloader", "Error " + statusCode
							+ " while retrieving bitmap from " + url);
					return null;

				}

				final HttpEntity entity = response.getEntity();
				if (entity != null) {
					InputStream inputStream = null;
					try {
						// getting contents from the stream
						inputStream = entity.getContent();

						// decoding stream data back into image Bitmap that
						// android understands
						final Bitmap bitmap = BitmapFactory
								.decodeStream(inputStream);

						return bitmap;
					} finally {
						if (inputStream != null) {
							inputStream.close();
						}
						entity.consumeContent();
					}
				}
			} catch (Exception e) {
				// You Could provide a more explicit error message for
				// IOException
				getRequest.abort();
				Log.e("ImageDownloader", "Something went wrong while"
						+ " retrieving bitmap from " + url + e.toString());
			}

			return null;
		}

	}
}
