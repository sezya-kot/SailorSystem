package com.sezyakot.android.sailorapp.sailor;



import com.sezyakot.android.sailorapp.sailor.db.DBHelper;
import com.sezyakot.android.sailorapp.sailor.model.DefaultObject;
import com.sezyakot.android.sailorapp.sailor.model.Item;

import java.util.ArrayList;

/**
 * Created by Android on 30.08.2014.
 */
public class OrderDetailsActivity extends DefaultDetailsActivity{


	@Override
	void setTitleName() {
		mTitleOfCommand.setText(getString(R.string.order_details));
	}

	@Override
    DefaultObject getObject() {
		return mDao.getOrder(getObjectIdfromIntent());
	}

	@Override
	protected ArrayList<Item> getItems() {
		ArrayList<Item> items = mDao.getItems(DBHelper.ORDERS_TN, DBHelper.ORDER_ITEMS_TN, getObjectIdfromIntent(), PRODUCT);
		items.addAll(mDao.getItems(DBHelper.ORDERS_TN, DBHelper.ORDER_ITEMS_TN, getObjectIdfromIntent(), SERVICE));
		return items;
	}
}
