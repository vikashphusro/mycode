package com.condivision.rentongo.fragment;

import java.util.ArrayList;

import com.condivision.rentongo.R;
import com.condivision.rentongo.adapters.CustomResultAdapter;
import com.condivision.rentongo.interfaces.OnMessageListner;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/**
 * This class show the result of all bikes with all details i.e name, vendor
 * name, location, rent etc.
 * 
 * @author rentongo
 *
 */
public class SearchResultFragment extends Fragment {

	private static final String TAG = "SearchResultFragment";
	private OnMessageListner mOnMessageListner;
	private ArrayList<String> listItem;
	private CustomResultAdapter mResultAdapter;
	private ListView mlistView;

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
		listItem = new ArrayList<String>();
		listItem.add("Hero Honda");
		listItem.add("Pulsar");
		listItem.add("Bajaj");
		listItem.add("Royal Enfield");
		listItem.add("TVS");
		listItem.add("Veshpa");
		mResultAdapter = new CustomResultAdapter(getActivity(), listItem);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		Log.d(TAG, "onCreateView");
		View view = inflater.inflate(R.layout.fragment_search_result,
				container, false);
		mlistView = (ListView) view.findViewById(R.id.listSearchList);
		mlistView.setAdapter(mResultAdapter);
		mlistView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Log.d(TAG, "List View Click : "+position);
				String str = listItem.get(position);
				Log.d(TAG, str);
				
				Fragment fragment = new BikeDetailsFragment();
				FragmentManager fragmentManager = getActivity().getFragmentManager();
				FragmentTransaction fragmentTransaction = fragmentManager
						.beginTransaction();
				fragmentTransaction.replace(R.id.content_frame, fragment);
				fragmentTransaction.addToBackStack("searchresult");
				fragmentTransaction.commit();
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
		Log.d(TAG, "onResume");
		mOnMessageListner.setEnableBackButton(false);
		mOnMessageListner.setEnableFilterPanel(true);
		mOnMessageListner.setEnableProfileButton(true);
		mOnMessageListner.setTitle("Search Results");
		mOnMessageListner.setBikeAvailability(listItem.size());
	}

	@Override
	public void onPause() {
		super.onPause();
		Log.d(TAG, "onPause");

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
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		Log.d(TAG, "onDestroyView");
	}

	@Override
	public void onDetach() {
		super.onDetach();
		Log.d(TAG, "onDetach");
	}

}
