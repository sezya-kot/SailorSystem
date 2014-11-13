package com.sezyakot.android.sailorapp.sailor.model;

import android.content.Context;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RequestPayment extends Session {
    @Expose
    @SerializedName("payments")
    List<? extends Payment> mPayments;

    RequestPayment(){};

    public RequestPayment(Context appContext) {
        this.setSession(appContext);
    }

    public List<? extends Payment> getPayments() {
        return mPayments;
    }

    public void setPayments(List<? extends Payment> pPayments) {
        mPayments = pPayments;
    }
}
