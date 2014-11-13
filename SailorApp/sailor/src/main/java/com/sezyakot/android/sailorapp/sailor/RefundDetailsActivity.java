package com.sezyakot.android.sailorapp.sailor;

import com.sezyakot.android.sailorapp.sailor.db.DBHelper;
import com.sezyakot.android.sailorapp.sailor.model.DefaultObject;
import com.sezyakot.android.sailorapp.sailor.model.Item;

import java.util.ArrayList;

/**
 * Created by Android on 12.09.2014.
 */
public class RefundDetailsActivity extends DefaultDetailsActivity {
	@Override
	void setTitleName() {
		mTitleOfCommand.setText(getString(R.string.refund_detail));
	}

	@Override
    DefaultObject getObject() {
		return mDao.getDispatch(getObjectIdfromIntent());
	}

	@Override
	protected ArrayList<Item> getItems() {
		ArrayList<Item> items = mDao.getItems(DBHelper.REFUNDS_TN, DBHelper.REFUND_ITEMS_TN, getObjectIdfromIntent(), PRODUCT);
		items.addAll(mDao.getItems(DBHelper.REFUNDS_TN, DBHelper.REFUND_ITEMS_TN, getObjectIdfromIntent(), SERVICE));
		return items;
	}
}
