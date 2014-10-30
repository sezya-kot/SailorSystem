/*
 * Copyright (c) 2014.
 */

package com.sezyakot.sailor;

import com.sezyakot.sailor.model.Invoice;
import com.sezyakot.sailor.model.MainCustomer;

/**
 * Created by Android on 11.09.2014.
 */
public class SalesNewInvoice extends DefaultSalesNew {
	private static final String DCI = "DialogCreateInvoice";

	@Override
	protected void setTitleOfCommand() {
		mTitleOfCommand.setText(getString(R.string.new_invoice));
	}

	@Override
	protected void createObject() {

		mObject = new Invoice();
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

		DialogCreateInvoice dci = new DialogCreateInvoice();
		dci.show(getFragmentManager(), DCI);

	}
}
