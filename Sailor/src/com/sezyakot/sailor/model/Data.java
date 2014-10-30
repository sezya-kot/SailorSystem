package com.sezyakot.sailor.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Android on 20.08.2014.
 */
public class Data {

    @Expose @SerializedName("products")             private     ArrayList<Product>           mProducts;
	@Expose @SerializedName("services")             private     ArrayList<Service>           mServices;
	@Expose @SerializedName("customers")            private     ArrayList<Customer>          mCustomers;
	@Expose @SerializedName("currency")             private     ArrayList<Currency>          mCurrencies;
	@Expose @SerializedName("unit")                 private     ArrayList<Unit>              mUnits;
	@Expose @SerializedName("unit_detail")          private     ArrayList<UnitDetail>        mUnitDetails;
	@Expose @SerializedName("service_prices")       private     ArrayList<ServicePrice>      mServicePrices;
	@Expose @SerializedName("product_prices")       private     ArrayList<ProductPrice>      mProductPrices;
	@Expose @SerializedName("department")           private     ArrayList<Department>        mDepartments;
	@Expose @SerializedName("division")             private     ArrayList<Division>          mDivisions;
	@Expose @SerializedName("plant")                private     ArrayList<Plant>             mPlants;
	@Expose @SerializedName("warehouse")            private     ArrayList<Warehouse>         mWarehouses;
	@Expose @SerializedName("orders")               private     ArrayList<Order>             mOrders;
	@Expose @SerializedName("order_item")           private     ArrayList<OrderItem>         mOrderItems;
	@Expose @SerializedName("dispatches")           private     ArrayList<Dispatch>          mDispatches;
	@Expose @SerializedName("dispatch_item")        private     ArrayList<DispatchItem>      mDispatchItems;
	@Expose @SerializedName("invoices")             private     ArrayList<Invoice>           mInvoices;
	@Expose @SerializedName("invoice_item")         private     ArrayList<InvoiceItem>       mInvoiceItems;
    @Expose @SerializedName("cash_payment")         private     ArrayList<CashPayment>       mCashPayments;
    @Expose @SerializedName("credit_card_payment")  private     ArrayList<CreditCardPayment> mCreditCardPayments;
    @Expose @SerializedName("cheque_payment")       private     ArrayList<ChequePayment>     mChequePayments;
    @Expose @SerializedName("bond_payment")         private     ArrayList<BondPayment>       mBondPayments;

    Data() {}

	public ArrayList<Product> getProducts() {
		return mProducts;
	}

	public void setProducts(ArrayList<Product> products) {
		mProducts = products;
	}

	public ArrayList<Service> getServices() {
        return mServices;
    }

    public void setServices(ArrayList<Service> services) {
        mServices = services;
    }

    public ArrayList<Customer> getCustomers() {
        return mCustomers;
    }

    public void setCustomers(ArrayList<Customer> customers) {
        mCustomers = customers;
    }

    public ArrayList<Currency> getCurrencies() {
        return mCurrencies;
    }

    public void setCurrencies(ArrayList<Currency> currencies) {
        mCurrencies = currencies;
    }

    public ArrayList<Unit> getUnits() {
        return mUnits;
    }

    public void setUnits(ArrayList<Unit> units) {
        mUnits = units;
    }

    public ArrayList<UnitDetail> getUnitDetails() {
        return mUnitDetails;
    }

    public void setUnitDetails(ArrayList<UnitDetail> unitDetails) {
        mUnitDetails = unitDetails;
    }

    public ArrayList<ServicePrice> getServicePrices() {
        return mServicePrices;
    }

    public void setServicePrices(ArrayList<ServicePrice> servicePrices) {
        mServicePrices = servicePrices;
    }

    public ArrayList<ProductPrice> getProductPrices() {
        return mProductPrices;
    }

    public void setProductPrices(ArrayList<ProductPrice> productPrices) {
        mProductPrices = productPrices;
    }

    public ArrayList<Department> getDepartments() {
        return mDepartments;
    }

    public void setDepartments(ArrayList<Department> departments) {
        mDepartments = departments;
    }

    public ArrayList<Division> getDivisions() {
        return mDivisions;
    }

    public void setDivisions(ArrayList<Division> divisions) {
        mDivisions = divisions;
    }

    public ArrayList<Plant> getPlants() {
        return mPlants;
    }

    public void setPlants(ArrayList<Plant> plants) {
        mPlants = plants;
    }

    public ArrayList<Warehouse> getWarehouses() {
        return mWarehouses;
    }

    public void setWarehouses(ArrayList<Warehouse> warehouses) {
        mWarehouses = warehouses;
    }

    public ArrayList<Order> getOrders() {
        return mOrders;
    }

    public void setOrders(ArrayList<Order> orders) {
        mOrders = orders;
    }

    public ArrayList<OrderItem> getOrderItems() {
        return mOrderItems;
    }

    public void setOrderItems(ArrayList<OrderItem> orderItems) {
        mOrderItems = orderItems;
    }

	public ArrayList<Dispatch> getDispatches() {
		return mDispatches;
	}

	public void setDispatches(ArrayList<Dispatch> dispatches) {
		mDispatches = dispatches;
	}

	public ArrayList<DispatchItem> getDispatchItems() {
		return mDispatchItems;
	}

	public void setDispatchItems(ArrayList<DispatchItem> dispatchItems) {
		mDispatchItems = dispatchItems;
	}

	public ArrayList<Invoice> getInvoices() {
		return mInvoices;
	}

	public void setInvoices(ArrayList<Invoice> invoices) {
		mInvoices = invoices;
	}

	public ArrayList<InvoiceItem> getInvoiceItems() {
		return mInvoiceItems;
	}

	public void setInvoiceItems(ArrayList<InvoiceItem> invoiceItems) {
		mInvoiceItems = invoiceItems;
	}

    public ArrayList<CashPayment> getCashPayments() {
        return mCashPayments;
    }

    public void setCashPayments(ArrayList<CashPayment> pCashPayments) {
        mCashPayments = pCashPayments;
    }

    public ArrayList<CreditCardPayment> getCreditCardPayments() {
        return mCreditCardPayments;
    }

    public void setCreditCardPayments(ArrayList<CreditCardPayment> pCreditCardPayments) {
        mCreditCardPayments = pCreditCardPayments;
    }

    public ArrayList<ChequePayment> getChequePayments() {
        return mChequePayments;
    }

    public void setChequePayments(ArrayList<ChequePayment> pChequePayments) {
        mChequePayments = pChequePayments;
    }

    public ArrayList<BondPayment> getBondPayments() {
        return mBondPayments;
    }

    public void setBondPayments(ArrayList<BondPayment> pBondPayments) {
        mBondPayments = pBondPayments;
    }
}
