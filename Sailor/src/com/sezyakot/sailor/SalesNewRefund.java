/*
 * Copyright (c) 2014.
 */

package com.sezyakot.sailor;

/**
 * Created by Android on 11.09.2014.
 */
public class SalesNewRefund extends DefaultSalesNew {
	@Override
	protected void setTitleOfCommand() {
		mTitleOfCommand.setText(getString(R.string.new_refund));
	}

	@Override
	protected void createObject() {

	}
}
