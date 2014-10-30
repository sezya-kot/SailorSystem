package com.sezyakot.sailor.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sezyakot.sailor.R;
import com.sezyakot.sailor.db.DAO;
import com.sezyakot.sailor.model.DefaultObject;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Android on 30.08.2014.
 */
public class ObjectAdapter extends ArrayAdapter<DefaultObject> {
    protected final Context mContext;
    protected List<DefaultObject> mValues;
	protected int mType;
	protected DAO mDao;

    public ObjectAdapter(Context context, List<DefaultObject> values, int type) {
        super(context, R.layout.order_list_item, values);
        this.mContext = context;
        this.mValues = values;
	    this.mType = type;
	    mDao = new DAO(mContext);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        DefaultObject object = mValues.get(position);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.order_list_item, parent, false);
            holder = new ViewHolder();
            holder.mOrderDate = (TextView) convertView.findViewById(R.id.oli_order_date);
            holder.mSlipNumber = (TextView) convertView.findViewById(R.id.oli_slip_number);
            holder.mOrderAmount = (TextView) convertView.findViewById(R.id.oli_order_amount);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        setDataItems(holder, object);

        return convertView;
    }
    private static class ViewHolder {
        TextView mOrderDate;
        TextView mSlipNumber;
        TextView mOrderAmount;
    }

    private void setDataItems(ViewHolder holder, DefaultObject object) {
//        String currency = order.getCurrencyName(mContext);
	    holder.mSlipNumber.setText(mContext.getString(R.string.slip_number_) + object.getSlipNumber());
	    String currency = getCurrency(object.getCurrency());
	    switch (mType) {
		    case 1: {
			    holder.mOrderDate.setText(mContext.getString(R.string.order_date_) + object.getDate());
			    holder.mOrderAmount.setText(mContext.getString(R.string.order_amount_) + object.getSubtotalPrice() + currency+
					    " ("+ object.getTotalPrice() + currency + " incl. VAT)" );
			    break;
		    }
		    case 2: {
			    holder.mOrderDate.setText(mContext.getString(R.string.dispatch_bill_date_) + object.getDate());
			    holder.mOrderAmount.setText(mContext.getString(R.string.dispatch_bill_amount_) + object.getSubtotalPrice() + currency+
					    " ("+ object.getTotalPrice() + currency + " incl. VAT)" );

			    break;
		    }
		    case 3: {
			    holder.mOrderDate.setText(mContext.getString(R.string.invoice_date_) + object.getDate());
			    holder.mOrderAmount.setText(mContext.getString(R.string.invoice_amount_) + object.getSubtotalPrice() + currency+
					    " ("+ object.getTotalPrice() + currency + " incl. VAT)" );
			    break;
		    }
		    case 4: {
			    holder.mOrderDate.setText(mContext.getString(R.string.refund_ticket_date_) + object.getDate());
			    holder.mOrderAmount.setText(mContext.getString(R.string.refund_ticket_amount_) + object.getSubtotalPrice() + currency+
					    " ("+ object.getTotalPrice() + currency + " incl. VAT)" );
			    break;
		    }
	    }
    }

	protected String getCurrency(int currencyId) {
		String currency = null;
		try {
			mDao.openToRead();
			currency = mDao.getCurrency(currencyId).getName();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mDao.close();
		}
		return currency;
	}
}
