/*
 * Copyright (c) 2014.
 */

package com.sezyakot.sailor.model;

import android.content.Context;

/**
 * Created by Android on 18.09.2014.
 */
public class MainCustomer {

	private Customer mCustomer;
	private static MainCustomer sMainCustomer;
	private Context mContext;

	private MainCustomer(Context appContext) {
		mContext = appContext;
	};

	public static MainCustomer get(Context c){
		if (sMainCustomer == null) {
			sMainCustomer = new MainCustomer(c.getApplicationContext());
		}
		return sMainCustomer;
	}

	public Customer getCustomer() {
		return mCustomer;
	}

	public void setCustomer(Customer customer) {
		mCustomer = customer;
	}
}
