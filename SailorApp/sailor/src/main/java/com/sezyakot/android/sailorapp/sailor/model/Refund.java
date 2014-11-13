package com.sezyakot.android.sailorapp.sailor.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Refund extends DefaultObject{
	@Expose
	@SerializedName("refund_amount")          private double             mAdvancePayment;
	@Expose @SerializedName("refund_items")   private List<RefundItem> mRefundItems = null;

	public Refund() {};

	public Refund(          int id,
	                          int serverId,
	                          int customerId,
	                          String slipNumber,
	                          String specialCode,
	                          String date,
	                          int division,
	                          int department,
	                          int plant,
	                          int warehouse,
	                          int currencyId,
	                          double subtotalPrice,
	                          double totalPrice,
	                          double advancePayment,
	                          int version,
	                          int status
	) {
		super(id, serverId, customerId, slipNumber,specialCode, date, division, department, plant, warehouse, currencyId, subtotalPrice, totalPrice,version, status );
		mAdvancePayment = advancePayment;
	}

	public double getAdvancePayment() {
		return mAdvancePayment;
	}

	public void setAdvancePayment(double advancePayment) {
		mAdvancePayment = advancePayment;
	}

	public List<RefundItem> getRefundItems() {
		return mRefundItems;
	}

	public void setRefundItems(List<RefundItem> refundItems) {
		mRefundItems = refundItems;
	}
}
