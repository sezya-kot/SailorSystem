package com.sezyakot.android.sailorapp.sailor;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.sezyakot.android.sailorapp.sailor.db.DAO;
import com.sezyakot.android.sailorapp.sailor.model.Customer;
import com.sezyakot.android.sailorapp.sailor.adapters.*;
import com.sezyakot.android.sailorapp.sailor.model.MainCustomer;
import com.sezyakot.android.sailorapp.sailor.system.Debug;


import java.sql.SQLException;
import java.util.ArrayList;

public abstract class DefaultCustomerActivity extends DefaultActivity {

    public static final String LOG_TAG = "DefaultCustomerActivity";

	protected ArrayList<Customer> mCustomers;
	protected CustomerAdapter mAdapter;
	protected ListView list;
	protected EditText searchET;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setWrapper(true);
		getLayoutInflater().inflate(R.layout.item_list_pathern, wrapper);
		setContentView(wrapper);
		setCategoryTitle(true);
		setListCustomer();

	}

	protected void setBackArrowAndClickable(TextView tv, boolean flag) {
		if (flag) {
			tv.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_back, 0);
			tv.setOnClickListener(onCloseActivity);
		}
	};

	protected void setListCustomer() {
		/* mCustomers - instance of customers) */
        DAO mDAO = new DAO(this);
        try {
            mDAO.openToRead();
            mCustomers = mDAO.getCustomers();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mDAO.close();

        }

        if(Debug.MODE) { Log.d(LOG_TAG, "mCustomers's address is " + mCustomers); }

		list = (ListView) findViewById(R.id.list);
		/* Create customer adapter */
		mAdapter = new CustomerAdapter(this, mCustomers);
		list.setAdapter(mAdapter);

		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				MainCustomer.get(getBaseContext()).setCustomer((Customer) list.getItemAtPosition(position));
				// Run SelectOptionActivity
				runGroupActivity(v);
			}
		});
		setSearchPanel();
	};

	protected abstract void runGroupActivity(View v);

	private void setSearchPanel() {
		/* Set Search Panel */
		searchET = (EditText) findViewById(R.id.searchET);
		ImageButton searchBtn = (ImageButton) findViewById(R.id.searchBtn);
		searchBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.getFilter().filter(searchET.getText());
            }
        });

		searchET.setOnEditorActionListener(new OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    mAdapter.getFilter().filter(searchET.getText());
                    handled = true;
                }
                return handled;
            }
        });

	}


}
