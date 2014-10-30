package com.sezyakot.sailor;

import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.sezyakot.sailor.model.Division;
import com.sezyakot.sailor.system.Debug;

/**
 * Created by Serge Cat on 08.08.2014.
 */
public class DialogSelectDivision extends DefaultDialogFragment{

    public static final String LOG_TAG = "DialogSelectDivision";
    @Override
    protected void setTitle() {
        mTitle.setText(getString(R.string.select_division));
    }

    @Override
    void createRadioGroup() {
        for (Division d : a.mDivisions) {
            RadioButton mRb = new RadioButton(getActivity());
            mRb.setText(d.getName());
            mRb.setId(d.getServerId());
            mRg.addView(mRb, mParams);
        }
	    mRg.check(a.mDivisionId);
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
                        a.mDivisionET.setText(mRb.getText());
                        a.mDivisionId = mRb.getId();
                        dismiss();
                    }
                }
                break;
            }
        }
    }
}
