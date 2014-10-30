package com.sezyakot.sailor;

import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.sezyakot.sailor.model.Plant;
import com.sezyakot.sailor.system.Debug;

/**
 * Created by Android on 29.08.2014.
 */
public class DialogSelectPlant extends DefaultDialogFragment{

    @Override
    protected void setTitle() {
        mTitle.setText(getString(R.string.select_plant));
    }

    @Override
    void createRadioGroup() {
        for (Plant d : a.mPlants) {
            RadioButton mRb = new RadioButton(getActivity());
            mRb.setText(d.getName());
            mRb.setId(d.getServerId());
            mRg.addView(mRb, mParams);
        }
	    mRg.check(a.mPlantId);
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
                        a.mPlantET.setText(mRb.getText());
                        a.mPlantId = mRb.getId();
                        dismiss();
                    }
                }
                break;
            }
        }
    }
}
