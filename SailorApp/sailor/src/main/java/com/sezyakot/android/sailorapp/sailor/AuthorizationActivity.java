package com.sezyakot.android.sailorapp.sailor;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.sezyakot.android.sailorapp.sailor.system.Debug;
import com.sezyakot.android.sailorapp.sailor.system.Net;
import com.sezyakot.android.sailorapp.sailor.system.Preferences;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Serge Cat
 * @version 1.0
 */
public class AuthorizationActivity extends DefaultActivity {

	private ProgressDialog dialog;
	private Button loginBtn;
	private EditText etEmail;
	private EditText etPassword;
	private EditText etLicense;
	private LinearLayout llLisenceView;

	public static final String TAG = "AuthorizationActivity";
	Context context;
	Activity activity;
	Preferences preferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
        if (Debug.MODE) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectDiskReads()
                    .detectDiskWrites()
                    .detectNetwork()
                    .penaltyLog()
                    .build()
            );
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectLeakedSqlLiteObjects()
                    .detectLeakedClosableObjects()
                    .penaltyLog()
                    .penaltyDeath()
                    .build()
            );
        }
		super.onCreate(savedInstanceState);
		setWrapper();
		getLayoutInflater().inflate(R.layout.login, wrapper);
		setContentView(wrapper);
		
		//get main context of application
		context = this.getApplication();
		activity = this;
                                                                                                        if (!Debug.WITHOUT_AUTORIZATION) {
                                                                                                            Intent i = new Intent(context, MainMenu.class);
                                                                                                            startActivity(i);
                                                                                                        }

        // get preferences
		preferences = new Preferences(context);
		if (Debug.MODE) {
			Log.d(TAG, "Session id: " + preferences.getSessionId());
		}
		// linked UI objects with resources
		loginBtn 	  = (Button) 		findViewById(R.id.enterBtn);
		etEmail 	  = (EditText) 		findViewById(R.id.email);
		etPassword 	  = (EditText) 		findViewById(R.id.pass);
		etLicense	  = (EditText) 		findViewById(R.id.license);
		llLisenceView = (LinearLayout)	findViewById(R.id.license_ll);
		etLicense.setText(preferences.getLicense());

		loginBtn.setFocusable(true);

		// if license exit in memory - get it, else - empty
		
		//preferences.setLicense("         ");
		if (preferences.hasLicense()) {
			llLisenceView.setVisibility(View.GONE);
		} else {
			llLisenceView.setVisibility(View.VISIBLE);
		}
		// Click button handler
		loginBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//check empty fields
				boolean lTestKey = 	etEmail.getText().toString().trim().equals("") || 
									etPassword.getText().toString().trim().equals("") || 
									etLicense.getText().toString().trim().equals("");
				
				if (lTestKey) {
					Toast.makeText(context, R.string.fill_fields, Toast.LENGTH_SHORT).show();
				} else {
					setData();
					if (Net.isConnected(context)) {
						try {
							PostFetcher fetcher = new PostFetcher();
							fetcher.execute();
						} catch (Exception e) {
							if (Debug.MODE) {
								Log.d(TAG, "" + e);
							}
						}
					}
					else Toast.makeText(context, getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
				}
			}
		});

	}

	@Override
	protected void onPause() {
		if (dialog != null) dialog.dismiss();
		super.onPause();
	}
	
	private void setupDialog() {
		dialog = new ProgressDialog(this);
		dialog.setMessage(getString(R.string.connecting));
		dialog.setIndeterminate(true);
		dialog.setCancelable(false);
	}

	private class PostFetcher extends AsyncTask<Void, Void, String> {
		public static final String TAG = "PostFetcher";
		Boolean key = false;
		
		@Override
		protected String doInBackground(Void... params) {
			key = Net.login(context);			
			return null;
		}

		@Override
		protected void onPreExecute() {
			lockScreenOrientation();
			setupDialog();
			dialog.show();
			super.onPreExecute();
		}

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);

        }

        @Override
		protected void onPostExecute(String result) {
//			if (key) {
//                switch (Net.user.getError().getCode()) {
//                    case 0:
//                        preferences.setLicense(Net.sDataLogin.getLicense());
//                        preferences.setSession(Net.user.getData().getSession());
//                        preferences.setUUID(Net.sDataLogin.getUUID());
//                        preferences.setUserName(etEmail.getText().toString());
//
//                        Intent i = new Intent(context, MainMenu.class);
//                        startActivity(i);
//
//                        activity.finish();
//                        break;
//                    case 5: Toast.makeText(context, getString(R.string.no_login_pass), Toast.LENGTH_SHORT).show(); break;
//                    case 40:
//                    case 4:
//                    case 31: Toast.makeText(context, getString(R.string.no_license), Toast.LENGTH_SHORT).show(); break;
//                    case 3:
//                    case 6:
//                    case 22: Toast.makeText(context, getString(R.string.try_again), Toast.LENGTH_SHORT).show(); break;
//                    default: Toast.makeText(context, getString(R.string.try_again), Toast.LENGTH_SHORT).show(); break;
//                }
//			} else {
//                Toast.makeText(context, getString(R.string.some_problem_with_server), Toast.LENGTH_SHORT).show();
//            }

            Intent i = new Intent(context, MainMenu.class);
            startActivity(i);
			
			if (dialog != null) dialog.dismiss();
			unlockScreenOrientation();
			super.onPostExecute(result);
		}
	}

	
	/**
	 * Generate COL_ID for current application
	 * @return COL_ID
	 */
	private String getUUID() {
		String ID = null;
		TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
		
		if (tm != null)
		      ID = tm.getDeviceId();
		if (ID == null || ID .length() == 0)
		      ID = Secure.getString(getContentResolver(),Secure.ANDROID_ID);
		
		ID = md5(ID);
		return ID;
	}
	
	/**
	 * MD5 coder
	 * @return md5-hash
	 */
	public final String md5(final String s) {
	    try {
	        // Create MD5 Hash
	        MessageDigest digest = java.security.MessageDigest
	                .getInstance("MD5");
	        digest.update(s.getBytes());
	        byte messageDigest[] = digest.digest();
	 
	        // Create Hex String
	        StringBuffer hexString = new StringBuffer();
	        for (int i = 0; i < messageDigest.length; i++) {
	            String h = Integer.toHexString(0xFF & messageDigest[i]);
	            while (h.length() < 2)
	                h = "0" + h;
	            hexString.append(h);
	        }
	        return hexString.toString();
	 
	    } catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    }
	    return "";
	}
	
	private void lockScreenOrientation() {
	    int currentOrientation = getResources().getConfiguration().orientation;
	    if (currentOrientation == Configuration.ORIENTATION_PORTRAIT) {
	        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	    } else {
	        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
	    }
	}
	 
	private void unlockScreenOrientation() {
	    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
	}

	/**
	 * 
	 */
	private void setData() {
		Net.sDataLogin.setEmail(etEmail.getText().toString());
		Net.sDataLogin.setPassword(etPassword.getText().toString());
		Net.sDataLogin.setLicense(etLicense.getText().toString());
		Net.sDataLogin.setUUID(getUUID());
		Net.sDataLogin.setSession(preferences.getSessionId());

		if (Debug.MODE) {
			Log.d(TAG, "sDataLogin: " + Net.sDataLogin.toString());
		}
	}
	
}
