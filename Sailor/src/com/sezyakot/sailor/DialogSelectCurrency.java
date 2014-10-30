package com.sezyakot.sailor;

import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.sezyakot.sailor.model.Currency;
import com.sezyakot.sailor.system.Debug;

/**
 * Created by Android on 08.08.2014.
 */
public class DialogSelectCurrency extends DefaultDialogFragment {

    public static final String LOG_TAG = "DialogSelectCurrency";

    @Override
    void createRadioGroup() {
        for (Currency c : a.mCurrencies) {
            RadioButton mRb = new RadioButton(getActivity());
            mRb.setText(c.getName());
            mRb.setId(c.getServerId());
            mRg.addView(mRb, mParams);
        }
	    mRg.check(a.mCurrencyId);
    }

    @Override
    public void onClick(View v) {
//        super.onClick(v);
        switch (v.getId()) {
            case R.id.close_btn: {
                if (Debug.MODE) {
                    Log.d(LOG_TAG, "<--Cancel button clicking...-->");
                }
                dismiss();
                break;
            }
            case R.id.ok_confirmation_button: {
                if (Debug.MODE) {
                    Log.d(LOG_TAG, "<--Add button clicking...-->");
                    if (mRg.getCheckedRadioButtonId() == -1) {
                        Toast.makeText(getActivity(), "You must check currency!", Toast.LENGTH_SHORT).show();
                    } else {
                        mRb = (RadioButton) mView.findViewById(mRg.getCheckedRadioButtonId());
	                    a.mCurrencyStr = mRb.getText().toString();
                        a.mCurrencyET.setText(a.mCurrencyStr);
                        a.mCurrencyId = mRb.getId();
                        dismiss();
                    }
                }
                break;
            }
        }
    }
}

