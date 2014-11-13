package com.sezyakot.android.sailorapp.sailor;

import android.view.View;

/**
 * Created by Android on 24.09.2014.
 */
public class FinancialsDialogEnterAmount extends FDEValue{
    @Override
    protected String getTitle() {
        return getString(R.string.enter_amount_);
    }

    @Override
    protected void init() {
        mEnterValue.setText("" + this.getArguments().getString(NCPFragment.AMOUNT));
    }

    @Override
    public void onClick(View v) {
        String value = getValue();
        mCallback.onButtonOkPressed(NCPFragment.AMOUNT, "" + value);
        hideKeyboard();
        dismiss();
    }
}
