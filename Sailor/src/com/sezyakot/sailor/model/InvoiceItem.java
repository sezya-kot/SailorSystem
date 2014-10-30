/*
 * Copyright (c) 2014.
 */

package com.sezyakot.sailor.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Android on 12.09.2014.
 */
public class InvoiceItem extends Item {

	@Expose @SerializedName("invoice")  protected int       mInvoiceId;

	InvoiceItem() {
		super();
	}
	public InvoiceItem(
			int id,
			int serverId,
			int type,
			int itemId,
			double quantity,
			double itemSubtotalPrice,
			int invoiceId,
			int unitDetailId,
			int version,
			int status
	) {
		super(id, serverId, type, itemId, quantity, itemSubtotalPrice, invoiceId, unitDetailId, version, status);
	}

	public int getInvoiceId() {
		return mInvoiceId;
	}

	public void setInvoiceId(int invoiceId) {
		mInvoiceId = invoiceId;
	}
}
