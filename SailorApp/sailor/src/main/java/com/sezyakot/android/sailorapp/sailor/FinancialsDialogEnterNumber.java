package com.sezyakot.android.sailorapp.sailor;

import android.text.InputType;
import android.view.View;

/**
 * Created by kot on 27-Sep-14.
 */
public class FinancialsDialogEnterNumber extends FDEValue {
    @Override
    protected String getTitle() {
        return getString(R.string.enter_card_number);
    }

    @Override
    protected void init() {
        mEnterValue.setInputType(InputType.TYPE_TEXT_VARIATION_FILTER);
        mEnterValue.setText(""+this.getArguments().getString(NCCPFragment.NUMBER));
    }

    @Override
    public void onClick(View v) {
        String value = getValue();
        mCallback.onButtonOkPressed(NCCPFragment.NUMBER, "" + value);
        hideKeyboard();
        dismiss();
    }
}
