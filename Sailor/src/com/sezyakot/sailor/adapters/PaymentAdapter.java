/*
 * Copyright (c) 2014.
 */

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
import com.sezyakot.sailor.model.Payment;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Android on 29.09.2014.
 */
public class PaymentAdapter extends ArrayAdapter<Payment>{
    protected Context mContext;
    protected List<? extends Payment> mPayments;
    protected int mType;

    public PaymentAdapter(Context context, List<? extends Payment> pPayments, int type) {
        super(context, R.layout.order_list_item, (List<Payment>) pPayments);
        this.mContext = context;
        this.mPayments = pPayments;
        this.mType = type;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Payment lPayment = mPayments.get(position);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.payment_list_item, parent, false);
            holder = new ViewHolder();
            holder.mDate = (TextView) convertView.findViewById(R.id.date);
            holder.mSlipNumber = (TextView) convertView.findViewById(R.id.slip_number);
            holder.mPaymentAmount = (TextView) convertView.findViewById(R.id.payment_amount);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        setDataItems(holder, lPayment);

        return convertView;
    }
    private static class ViewHolder {
        TextView mDate;
        TextView mSlipNumber;
        TextView mPaymentAmount;
    }

    private void setDataItems(ViewHolder holder, Payment pPayment) {
//        String currency = order.getCurrencyName(mContext);
        String currency = pPayment.getCurrencyName(getContext());
        holder.mSlipNumber.setText(mContext.getString(R.string.slip_number_) +" "+ pPayment.getSlipNumber());
        holder.mPaymentAmount.setText(mContext.getString(R.string.payment_amount_) +" "+ pPayment.getAmount() +" "+ currency);
        switch (mType) {
            case 1: {
                holder.mDate.setText(mContext.getString(R.string.cash_payment_date_) +" "+ pPayment.getDate());
                break;
            }
            case 2: {
                holder.mDate.setText(mContext.getString(R.string.credit_card_payment_date_) +" "+ pPayment.getDate());
                break;
            }
            case 3: {
                holder.mDate.setText(mContext.getString(R.string.cheque_payment_date_) +" "+ pPayment.getDate());
                break;
            }
            case 4: {
                holder.mDate.setText(mContext.getString(R.string.bond_payment_date_) +" "+ pPayment.getDate());
                break;
            }
        }
    }
}
