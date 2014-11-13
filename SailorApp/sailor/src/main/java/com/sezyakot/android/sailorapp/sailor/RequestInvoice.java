package com.sezyakot.android.sailorapp.sailor;
import android.content.Context;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sezyakot.android.sailorapp.sailor.model.Invoice;
import com.sezyakot.android.sailorapp.sailor.model.Session;


import java.util.ArrayList;

/**
 * Created by Android on 15.09.2014.
 */
public class RequestInvoice extends Session {

	@Expose
	@SerializedName("invoices") private ArrayList<Invoice> mInvoices;

	public RequestInvoice() {}

	public RequestInvoice(Context appContext) {
		this.setSession(appContext);
	}

	public ArrayList<Invoice> getInvoices() {
		return mInvoices;
	}

	public void setInvoices(ArrayList<Invoice> invoices) {
		mInvoices = invoices;
	}
}
