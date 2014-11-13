package com.sezyakot.android.sailorapp.sailor;

import android.text.InputFilter;
import android.text.InputType;
import android.view.View;

/**
 * Created by kot on 28-Sep-14.
 */
public class FinancialsDialogEnterIssuerName extends FDEValue{
    @Override
    protected String getTitle() {
        return getString(R.string.enter_issuer_name_);
    }

    @Override
    protected void init() {
        mEnterValue.setInputType(InputType.TYPE_CLASS_TEXT);
        InputFilter[] inputFilters = new InputFilter[1];
        inputFilters[0] = new InputFilter.LengthFilter(25);
        mEnterValue.setFilters(inputFilters);
        mEnterValue.setText("" + this.getArguments().getString(NCChPFragment.ISSUER_NAME));
        mEnterValue.setSelection(mEnterValue.getText().length());
    }

    @Override
    public void onClick(View v) {
        String value = getValue();
        mCallback.onButtonOkPressed(NCChPFragment.ISSUER_NAME, "" + value);
        hideKeyboard();
        dismiss();
    }
}
