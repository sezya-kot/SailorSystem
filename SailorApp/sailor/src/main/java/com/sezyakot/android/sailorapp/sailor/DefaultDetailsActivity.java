package com.sezyakot.android.sailorapp.sailor;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.sezyakot.android.sailorapp.sailor.adapters.ItemAdapter;
import com.sezyakot.android.sailorapp.sailor.db.DAO;
import com.sezyakot.android.sailorapp.sailor.model.DefaultObject;
import com.sezyakot.android.sailorapp.sailor.model.Item;
import com.sezyakot.android.sailorapp.sailor.model.MainCustomer;
import com.sezyakot.android.sailorapp.sailor.system.Preferences;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android on 12.09.2014.
 */
public abstract class DefaultDetailsActivity  extends DefaultActivity implements View.OnClickListener {

	protected DAO mDao = new DAO(this);
	public static final int PRODUCT = 1;
	public static final int SERVICE = 2;

	protected ItemAdapter mAdapter;
	protected List<Item> mItems;
	protected ListView mListOfOrdersDetailsView;

	protected TextView mOdlDate;
	protected TextView mOdlSlipNumber;
	protected TextView mOdlCreateBy;
	protected TextView mOdlSubtotal;
	protected TextView mOdlVat;
	protected TextView mOdlGTotal;
	protected TextView mOdlAdvancePayment;
	protected TextView mTitleOfCommand;
	
	

	protected String mCurrencyStr;


	protected void setUI() {
		mOdlDate            = (TextView) findViewById(R.id.odl_date);
		mOdlSlipNumber      = (TextView) findViewById(R.id.odl_slip_number);
		mOdlCreateBy        = (TextView) findViewById(R.id.odl_created_by);
		mOdlSubtotal        = (TextView) findViewById(R.id.odl_subtotal);
		mOdlVat             = (TextView) findViewById(R.id.odl_vat);
		mOdlGTotal          = (TextView) findViewById(R.id.odl_g_total);
		mOdlAdvancePayment  = (TextView) findViewById(R.id.odl_advance_payment);
		mTitleOfCommand     = (TextView) findViewById(R.id.title_of_command);
	};

	protected void fillData(DefaultObject object) {
		mOdlDate            .setText(object.getDate());
		mOdlSlipNumber      .setText(object.getSlipNumber());
		mOdlCreateBy        .setText(new Preferences(this).getUserName());
		mOdlSubtotal        .setText("" + object.getSubtotalPrice() + " " + mCurrencyStr);
		mOdlVat             .setText("" + (object.getTotalPrice() - object.getSubtotalPrice())+ " " + mCurrencyStr);
		mOdlGTotal          .setText("" + object.getTotalPrice()+ " " + mCurrencyStr);
		mOdlAdvancePayment  .setText("" + object.getAdvancePayment()+ " " + mCurrencyStr);
	}



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
		setUI();
		setTitleName();

		mItems = getItems();
		if (mItems == null || mItems.size() == 0) mCurrencyStr = "";
		else mCurrencyStr = mItems.get(0).getCurrency();


		fillData(getObject());

		mListOfOrdersDetailsView 		= (ListView) findViewById(R.id.odl_list);
		mAdapter 				        = new ItemAdapter(this, mItems);

		mListOfOrdersDetailsView        .setAdapter(mAdapter);
	}
	abstract void setTitleName();


	protected abstract ArrayList<Item> getItems();

	abstract DefaultObject getObject();

	@Override
	protected ViewGroup setWrapper() {
		super.setWrapper();
		getLayoutInflater().inflate(R.layout.order_details_list, wrapper, true);
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

	protected int getObjectIdfromIntent() {
		return getIntent().getIntExtra(DefaultListActivity.EXTRA_OBJECT_ID, 0);
	}

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
}
