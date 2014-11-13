package com.sezyakot.android.sailorapp.sailor;

import android.view.View;

/**
 * Created by kot on 27-Sep-14.
 */
public class FinancialsDialogEnterCVVCode  extends FDEValue{
    @Override
    protected String getTitle() {
        return getString(R.string.enter_ccv_code);
    }

    @Override
    protected void init() {
        mEnterValue.setText("" + this.getArguments().getString(NCCPFragment.CVV_CODE));
    }

    @Override
    public void onClick(View v) {
        String value = getValue();
        mCallback.onButtonOkPressed(NCCPFragment.CVV_CODE, "" + value);
        hideKeyboard();
        dismiss();
    }
}
