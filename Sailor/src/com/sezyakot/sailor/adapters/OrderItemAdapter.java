package com.sezyakot.sailor.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sezyakot.sailor.R;
import com.sezyakot.sailor.model.OrderItem;

import java.util.List;

public class OrderItemAdapter extends ArrayAdapter<OrderItem> {
	private final Context mContext;
	private List<OrderItem> mValues;


	public OrderItemAdapter(Context context, List<OrderItem> values) {
		super(context, R.layout.item_in_create_order, values);
		this.mContext = context;
		this.mValues = values;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		OrderItem orderItem = mValues.get(position);
		
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


        setDataItems(holder, orderItem);
		
		return convertView;
	}

    private void setDataItems(ViewHolder holder, final OrderItem orderItem) {
        holder.description.setText(orderItem.getName() + " - " + orderItem.getDescription());
        holder.code.setText(orderItem.getCode());
        holder.summary.setText(orderItem.getBlueString());
        holder.subtotal.setText(orderItem.getGreenString());
    }

    private static class ViewHolder {
		TextView description;
		TextView code;
		TextView summary;
		TextView subtotal;
	}

}
