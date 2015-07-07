package com.condivision.rentongo.db;

import java.util.ArrayList;
import java.util.List;

import com.condivision.rentongo.https.bins.BikeBrandBin;
import com.condivision.rentongo.https.bins.BikeEngineBin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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

	String createBikeBrandTable = "CREATE TABLE " + BikeBrandTable.TABLE_NAME
			+ "( " + BikeBrandTable.ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
			+ BikeBrandTable.BIKE_BRAND_NAME + " TEXT" + ");";

	String createBikeEngineTable = "CREATE TABLE " + BikeEngineTable.TABLE_NAME
			+ "( " + BikeEngineTable.ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT ,"
			+ BikeEngineTable.BIKE_BRAND_NAME + " TEXT ,"
			+ BikeEngineTable.BIKE_ENGINE_NAME + " TEXT" + ");";

	public DatabaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, DB_NAME, factory, DB_VERSION);
	}

	public DatabaseHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		Log.d(TAG, "DatabaseHelper()");
		db = getWritableDatabase();
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
		Log.d(TAG, "onCreate");
		db.execSQL(createBikeBrandTable);
		db.execSQL(createBikeEngineTable);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.d(TAG, "onUpgrade");
		onCreate(db);
	}

	/**
	 * insert bike name into database.
	 * 
	 * @param list
	 * @return
	 */
	public synchronized long insertBikeBrand(List<BikeBrandBin> list) {
		Log.d(TAG, "insertBikeBrand");
		long i = 0;
		db = getWritableDatabase();
		try {
			db.beginTransaction();
			db.delete(BikeBrandTable.TABLE_NAME, null, null);
			for (int j = 0; j < list.size(); j++) {
				ContentValues contentValues = new ContentValues();
				contentValues.put(BikeBrandTable.BIKE_BRAND_NAME, list.get(j)
						.getBrandName());
				i = db.insert(BikeBrandTable.TABLE_NAME, null, contentValues);
			}
			db.setTransactionSuccessful();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.endTransaction();
			db.close();
		}

		return i;
	}

	/**
	 * insert bike engine capacity into database.
	 * 
	 * @param list
	 * @return
	 */
	public synchronized long insertBikeEngine(List<BikeEngineBin> list) {
		Log.d(TAG, "insertBikeEngine");
		long i = 0;
		db = getWritableDatabase();
		try {
			db.beginTransaction();
			db.delete(BikeEngineTable.TABLE_NAME, null, null);
			for (int j = 0; j < list.size(); j++) {
				ContentValues contentValues = new ContentValues();
				contentValues.put(BikeEngineTable.BIKE_BRAND_NAME, list.get(j)
						.getBrandName());
				contentValues.put(BikeEngineTable.BIKE_ENGINE_NAME, list.get(j)
						.getEngineCapacity());
				i = db.insert(BikeEngineTable.TABLE_NAME, null, contentValues);
			}
			db.setTransactionSuccessful();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.endTransaction();
			db.close();
		}

		return i;
	}

	/**
	 * get bikebrand name from database.
	 * 
	 * @return
	 */
	public synchronized List<BikeBrandBin> getBikeBrandName() {
		Log.d(TAG, "getBikeBrandName");
		BikeBrandBin bikeBrandBin = null;
		List<BikeBrandBin> list = new ArrayList<BikeBrandBin>();
		Cursor cursor = null;
		String query = "select * from " + BikeBrandTable.TABLE_NAME;
		db = getWritableDatabase();
		cursor = db.rawQuery(query, null);
		try {
			if (cursor.getCount() > 0) {
				while (cursor.moveToNext()) {
					bikeBrandBin = new BikeBrandBin();
					String bikeBrandName = cursor.getString(cursor
							.getColumnIndex(BikeBrandTable.BIKE_BRAND_NAME));
					int _id = cursor.getInt(cursor
							.getColumnIndex(BikeBrandTable.ID));
					bikeBrandBin.setBrandName(bikeBrandName);
					bikeBrandBin.setId(_id);
					list.add(bikeBrandBin);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}

		return list;
	}

	/**
	 * get name of engine capacity.
	 * 
	 * @param name
	 * @return
	 */
	public synchronized List<BikeEngineBin> getEngineCapacityName(String name) {
		Log.d(TAG, "getEngineCapacityName");
		BikeEngineBin bikeEngineBin = null;
		List<BikeEngineBin> bikeEngineBins = new ArrayList<BikeEngineBin>();
		Cursor cursor = null;
		String query = "select * from " + BikeEngineTable.TABLE_NAME
				+ " where " + BikeEngineTable.BIKE_BRAND_NAME + " =? ";
		db = getWritableDatabase();
		String[] args = { name };
		cursor = db.rawQuery(query, args);
		try {
			if (cursor.getCount() > 0) {
				while (cursor.moveToNext()) {
					bikeEngineBin = new BikeEngineBin();
					String bikeEngineName = cursor.getString(cursor
							.getColumnIndex(BikeEngineTable.BIKE_ENGINE_NAME));
					int _id = cursor.getInt(cursor
							.getColumnIndex(BikeEngineTable.ID));
					bikeEngineBin.setEngineCapacity(bikeEngineName);
					bikeEngineBin.setId(_id);
					bikeEngineBins.add(bikeEngineBin);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}

		return bikeEngineBins;
	}

	@Override
	public SQLiteDatabase getWritableDatabase() {
		Log.d(TAG, "getWritableDatabase");
		while (true) {
			try {
				return super.getWritableDatabase();
			} catch (SQLiteDatabaseLockedException e) {
				System.err.println(e);
			}
		}
	}

}
