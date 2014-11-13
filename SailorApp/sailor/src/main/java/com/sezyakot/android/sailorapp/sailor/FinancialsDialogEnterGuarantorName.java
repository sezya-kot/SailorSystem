package com.sezyakot.android.sailorapp.sailor;

import android.text.InputFilter;
import android.text.InputType;
import android.view.View;

/**
 * Created by Android on 29.09.2014.
 */
public class FinancialsDialogEnterGuarantorName extends FDEValue{
    @Override
    protected String getTitle() {
        return getString(R.string.enter_guarantors_name);
    }

    @Override
    protected void init() {
        mEnterValue.setInputType(InputType.TYPE_CLASS_TEXT);
        InputFilter[] inputFilters = new InputFilter[1];
        inputFilters[0] = new InputFilter.LengthFilter(25);
        mEnterValue.setFilters(inputFilters);
        mEnterValue.setText("" + this.getArguments().getString(NCBPFragment.GUARANTOR_NAME));
        mEnterValue.setSelection(mEnterValue.getText().length());
    }

    @Override
    public void onClick(View v) {
        String value = getValue();
        mCallback.onButtonOkPressed(NCBPFragment.GUARANTOR_NAME, "" + value);
        hideKeyboard();
        dismiss();
    }
}
