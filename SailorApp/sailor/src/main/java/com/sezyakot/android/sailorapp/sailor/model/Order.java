package com.sezyakot.android.sailorapp.sailor.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Order extends DefaultObject{


	@Expose
    @SerializedName("advance_payment")  private double                  mAdvancePayment;
	@Expose @SerializedName("order_items")      private List<Item> mOrderItems = null;

    public Order() {}

	public Order(          int id,
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
		super(id,
				serverId,
				customerId,
				slipNumber,
				specialCode,
				date,
				division,
				department,
				plant,
				warehouse,
				currencyId,
				subtotalPrice,
				totalPrice,
				version,
				status );
		mAdvancePayment = advancePayment;
	}
	@Override
    public double getAdvancePayment() {
        return mAdvancePayment;
    }

    public void setAdvancePayment(double advancePayment) {
        mAdvancePayment = advancePayment;
    }

	@Override
	public List<Item> getItems() {
		return mOrderItems;
	}

	@Override
	public void setItems(List<Item> items) {
		mOrderItems = items;
	}
}
