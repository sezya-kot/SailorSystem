/*
 * Copyright (c) 2014.
 */

package com.sezyakot.sailor;

import android.app.FragmentManager;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.sezyakot.sailor.model.CashPayment;
import com.sezyakot.sailor.model.MainCustomer;
import com.sezyakot.sailor.model.Payment;
import com.sezyakot.sailor.system.Debug;
import com.sezyakot.sailor.system.Preferences;

/**
 * Created by Android on 24.09.2014.
 */
public class FinancialsDialogCreateCashPayment extends DefaultDialogFinancials implements TaskFragment.TaskCallbacks{
    public static final String LOG_TAG = "FinancialsDialogCreateCashPayment";
    private static final String STATUS = "status";
    private static final String STATUS_STR = "status_str";
    protected TextView mCustomerTV;
    protected TextView mDateTV;
    protected TextView mSlipNumberTV;
    protected TextView mCreatedByTV;
    protected TextView mDescriptionTV;
    protected TextView mPaymentAmountTV;
    protected TextView mStatusLine;

    protected ImageView mAnimatedCircle;
    protected TaskFragment mTaskFragment;

    protected Payment mPayment;
    protected AnimationDrawable mAnimationCircle;
    private boolean mStatus;

    @Override
    protected int getIntResource() {
        return R.layout.new_cash_payment_dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setUI(savedInstanceState);
        fillData();
        return mView;
    }

    @Override
    public void dismiss() {
        if (mTaskFragment != null) {
            mTaskFragment.onCancel();
        }
        super.dismiss();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATUS, mStatus);
        outState.putString(STATUS_STR, mStatusLine.getText().toString());

    }

    protected void fillData() {
        mCustomerTV.setText(MainCustomer.get(getActivity()).getCustomer().getName());
        mDateTV.setText(getArguments().getString(NCPFragment.DATE));
        mSlipNumberTV.setText(getArguments().getString(NCPFragment.SLIP_NUMBER));
        mCreatedByTV.setText(new Preferences(getActivity()).getUserName());
        mDescriptionTV.setText(getArguments().getString(NCPFragment.DESCRIPTION));
        mPaymentAmountTV.setText(""+getArguments().getDouble(NCPFragment.AMOUNT) + " "
                + getArguments().getString(NCPFragment.CURRENCY_STR ) );

    }

    protected Payment createPayment() {
        CashPayment cp = new CashPayment();
        {
            cp.setCustomerId(MainCustomer.get(getActivity()).getCustomer().getServerId());
            cp.setDate(getArguments().getString(NCPFragment.DATE));
            cp.setSpecialCode(getArguments().getString(NCPFragment.SPECIAL_CODE));
            cp.setSlipNumber(getArguments().getString(NCPFragment.SLIP_NUMBER));
            cp.setCurrencyId(getArguments().getInt(NCPFragment.CURRENCY_ID));
            cp.setAmount(getArguments().getDouble(NCPFragment.AMOUNT));
            cp.setDescription(getArguments().getString(NCPFragment.DESCRIPTION));
        }
        return cp;
    }

    protected void setUI(Bundle s) {

        mCustomerTV =       (TextView) mView.findViewById(R.id.oc_company_name);
        mDateTV =           (TextView) mView.findViewById(R.id.oc_date);
        mSlipNumberTV =     (TextView) mView.findViewById(R.id.oc_slip_number);
        mCreatedByTV =      (TextView) mView.findViewById(R.id.oc_created_by);
        mDescriptionTV =    (TextView) mView.findViewById(R.id.oc_description);
        mPaymentAmountTV =  (TextView) mView.findViewById(R.id.payment_amount);
        mAnimatedCircle =   (ImageView)mView.findViewById(R.id.animated_arrow_circle);
        mAnimationCircle =  (AnimationDrawable) mAnimatedCircle.getBackground();
        mStatusLine       = (TextView) mView.findViewById(R.id.status);
        mCreate         =   (Button)   mView.findViewById(R.id.cash_payment_create_btn);
        mCreate             .setOnClickListener(this);

        if (s == null) {
            mStatus = false;
            mStatusLine.setText("");
        } else {
            mStatus = s.getBoolean(STATUS);
            mStatusLine.setText(s.getString(STATUS_STR));
        }

        if (mStatus) {

            mAnimatedCircle.setVisibility(View.VISIBLE);
            mAnimationCircle.start();
        } else {
            mAnimatedCircle.setVisibility(View.INVISIBLE);
            mAnimationCircle.stop();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cash_payment_create_btn: {
                if (Debug.MODE) { Log.d(LOG_TAG, "Button CREATE was pressed!"); }
                doCreateWork();
                break;
            }
            default: {
                break;
            }
        }
    }

    private boolean doCreateWork() {

        Bundle b = new Bundle();
        b.putParcelable(Payment.PAYMENT,  createPayment());
        FragmentManager fm = getFragmentManager();
        if (mTaskFragment == null) {
            mTaskFragment = new TaskFragment();
            mTaskFragment.setArguments(b);
            fm.beginTransaction().add(mTaskFragment, CashCreateNew.FRAG_TAG).commit();
        }
        return false;
    }

    @Override
    protected String getTitle() {
        return getString(R.string.new_cash_payment_title);
    }

    @Override
    public void onPreExecute() {
        mAnimatedCircle.setVisibility(View.VISIBLE);
        mAnimationCircle.start();
        mStatus = true;
    }

    @Override
    public void onProgressUpdate(String msg) {
        Log.d(LOG_TAG, "FinancialsDialogCreateCashPayment message: " + msg);
        mStatusLine.setText(msg);
    }

    @Override
    public void onCancelled() {

    }

    @Override
    public void onPostExecute() {
        mAnimatedCircle.setVisibility(View.INVISIBLE);
        mAnimationCircle.stop();
        mTaskFragment = null;
        mStatus = false;
    }
}
