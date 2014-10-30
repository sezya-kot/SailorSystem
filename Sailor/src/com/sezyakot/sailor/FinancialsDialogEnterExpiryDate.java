/*
 * Copyright (c) 2014.
 */

package com.sezyakot.sailor;

import android.view.View;

/**
 * Created by kot on 27-Sep-14.
 */
public class FinancialsDialogEnterExpiryDate extends FDEValue{
    @Override
    protected String getTitle() {
        return getString(R.string.enter_expiry_date);
    }

    @Override
    protected void init() {
        mEnterValue.setText("" + this.getArguments().getString(NCCPFragment.EXPIRY_DATE));
    }

    @Override
    public void onClick(View v) {
        String value = getValue();
        mCallback.onButtonOkPressed(NCCPFragment.EXPIRY_DATE, "" + value);
        hideKeyboard();
        dismiss();
    }
}
