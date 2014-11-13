package com.sezyakot.android.sailorapp.sailor;

import android.view.View;

/**
 * Created by Android on 25.09.2014.
 */
public class FinancialsDialogEnterSpecialCode extends FDEValue {
    @Override
    protected String getTitle() {
        return getString(R.string.enter_special_code);
    }

    @Override
    protected void init() {
        mEnterValue.setText(""+this.getArguments().getString(NCPFragment.SPECIAL_CODE));
    }

    @Override
    public void onClick(View v) {
        String value = getValue();
        mCallback.onButtonOkPressed(NCPFragment.SPECIAL_CODE, "" + value);
        hideKeyboard();
        dismiss();
    }

}
