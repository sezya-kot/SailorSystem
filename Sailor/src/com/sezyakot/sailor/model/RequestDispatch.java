/*
 * Copyright (c) 2014.
 */

package com.sezyakot.sailor.model;

import android.content.Context;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Android on 15.09.2014.
 */
public class RequestDispatch extends Session {
	@Expose @SerializedName("dispatches") private ArrayList<Dispatch> mDispatches;

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
