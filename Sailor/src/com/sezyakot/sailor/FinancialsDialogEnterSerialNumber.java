/*
 * Copyright (c) 2014.
 */

package com.sezyakot.sailor;

import android.view.View;

/**
 * Created by kot on 28-Sep-14.
 */
public class FinancialsDialogEnterSerialNumber extends FDEValue{
    @Override
    protected String getTitle() {
        return getString(R.string.enter_serial_number);
    }

    @Override
    protected void init() {
        mEnterValue.setText("" + this.getArguments().getString(NCChPFragment.SERIAL_NUMBER));
    }

    @Override
    public void onClick(View v) {
        String value = getValue();
        mCallback.onButtonOkPressed(NCChPFragment.SERIAL_NUMBER, "" + value);
        hideKeyboard();
        dismiss();
    }
}
