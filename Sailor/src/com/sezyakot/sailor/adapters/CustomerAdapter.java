package com.sezyakot.sailor.adapters;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sezyakot.sailor.R;
import com.sezyakot.sailor.model.Customer;

public class CustomerAdapter extends ArrayAdapter<Customer> {
	private Context	mContext;
	
	public CustomerAdapter(Context c, ArrayList<Customer> customers) {
		super(c, R.layout.sales_item, customers);
		mContext = c;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.sales_item, parent, false);
			holder = new ViewHolder();
			holder.tv = (TextView) convertView.findViewById(R.id.salesItemTV);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		// Take position of Customer
		
		Customer customer = getItem(position);
		// if (customer.getLongitude() == 0 && customer.getLatitude() == 0) {
		if (false) {
			holder.tv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_gps_point, 0, 0, 0);
		} else {
			holder.tv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_empty, 0, 0, 0);
		}
		holder.tv.setText(customer.getName());
		return convertView;
	}
	
	private static class ViewHolder {
		TextView tv;
	}
}
