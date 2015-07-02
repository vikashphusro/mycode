package com.condivision.rentongo.adapters;

import java.util.ArrayList;
import java.util.List;

import com.condivision.rentongo.R;
import com.condivision.rentongo.https.bins.DrawerItemBin;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomDrawerAdapter extends ArrayAdapter {

	Activity context;
	ArrayList<DrawerItemBin> drawerItemList;
	int layoutResID;

	public CustomDrawerAdapter(Activity context, int layoutResourceID,
			ArrayList<DrawerItemBin> listItems) {
		super(context, layoutResourceID, listItems);
		this.context = context;
		this.drawerItemList = listItems;
		this.layoutResID = layoutResourceID;

	}

	@Override
	public int getCount() {
		return drawerItemList.size();
	}

	@Override
	public Object getItem(int location) {
		return drawerItemList.get(location);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressLint("NewApi")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		DrawerItemHolder drawerHolder;
		View view = convertView;

		if (view == null) {
			LayoutInflater inflater = context.getLayoutInflater();
			drawerHolder = new DrawerItemHolder();

			view = inflater.inflate(layoutResID, parent, false);
			drawerHolder.ItemName = (TextView) view
					.findViewById(R.id.txtDrawerList);
			drawerHolder.ItemName.setTypeface(Typeface.createFromAsset(
					context.getAssets(), "proximanova-semibold-webfont.ttf"));
			drawerHolder.icon = (ImageView) view
					.findViewById(R.id.imgDrawerList);

			view.setTag(drawerHolder);

		} else {
			drawerHolder = (DrawerItemHolder) view.getTag();

		}

		DrawerItemBin dItem = drawerItemList.get(position);

		drawerHolder.icon.setImageDrawable(view.getResources().getDrawable(
				dItem.getImgResID()));
		// drawerHolder.icon.setImageDrawable(context.getDrawable(dItem.getImgResID()));
		drawerHolder.ItemName.setText("" + dItem.getItemName());

		return view;
	}

	private static class DrawerItemHolder {
		TextView ItemName;
		ImageView icon;
	}

}
