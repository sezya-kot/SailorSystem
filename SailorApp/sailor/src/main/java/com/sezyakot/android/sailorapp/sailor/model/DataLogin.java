package com.sezyakot.android.sailorapp.sailor.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataLogin {
	@Expose
    @SerializedName("session")	private String mSession = "";
    @Expose @SerializedName("uuid")		private String mUUID = "";
    @Expose @SerializedName("email")	private String mEmail = "";
    @Expose @SerializedName("password")	private String mPassword = "";
    @Expose @SerializedName("license")	private String mLicense = "";
	

	public DataLogin() {
		//constructor
	}

	public String toString() {
		String str;
            str = 	"Session: " 	+ mSession +
                    "COL_ID: " 		+ mUUID +
                    "Email: " 		+ mEmail +
                    "Password: " 	+ mPassword +
                    "License: " 	+ mLicense;
		return str;

	}

	public void test1() {
		mSession = "41e492078d79082c43dbb8c6c1a57b5c";
		mUUID = "28";
		mEmail = "test@test.com";
		mPassword = "test";
		mLicense = "0987654321";
	}
	public void test2() {
		mSession = "";
		mUUID = "28";
		mEmail = "test@test.com";
		mPassword = "test";
		mLicense = "0987654321";
	}

	public String getSession() {
		return mSession;
	}

	public void setSession(String session) {
		mSession = session;
	}

	public String getUUID() {
		return mUUID;
	}

	public void setUUID(String uUID) {
		mUUID = uUID;
	}

	public String getEmail() {
		return mEmail;
	}

	public void setEmail(String email) {
		mEmail = email;
	}

	public String getPassword() {
		return mPassword;
	}

	public void setPassword(String password) {
		mPassword = password;
	}

	public String getLicense() {
		return mLicense;
	}

	public void setLicense(String license) {
		mLicense = license;
	}


}
