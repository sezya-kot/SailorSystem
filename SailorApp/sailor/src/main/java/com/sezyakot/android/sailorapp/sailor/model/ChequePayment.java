package com.sezyakot.android.sailorapp.sailor.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kot on 27-Sep-14.
 */
public class ChequePayment extends Payment {

    @Expose @SerializedName("issuer_name")      private String mIssuerName;
    @Expose @SerializedName("serial_number")    private String mSerialNumber;
    @Expose @SerializedName("due_date")         private String mDueDate;

    public ChequePayment() {}

    public String getIssuerName() {
        return mIssuerName;
    }

    public void setIssuerName(String issuerName) {
        mIssuerName = issuerName;
    }

    public String getSerialNumber() {
        return mSerialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        mSerialNumber = serialNumber;
    }

    public String getDueDate() {
        return mDueDate;
    }

    public void setDueDate(String dueDate) {
        mDueDate = dueDate;
    }
}
