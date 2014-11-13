package com.sezyakot.android.sailorapp.sailor;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.Calendar;

/**
 * Created by kot on 27-Sep-14.
 */
public class NCBPFragment extends NCChPFragment {

    protected static final String LOG_TAG           = "NCChPFragment";
    protected static final String GUARANTOR_NAME    = "guarantor_name";
    protected static final String FDEGN             = "FinancialsDialogEnterGuarantorName";
    protected static final String FDCBP             = "FinancialsDialogCreateBondPayment";

    protected String mGuarantorName;
    protected EditText mGuarantorNameET;

    @Override
    protected DefaultFinancialsCreate setActivity() {
        return (BondCreateNew) getActivity();
    }
    @Override
    protected View setInflate(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_ncbp, container, false);
    }

    @Override
    protected void setUI(View v) {
        super.setUI(v);
        mGuarantorNameET = (EditText) v.findViewById(R.id.guarantor_name);
    }

    @Override
    protected void setListeners() {
        super.setListeners();
        mGuarantorNameET.setOnClickListener(this);
    }

    @Override
    protected void init(Bundle b) {
        super.init(b);
        if (b == null) {
            mGuarantorName = "";
        } else {
            mGuarantorName = b.getString(GUARANTOR_NAME);
        }
        mGuarantorNameET.setText(mGuarantorName);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(GUARANTOR_NAME, mGuarantorName);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.guarantor_name: {
                FinancialsDialogEnterGuarantorName fdegn = new FinancialsDialogEnterGuarantorName();
                Bundle b = new Bundle();
                b.putString(GUARANTOR_NAME, ""+mGuarantorName);
                fdegn.setArguments(b);
                fdegn.show(getFragmentManager(), FDEGN);
                break;
            }
        }
    }

    @Override
    protected void setCreatePayment() {
        FinancialsDialogCreateBondPayment fdcbp = new FinancialsDialogCreateBondPayment();
        Bundle b = new Bundle();
        {
            b.putString(DATE, mDateStr);
            b.putString(SLIP_NUMBER, mSlipNumber);
            b.putString(SPECIAL_CODE, mSpecialCode);
            b.putString(DESCRIPTION, mDescription);
            b.putDouble(AMOUNT, mAmount);
            b.putInt(CURRENCY_ID, mCurrencyId);
            b.putString(CURRENCY_STR, mCurrencyStr);

            b.putString(ISSUER_NAME, mIssuerName);
            b.putString(GUARANTOR_NAME, mGuarantorName);
            b.putString(SERIAL_NUMBER, mSerialNumber);
            b.putString(DUE_DATE, mDueDate);
        }
        fdcbp.setArguments(b);

        fdcbp.show(getFragmentManager(), FDCBP);
    }

    @Override
    protected boolean allEmpty() {
        if(     super.allEmpty() &&
                mGuarantorNameET.getText().toString().trim().length() > 0 &&
                mGuarantorNameET != null
                ) {
            return true;
        } else return false;
    }

    @Override
    public void onButtonOkPressed(String element, String value) {
        super.onButtonOkPressed(element, value);
        switch (element) {
            case GUARANTOR_NAME: {
                mGuarantorName = value;
                if (mGuarantorNameET != null) mGuarantorNameET.setText(mGuarantorName);
                break;
            }
        }
    }
}
