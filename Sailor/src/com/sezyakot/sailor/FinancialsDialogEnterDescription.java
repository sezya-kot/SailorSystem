/*
 * Copyright (c) 2014.
 */

package com.sezyakot.sailor;

import android.text.InputType;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Android on 25.09.2014.
 */
public class FinancialsDialogEnterDescription extends FDEValue {
    @Override
    protected String getTitle() {
        return getString(R.string.enter_description);
    }
    @Override
    protected void init() {
        mEnterValue.setFadingEdgeLength(20);
        mEnterValue.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        mEnterValue.setText(""+this.getArguments().getString(NCPFragment.DESCRIPTION));

    }

    @Override
    public void onClick(View v) {
        String value = getValue();
        mCallback.onButtonOkPressed(NCPFragment.DESCRIPTION, "" + value);
        hideKeyboard();
        dismiss();
    }

}
