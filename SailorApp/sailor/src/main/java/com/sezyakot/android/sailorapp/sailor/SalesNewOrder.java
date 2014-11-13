package com.sezyakot.android.sailorapp.sailor;

import com.sezyakot.android.sailorapp.sailor.model.MainCustomer;
import com.sezyakot.android.sailorapp.sailor.model.Order;

/**
 * Created by Android on 11.09.2014.
 */
public class SalesNewOrder extends DefaultSalesNew{

	public static final String LOG_TAG = "SalesNewOrder";
	private TaskFragment mTaskFragment;


	@Override
	protected void setTitleOfCommand() {
		mTitleOfCommand.setText(getString(R.string.new_order));
	}

	@Override
	protected void createObject() {
		mObject = new Order();
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

		DialogCreateOrder dco = new DialogCreateOrder();
		dco.show(getFragmentManager(), DCO);
	}


}
