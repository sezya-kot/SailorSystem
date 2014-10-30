package com.sezyakot.sailor.system;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceActivity;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.sezyakot.sailor.model.*;

import com.sezyakot.sailor.model.Error;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Net {
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int SOCKET_TIMEOUT = 20000;
    public static DataLogin sDataLogin = new DataLogin();
    public static User user = new User();
	public static final String DATA = "data";
	public static final String LOG_TAG = "NetUser";
    public static final String SERVER_URL = "http://94.158.70.196:8080/service/mobile";
    public static final String SERVER_CUSTOMER_URL = SERVER_URL + "/customers";
	public static final String SERVER_LOGIN_URL = SERVER_URL + "/login";
    public static final String SERVER_ORDER_CREATE = SERVER_URL + "/orders/create";
    public static final String SERVER_DISPATCH_CREATE = SERVER_URL + "/dispatches/create";
	public static final String SERVER_INVOICE_CREATE = SERVER_URL + "/invoices/create";

	public static final String SERVER_LOGOUT_URL = SERVER_URL + "/logout";
    public static final String SERVER_SYNCH_URL = SERVER_URL + "/synchronize";
	static Preferences preferences;

	/**
	 * Check statement of Internet
	 * 
	 * @return true if Internet available, else return false
	 */

	public static boolean isConnected(Context c) {
		ConnectivityManager connMgr = (ConnectivityManager) c.getSystemService(Activity.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected()) return true;
		else return false;
	}

    private void postData(String server_url, String dataJson, Object object, Class pClass) {
        // 1. Create HttpClient
        HttpClient client = new DefaultHttpClient();
        // 2. make POST request to the given URL
        HttpPost post = new HttpPost(server_url);
        // 3. build dataJson
        Gson gson = new Gson();
        // 4. convert JSONObject to JSON to String
        dataJson = gson.toJson(sDataLogin);


    }

	public static boolean logout(Context context) {
		preferences = new Preferences(context);
		try {
			// 1. create HttpClient
            HttpParams httpParameters = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParameters, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpParameters, SOCKET_TIMEOUT);
            HttpClient client = new DefaultHttpClient(httpParameters);
			// 2. make POST request to the given URL
			HttpPost post = new HttpPost(SERVER_LOGOUT_URL);
			// 3. build dataJson
			Gson gson = new Gson();
			// 4. convert JSONObject to JSON to String
			String dataJson = gson.toJson(sDataLogin);

			if (Debug.VERBOSE) {
				Log.v(LOG_TAG, "dataJson: " + dataJson);
			}

			// 5. set json to sDataLogin
			List<NameValuePair> pairs = new ArrayList<NameValuePair>();
			pairs.add(new BasicNameValuePair(DATA, dataJson));

			Log.d(LOG_TAG, "nameValuePairs: " + pairs.toString());

			// 6. set httpPost Entity
			post.setEntity(new UrlEncodedFormEntity(pairs));

			// Perform the request and check the status code
			// 8. Execute POST request to the given URL
			HttpResponse response = client.execute(post);

			StatusLine statusLine = response.getStatusLine();

			if (statusLine.getStatusCode() == 200) {
				//preferences.setLicense("");
				// 9. receive response as inputStream
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();

				if (Debug.MODE) {
					Log.d(LOG_TAG, content.toString());
				}

				// Read the server response and attempt to parse it as
				// JSON

				Reader reader = new InputStreamReader(content);
				if (Debug.MODE) {
					Log.d(LOG_TAG, reader.toString());
				}
				// 10. convert inputStream to string
				user = gson.fromJson(reader, User.class);
				if (Debug.MODE) {
					Log.d(LOG_TAG, user.getData().toString());
					Log.d(LOG_TAG, "" + gson.toJson(user));
				}
				content.close();

			} else {
				Log.e(LOG_TAG, "Server's responded with status code: " + statusLine.getStatusCode());
				return false;
			}
		} catch (JsonSyntaxException | JsonIOException ex) {
			Log.e(LOG_TAG, "Failed to parse JSON due to: " + ex);
			return false;
		} catch (UnsupportedEncodingException e) {
			Log.e(LOG_TAG, "UnsupportedEncodingException: "+e);
			return false;
		} catch (ClientProtocolException e) {
			Log.e(LOG_TAG, "ClientProtocolException: "+e);
			return false;
		} catch (SocketTimeoutException | ConnectTimeoutException e) {
            Log.e(LOG_TAG, "TimeoutException: "+e);
            return false;
        } catch (IOException e) {
			Log.e(LOG_TAG, "IOException: "+e);
			return false;
        }

		if (user.getError().getCode() == 0 && user != null) {
			preferences.setSession("");
			return true;
		} else {
			Log.e(LOG_TAG, "Error from server is: " + user.getError().getCode() + "\n"
			+ "Message: " + user.getError().getMsg());
			return false;
		}
				

	}

	public static boolean login(Context context) {
		try {
			// 1. create HttpClient
            HttpParams httpParameters = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParameters, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpParameters, SOCKET_TIMEOUT);
			HttpClient client = new DefaultHttpClient(httpParameters);
			// 2. make POST request to the given URL
			HttpPost post = new HttpPost(SERVER_LOGIN_URL);
			// 3. build dataJson
			Gson gson = new Gson();
			// 4. convert JSONObject to JSON to String
			String dataJson = gson.toJson(sDataLogin);
            if (Debug.MODE) {
                Log.d(LOG_TAG, "dataJson: " + dataJson);
            }
            // 5. set json to sDataLogin
			List<NameValuePair> pairs = new ArrayList<NameValuePair>();
			pairs.add(new BasicNameValuePair(DATA, dataJson));

            if (Debug.MODE) {
                Log.d(LOG_TAG, "nameValuePairs: " + pairs.toString());
            }

            // 6. set httpPost Entity
			post.setEntity(new UrlEncodedFormEntity(pairs));

            // Perform the request and check the status code
			// 8. Execute POST request to the given URL
            if (Debug.MODE) {
                Log.d(LOG_TAG, "nameValuePairs.length(): " + new UrlEncodedFormEntity(pairs).getContentLength());
            }
			HttpResponse response;
			if (Debug.MODE) { long lStartTime = new Date().getTime();
				response = client.execute(post);
				long lEndTime = new Date().getTime();
				Log.i(LOG_TAG, "Time of response: " +  (lEndTime - lStartTime) + " ms");
			} else response = client.execute(post);

			StatusLine statusLine = response.getStatusLine();
			if (statusLine.getStatusCode() == 200) {

				// 9. receive response as inputStream
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();

                if (Debug.MODE) {
                    Log.d(LOG_TAG, content.toString());
                }

					// Read the server response and attempt to parse it as
					// JSON

					Reader reader = new InputStreamReader(content);
                    if (Debug.MODE) {
                        Log.d(LOG_TAG, reader.toString());
                    }
                    // 10. convert inputStream to string
					user = gson.fromJson(reader, User.class);
                    if (Debug.MODE) {
                        Log.d(LOG_TAG, user.getData().toString());
                        Log.d(LOG_TAG, "" + gson.toJson(user));
                    }

                    content.close();

			} else {
				Log.e(LOG_TAG, "Server's responded with status code: " + statusLine.getStatusCode());
                generateUserErrorCode();
				return false;
			}
		} catch (JsonSyntaxException | JsonIOException ex) {
			Log.e(LOG_TAG, "Failed to parse JSON due to: " + ex);
			return false;
		} catch (UnsupportedEncodingException e) {
			Log.e(LOG_TAG, "UnsupportedEncodingException: "+e);
			return false;
		} catch (ClientProtocolException e) {
			Log.e(LOG_TAG, "ClientProtocolException: " + e);
            generateUserErrorCode();
			return false;
        } catch (SocketTimeoutException | ConnectTimeoutException e) {
            Log.e(LOG_TAG, "TimeoutException: "+e);
            return false;
		} catch (IOException e) {
			Log.e(LOG_TAG, "IOException: "+e);
            generateUserErrorCode();
			return false;
		}
		return true;
		
	}

    private static void generateUserErrorCode() {
        user = new User();
        user.setError(new Error());
        user.getError().setCode(22);
    }


}
