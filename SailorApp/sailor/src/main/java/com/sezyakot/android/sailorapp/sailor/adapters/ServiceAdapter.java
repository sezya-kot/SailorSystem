package com.sezyakot.android.sailorapp.sailor.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;
import android.widget.Toast;
import com.sezyakot.android.sailorapp.sailor.R;
import com.sezyakot.android.sailorapp.sailor.model.Service;
import com.sezyakot.android.sailorapp.sailor.system.Debug;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android on 27.08.2014.
 */
public class ServiceAdapter extends ArrayAdapter<Service> {
    public static final String LOG_TAG = "ServiceAdapter";
    private final Context mContext;
    private List<Service> mServices;
    private List<Service> mOrigServices;
    public ServiceAdapter(Context context, ArrayList<Service> services) {
        super(context, R.layout.single_item, services);
        mContext = context;
        mServices = services;
        mOrigServices = new ArrayList<Service>(services);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.single_item, parent, false);
            holder = new ViewHolder();
            holder.description = (TextView) convertView.findViewById(R.id.item_description);
            holder.code = (TextView) convertView.findViewById(R.id.item_code_si);
            convertView.setTag(holder);
        } else{
            holder = (ViewHolder) convertView.getTag();
        }
        Service service = mServices.get(position);
        holder.description.setText(service.getName() + " - " + service.getDescription());
        holder.code.setText(service.getCode());
        return convertView;
    }

    private static class ViewHolder {
        TextView description;
        TextView code;
    }

    @Override
    public Filter getFilter() {
        final Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();

                // We implement here the filter logic!
                if (constraint == null || constraint.length() == 0) {
                    // No filter implemented we return all the list

                    if (Debug.MODE) { Log.d(LOG_TAG, "< -- No filter implemented we return all the list! -- >");}
                    ArrayList<Service> list = new ArrayList<Service>(mOrigServices);
                    results.values = list;
                    results.count = list.size();

                }
                else {
                    // We perform filtering operation
                    List<Service> filteredArrayNames = new ArrayList<Service>();
                    constraint = constraint.toString().toLowerCase();

                    if (Debug.MODE) { Log.d(LOG_TAG, " < --- performFiltering() : constraint == " + constraint + " --- > "); }

                    for (Service s: mServices) {
                        if (s.getName().toLowerCase().contains(constraint)|| s.getDescription().toLowerCase().contains(constraint) || s.getCode().toLowerCase().contains(constraint)) {
                            filteredArrayNames.add(s);
                        }
                    }
                    results.values = filteredArrayNames;
                    results.count = filteredArrayNames.size();

                    if (Debug.MODE) { Log.d(LOG_TAG, "results.values: " + results.values.toString() + "\n results.count: " + results.count); }
                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results.count == 0) {
                    notifyDataSetInvalidated();
                    Toast.makeText(mContext, "Nothing found!", Toast.LENGTH_SHORT).show();
                }
                else {
                    mServices = (List<Service>)results.values;
                    notifyDataSetChanged();
                    if (Debug.MODE) { Log.d(LOG_TAG, "mServices: " + mServices); }
                }
            }
        };
        return filter;
    }

    @Override
    public int getCount() {
        return mServices.size();
    }

    @Override
    public Service getItem(int position) {
        //it needs to get a correct position of item after filtering
        return mServices.get(position);
    }

}