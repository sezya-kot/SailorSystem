package com.sezyakot.sailor.model;

import android.content.Context;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sezyakot.sailor.system.Preferences;

/**
 * Created by PC on 18.08.2014.
 */
public class Session {
    @Expose @SerializedName("session") protected String mSession;

    public Session() {};

    public Session(Context c) {
        Preferences preferences = new Preferences(c);
        mSession = preferences.getSessionId();
    };


    public String getSession() {
        return mSession;
    }

    public void setSession(String session) {
        this.mSession = session;
    }

    protected void setSession(Context c) {
        Preferences preferences = new Preferences(c);
        mSession = preferences.getSessionId();
    }
}
