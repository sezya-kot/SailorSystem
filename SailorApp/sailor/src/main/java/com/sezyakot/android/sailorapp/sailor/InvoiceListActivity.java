package com.sezyakot.android.sailorapp.sailor;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import com.sezyakot.android.sailorapp.sailor.adapters.ObjectAdapter;
import com.sezyakot.android.sailorapp.sailor.model.DefaultObject;

import java.util.ArrayList;

/**
 * Created by Android on 12.09.2014.
 */
public class InvoiceListActivity extends DispatchListActivity {
	@Override
	void setTitleName() {
		mTitleCommand.setText(getString(R.string.invoice_list));
	}

	@Override
	protected ArrayList<DefaultObject> getObjects() {
		return mDao.getInvoices(null);
	}

	@Override
	int getObjectServerId(int position) {
		return getObjects().get(position).getServerId();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent i = new Intent(this, InvoiceDetailsActivity.class);
		runActivity(i, position);
	}

	@Override
	protected ObjectAdapter getObjectAdapter() {
		return new ObjectAdapter(this, getObjects(), INVOICE_TYPE);
	}
}
