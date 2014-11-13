package com.sezyakot.android.sailorapp.sailor;

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
 * Created by Android on 25.09.2014.
 */
public abstract class FDEValue extends DefaultDialogFinancials implements View.OnClickListener{
    protected Button mButtonOk;
    protected EditText mEnterValue;
    DefaultFinancialsCreate mActivity;

    OnButtonOkListener mCallback;

    //

    public interface OnButtonOkListener {
        public void onButtonOkPressed(String nameOfElement, String value);
    }

    @Override
    protected int getIntResource() {
        return R.layout.enter_value;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mActivity = (DefaultFinancialsCreate) getActivity( );
        mButtonOk = (Button) mView.findViewById(R.id.ok);
        mEnterValue = (EditText) mView.findViewById(R.id.enter_value);
        mButtonOk.setOnClickListener(this);
        init();
        return mView;

    }

    protected abstract void init();

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallback = (OnButtonOkListener) activity.getFragmentManager().findFragmentById(R.id.fragment_container);
    }

    @Override
    protected String getTitle() {
        return getString(R.string.enter_amount_);
    }

    @Override
    public void onClick(View v) {
    }

    protected String getValue() {
        return (mEnterValue.getText().toString().trim().length() != 0 || mEnterValue != null || mEnterValue.getText().toString().trim() != "")
                ? mEnterValue.getText().toString() : "0";
    }

    protected void hideKeyboard(){
        InputMethodManager imm = (InputMethodManager)mActivity.getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mEnterValue.getWindowToken(), 0);
    };

}
