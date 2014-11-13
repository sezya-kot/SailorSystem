package com.sezyakot.android.sailorapp.sailor.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RefundItem extends Item {

	@Expose
    @SerializedName("refund")   protected int       mRefundId;

	RefundItem() {
		super();
	}
	public RefundItem(
			int id,
			int serverId,
			int type,
			int itemId,
			double quantity,
			double itemSubtotalPrice,
			int refundId,
			int unitDetailId,
			int version,
			int status
	) {
		super(id, serverId, type, itemId, quantity, itemSubtotalPrice, refundId, unitDetailId, version, status);
	}

	public int getRefundId() {
		return mRefundId;
	}

	public void setRefundId(int refundId) {
		mRefundId = refundId;
	}
}
