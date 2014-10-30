/*
 * Copyright (c) 2014.
 */

package com.sezyakot.sailor.model;

import android.content.Context;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sezyakot.sailor.db.DAO;

import java.sql.SQLException;

/**
 * Created by Android on 25.09.2014.
 */
public class Payment extends DefaultData {
    @Expose @SerializedName("slip_number")      protected     String      mSlipNumber;
    @Expose @SerializedName("date")             protected     String      mDate;
    @Expose @SerializedName("special_code")     protected     String      mSpecialCode;
    @Expose @SerializedName("currency_id")      protected     int         mCurrencyId;
    @Expose @SerializedName("amount")           protected     double      mAmount;
    @Expose @SerializedName("description")      protected     String      mDescription;
    @Expose @SerializedName("customer_id")      protected     int         mCustomerId;
                                                protected     String      mCurrencyName;
    public Payment() {};

    public String getSlipNumber() {
        return mSlipNumber;
    }

    public void setSlipNumber(String pSlipNumber) {
        mSlipNumber = pSlipNumber;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String pDate) {
        mDate = pDate;
    }

    public String getSpecialCode() {
        return mSpecialCode;
    }

    public void setSpecialCode(String pSpecialCode) {
        mSpecialCode = pSpecialCode;
    }

    public int getCurrencyId() {
        return mCurrencyId;
    }

    public void setCurrencyId(int pCurrencyId) {
        mCurrencyId = pCurrencyId;
    }

    public double getAmount() {
        return mAmount;
    }

    public void setAmount(double pAmount) {
        mAmount = pAmount;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String pDescription) {
        mDescription = pDescription;
    }

    public int getCustomerId() {
        return mCustomerId;
    }

    public void setCustomerId(int pCustomerId) {
        mCustomerId = pCustomerId;
    }

    public String getCurrencyName() {
        return mCurrencyName;
    }

    public String getCurrencyName(Context pContext) {
        DAO lDAO = new DAO(pContext);
        String curName = null;
        try {
            lDAO.openToRead();
            curName = lDAO.getCurrency(mCurrencyId).getName();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return curName;
    }

    public void setCurrencyName(String pCurrencyName) {
        mCurrencyName = pCurrencyName;
    }
}
