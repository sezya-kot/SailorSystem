package com.sezyakot.android.sailorapp.sailor.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response {

       @Expose
       @SerializedName("error") private Error mError;
       @Expose @SerializedName("data")  private Data mData;

    public Response() {};

    public Error getError() {
        return mError;
    }

    public void setError(Error error) {
        mError = error;
    }

    public Data getData() {
        return mData;
    }

    public void setData(Data data) {
        mData = data;
    }
}
