package com.sezyakot.android.sailorapp.sailor;
public class SalesNewRefund extends DefaultSalesNew {
	@Override
	protected void setTitleOfCommand() {
		mTitleOfCommand.setText(getString(R.string.new_refund));
	}

	@Override
	protected void createObject() {

	}
}
