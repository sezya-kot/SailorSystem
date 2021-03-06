package com.sezyakot.sailor.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Android on 22.08.2014.
 */
public class OrderItem extends Item {

	@Expose @SerializedName("order")    protected int           mOrderId;

	public OrderItem() {
		super();
	}

	public OrderItem(
			int id,
			int serverId,
			int type,
			int itemId,
			double quantity,
			double itemSubtotalPrice,
			int orderId,
			int unitDetailId,
			int version,
			int status
	) {
		super(id, serverId, type, itemId, quantity, itemSubtotalPrice, orderId, unitDetailId, version, status);
	}

	public int getOrderId() {
		return mOrderId;
	}

	public void setOrderId(int orderId) {
		mOrderId = orderId;
	}
}
