package com.condivision.rentongo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * A helper class to manage database creation and version management.
 * 
 * @author rentongo
 *
 */

public class DatabaseHelper extends SQLiteOpenHelper {
	private static final String TAG = "DatabaseHelper";
	public static final String DB_NAME = "rentongo.db";
	public static final int DB_VERSION = 1;
	private static DatabaseHelper instance = null;
	private SQLiteDatabase db;

	public DatabaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, DB_NAME, factory, DB_VERSION);
	}

	public DatabaseHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		Log.d(TAG, "DatabaseHelper()");
	}

	public static DatabaseHelper getInstacne(Context c) {
		if (instance != null) {
			return instance;
		} else {
			instance = new DatabaseHelper(c);
			return instance;
		}

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	@Override
	public SQLiteDatabase getWritableDatabase() {
		while (true) {
			try {
				return super.getWritableDatabase();
			} catch (SQLiteDatabaseLockedException e) {
				System.err.println(e);
			}
		}
	}

}
