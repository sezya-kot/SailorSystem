package com.sezyakot.android.sailorapp.sailor.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sezyakot.android.sailorapp.sailor.interfaces.Itemer;


import java.util.List;

/**
 * Created by Android on 11.09.2014.
 */
public class DefaultObject extends DefaultData implements Itemer {

	@Expose @SerializedName("customer")         protected int                     mCustomerId;
	@Expose @SerializedName("slip_number")      protected String                  mSlipNumber;
	@Expose @SerializedName("special_code")     protected String                  mSpecialCode = "";
	@Expose @SerializedName("date")             protected String                  mDate;
	@Expose @SerializedName("division")         protected int                     mDivision;
	@Expose @SerializedName("department")       protected int                     mDepartment;
	@Expose @SerializedName("plant")            protected int                     mPlant;
	@Expose @SerializedName("warehouse")        protected int                     mWarehouse;
	@Expose @SerializedName("currency")         protected int                     mCurrency;
	@Expose @SerializedName("subtotal")         protected double                  mSubtotalPrice;
	@Expose @SerializedName("total")            protected double                  mTotalPrice;

	public DefaultObject() {};

	public DefaultObject(  int id,
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
	               int version,
	               int status
	) {
		super(id,serverId,"", version, status);
		mCustomerId         = customerId;
		mSlipNumber         = slipNumber;
		mSpecialCode        = specialCode;
		mDate               = date;
		mDivision           = division;
		mDepartment         = department;
		mPlant              = plant;
		mWarehouse          = warehouse;
		mCurrency           = currencyId;
		mSubtotalPrice      = subtotalPrice;
		mTotalPrice         = totalPrice;
	}

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

	public String getSpecialCode() {
		return mSpecialCode;
	}

	public void setSpecialCode(String specialCode) {
		mSpecialCode = specialCode;
	}

	public String getDate() {
		return mDate;
	}

	public void setDate(String date) {
		mDate = date;
	}

	public int getDivision() {
		return mDivision;
	}

	public void setDivision(int division) {
		mDivision = division;
	}

	public int getDepartment() {
		return mDepartment;
	}

	public void setDepartment(int department) {
		mDepartment = department;
	}

	public int getPlant() {
		return mPlant;
	}

	public void setPlant(int plant) {
		mPlant = plant;
	}

	public int getWarehouse() {
		return mWarehouse;
	}

	public void setWarehouse(int warehouse) {
		mWarehouse = warehouse;
	}

	public int getCurrency() {
		return mCurrency;
	}

	public void setCurrency(int currency) {
		mCurrency = currency;
	}

	public double getSubtotalPrice() {
		return mSubtotalPrice;
	}

	public void setSubtotalPrice(double subtotalPrice) {
		mSubtotalPrice = subtotalPrice;
	}

	public double getTotalPrice() {
		return mTotalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		mTotalPrice = totalPrice;
	}

	@Override
	public List<Item> getItems() {
		return null;
	}

	@Override
	public void setItems(List<Item> items) {

	}

	@Override
	public double getAdvancePayment() {
		return 0;
	}
}
