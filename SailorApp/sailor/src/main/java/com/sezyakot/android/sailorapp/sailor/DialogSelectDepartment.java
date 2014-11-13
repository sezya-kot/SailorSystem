package com.sezyakot.android.sailorapp.sailor;

import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;
import com.sezyakot.android.sailorapp.sailor.model.Department;
import com.sezyakot.android.sailorapp.sailor.system.Debug;

public class DialogSelectDepartment extends DefaultDialogFragment{

    @Override
    protected void setTitle() {
        mTitle.setText(getString(R.string.select_department));
    }

    @Override
    void createRadioGroup() {
        for (Department d : a.mDepartments) {
            RadioButton mRb = new RadioButton(getActivity());
            mRb.setText(d.getName());
            mRb.setId(d.getServerId());
            mRg.addView(mRb, mParams);
        }
	    mRg.check(a.mDepartmentId);
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
                        a.mDepartmentET.setText(mRb.getText());
                        a.mDepartmentId = mRb.getId();
                        dismiss();
                    }
                }
                break;
            }
        }
    }
}
