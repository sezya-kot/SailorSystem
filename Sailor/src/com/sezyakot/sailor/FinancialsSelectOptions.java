package com.sezyakot.sailor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class FinancialsSelectOptions extends DefaultGroupActivity {
    protected static final String TYPE = "type";

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addButtonsCreateList ();
		//Take namesOfItems from string.xml
		String[] titleOfItemOptions = getResources().getStringArray(R.array.financials_select_options);
		setRadioGroup(titleOfItemOptions);
		Button createNewBtn = (Button) findViewById(R.id.createNewBt);
		Button listBtn = (Button)findViewById(R.id.listBt);
		listBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedID = mRadioGroup.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton)findViewById(selectedID);
                switch (selectedID) {
                    case 1: {
                        runActivity(PaymentListActivity.class);
                        break;
                    }
                    case 2: {
                        runActivity(PaymentListActivity.class);
                        break;
                    }
                    case 3: {
                        runActivity(PaymentListActivity.class);
                        break;
                    }
                    case 4: {
                        runActivity(PaymentListActivity.class);
                        break;
                    }
                    default: {
                        Toast.makeText(getApplicationContext(), getString(R.string.checked_option), Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
            }
        });

		createNewBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				selectedID = mRadioGroup.getCheckedRadioButtonId();
				RadioButton rb = (RadioButton)findViewById(selectedID);
				switch (selectedID) {
					case 1: {
						runActivity(CashCreateNew.class);
						break;
					}
					case 2: {
						runActivity(CreditCardCreateNew.class);
						break;
					}
					case 3: {
						runActivity(ChequeCreateNew.class);
						break;
					}
					case 4: {
						runActivity(BondCreateNew.class);
						break;
					}
					default: {
						Toast.makeText(getApplicationContext(), getString(R.string.checked_option), Toast.LENGTH_SHORT).show();
						break;
					}
				}
			}
		});

	}

	private void runActivity(Class<? extends DefaultFinancialsCreate> tClass) {
		Intent i = new Intent(this, tClass);
        i.putExtra(TYPE, selectedID);
		startActivity(i);
	}
}
