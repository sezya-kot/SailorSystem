package com.sezyakot.android.sailorapp.sailor;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

/**
 * Created by kot on 27-Sep-14.
 */
public class NCChPFragment extends NCPFragment {

    protected static final String LOG_TAG       = "NCChPFragment";

    protected static final String ISSUER_NAME   = "issuer_name";
    protected static final String SERIAL_NUMBER = "serial_number";
    protected static final String DUE_DATE      = "due_date";
    protected static final String DUE_DATE_CALENDAR = "due_date_calendar";
    protected static final String FDEIN = "FinancialsDialogEnterIssuerName" ;
    protected static final String FDESN = "FinancialsDialogEnterSerialNumber";
    protected static final String FDCCHP = "FinancialsDialogCreateChequePayment";

    protected String mIssuerName;
    protected String mSerialNumber;
    protected String mDueDate;
    protected Calendar mDueDateCal;

    protected EditText mIssuerNameET;
    protected EditText mSerialNumberET;
    protected EditText mDueDateET;

    @Override
    protected void setUI(View v) {
        super.setUI(v);
        mIssuerNameET = (EditText) v.findViewById(R.id.issuer_name);
        mSerialNumberET = (EditText) v.findViewById(R.id.serial_number);
        mDueDateET = (EditText) v.findViewById(R.id.due_date);
    }

    @Override
    protected void setListeners() {
        super.setListeners();
        mIssuerNameET.setOnClickListener(this);
        mSerialNumberET.setOnClickListener(this);
        mDueDateET.setOnClickListener(this);
    }

    @Override
    protected void init(Bundle b) {
        super.init(b);
        if (b == null) {
            mIssuerName = "";
            mSerialNumber = "";
            mDueDate = "";
            mDueDateCal = Calendar.getInstance();
        } else {
            mIssuerName = b.getString(ISSUER_NAME);
            mSerialNumber = b.getString(SERIAL_NUMBER);
            mDueDate = b.getString(DUE_DATE);
            mDueDateCal = (Calendar) b.getSerializable(DUE_DATE_CALENDAR);
        }
        mIssuerNameET.setText(mIssuerName);
        mSerialNumberET.setText(mSerialNumber);
        mDueDate = setDate(mDueDateCal);
        mDueDateET.setText(mDueDate);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(ISSUER_NAME, mIssuerName);
        outState.putString(SERIAL_NUMBER, mSerialNumber);
        outState.putSerializable(DUE_DATE_CALENDAR, mDueDateCal);
    }

    @Override
    protected DefaultFinancialsCreate setActivity() {
        return (ChequeCreateNew) getActivity();
    }
    @Override
    protected View setInflate(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_ncchp, container, false);
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.issuer_name: {
                FinancialsDialogEnterIssuerName fdein = new FinancialsDialogEnterIssuerName();
                Bundle b = new Bundle();
                    b.putString(ISSUER_NAME, ""+mIssuerName);
                fdein.setArguments(b);
                fdein.show(getFragmentManager(), FDEIN);
                break;
            }
            case R.id.serial_number: {
                FinancialsDialogEnterSerialNumber fdesn = new FinancialsDialogEnterSerialNumber();
                Bundle b = new Bundle();
                    b.putString(SERIAL_NUMBER, ""+mSerialNumber);
                fdesn.setArguments(b);
                fdesn.show(getFragmentManager(), FDESN);
                break;
            }
            case R.id.due_date: {
                dateDialog();
                break;
            }
        }
    }

    @Override
    protected void setCreatePayment() {
        FinancialsDialogCreateChequePayment fdcchp = new FinancialsDialogCreateChequePayment();
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
            b.putString(SERIAL_NUMBER, mSerialNumber);
            b.putString(DUE_DATE, mDueDate);
        }
        fdcchp.setArguments(b);

        fdcchp.show(getFragmentManager(), FDCCHP);
    }

    @Override
    protected boolean allEmpty() {
        if(     super.allEmpty() &&
                mIssuerNameET.getText().toString().trim().length() > 0 &&
                mSerialNumberET.getText().toString().trim().length() > 0 &&
                mDueDateET.getText().toString().trim().length() > 0 &&
                mIssuerNameET != null &&
                mSerialNumberET != null &&
                mDueDateET != null
                ) {
            return true;
        } else return false;
    }

    @Override
    public void onButtonOkPressed(String element, String value) {
        super.onButtonOkPressed(element, value);
        switch (element) {
            case ISSUER_NAME: {
                mIssuerName = value;
                if (mIssuerNameET != null) mIssuerNameET.setText(mIssuerName);
                break;
            }
            case SERIAL_NUMBER: {
                mSerialNumber = value;
                if (mSerialNumberET != null) mSerialNumberET.setText(mSerialNumber);
                break;
            }
            case DUE_DATE: {
                mDueDate = value;
                if (mDueDateET != null) mDueDateET.setText(mDueDate);
                break;
            }
        }
    }

    @Override
    public void dateDialog() {
        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                mDueDateCal.set(year,monthOfYear,dayOfMonth);
                mDueDate = setDate(mDueDateCal);
                mDueDateET.setText(mDueDate);
            }
        };
        DatePickerDialog dpDialog = new DatePickerDialog(
                mActivity,
                listener,
                mDueDateCal.get(Calendar.YEAR),
                mDueDateCal.get(Calendar.MONTH),
                mDueDateCal.get(Calendar.DAY_OF_MONTH));
        dpDialog.show();
    }
}
