package com.sezyakot.sailor;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Android on 04.09.2014.
 */
public class OrderTemp {
	@SerializedName("customer")         private     int                         mCustomerId;
	@SerializedName("slip_number")      private     String                      mSlipNumber;
	@SerializedName("special_code")     private     String                      mSpecialNumber;
	@SerializedName("date")             private     String                      mDate;
	@SerializedName("subtotal")         private     double                      mSubtotal;
	@SerializedName("total")            private     double                      mGTotal;
	@SerializedName("division")         private     int                         mDivisionId;
	@SerializedName("department")       private     int                         mDepartmentId;
	@SerializedName("plant")            private     int                         mPlantId;
	@SerializedName("warehouse")        private     int                         mWarehouseId;
	@SerializedName("currency")         private     int                         mCurrencyId;
	@SerializedName("advance_payment")  private     double                      mAdvancePayment;
	@SerializedName("order_items")      private     ArrayList<OrderItemsTemp>   mOrderItemsTemps;

	OrderTemp() {};

	public int getCustomerId() {
		return mCustomerId;
	}

	public void setCustomerId(int customerId) {
		mCustomerId = customerId;
	}

	public String getSlipNumber() {
		return mSlipNumber;
	}

	public void setSlipNumber(String slipNumber) {
		mSlipNumber = slipNumber;
	}

	public String getSpecialNumber() {
		return mSpecialNumber;
	}

	public void setSpecialNumber(String specialNumber) {
		mSpecialNumber = specialNumber;
	}

	public String getDate() {
		return mDate;
	}

	public void setDate(String date) {
		mDate = date;
	}

	public double getSubtotal() {
		return mSubtotal;
	}

	public void setSubtotal(double subtotal) {
		mSubtotal = subtotal;
	}

	public double getGTotal() {
		return mGTotal;
	}

	public void setGTotal(double GTotal) {
		mGTotal = GTotal;
	}

	public int getDivisionId() {
		return mDivisionId;
	}

	public void setDivisionId(int divisionId) {
		mDivisionId = divisionId;
	}

	public int getDepartmentId() {
		return mDepartmentId;
	}

	public void setDepartmentId(int departmentId) {
		mDepartmentId = departmentId;
	}

	public int getPlantId() {
		return mPlantId;
	}

	public void setPlantId(int plantId) {
		mPlantId = plantId;
	}

	public int getWarehouseId() {
		return mWarehouseId;
	}

	public void setWarehouseId(int warehouseId) {
		mWarehouseId = warehouseId;
	}

	public int getCurrencyId() {
		return mCurrencyId;
	}

	public void setCurrencyId(int currencyId) {
		mCurrencyId = currencyId;
	}

	public double getAdvancePayment() {
		return mAdvancePayment;
	}

	public void setAdvancePayment(double advancePayment) {
		mAdvancePayment = advancePayment;
	}

	public ArrayList<OrderItemsTemp> getOrderItemsTemps() {
		return mOrderItemsTemps;
	}

	public void setOrderItemsTemps(ArrayList<OrderItemsTemp> orderItemsTemps) {
		mOrderItemsTemps = orderItemsTemps;
	}
}
