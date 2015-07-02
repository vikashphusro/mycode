package com.condivision.rentongo.customviews;

import com.condivision.rentongo.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

public class CustomButton extends Button {
	private static final String TAG = "CustomButton";

	public CustomButton(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		applyAttributes(context, attrs);
	}

	public CustomButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		applyAttributes(context, attrs);
	}

	public CustomButton(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	private void applyAttributes(Context context, AttributeSet attrs) {
		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.CustomTextView);
		final int N = a.getIndexCount();
		for (int i = 0; i < N; i++) {
			int attr = a.getIndex(i);
			switch (attr) {
			case R.styleable.CustomTextView_fontAssetName:
				try {
					Typeface font = Typeface.createFromAsset(getResources()
							.getAssets(), a.getString(attr));
					if (font != null) {
						this.setTypeface(font);
					}
				} catch (RuntimeException e) {

				}
			}
		}
	}
}
