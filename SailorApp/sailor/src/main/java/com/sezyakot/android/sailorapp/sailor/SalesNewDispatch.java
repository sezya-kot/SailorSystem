package com.sezyakot.android.sailorapp.sailor;

import com.sezyakot.android.sailorapp.sailor.model.Dispatch;
import com.sezyakot.android.sailorapp.sailor.model.MainCustomer;

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
