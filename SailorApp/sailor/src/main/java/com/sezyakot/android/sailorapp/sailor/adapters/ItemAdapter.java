package com.sezyakot.android.sailorapp.sailor.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.sezyakot.android.sailorapp.sailor.R;
import com.sezyakot.android.sailorapp.sailor.model.Item;


import java.util.List;

public class ItemAdapter extends ArrayAdapter<Item> {
	private final Context mContext;
	private List<Item> mValues;


	public ItemAdapter(Context context, List<Item> values) {
		super(context, R.layout.item_in_create_order, values);
		this.mContext = context;
		this.mValues = values;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		Item item = mValues.get(position);

		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.item_in_create_order, parent, false);
			holder = new ViewHolder();
			holder.description = (TextView) convertView.findViewById(R.id.item_description_in_order);
			holder.code = (TextView) convertView.findViewById(R.id.item_code_in_order);
			holder.summary = (TextView) convertView.findViewById(R.id.item_summary_in_order);
			holder.subtotal = (TextView) convertView.findViewById(R.id.item_subtotal);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}


		setDataItems(holder, item);

		return convertView;
	}

	private void setDataItems(ViewHolder holder, final Item item) {
		holder.description.setText(item.getName() + " - " + item.getDescription());
		holder.code.setText(item.getCode());
		holder.summary.setText(item.getBlueString());
		holder.subtotal.setText(item.getGreenString());
	}

	private static class ViewHolder {
		TextView description;
		TextView code;
		TextView summary;
		TextView subtotal;
	}

}
