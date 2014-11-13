package com.sezyakot.android.sailorapp.sailor.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServicePrice extends DefaultData {

	@Expose @SerializedName("service")          private     int     mServiceId;
	@Expose
    @SerializedName("price")            private     double  mPrice;
	@Expose @SerializedName("currency")         private     int     mCurrencyId;

    public ServicePrice() {}

    public ServicePrice(     int id,
                      int serverId,
                      int serviceId,
                      double price,
                      int currencyId,
                      int version) {
        super(id, serverId, "", version, 0);

        mServiceId  = serviceId;
        mPrice      = price;
        mCurrencyId = currencyId;
    }

    public int getServiceId() {
        return mServiceId;
    }

    public void setServiceId(int serviceId) {
        mServiceId = serviceId;
    }

    public double getPrice() {
        return mPrice;
    }

    public void setPrice(double price) {
        mPrice = price;
    }

    public int getCurrencyId() {
        return mCurrencyId;
    }

    public void setCurrencyId(int currencyId) {
        mCurrencyId = currencyId;
    }
}
