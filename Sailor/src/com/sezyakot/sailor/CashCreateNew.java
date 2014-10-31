/*
 * Copyright (c) 2014.
 */

package com.sezyakot.sailor;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import com.sezyakot.sailor.model.Currency;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Android on 18.09.2014.
 */
public class CashCreateNew extends DefaultFinancialsCreate implements TaskFragment.TaskCallbacks{
    public static final String FRAG_TAG = "task_fragment";
    private static final String LOG_TAG = "CashCreateNew";
	@Override
	protected void setTitle() {
		mTitleName.setText(getString(R.string.new_cash_payment));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		FragmentManager fm = getFragmentManager();
		Fragment fragment = fm.findFragmentById(R.id.fragment_container);

		if (fragment == null) {
			fragment = new NCPFragment();
			fm.beginTransaction()
              .add(R.id.fragment_container, fragment)
              .commit();
		}

	}

    @Override
    public void onPreExecute() {

    }

    @Override
    public void onProgressUpdate(int percent) {
        Log.v(LOG_TAG, "Percent: " + percent);
    }

    @Override
    public void onCancelled() {

    }

    @Override
    public void onPostExecute() {

    }
}
