package com.sezyakot.android.sailorapp.sailor.system;

import android.content.Context;
import android.content.SharedPreferences;
import com.sezyakot.android.sailorapp.sailor.R;


public class Preferences {
	private SharedPreferences sP;
	private SharedPreferences.Editor editor;
	private Context context;



    public Preferences (Context c) {
		context = c;
		sP = c.getSharedPreferences(context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
		editor = sP.edit();
		
	}
	
	public String getSessionId() {
		return sP.getString(context.getString(R.string.session_id), "");
	}
	
	public String getLicense() {
		return sP.getString(context.getString(R.string.license), "");
	}

    public String getUserName() {
        return sP.getString(context.getString(R.string.user_name), "");
    }
	
	public boolean hasLicense() {
		boolean key = false;
		if (getLicense().trim().length() == 0) return key;
		return !key;
	}

	public void setLicense(String license) {
		editor.putString(context.getString(R.string.license), license);
		editor.commit();
	}
	
	public void setSession(String session) {
		editor.putString(context.getString(R.string.session_id), session);
		editor.commit();
	}

    public void setUserName(String userName){
        editor.putString(context.getString(R.string.user_name), userName);
        editor.commit();
    }
	
	public void setUUID(String UUID) {
		editor.putString(context.getString(R.string.uuid), UUID);
		editor.commit();
	}
	
}
