/*
 * Copyright (c) 2014.
 */

package com.sezyakot.sailor;

import android.text.InputType;
import android.view.View;

/**
 * Created by kot on 27-Sep-14.
 */
public class FinancialsDialogEnterHolderName extends FDEValue{
    @Override
    protected String getTitle() {
        return getString(R.string.enter_holder_name);
    }
    @Override
    protected void init() {
        mEnterValue.setFadingEdgeLength(20);
        mEnterValue.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        mEnterValue.setText(""+this.getArguments().getString(NCCPFragment.HOLDER_NAME));
    }
    @Override
    public void onClick(View v) {
        String value = getValue();
        mCallback.onButtonOkPressed(NCCPFragment.HOLDER_NAME, "" + value);
        hideKeyboard();
        dismiss();
    }
}
