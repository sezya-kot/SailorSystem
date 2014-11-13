package com.sezyakot.android.sailorapp.sailor;

import android.os.Bundle;
import android.widget.TextView;
import com.sezyakot.android.sailorapp.sailor.model.ChequePayment;
import com.sezyakot.android.sailorapp.sailor.model.MainCustomer;
import com.sezyakot.android.sailorapp.sailor.model.Payment;

public class FinancialsDialogCreateChequePayment extends FinancialsDialogCreateCashPayment {

    protected TextView mIssuerNameTV;
    protected TextView mSerialNumberTV;
    protected TextView mDueDateTV;

    @Override
    protected int getIntResource() {
        return R.layout.new_cheque_payment_create_dialog;
    }

    @Override
    protected String getTitle() {
        return getString(R.string.new_cheque_payment_title);
    }

    @Override
    protected void setUI(Bundle s) {
        super.setUI(s);
        mIssuerNameTV = (TextView) mView.findViewById(R.id.issuer_name);
        mSerialNumberTV = (TextView) mView.findViewById(R.id.serial_number);
        mDueDateTV = (TextView) mView.findViewById(R.id.due_date);


    }

    @Override
    protected void fillData() {
        super.fillData();

        mIssuerNameTV.setText(getArguments().getString(NCChPFragment.ISSUER_NAME));
        mSerialNumberTV.setText(getArguments().getString(NCChPFragment.SERIAL_NUMBER));
        mDueDateTV.setText(getArguments().getString(NCChPFragment.DUE_DATE));
    }

    @Override
    protected Payment createPayment() {
        ChequePayment cp = new ChequePayment();
        {
            cp.setCustomerId(MainCustomer.get(getActivity()).getCustomer().getServerId());
            cp.setDate(getArguments().getString(NCPFragment.DATE));
            cp.setSpecialCode(getArguments().getString(NCPFragment.SPECIAL_CODE));
            cp.setSlipNumber(getArguments().getString(NCPFragment.SLIP_NUMBER));
            cp.setCurrencyId(getArguments().getInt(NCPFragment.CURRENCY_ID));
            cp.setAmount(getArguments().getDouble(NCPFragment.AMOUNT));
            cp.setDescription(getArguments().getString(NCPFragment.DESCRIPTION));

            cp.setIssuerName(getArguments().getString(NCChPFragment.ISSUER_NAME));
            cp.setSerialNumber(getArguments().getString(NCChPFragment.SERIAL_NUMBER));
            cp.setDueDate(getArguments().getString(NCChPFragment.DUE_DATE));

        }

        return cp;
    }

}
