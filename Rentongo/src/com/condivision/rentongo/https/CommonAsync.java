package com.condivision.rentongo.https;

import org.json.JSONObject;

import android.os.AsyncTask;

import com.condivision.rentongo.interfaces.DelegateData;

public class CommonAsync extends AsyncTask<JSONObject, Void, String> { 
	
	private static final String TAG = "CommonAsync";

	public DelegateData mDelegateData;
	private String mUrl = "";
	private String jsonRequestBody;
	private String status = "";

	public void setUrl(String url) {
		this.mUrl = url;
	}

	public void setRequestBody(String jsonObject) {
		this.jsonRequestBody = jsonObject;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		mDelegateData.dataFetchStarted();
	}

	/**/
	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);

		mDelegateData.dataFetchCompleted(result);

	}

	@Override
	protected String doInBackground(JSONObject... params) {
		String response = "";

		return response;
	}

}
