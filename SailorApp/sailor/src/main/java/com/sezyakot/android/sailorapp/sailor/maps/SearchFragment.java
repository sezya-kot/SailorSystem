package com.sezyakot.android.sailorapp.sailor.maps;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import com.sezyakot.android.sailorapp.sailor.R;
import com.sezyakot.android.sailorapp.sailor.adapters.CustomerAdapter;
import com.sezyakot.android.sailorapp.sailor.db.DAO;
import com.sezyakot.android.sailorapp.sailor.model.Customer;
import com.sezyakot.android.sailorapp.sailor.model.MainCustomer;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Android on 01.12.2014.
 */
public class SearchFragment extends ListFragment {

    private ArrayList<Customer> mCustomers;
    private CustomerAdapter mAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListCustomer();
        setListAdapter(mAdapter);
    }

    private void setListCustomer() {
		/* mCustomers - instance of customers) */
            DAO mDAO = new DAO(getActivity());
            try {
                mDAO.openToRead();
                mCustomers = mDAO.getCustomers();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                mDAO.close();
            }

        mAdapter = new CustomerAdapter(getActivity(), mCustomers);

        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.search_fragment_view, container, false);
    }


}
