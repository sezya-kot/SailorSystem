package com.sezyakot.android.sailorapp.sailor.model;

import android.content.Context;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sezyakot.android.sailorapp.sailor.db.DAO;
import com.sezyakot.android.sailorapp.sailor.db.DBHelper;

import java.sql.SQLException;

public class Versions {
    @Expose @SerializedName("services")             private int mServices;
	@Expose @SerializedName("products")             private int mProducts;
	@Expose @SerializedName("customers")            private int mCustomers;
	@Expose @SerializedName("currency")             private int mCurrency;
	@Expose @SerializedName("unit")                 private int mUnit;
	@Expose @SerializedName("unit_detail")          private int mUnitDetails;
	@Expose @SerializedName("product_prices")       private int mProductPrices;
	@Expose @SerializedName("service_prices")       private int mServicePrices;
	@Expose @SerializedName("department")           private int mDepartment;
	@Expose @SerializedName("division")             private int mDivision;
	@Expose @SerializedName("plant")                private int mPlant;
	@Expose @SerializedName("warehouse")            private int mWarehouse;
	@Expose @SerializedName("orders")               private int mOrder;
	@Expose @SerializedName("order_item")           private int mOrderItem;
	@Expose @SerializedName("dispatches")           private int mDispatches;
	@Expose @SerializedName("dispatch_item")        private int mDispatchItems;
	@Expose @SerializedName("invoices")             private int mInvoices;
	@Expose @SerializedName("invoice_item")         private int mInvoiceItem;
    @Expose @SerializedName("cash_payment")         private int mCashPayment;
    @Expose @SerializedName("credit_card_payment")  private int mCreditCardPayment;
    @Expose @SerializedName("bond_payment")         private int mBondPayment;
    @Expose
    @SerializedName("cheque_payment")       private int mChequePayment;
    
    public Versions() {}

    public Versions(Context c) {
        mServices           = 0;
        mProducts           = 0;
        mCustomers          = 0;
        mCurrency           = 0;
        mUnit               = 0;
        mUnitDetails        = 0;
        mProductPrices      = 0;
        mServicePrices      = 0;
        mDepartment         = 0;
        mDivision           = 0;
        mPlant              = 0;
        mWarehouse          = 0;
        mOrder              = 0;
        mOrderItem          = 0;
	    mDispatches         = 0;
	    mDispatchItems      = 0;
	    mInvoices           = 0;
	    mInvoiceItem        = 0;
        mCashPayment        = 0;
        mCreditCardPayment  = 0;
        mBondPayment        = 0;
        mChequePayment      = 0;
    }

    public void setVersion(Context appContext) {
        DAO dao = new DAO(appContext);
        try {
            dao.openToRead();
            mCustomers = dao.getMaxVersion(DBHelper.CUSTOMERS_TN);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dao.close();
        }
    }

    public int getServices() {
        return mServices;
    }

    public void setServices(int services) {
        mServices = services;
    }

    public int getProducts() {
        return mProducts;
    }

    public void setProducts(int products) {
        mProducts = products;
    }

    public int getCustomers() {
        return mCustomers;
    }

    public void setCustomers(int customers) {
        mCustomers = customers;
    }

    public int getCurrency() {
        return mCurrency;
    }

    public void setCurrency(int currency) {
        mCurrency = currency;
    }

    public int getUnit() {
        return mUnit;
    }

    public void setUnit(int unit) {
        mUnit = unit;
    }

    public int getUnitDetails() {
        return mUnitDetails;
    }

    public void setUnitDetails(int unitDetails) {
        mUnitDetails = unitDetails;
    }

    public int getProductPrices() {
        return mProductPrices;
    }

    public void setProductPrices(int productPrices) {
        mProductPrices = productPrices;
    }

    public int getServicePrices() {
        return mServicePrices;
    }

    public void setServicePrices(int servicePrices) {
        mServicePrices = servicePrices;
    }

    public int getDepartment() {
        return mDepartment;
    }

    public void setDepartment(int department) {
        mDepartment = department;
    }

    public int getDivision() {
        return mDivision;
    }

    public void setDivision(int division) {
        mDivision = division;
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

    public int getOrder() {
        return mOrder;
    }

    public void setOrder(int order) {
        mOrder = order;
    }

    public int getOrderItem() {
        return mOrderItem;
    }

    public void setOrderItem(int orderItem) {
        mOrderItem = orderItem;
    }

	public int getDispatches() {
		return mDispatches;
	}

	public void setDispatches(int dispatches) {
		mDispatches = dispatches;
	}

	public int getDispatchItems() {
		return mDispatchItems;
	}

	public void setDispatchItems(int dispatchItems) {
		mDispatchItems = dispatchItems;
	}

	public int getInvoices() {
		return mInvoices;
	}

	public void setInvoices(int invoices) {
		mInvoices = invoices;
	}

	public int getInvoiceItem() {
		return mInvoiceItem;
	}

	public void setInvoiceItem(int invoiceItem) {
		mInvoiceItem = invoiceItem;
	}

    public int getCashPayment() {
        return mCashPayment;
    }

    public void setCashPayment(int pCashPayment) {
        mCashPayment = pCashPayment;
    }

    public int getCreditCardPayment() {
        return mCreditCardPayment;
    }

    public void setCreditCardPayment(int pCreditCardPayment) {
        mCreditCardPayment = pCreditCardPayment;
    }

    public int getBondPayment() {
        return mBondPayment;
    }

    public void setBondPayment(int pBondPayment) {
        mBondPayment = pBondPayment;
    }

    public int getChequePayment() {
        return mChequePayment;
    }

    public void setChequePayment(int pChequePayment) {
        mChequePayment = pChequePayment;
    }
}
