package com.sezyakot.sailor;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.sezyakot.sailor.db.DAO;
import com.sezyakot.sailor.model.Customer;
import com.sezyakot.sailor.model.MainCustomer;

import java.sql.SQLException;

public class DefaultGroupActivity extends DefaultActivity {

	public final static String EXTRA_SUBTITLE = "com.sezyakot.sailor.SUBTITLE";
	private final static String TAG = "DefaultGroupActivity";
	protected static final String SELECTED_ID = "selectedID";

	protected       TextView mSibtitleName;
	protected       RadioGroup mRadioGroup;
	protected       RadioButton mRadioButton;
	protected       int   selectedID;
	protected       LinearLayout mSibtitleNameLL;

	@Override
	protected ViewGroup setWrapper(boolean titleFlag) {
		super.setWrapper(titleFlag);
		getLayoutInflater().inflate(R.layout.company_name_subtitle, wrapper);
		getLayoutInflater().inflate(R.layout.item_group, wrapper);
		return wrapper;
	}

//	protected void getCustomerFromIntent() {
//    }

	protected void setSubtitle() {
		mSibtitleName = (TextView) findViewById(R.id.customerName);
		mSibtitleNameLL = (LinearLayout) findViewById(R.id.customer_name_ll);
		mSibtitleName.setText(MainCustomer.get(this).getCustomer().getName());
		mSibtitleNameLL.setOnClickListener(onCloseActivity);
	};

	private void setRadioGroup() {
		mRadioGroup = (RadioGroup) findViewById(R.id.selectOptionRG);
	}

	protected void setRadioGroup(String[] titleOfItemOptions) {
		int id = 0;
		for (String st : titleOfItemOptions) {
			// mRadioButton = new RadioButton(this, null,
			// R.style.SailorRadioButtonStyle);
			mRadioButton = new RadioButton(this);
			mRadioButton.setText(st);
			mRadioButton.setId(++id);
			Log.d(TAG, "id: " + id);
			mRadioGroup.addView(mRadioButton);
		}
	}

	protected void addButtonsCreateList() {
		// Add buttons CREATE NEW and LIST
		LinearLayout radioGroupWrapper = (LinearLayout) findViewById(R.id.radio_group_wrapper);
		getLayoutInflater().inflate(R.layout.create_and_list_buttons, radioGroupWrapper);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		getCustomerFromIntent();
		setWrapper(true);
		setContentView(wrapper);
		setCategoryTitle(false);
		setSubtitle();
		setRadioGroup();

	}
}