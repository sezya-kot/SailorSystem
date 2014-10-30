/*
 * Copyright (c) 2014.
 */

package com.sezyakot.sailor;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

/**
 * Created by Android on 22.09.2014.
 */
public class BondCreateNew extends DefaultFinancialsCreate {
	@Override
	protected void setTitle() {
		mTitleName.setText(getString(R.string.new_bond_payment));
	}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentManager fm = getFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = new NCBPFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }
}
