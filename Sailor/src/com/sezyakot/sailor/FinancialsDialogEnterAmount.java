/*
 * Copyright (c) 2014.
 */

package com.sezyakot.sailor;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

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
