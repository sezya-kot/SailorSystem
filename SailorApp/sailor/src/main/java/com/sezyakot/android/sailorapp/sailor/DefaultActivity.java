package com.sezyakot.android.sailorapp.sailor;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.reflect.Field;

@SuppressWarnings("ALL")
@SuppressLint("DefaultLocale")
public class DefaultActivity extends Activity {

	protected String mTitle;
	protected boolean titleBackArrowFlag = true;
	protected int mMarkerOfActivity;
	public final static String EXTRA_TITLE = "com.sezyakot.sailor.TITLE";
	public final static String EXTRA_KEY = "com.sezyakot.sailor.KEY";
	protected ViewGroup wrapper;
	public final static String EXTRA_CUSTOMER_ID = "com.sezyakot.sailor.CUSTOMER_ID";
	protected Intent mCustomerIntent;

	protected ViewGroup setWrapper(boolean titleFlag) {
		wrapper = (ViewGroup) getLayoutInflater().inflate(R.layout.wrapper,	null);
		if (titleFlag)
			getLayoutInflater().inflate(R.layout.title, wrapper);
		else
			getLayoutInflater().inflate(R.layout.title, wrapper);
		return wrapper;
	};
	
	protected ViewGroup setWrapper() {
		wrapper = (ViewGroup) getLayoutInflater().inflate(R.layout.wrapper,	null);
		return wrapper;
	};



	protected void setupActionBar() {
		ActionBar ab = getActionBar();
		ab.setDisplayShowCustomEnabled(true);
		ab.setDisplayShowTitleEnabled(false);
		ab.setDisplayShowHomeEnabled(false);
		ab.setCustomView(R.layout.action_bar_title);
	};

	protected String getTitleOfCategory() {
		mTitle = getIntent().getStringExtra(EXTRA_TITLE);
		return mTitle;
	};

	OnClickListener onCloseActivity = new OnClickListener() {
		@Override
		public void onClick(View v) {
			finish();
		}
	};

	protected void setCategoryTitle(boolean isClickableBackArrowFlag) {
		getTitleOfCategory();
		TextView titleOfCategory = (TextView) findViewById(R.id.title_of_category);
		titleOfCategory.setText(mTitle);
		String resSt = "ic_" + mTitle.toLowerCase();

		try {
//          Class<drawable> res = R.drawable.class
			Class res = R.drawable.class;
			Field field = res.getField(resSt);
			int resID = field.getInt(null);
			if (isClickableBackArrowFlag) {
				titleOfCategory.setCompoundDrawablesWithIntrinsicBounds(resID,
						0, R.drawable.ic_back, 0);
				titleOfCategory.setOnClickListener(onCloseActivity);
			} else {
				titleOfCategory.setCompoundDrawablesWithIntrinsicBounds(resID,
						0, 0, 0);
			}
		} catch (Exception e) {
			Log.e("SetupContentView", "Failure to get drawable id.", e);
		}
	};
	

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setupActionBar();

	}
}
