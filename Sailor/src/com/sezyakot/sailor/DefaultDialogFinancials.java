/*
 * Copyright (c) 2014.
 */

package com.sezyakot.sailor;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import com.sezyakot.sailor.model.CashPayment;
import com.sezyakot.sailor.model.MainCustomer;
import com.sezyakot.sailor.model.Payment;
import com.sezyakot.sailor.system.Preferences;

/**
 * Created by Android on 24.09.2014.
 */
public abstract class DefaultDialogFinancials extends DialogFragment implements View.OnClickListener {
    protected View mView;
    protected ImageButton mDissmiss;
    protected Button mCreate;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        // request a window without the title
        dialog.setCancelable(false);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.dialog_wrapper, null);
        mDissmiss = (ImageButton) mView.findViewById(R.id.close_btn);
        mDissmiss.setOnClickListener(new OnCloseListener());
        TextView lTitle = (TextView) mView.findViewById(R.id.dialog_title);
        lTitle.setText(getTitle());
        FrameLayout lContainer = (FrameLayout) mView.findViewById(R.id.container);
        lContainer.addView(inflater.inflate(getIntResource(), null));

		return mView;
	}

    protected abstract int getIntResource();
    protected abstract String   getTitle();


    protected class OnCloseListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            dismiss();
        }
    }

}
