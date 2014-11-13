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
import com.sezyakot.android.sailorapp.sailor.model.Product;
import com.sezyakot.android.sailorapp.sailor.system.Debug;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android on 27.08.2014.
 */
public class ProductAdapter extends ArrayAdapter<Product> {
    public static final String LOG_TAG = "ProductAdapter";
    private final Context mContext;
    private List<Product> mProducts;
    private List<Product> mOrigProducts;

    public ProductAdapter(Context context, ArrayList<Product> products) {
        super(context, R.layout.single_item, products);
        mContext = context;
        mProducts = products;
        mOrigProducts = new ArrayList<Product>(products);
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

        Product product = mProducts.get(position);

        holder.description.setText(product.getName() + " - " + product.getDescription());
        holder.code.setText(product.getCode());

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
                    ArrayList<Product> list;
                    list = new ArrayList<Product>(mOrigProducts);
                    if (Debug.MODE) { Log.d(LOG_TAG, "< -- No filter implemented we return all the list! -- >");}

                    results.values = list;
                    results.count = list.size();

                }
                else {
                    // We perform filtering operation
                    List<Product> filteredArrayNames = new ArrayList<Product>();
                    constraint = constraint.toString().toLowerCase();

                    if (Debug.MODE) { Log.d(LOG_TAG, " < --- performFiltering() : constraint == " + constraint + " --- > "); }

                    for (Product p: mProducts) {
                        if (p.getName().toLowerCase().contains(constraint)|| p.getDescription().toLowerCase().contains(constraint) || p.getCode().toLowerCase().contains(constraint)) {
                            filteredArrayNames.add(p);
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

                if (Debug.MODE) { Log.d(LOG_TAG, "<=== publishResults ===>: " + "Done!"); }

                if (results.count == 0) {
                    notifyDataSetInvalidated();
                    Toast.makeText(mContext, "Nothing found!", Toast.LENGTH_SHORT).show();
                }
                else {
                    mProducts = (List<Product>)results.values;
                    notifyDataSetChanged();

                    if (Debug.MODE) { Log.d(LOG_TAG, "mProducts: " + mProducts); }
//                    mProducts = mOrigProducts;

                }
            }
        };
        return filter;
    }


    @Override
    public int getCount() {
        return mProducts.size();
    }

    @Override
    public Product getItem(int position) {
        //it needs to get a correct position of item after filtering
        return mProducts.get(position);
    }

}
