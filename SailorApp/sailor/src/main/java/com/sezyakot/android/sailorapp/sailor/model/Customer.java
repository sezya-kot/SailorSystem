package com.sezyakot.android.sailorapp.sailor.model;

import com.google.gson.annotations.*;

public class Customer extends DefaultData{

	@Expose @SerializedName("longitude")private double  mLongitude;     // GPS coordinates
	@Expose @SerializedName("latitude") private double  mLatitude;      // GPS coordinates

    public Customer() {};

    // Constructor's
	public Customer(String name) {
        this.mName = name;
	}

	public Customer(int id, int serverId, String name, double longitude, double latitude, int version, int status) {
		super(id, serverId, name, version, status);
		this.mLongitude = longitude;
		this.mLatitude  = latitude;
	}



    // Getters and setters
	public double getLongitude() { return mLongitude; }
	public void setLongitude(double longitude) { mLongitude = longitude; }
	
	public double getLatitude() { return mLatitude; }
	public void setLatitude(double latitude) { mLatitude = latitude; }
}
