package com.sezyakot.sailor;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.sezyakot.sailor.model.UnitDetail;
import com.sezyakot.sailor.system.Debug;

/**
 * Created by Android on 30.08.2014.
 */
public class DialogSelectUnitDetail extends DefaultDialogFragment{

    @Override
    protected void setTitle() {
        mTitle.setText(getString(R.string.select_unit));
    }

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
    void createRadioGroup() {
        for (UnitDetail ud : a.mUnitDetails) {
            RadioButton mRb = new RadioButton(getActivity());
            mRb.setText(ud.getName());
            mRb.setId(ud.getServerId());
            mRg.addView(mRb, mParams);
        }
		mRg.check(a.mUnitDetailId);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.close_btn: {
                if (Debug.MODE) { Log.d(LOG_TAG, "<--Cancel button clicking...-->"); }
                dismiss();
                break;
            }
            case R.id.ok_confirmation_button: {
                if (Debug.MODE) { Log.d(LOG_TAG, "<--Add button clicking...-->");}
                    if (mRg.getCheckedRadioButtonId() == -1) {
                        Toast.makeText(getActivity(), "You must check some unit!", Toast.LENGTH_SHORT).show();
                    } else {
                        mRb = (RadioButton) mView.findViewById(mRg.getCheckedRadioButtonId());
                        a.mUnitET.setText(mRb.getText());
	                    a.mUnitDetailName = mRb.getText().toString();
                        a.mUnitDetailId = mRb.getId();
	                    a.mUnitDetail = a.mDao.getUnitDetail(a.mUnitDetailId);
	                    a.mUnitPriceET.setText("" + a.getConvertedUnitPrice() + " " + a.mCurrencyStr);

	                    if (a.mQuantityET.getText().toString().toLowerCase().trim().length() != 0 && a.mQuantityET != null) {
		                    a.mItemSubtotalPrice = a.countSubtotalPrice(
				                    Double.parseDouble(a.mQuantityET.getText().toString()),
				                    a.getConvertedUnitPrice());
		                    a.mItemSubtotalPriceET.setText(a.mItemSubtotalPrice + " " + a.mCurrencyStr);
	                    }

	                    dismiss();
                    }
                break;
            }
        }
    }


}
