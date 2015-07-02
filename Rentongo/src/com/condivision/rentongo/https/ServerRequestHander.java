package com.condivision.rentongo.https;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class ServerRequestHander {
	
	private static final String TAG = "ServerRequestHander";
	
	private DefaultHttpClient httpClient;
	private Object nameValuePairs;
	private static ServerRequestHander serverRequest;

	private ServerRequestHander() {
		// Create HTTPClient with http version 1.1
		HttpParams params = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(params, 10000);
		HttpConnectionParams.setSoTimeout(params, 10000);
		httpClient = new DefaultHttpClient(params);

	}

	/**
	 * Since httpclient holds the session information, it is needed for all
	 * requests after login. So declared this class as singleton
	 * 
	 * @return
	 */
	public static ServerRequestHander getInstance() {

		if (serverRequest == null) {
			serverRequest = new ServerRequestHander();
		}

		return serverRequest;
	}

}
