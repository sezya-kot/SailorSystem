package com.sezyakot.android.sailorapp.sailor.model;

import android.content.Context;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RequestDispatch extends Session {
	@Expose
    @SerializedName("dispatches") private ArrayList<Dispatch> mDispatches;

	public RequestDispatch() {}

	public RequestDispatch(Context appContext) {
		this.setSession(appContext);
	}

	public ArrayList<Dispatch> getDispatches() {
		return mDispatches;
	}

	public void setDispatches(ArrayList<Dispatch> dispatches) {
		mDispatches = dispatches;
	}
}
