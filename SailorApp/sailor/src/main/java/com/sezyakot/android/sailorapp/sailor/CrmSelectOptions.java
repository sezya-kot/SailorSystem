package com.sezyakot.android.sailorapp.sailor;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class CrmSelectOptions extends DefaultGroupActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addButtonsCreateList ();
		//Take namesOfItems from string.xml
		String[] titleOfItemOptions = getResources().getStringArray(R.array.crm_select_options);
		setRadioGroup(titleOfItemOptions);
		
		Button createNewBtn = (Button) findViewById(R.id.createNewBt);
		Button listBtn = (Button)findViewById(R.id.listBt);
		listBtn.setOnClickListener(onCloseActivity);
		
		createNewBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				selectedID = mRadioGroup.getCheckedRadioButtonId();
				RadioButton rb = (RadioButton)findViewById(selectedID);
				switch (selectedID) {
					case 1: {
						Toast.makeText(getApplicationContext(), ""+rb.getText(), Toast.LENGTH_SHORT).show();
						break;
					}
					case 2: {
						Toast.makeText(getApplicationContext(), ""+rb.getText(), Toast.LENGTH_SHORT).show();
						break;
					}
					case 3: {
						Toast.makeText(getApplicationContext(), ""+rb.getText(), Toast.LENGTH_SHORT).show();
						break;
					}
					case 4: {
						Toast.makeText(getApplicationContext(), ""+rb.getText(), Toast.LENGTH_SHORT).show();
						break;
					}
					case 5: {
						Toast.makeText(getApplicationContext(), ""+rb.getText(), Toast.LENGTH_SHORT).show();
						break;
					}
					default: {
						Toast.makeText(getApplicationContext(), "You must checked options", Toast.LENGTH_SHORT).show();
						break;
					}
				}
			}
		});

	}
}
