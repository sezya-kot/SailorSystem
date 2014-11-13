package com.sezyakot.android.sailorapp.sailor;

import android.os.Bundle;
import android.widget.TextView;
import com.sezyakot.android.sailorapp.sailor.model.CreditCardPayment;
import com.sezyakot.android.sailorapp.sailor.model.MainCustomer;
import com.sezyakot.android.sailorapp.sailor.model.Payment;

public class FinancialsDialogCreateCreditCartPayment extends FinancialsDialogCreateCashPayment{

    protected TextView mHolderNameTV;
    protected TextView mCardNumberTV;
    protected TextView mExpireDateTV;
    protected TextView mCVVCodeTV;

    @Override
    protected String getTitle() {
        return getString(R.string.new_credit_card_payment_title);
    }

    @Override
    protected int getIntResource() {
        return R.layout.new_credit_cart_payment_dialog;
    }

    @Override
    protected void setUI(Bundle s) {
        super.setUI(s);
        mHolderNameTV = (TextView) mView.findViewById(R.id.holder_name);
        mCardNumberTV = (TextView) mView.findViewById(R.id.card_name);
        mExpireDateTV = (TextView) mView.findViewById(R.id.expiry_date);
        mCVVCodeTV = (TextView) mView.findViewById(R.id.cvv_code);


    }

    @Override
    protected void fillData() {
        super.fillData();

        mHolderNameTV.setText(getArguments().getString(NCCPFragment.HOLDER_NAME));
        mCardNumberTV.setText(getArguments().getString(NCCPFragment.NUMBER));
        mExpireDateTV.setText(getArguments().getString(NCCPFragment.EXPIRY_DATE));
        mCVVCodeTV.setText(getArguments().getString(NCCPFragment.CVV_CODE));
    }

    @Override
    protected Payment createPayment() {
        CreditCardPayment cp = new CreditCardPayment();
        {
            cp.setCustomerId(MainCustomer.get(getActivity()).getCustomer().getServerId());
            cp.setDate(getArguments().getString(NCPFragment.DATE));
            cp.setSpecialCode(getArguments().getString(NCPFragment.SPECIAL_CODE));
            cp.setSlipNumber(getArguments().getString(NCPFragment.SLIP_NUMBER));
            cp.setCurrencyId(getArguments().getInt(NCPFragment.CURRENCY_ID));
            cp.setAmount(getArguments().getDouble(NCPFragment.AMOUNT));
            cp.setDescription(getArguments().getString(NCPFragment.DESCRIPTION));

            cp.setHolderName(getArguments().getString(NCCPFragment.HOLDER_NAME));
            cp.setNumber(getArguments().getString(NCCPFragment.NUMBER));
            cp.setExpiryDate(getArguments().getString(NCCPFragment.EXPIRY_DATE));
            cp.setCVVCode(getArguments().getString(NCCPFragment.CVV_CODE));

        }

        return cp;
    }
}
