package com.sezyakot.android.sailorapp.sailor;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

/**
 * Created by Android on 29.09.2014.
 */
public class PaymentListActivity extends DefaultFinancialsCreate {
    @Override
    protected void setTitle() {
        switch (getIntent().getIntExtra(FinancialsSelectOptions.TYPE, 0)) {
            case 1:
                mTitleName.setText(getString(R.string.cash_payment_list));
                break;
            case 2:
                mTitleName.setText(getString(R.string.credit_card_payment_list));
                break;
            case 3:
                mTitleName.setText(getString(R.string.cheque_payment_list));
                break;
            case 4:
                mTitleName.setText(getString(R.string.bond_payment_list));
                break;
            default:
                mTitleName.setText(getString(R.string.cash_payment_list));
                break;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentManager fm = getFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = new PaymentListFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }
}
