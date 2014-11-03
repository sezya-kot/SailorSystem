/*
 * Copyright (c) 2014.
 */

package com.sezyakot.sailor.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sezyakot.sailor.db.DAO;

import java.sql.SQLException;

/**
 * Created by Android on 25.09.2014.
 */
public class Payment extends DefaultData implements Parcelable {

    public static final String PAYMENT = "payment";

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeIntArray(new int[] {
                mId,
                mServerId,
                mVersion,
                mStatus,
                mCurrencyId,
                mCustomerId
        });

        out.writeDouble(mAmount);

        out.writeStringArray(new String[] {
                mName,
                mSlipNumber,
                mDate,
                mSpecialCode,
                mDescription,
                mCurrencyName
        });
    }

    public static final Creator<Payment> CREATOR = new Creator<Payment>() {
        @Override
        public Payment createFromParcel(Parcel in) {
            return new Payment(in);
        }

        @Override
        public Payment[] newArray(int size) {
            return new Payment[size];
        }
    };

    private Payment(Parcel in) {
        int[] dataInt = new int[6];
        in.readIntArray(dataInt);
        {
            mId = dataInt[0];
            mServerId = dataInt[1];
            mVersion = dataInt[2];
            mStatus = dataInt[3];
            mCurrencyId = dataInt[4];
            mCustomerId = dataInt[5];
        }

        mAmount = in.readDouble();

        String[] dataStr = new String[6];
        {
            mName = dataStr[0];
            mSlipNumber = dataStr[1];
            mDate = dataStr[2];
            mSpecialCode = dataStr[3];
            mDescription = dataStr[4];
            mCurrencyName = dataStr[5];
        }

    }
}
