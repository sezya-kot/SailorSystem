package com.sezyakot.android.sailorapp.sailor;

import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Android on 05.11.2014.
 */
public class FinancialsPaymentDetailsActivity extends DefaultActivity {

    private int mServerId;
    private int mType;
    private TextView mTitleTV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setWrapper();
        getLayoutInflater().inflate(R.layout.payment_details, wrapper);
        setContentView(wrapper);
        init();
        setUI();
        setMainTitle(getString(R.string.title_cash_payment_details));
    }

    private void init() {
    }

    private void setUI() {
        mTitleTV = (TextView) findViewById(R.id.title_of_payment);

    }

    private void setMainTitle(String pMainTitle) {
        mTitleTV.setText(pMainTitle);
    }
}
