package com.condivision.rentongo;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import android.app.Application;
import android.util.Log;

/**
 * Base class for those who need to maintain global application state.
 * 
 * @author rentongo
 *
 */
public class Rentongo extends Application {
	private static final String TAG = "Rentongo";

	private static Rentongo instance = null;

	public static Rentongo getInstance() {
		Log.d(TAG, "getInstance");
		if (instance != null) {
			return instance;
		} else {
			return new Rentongo();
		}

	}

	@Override
	public void onCreate() {
		Log.d(TAG, "onCreate");
		super.onCreate();
		instance = this;
		//Universal image loader setup
		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
				.cacheOnDisc(true).cacheInMemory(true)
				.imageScaleType(ImageScaleType.EXACTLY)
				.displayer(new FadeInBitmapDisplayer(300)).build();

		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				getApplicationContext())
				.defaultDisplayImageOptions(defaultOptions)
				.memoryCache(new WeakMemoryCache())
				.discCacheSize(100 * 1024 * 1024).build();

		ImageLoader.getInstance().init(config);
		// end of universal image 
	}

}
