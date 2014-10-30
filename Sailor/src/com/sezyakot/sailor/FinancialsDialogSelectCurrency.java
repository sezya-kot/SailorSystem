/*
 * Copyright (c) 2014.
 */

package com.sezyakot.sailor;

import android.app.ActionBar;
import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.sezyakot.sailor.model.Currency;
import com.sezyakot.sailor.system.Debug;

import java.util.ArrayList;

/**
 * Created by Android on 24.09.2014.
 */
public class FinancialsDialogSelectCurrency extends DefaultDialogFinancials implements View.OnClickListener{
    RadioGroup mRg;
    FDEValue.OnButtonOkListener mCallback;

    @Override
    protected int getIntResource() {
        return R.layout.dialog_select_currency;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallback = (FDEValue.OnButtonOkListener) activity.getFragmentManager().findFragmentById(R.id.fragment_container);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Button button = (Button) mView.findViewById(R.id.ok_confirmation_button);
        button.setOnClickListener(this);
        ArrayList<Currency> currencies = ((DefaultFinancialsCreate) getActivity()).mDao.getCurrencies(null);
        mRg = (RadioGroup) mView.findViewById(R.id.select_options_dsc);
        RadioGroup.LayoutParams mParams = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
        for (Currency c : currencies) {
            RadioButton mRb = new RadioButton(getActivity());
            mRb.setText(c.getName());
            mRb.setId(c.getServerId());
            mRg.addView(mRb, mParams);
        }
        mRg.check(getArguments().getInt(NCPFragment.CURRENCY_ID));
        return mView;
    }

    @Override
    protected String getTitle() {
        return getString(R.string.select_currency);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ok_confirmation_button: {
                if (Debug.MODE) {
                    if (mRg.getCheckedRadioButtonId() == -1) {
                        Toast.makeText(getActivity(), "You must check currency!", Toast.LENGTH_SHORT).show();
                    } else {
                        RadioButton radioButton = (RadioButton) mView.findViewById(mRg.getCheckedRadioButtonId());
                        int value = radioButton.getId();
                        mCallback.onButtonOkPressed(NCPFragment.CURRENCY_ID, "" + value);
                        dismiss();
                    }
                }
                break;
            }
        }
    }
}
