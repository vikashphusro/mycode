package com.condivision.rentongo.util;

import android.content.Context;
import android.graphics.Typeface;

/**
 * All constant define in this class.
 * 
 * @author rentongo
 *
 */
public class Constant {

	/**
	 * Get Normal Font
	 * 
	 * @param context
	 * @return
	 */
	public static Typeface getFontNormal(Context context) {
		return Typeface.createFromAsset(context.getAssets(),
				"proximanova-regular-webfont.ttf");

	}

	/**
	 * Get SemiBold Font
	 * 
	 * @param context
	 * @return
	 */
	public static Typeface getFontSemiBold(Context context) {

		return Typeface.createFromAsset(context.getAssets(),
				"proximanova-semibold-webfont.ttf");

	}

	/**
	 * Get Font Italic Bold
	 * 
	 * @param context
	 * @return
	 */
	public static Typeface getFontItalicBold(Context context) {
		return Typeface.createFromAsset(context.getAssets(),
				"proximanova-semibolditalic-webfont.ttf");

	}

	/**
	 * Get Array of Month
	 * 
	 * @param month
	 * @return
	 */
	public static String getMonth(int month) {
		String[] monthNames = { "Jan", "Feb", "Mar", "Apr", "May", "Jun",
				"Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
		return monthNames[month];
	}

	/**
	 * Get Array of week
	 * 
	 * @param day
	 * @return
	 */
	public static String getDay(int day) {
		String[] dayName = { "", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri",
				"Sat" };
		return dayName[day];

	}

	/**
	 * Validate Phone Number
	 * 
	 * @param phoneNo
	 * @return
	 */
	public static boolean validatePhoneNumber(String phoneNo) {
		if (!phoneNo.isEmpty() && phoneNo != null) {
			if (phoneNo.matches("\\d{10}"))
				return true;
		}
		return false;
	}
	
	
	/**
	 * Validate Email Address
	 * @param emailId
	 * @return
	 */
	public static boolean validateEmailId(String emailId) {
		String pattern = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		if (!emailId.isEmpty() && emailId != null) {
			if (emailId.matches(pattern)) {
				return true;
			}
		}
		return false;
	}
}
