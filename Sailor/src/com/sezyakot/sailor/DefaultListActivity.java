/*
 * Copyright (c) 2014.
 */

package com.sezyakot.sailor;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.sezyakot.sailor.adapters.ObjectAdapter;
import com.sezyakot.sailor.db.DAO;
import com.sezyakot.sailor.model.Customer;
import com.sezyakot.sailor.model.DefaultObject;
import com.sezyakot.sailor.model.MainCustomer;
import com.sezyakot.sailor.system.Debug;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Android on 12.09.2014.
 */
public abstract class DefaultListActivity extends DefaultActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

	public static final String LOG_TAG = "DefaultListActivity";

	public static final String EXTRA_OBJECT_ID = "com.sezyakot.sailor.ORDER_ID";
	protected static final int REFUND_TYPE = 4;
	protected static final int INVOICE_TYPE = 3;
	protected static final int DISPATCH_TYPE = 2;
	protected static final int ORDER_TYPE = 1;
	protected DAO mDao = new DAO(this);
	protected DefaultObject mObject;
	protected ObjectAdapter mAdapter;
	protected ListView mListOfOrdersView;
	protected TextView mTitleCommand;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			mDao.openToRead();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		setWrapper();
		setContentView(wrapper);
		setCompanyName();

		mTitleCommand = (TextView) findViewById(R.id.title_of_command);

		setTitleName();

		mListOfOrdersView 		= (ListView) findViewById(R.id.ol_list);
		mAdapter 				= getObjectAdapter();

		mListOfOrdersView       .setAdapter(mAdapter);
		mListOfOrdersView       .setOnItemClickListener(this);
	}

	abstract protected ObjectAdapter getObjectAdapter();

	abstract void setTitleName();

	protected abstract ArrayList<DefaultObject> getObjects();

	abstract int getObjectServerId(int position);

	@Override
	protected ViewGroup setWrapper() {
		super.setWrapper();
		getLayoutInflater().inflate(R.layout.order_list, wrapper, true);
		return wrapper;
	}
//	protected Customer getCustomerFromIntent() {
//		mCustomerId = this.getIntent().getIntExtra(EXTRA_CUSTOMER_ID, 0);
//		Customer customer = mDao.getCustomer(mCustomerId);
//		return customer;
//	}

	protected void setBackClickable() {
		LinearLayout titleLL = (LinearLayout) findViewById(R.id.grey_title_ll);
		titleLL.setOnClickListener(this);
	}

	protected void setCompanyName() {
		TextView tv = (TextView) findViewById(R.id.company_name);
		tv.setText(MainCustomer.get(this).getCustomer().getName());
		setBackClickable();
	};

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.grey_title_ll: {
				finish();
				break;
			}
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		try {
			mDao.openToRead();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		mDao.close();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

		if (Debug.MODE) {
			Log.d(LOG_TAG, "onItemClick()");
		}


	}

	protected void runActivity(Intent i, int position) {
		i.putExtra(EXTRA_OBJECT_ID, getObjectServerId(position));
		startActivity(i);
	}

}
