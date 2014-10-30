package com.sezyakot.sailor;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.sezyakot.sailor.adapters.ObjectAdapter;
import com.sezyakot.sailor.model.DefaultObject;

import java.util.ArrayList;

/**
 * Created by Android on 30.08.2014.
 */
public class OrderListActivity extends DefaultListActivity{

	@Override
	void setTitleName() {
		mTitleCommand.setText(getString(R.string.order_list));
	}

	@Override
	protected ArrayList<DefaultObject> getObjects() {
		return mDao.getOrders(null);
	}

	@Override
	int getObjectServerId(int position) {
		return getObjects().get(position).getServerId();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent i = new Intent(this, OrderDetailsActivity.class);
		runActivity(i, position);
	}

	@Override
	protected ObjectAdapter getObjectAdapter() {
		return new ObjectAdapter(this, getObjects(), ORDER_TYPE);
	}
}
