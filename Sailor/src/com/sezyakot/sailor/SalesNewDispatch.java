/*
 * Copyright (c) 2014.
 */

package com.sezyakot.sailor;

import com.sezyakot.sailor.model.Dispatch;
import com.sezyakot.sailor.model.MainCustomer;

/**
 * Created by Android on 11.09.2014.
 */
public class SalesNewDispatch extends DefaultSalesNew {
	private static final String DCD = "DialogCreateDispatch";

	@Override
	protected void setTitleOfCommand() {
		mTitleOfCommand.setText(getString(R.string.new_dispatch_bill));
	}

	@Override
	protected void createObject() {
		mObject = new Dispatch();
		mObject.setCustomerId(MainCustomer.get(this).getCustomer().getServerId());
		mObject.setCurrency(mCurrencyId);
		mObject.setSlipNumber(mSlipNumber);
		mObject.setSpecialCode(mSpecialCode);
		mObject.setDate(mDateET.getText().toString());
		mObject.setDivision(mDivisionId);
		mObject.setDepartment(mDepartmentId);
		mObject.setPlant(mPlantId);
		mObject.setWarehouse(mWarehouseId);
		mObject.setSubtotalPrice(mSubtotal);
		mObject.setTotalPrice(mGTotal);
		//        mObject.setTotalVAT(mVAT);
		mObject.setItems(mItems);

		DialogCreateDispatch dcd = new DialogCreateDispatch();
		dcd.show(getFragmentManager(), DCD);

	}
}
