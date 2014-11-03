/*
 * Copyright (c) 2014.
 */

package com.sezyakot.sailor.model;

import android.content.Context;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android on 03.11.2014.
 */
public class RequestPayment extends Session {
    @Expose @SerializedName("payments")
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
