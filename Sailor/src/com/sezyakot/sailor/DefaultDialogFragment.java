package com.sezyakot.sailor;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.*;

import com.sezyakot.sailor.system.Debug;

abstract class DefaultDialogFragment extends DialogFragment implements OnClickListener {

	public final static String LOG_TAG = "DefaultDialogFragment";

    protected DefaultSalesNew a;

	RadioGroup mRg;
	RadioButton[] mRbs;
	ImageButton mCloseBtn;
    RadioButton mRb;
    Button mOkButton;
	View mView;
    RadioGroup.LayoutParams mParams;
    TextView mTitle;

//    protected abstract ArrayList<String> getStrArr();

    protected void setTitle() {
        mTitle.setText(getString(R.string.select_currency));
    };



 	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        a = (DefaultSalesNew) getActivity();

		mView = inflater.inflate(R.layout.select_currency, null);

        mTitle = (TextView) mView.findViewById(R.id.sc_title);
        setTitle();

		mRg = (RadioGroup) mView.findViewById(R.id.select_options);

        mCloseBtn = (ImageButton) mView.findViewById(R.id.close_btn);
        mCloseBtn.setOnClickListener(this);

        mOkButton = (Button) mView.findViewById(R.id.ok_confirmation_button);
        mOkButton.setOnClickListener(this);

        mParams = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);

        createRadioGroup();

		return mView;
	}


    abstract void createRadioGroup();
//    {
//        RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
//        for (Object o: namesOfItemsRBs) {
//            RadioButton mRb = new RadioButton(getActivity());
//            mRb.setText(o.toString());
//            mRg.addView(mRb, params);
//        }
//    };

    @Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		Dialog dialog = super.onCreateDialog(savedInstanceState);
		// request a window without the title
		dialog.setCancelable(false);
		dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		return dialog;
	}

	@Override
	public void onClick(View v) {
		
//		if (mRg.getCheckedRadioButtonId() == -1) {
//			Toast.makeText(getActivity(), "You must check currency!", Toast.LENGTH_SHORT).show();
//		} else {
//			mRb = (RadioButton) mView.findViewById(mRg.getCheckedRadioButtonId());
//			activity.mCurrencyET.setText(mRb.getText());
//			activity.setCurrencyStr(mRb.getText().toString());
//			dismiss();
//		}


        switch (v.getId()) {
            case R.id.close_btn: {
                if(Debug.MODE) { Log.d(LOG_TAG, "<--Cancel button clicking...-->"); }
                dismiss();
                break;
            }
            case R.id.ok_confirmation_button: {
                if(Debug.MODE) { Log.d(LOG_TAG, "<--Add button clicking...-->"); }
                break;
            }
}
        }


}
