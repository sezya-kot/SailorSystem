/*
 * Copyright (c) 2014.
 */

package com.sezyakot.sailor.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Android on 11.09.2014.
 */
public class DispatchItem extends Item {

	@Expose @SerializedName("dispatch")     protected int       mDispatchId;

	DispatchItem() {
		super();
	}
	public DispatchItem(
			int id,
			int serverId,
			int type,
			int itemId,
			double quantity,
			double itemSubtotalPrice,
			int dispatchId,
			int unitDetailId,
			int version,
			int status
	) {
		super(id, serverId, type, itemId, quantity, itemSubtotalPrice, dispatchId, unitDetailId, version, status);
	}

	public int getDispatchId() {
		return mDispatchId;
	}

	public void setDispatchId(int dispatchId) {
		mDispatchId = dispatchId;
	}
}
