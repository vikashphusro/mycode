package com.condivision.rentongo.interfaces;

/**
 * Interface to pass data from fragement to fragment via Activity.
 * 
 * @author rentongo
 *
 */
public interface OnMessageListner {

	public void setTitle(String title);
	public void setEnableFilterPanel(boolean b);
	public void setEnableBackButton(boolean b);
	public void setEnableProfileButton(boolean b);
	public void setBikeAvailability(int number);
	public void closeOpenDrawer(boolean b);

}
