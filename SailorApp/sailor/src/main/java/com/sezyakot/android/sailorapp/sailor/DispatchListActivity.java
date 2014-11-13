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
public class DispatchListActivity extends DefaultListActivity {

	@Override
	void setTitleName() {
		mTitleCommand.setText(getString(R.string.dispatch_bill_list));
	}

	@Override
	protected ArrayList<DefaultObject> getObjects() {
		return mDao.getDispatches(null);
	}

	@Override
	int getObjectServerId(int position) {
		return getObjects().get(position).getServerId();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent i = new Intent(this, DispatchDetailsActivity.class);
		runActivity(i, position);
	}

	@Override
	protected ObjectAdapter getObjectAdapter() {
		return new ObjectAdapter(this, getObjects(), DISPATCH_TYPE);
	}
}
