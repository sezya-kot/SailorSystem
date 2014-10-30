/*
 * Copyright (c) 2014.
 */

package com.sezyakot.sailor.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Android on 12.09.2014.
 */
public class Invoice extends DefaultObject{
	@Expose
	@SerializedName("advance_payment")          private double                 mAdvancePayment;
	@Expose @SerializedName("invoice_items")    private List<Item>             mItems = null;

	public Invoice() {}

	public Invoice(           int id,
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

	public List<Item> getItems() {
		return mItems;
	}

	public void setItems(List<Item> items) {
		mItems = items;
	}
}
