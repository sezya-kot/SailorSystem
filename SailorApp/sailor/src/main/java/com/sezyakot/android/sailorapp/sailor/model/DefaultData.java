package com.sezyakot.android.sailorapp.sailor.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

abstract public class DefaultData{
				                protected int	    mId;            // Auto-generate ID
	@Expose @SerializedName("id")       protected int       mServerId;      // id will be gotten from server
	@Expose @SerializedName("name")     protected String	mName;          // Name of customer
	@Expose @SerializedName("version")  protected int       mVersion;       // version for synchronization
	@Expose @SerializedName("status")   protected int       mStatus;

    public DefaultData() {};

    public DefaultData(int id,
                       int serverId,
                       String name,
                       int version,
                       int status) {
        mId = id;
        mServerId = serverId;
        mName = name;
        mVersion = version;
        mStatus = status;
    }


    @Override
    public String toString() {
        return this.mName;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getServerId() {
        return mServerId;
    }

    public void setServerId(int serverId) {
        mServerId = serverId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getVersion() {
        return mVersion;
    }

    public void setVersion(int version) {
        mVersion = version;
    }

    public int getStatus() {
        return mStatus;
    }

    public void setStatus(int status) {
        mStatus = status;
    }
}
