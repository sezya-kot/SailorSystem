package com.sezyakot.android.sailorapp.sailor.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kot on 27-Sep-14.
 */
public class BondPayment extends ChequePayment {

    @Expose @SerializedName("guarantor_name")   private String mGuarantorName;

    public BondPayment() {}

    public String getGuarantorName() {
        return mGuarantorName;
    }

    public void setGuarantorName(String guarantorName) {
        mGuarantorName = guarantorName;
    }
}
