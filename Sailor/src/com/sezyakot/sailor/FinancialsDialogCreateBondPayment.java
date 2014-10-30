/*
 * Copyright (c) 2014.
 */

package com.sezyakot.sailor;

import android.os.Bundle;
import android.widget.TextView;
import com.sezyakot.sailor.model.BondPayment;
import com.sezyakot.sailor.model.CreditCardPayment;
import com.sezyakot.sailor.model.MainCustomer;
import com.sezyakot.sailor.model.Payment;

/**
 * Created by Android on 29.09.2014.
 */
public class FinancialsDialogCreateBondPayment extends FinancialsDialogCreateChequePayment{
    protected TextView mGuarantorNameTV;

    @Override
    protected int getIntResource() {
        return R.layout.new_bond_payment_create_dialog;
    }
    @Override
    protected String getTitle() {
        return getString(R.string.new_bond_payment_title);
    }

    @Override
    protected void setUI(Bundle s) {
        super.setUI(s);
        mGuarantorNameTV = (TextView) mView.findViewById(R.id.guarantor_name);
    }
    @Override
    protected void fillData() {
        super.fillData();
        mGuarantorNameTV.setText(getArguments().getString(NCBPFragment.GUARANTOR_NAME));
    }

    @Override
    protected Payment createPayment() {
        BondPayment cp = new BondPayment();
        {
            cp.setCustomerId(MainCustomer.get(getActivity()).getCustomer().getServerId());
            cp.setDate(getArguments().getString(NCPFragment.DATE));
            cp.setSpecialCode(getArguments().getString(NCPFragment.SPECIAL_CODE));
            cp.setSlipNumber(getArguments().getString(NCPFragment.SLIP_NUMBER));
            cp.setCurrencyId(getArguments().getInt(NCPFragment.CURRENCY_ID));
            cp.setAmount(getArguments().getDouble(NCPFragment.AMOUNT));
            cp.setDescription(getArguments().getString(NCPFragment.DESCRIPTION));

            cp.setIssuerName(getArguments().getString(NCChPFragment.ISSUER_NAME));
            cp.setGuarantorName(getArguments().getString(NCBPFragment.GUARANTOR_NAME));
            cp.setSerialNumber(getArguments().getString(NCChPFragment.SERIAL_NUMBER));
            cp.setDueDate(getArguments().getString(NCChPFragment.DUE_DATE));

        }

        return cp;
    }
}
