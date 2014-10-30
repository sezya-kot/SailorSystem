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
import com.sezyakot.sailor.adapters.PaymentAdapter;
import com.sezyakot.sailor.db.DAO;
import com.sezyakot.sailor.model.*;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Android on 29.09.2014.
 */
public class PaymentListFragment extends ListFragment {
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
            case 1: return mDAO.getCashPayments(null);
            case 2: return mDAO.getCreditCardPayments(null);
            case 3: return mDAO.getChequePayments(null);
            case 4: return mDAO.getBondPayments(null);
            default: break;
        }
        return null;
    }
}
