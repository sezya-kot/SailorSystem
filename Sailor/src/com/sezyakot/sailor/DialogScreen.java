package com.sezyakot.sailor;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.View;

public class DialogScreen {

	public static final int IDD_ABOUT = 1; // Идентификаторы диалоговых окон
	public static final int IDD_SETTINGS = 2;
	public static final int IDD_RATE = 3;

	public static Dialog getDialog(Activity activity, int ID) {
		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		
		View inflater = (View) activity.getLayoutInflater().inflate(R.layout.select_currency, null);

		switch (ID) {
			case IDD_ABOUT : // Диалоговое окно About
				builder.setCancelable(true);
				builder.setView(inflater);
				return builder.create();
			default :
				return null;
		}
	}
}