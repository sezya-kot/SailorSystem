package com.sezyakot.android.sailorapp.sailor;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;



public class SalesSelectOptions extends DefaultGroupActivity {


	public final static String LOG_TAG = "SalesSelectOptions";
	RadioButton mRb;
	Button mCreateNewBtn;
	Button mListBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		addButtonsCreateList ();
		//Take namesOfItems from string.xml
		String[] titleOfItemOptions = getResources().getStringArray(R.array.sales_select_options);
		
		setRadioGroup(titleOfItemOptions);
		
		mCreateNewBtn = (Button) findViewById(R.id.createNewBt);
		mListBtn = (Button)findViewById(R.id.listBt);
//		mRb = (RadioButton)findViewById(selectedID);


		mListBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
	            selectedID = mRadioGroup.getCheckedRadioButtonId();
	            mRb = (RadioButton)findViewById(selectedID);
	            switch (selectedID) {
		            case 1: {
			            Log.d(LOG_TAG, ""+mRb.getText());
			            Intent i = new Intent(getApplicationContext(), OrderListActivity.class);
			            runActivity(i);
			            break;
		            }
		            case 2: {
			            Log.d(LOG_TAG, ""+mRb.getText());
			            Intent i = new Intent(getApplicationContext(), DispatchListActivity.class);
			            runActivity(i);
			            break;
		            }
		            case 3: {
			            Log.d(LOG_TAG, ""+mRb.getText());
			            Intent i = new Intent(getApplicationContext(), InvoiceListActivity.class);
			            runActivity(i);
			            break;
		            }
		            case 4: {
			            Log.d(LOG_TAG, ""+mRb.getText());
			            Intent i = new Intent(getApplicationContext(), RefundListActivity.class);
			            runActivity(i);
			            break;
		            }
		            default: {
			            Toast.makeText(getApplicationContext(), getString(R.string.check_any_options), Toast.LENGTH_SHORT).show();
			            break;
		            }
	            }

            }
        });
		
		mCreateNewBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				selectedID = mRadioGroup.getCheckedRadioButtonId();
				mRb = (RadioButton)findViewById(selectedID);
				switch (selectedID) {
					case 1: {
						Log.d(LOG_TAG, ""+mRb.getText());
						Intent i = new Intent(getApplicationContext(), SalesNewOrder.class);
						runActivity(i);
						break;
					}
					case 2: {
						Log.d(LOG_TAG, ""+mRb.getText());
						Intent i = new Intent(getApplicationContext(), SalesNewDispatch.class);
						runActivity(i);
						break;
					}
					case 3: {
						Log.d(LOG_TAG, ""+mRb.getText());
						Intent i = new Intent(getApplicationContext(), SalesNewInvoice.class);
						runActivity(i);
						break;
					}
					case 4: {
						Log.d(LOG_TAG, ""+mRb.getText());
						Intent i = new Intent(getApplicationContext(), SalesNewRefund.class);
						runActivity(i);
						break;
					}
					default: {
						Toast.makeText(getApplicationContext(), getString(R.string.check_any_options), Toast.LENGTH_SHORT).show();
						break;
					}
				}
			}
		});

	}

	private void runActivity(Intent i) {
		startActivity(i);
	}

}
