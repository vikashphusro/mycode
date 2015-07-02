package com.condivision.rentongo.interfaces;

public interface DelegateData {

	public void dataFetchStarted();

	public void dataFetchCompleted(String response);

	public void dateFetchFailed();

}