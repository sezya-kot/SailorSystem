package com.sezyakot.sailor;

import java.lang.reflect.Field;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

class SetupContentView {

	static void setupActionBar(Context c) {
		ActionBar ab = ((Activity)c).getActionBar();
		ab.setDisplayShowCustomEnabled(true);
		ab.setDisplayShowTitleEnabled(false);
		ab.setDisplayShowHomeEnabled(false);
		ab.setCustomView(R.layout.action_bar_title);
	}
	
	@SuppressLint("DefaultLocale")
	static void addCategoryTitle(Context c, View v, String title) {
		
		TextView titleOfCategory = (TextView) v.findViewById(R.id.title_of_category);
		final Activity a = (Activity) c;
		titleOfCategory.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				a.finish();
			}
		});
		
		titleOfCategory.setText(title);
		String resSt = "ic_"+title.toLowerCase();
		
		try {
		    Class res = R.drawable.class;
		    Field field = res.getField(resSt);
		    int resID = field.getInt(null);
		    titleOfCategory.setCompoundDrawablesWithIntrinsicBounds(resID, 0, R.drawable.ic_back, 0);
		}
		catch (Exception e) {
		    Log.e("SetupContentView", "Failure to get drawable id.", e);
		}
		
	}

}
