package com.sezyakot.android.sailorapp.sailor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sezyakot.android.sailorapp.sailor.db.DAO;
import com.sezyakot.android.sailorapp.sailor.model.MainCustomer;
import com.sezyakot.android.sailorapp.sailor.system.Debug;


import java.sql.SQLException;

/**
 * Created by Android on 18.09.2014.
 */
public abstract class DefaultFinancialsCreate extends DefaultActivity {

	private static final String LOG_TAG = "DefaultFinancialsCreate";
	protected TextView  mCustomerTitle;
	protected TextView  mTitleName;
	public DAO mDao = new DAO(this);



	@Override
	protected ViewGroup setWrapper() {
		super.setWrapper();
		getLayoutInflater().inflate(R.layout.financial_create_titles, wrapper);
		return wrapper;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		openDAO();
		setWrapper();
		setContentView(wrapper);
		setUI();
		setCustomerTitle(MainCustomer.get(this).getCustomer().getName());
		setTitle();
	}
	protected void setBackClickable() {
		LinearLayout titleLL = (LinearLayout) findViewById(R.id.grey_title_ll);
		titleLL.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (Debug.MODE) {
					Log.d(LOG_TAG, "<-- Click on title) -->");
				}
				setAskDialog();
			}
		});
	}

	@Override
	public void onBackPressed() {
		setAskDialog();
	}

	protected void setAskDialog() {
		DialogExit de = new DialogExit();
		de.show(getFragmentManager(), "de");
	}

	protected abstract void setTitle();

	protected void setUI() {
		mCustomerTitle  = (TextView) findViewById(R.id.company_name);
		mTitleName      = (TextView) findViewById(R.id.fin_subtitle);
	}

	private void setCustomerTitle(String customerTitle) {
		mCustomerTitle.setText(customerTitle);
		setBackClickable();
	}

	@Override
	protected void onPause() {
		super.onPause();
		closeDAO();
	}

	@Override
	protected void onResume() {
		super.onResume();
		openDAO();
	}

	protected void openDAO() {
		try {
			mDao.openToWrite();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	protected void closeDAO() {
		mDao.close();
	}




}
