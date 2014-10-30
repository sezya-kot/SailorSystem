package com.sezyakot.sailor.model;

import android.content.Context;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Android on 20.08.2014.
 * This class for request to server on synchronization
 */
public class Request extends Session {
    @Expose @SerializedName("versions") Versions mVersions;

    public Request() {};

    public Request(Context c){
        super(c);
        mVersions   = new Versions(c);
    }

    public Versions getVersions() {
        return mVersions;
    }

    public void setVersions(Versions pVersions) {
        mVersions = pVersions;
    }
}
