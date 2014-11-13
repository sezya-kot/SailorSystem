/*
 * Copyright (c) 2014.
 */

package com.sezyakot.sailor;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.sezyakot.sailor.adapters.PaymentAdapter;
import com.sezyakot.sailor.db.DAO;
import com.sezyakot.sailor.model.*;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Android on 29.09.2014.
 */
public class PaymentListFragment extends ListFragment {
    public static final String SERVER_ID = "server_id";
    public static final int CASH_PAYMENT = 1;
    public static final int CREDIT_CARD_PAYMENT = 2;
    public static final int CHEQUE_PAYMENT = 3;
    public static final int BOND_PAYMENT = 4;
    public static final String TYPE = "type";
    protected PaymentAdapter mAdapter;
    protected ArrayList<? extends Payment> mPayments;
    protected int mType;
    protected DefaultFinancialsCreate mActivity;
    protected DAO mDAO;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDAO = new DAO(getActivity());
        try {
            mDAO.openToRead();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        try {
            mDAO.openToRead();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mDAO.close();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = setActivity();
        mType = getActivity().getIntent().getIntExtra(FinancialsSelectOptions.TYPE, 0);
        mPayments = getPayments();
        mAdapter = new PaymentAdapter(mActivity, mPayments, mType);
        setListAdapter(mAdapter);
    }
    protected DefaultFinancialsCreate setActivity() {
        return  (PaymentListActivity) getActivity();
    }

    private ArrayList<? extends Payment> getPayments() {
        switch (mType) {
            case CASH_PAYMENT: return mDAO.getCashPayments(null);
            case CREDIT_CARD_PAYMENT: return mDAO.getCreditCardPayments(null);
            case CHEQUE_PAYMENT: return mDAO.getChequePayments(null);
            case BOND_PAYMENT: return mDAO.getBondPayments(null);
            default: break;
        }
        return null;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent i = new Intent(getActivity(), FinancialsPaymentDetailsActivity.class);
        i.putExtra(SERVER_ID, mPayments.get(position).getServerId());
        i.putExtra(TYPE, mType);
        startActivity(i);
    }


}
