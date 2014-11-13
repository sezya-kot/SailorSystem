package com.sezyakot.android.sailorapp.sailor;
import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by kot on 27-Sep-14.
 */
public class NCCPFragment extends NCPFragment {

    protected static final String LOG_TAG       = "NCCPFragment";
    protected static final String HOLDER_NAME   = "holder_name";
    protected static final String NUMBER        = "number";
    protected static final String EXPIRY_DATE   = "expiry_date";
    protected static final String CVV_CODE      = "cvv_code";

    protected static final String FDEHN         = "finansials_dialog_enter_holder_name";
    protected static final String FDEN          = "finansials_dialog_enter_number";
    protected static final String FDEED         = "finansials_dialog_enter_expiry_date";
    protected static final String FDECCV        = "finansials_dialog_enter_ccv_code";
    protected static final String FDCCCP        = "FinancialsDialogCreateCashPayment";

    protected String mHolderName;
    protected String mNumber;
    protected String mExpiryDate;
    protected String mCVVCode;

    protected EditText mHolderNameET;
    protected EditText mNumberET;
    protected EditText mExpiryDateET;
    protected EditText mCVVCodeET;



    @Override
    protected DefaultFinancialsCreate setActivity() {
        return (CreditCardCreateNew) getActivity();
    }

    @Override
    protected View setInflate(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_nccp, container, false);
    }

    @Override
    protected void setUI(View v) {
        super.setUI(v);
        mHolderNameET = (EditText) v.findViewById(R.id.holder_name);
        mNumberET = (EditText) v.findViewById(R.id.number);
        mExpiryDateET = (EditText) v.findViewById(R.id.expiry_date);
        mCVVCodeET = (EditText) v.findViewById(R.id.cvv_code);
    }

    @Override
    protected void setListeners() {
        super.setListeners();
        mHolderNameET.setOnClickListener(this);
        mNumberET.setOnClickListener(this);
        mExpiryDateET.setOnClickListener(this);
        mCVVCodeET.setOnClickListener(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(HOLDER_NAME, mHolderName);
        outState.putString(NUMBER, mNumber);
        outState.putString(EXPIRY_DATE, mExpiryDate);
        outState.putString(CVV_CODE, mCVVCode);
    }

    @Override
    protected void init(Bundle b) {
        super.init(b);
        if (b == null) {
            mHolderName = "";
            mNumber = "";
            mExpiryDate = "";
            mCVVCode = "";
        } else {
            mHolderName = b.getString(HOLDER_NAME);
            mNumber = b.getString(NUMBER);
            mExpiryDate = b.getString(EXPIRY_DATE);
            mCVVCode = b.getString(CVV_CODE);
        }
        mHolderNameET.setText(mHolderName);
        mNumberET.setText(mNumber);
        mExpiryDateET.setText(mExpiryDate);
        mCVVCodeET.setText(mCVVCode);
    }



    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.holder_name: {
                FinancialsDialogEnterHolderName fdehn = new FinancialsDialogEnterHolderName();
                Bundle b = new Bundle();
                b.putString(HOLDER_NAME, ""+mHolderName);
                fdehn.setArguments(b);
                fdehn.show(getFragmentManager(), FDEHN);
                break;
            }
            case R.id.number: {
                FinancialsDialogEnterNumber fden = new FinancialsDialogEnterNumber();
                Bundle b = new Bundle();
                b.putString(NUMBER, ""+mNumber);
                fden.setArguments(b);
                fden.show(getFragmentManager(), FDEN);
                break;
            }
            case R.id.expiry_date: {
                FinancialsDialogEnterExpiryDate fdeed = new FinancialsDialogEnterExpiryDate();
                Bundle b = new Bundle();
                b.putString(EXPIRY_DATE, ""+mExpiryDate);
                fdeed.setArguments(b);
                fdeed.show(getFragmentManager(), FDEED);
                break;
            }
            case R.id.cvv_code: {
                FinancialsDialogEnterCVVCode fdeccv = new FinancialsDialogEnterCVVCode();
                Bundle b = new Bundle();
                b.putString(CVV_CODE, ""+mCVVCode);
                fdeccv.setArguments(b);
                fdeccv.show(getFragmentManager(), FDECCV);
                break;
            }

        }
    }

    @Override
    protected void setCreatePayment() {
        FinancialsDialogCreateCreditCartPayment fdcccp = new FinancialsDialogCreateCreditCartPayment();
        Bundle b = new Bundle();
        {
            b.putString(DATE, mDateStr);
            b.putString(SLIP_NUMBER, mSlipNumber);
            b.putString(SPECIAL_CODE, mSpecialCode);
            b.putString(DESCRIPTION, mDescription);
            b.putDouble(AMOUNT, mAmount);
            b.putInt(CURRENCY_ID, mCurrencyId);
            b.putString(CURRENCY_STR, mCurrencyStr);

            b.putString(HOLDER_NAME, mHolderName);
            b.putString(NUMBER, mNumber);
            b.putString(EXPIRY_DATE, mExpiryDate);
            b.putString(CVV_CODE, mCVVCode);
        }
        fdcccp.setArguments(b);

        fdcccp.show(getFragmentManager(), FDCCCP);
    }

    @Override
    protected boolean allEmpty() {
        if(     super.allEmpty() &&
                mHolderNameET.getText().toString().trim().length() > 0 &&
                mNumberET.getText().toString().trim().length() > 0 &&
                mExpiryDateET.getText().toString().trim().length() > 0 &&
                mCVVCodeET.getText().toString().trim().length() > 0 &&
                mHolderNameET != null &&
                mNumberET != null &&
                mExpiryDateET != null &&
                mCVVCodeET != null
                ) {
            return true;
        } else return false;
    }

    @Override
    public void onButtonOkPressed(String element, String value) {
        super.onButtonOkPressed(element, value);
        switch (element) {
            case HOLDER_NAME: {
                mHolderName = value;
                if (mHolderNameET != null) mHolderNameET.setText(mHolderName);
                break;
            }
            case NUMBER: {
                mNumber = value;
                if (mNumberET != null) mNumberET.setText(mNumber);
                break;
            }
            case EXPIRY_DATE: {
                mExpiryDate = value;
                if (mExpiryDateET != null) mExpiryDateET.setText(mExpiryDate);
                break;
            }
            case CVV_CODE: {
                mCVVCode = value;
                if (mCVVCodeET != null) mCVVCodeET.setText(mCVVCode);
                break;
            }
        }
    }
}
