package com.sezyakot.android.sailorapp.sailor.adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.sezyakot.android.sailorapp.sailor.R;


public class MainMenuArrayAdapter extends ArrayAdapter<String> {
	private final Context mContext;
	private final String[] values;
	private final int[] itemsOfCompound;
	
	public MainMenuArrayAdapter(Context context, String[] values) {
		super(context, R.layout.main_list_item, values);
	    this.mContext = context;
	    this.values = values;
	    this.itemsOfCompound = new int[] { 	  R.drawable.ic_sales,
											  R.drawable.ic_financials,
											  R.drawable.ic_crm,
											  R.drawable.ic_reports,
											  R.drawable.ic_gps,
											  R.drawable.ic_calendar,
											  R.drawable.ic_others,
											  R.drawable.ic_communication,
											  R.drawable.ic_synchronization,
											  R.drawable.ic_about };
	}
	 @Override
	 public View getView(int position, View convertView, ViewGroup parent) {
		 ViewHolder holder;
		 if (convertView == null) {
			 LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			 convertView = inflater.inflate(R.layout.main_list_item, parent, false);
			 holder = new ViewHolder();
			 holder.menu = (TextView) convertView.findViewById(R.id.textView);
			 convertView.setTag(holder);
		 } else {
			 holder = (ViewHolder) convertView.getTag();
		 }
		 holder.menu.setText(values[position]);
		 holder.menu.setCompoundDrawablesWithIntrinsicBounds(itemsOfCompound[position], 0, 0, 0);
		 
		 return convertView;
	 }
	 private static class ViewHolder {
		 TextView menu;
	 }
	
}
