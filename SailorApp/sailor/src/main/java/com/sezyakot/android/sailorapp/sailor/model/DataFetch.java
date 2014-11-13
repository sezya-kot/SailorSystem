package com.sezyakot.android.sailorapp.sailor.model;

import com.google.gson.annotations.SerializedName;

public class DataFetch {
	@SerializedName("session")	private String mSession;
	
	DataFetch() {
		//constructor
	}
	
	@Override
	public String toString() {
		return 	"Session: "	+	mSession;
	}

	public String getSession() {
		return mSession;
	}

	public void setSession(String session) {
		mSession = session;
	}
	
	
}
