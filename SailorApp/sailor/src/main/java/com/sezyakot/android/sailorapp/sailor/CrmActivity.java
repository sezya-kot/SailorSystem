package com.sezyakot.android.sailorapp.sailor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class CrmActivity extends DefaultCustomerActivity {
	protected void runGroupActivity(View v) {
		mCustomerIntent = new Intent(v.getContext(), CrmSelectOptions.class);
		mCustomerIntent.putExtra(EXTRA_TITLE, mTitle);
		startActivity(mCustomerIntent);
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
}

