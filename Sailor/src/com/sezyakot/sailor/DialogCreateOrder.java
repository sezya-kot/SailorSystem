package com.sezyakot.sailor;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.sezyakot.sailor.model.*;
import com.sezyakot.sailor.system.Debug;
import com.sezyakot.sailor.system.Net;
import com.sezyakot.sailor.system.Preferences;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android on 30.08.2014.
 */
public class DialogCreateOrder extends DialogFragment implements View.OnClickListener {
    public final static String LOG_TAG = "DialogCreateOrder";

    protected DefaultSalesNew activity;
    private View mView;
    private Button mCreateButton;
    private ImageButton mCloseBtn;
    private TextView mCompanyNameTV;
    private TextView mDateTV;
    private TextView mSlipNumberTV;
    private TextView mCreateByTV;
    private TextView mSubtotalTV;
    private TextView mVATTV;
    private TextView mGTotalTV;
    private EditText mAdvancePaymentET;
	private boolean  mReady = false;
	private Order mOrder;

	public static DialogCreateOrder newInstance() {
		return new DialogCreateOrder();
	}


	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        activity = (SalesNewOrder) getActivity();
	    
	    mOrder = (Order) activity.mObject;

        mView = inflater.inflate(R.layout.dialog_create_order, null);
        TextView lTitle = (TextView) mView.findViewById(R.id.dc_title_of_category);
        lTitle.setText(getString(R.string.dialog_new_order_title));

        setUI();
        fillData();

        return mView;
    }

    private void fillData() {
        mCompanyNameTV.setText(""+MainCustomer.get(activity).getCustomer().getName());
        mDateTV.setText(mOrder.getDate());
        mSlipNumberTV.setText(mOrder.getSlipNumber());
        mCreateByTV.setText(new Preferences(activity).getUserName());
        mSubtotalTV.setText("" + mOrder.getSubtotalPrice() + " "+ activity.mCurrencyStr);
        mVATTV.setText(""+(mOrder.getTotalPrice() - mOrder.getSubtotalPrice()) + " "+ activity.mCurrencyStr);
        mGTotalTV.setText(""+mOrder.getTotalPrice() + " "+ activity.mCurrencyStr);
    }

    private void setUI() {
        mCompanyNameTV = (TextView) mView.findViewById(R.id.oc_company_name);
        mDateTV = (TextView) mView.findViewById(R.id.oc_date);
        mSlipNumberTV = (TextView) mView.findViewById(R.id.oc_slip_number);
        mCreateByTV = (TextView) mView.findViewById(R.id.oc_created_by);
        mSubtotalTV = (TextView) mView.findViewById(R.id.oc_subtotal);
        mVATTV = (TextView) mView.findViewById(R.id.oc_vat);
        mGTotalTV = (TextView) mView.findViewById(R.id.oc_g_total);
	    mAdvancePaymentET = (EditText) mView.findViewById(R.id.oc_advance_payment);

	    mCloseBtn = (ImageButton) mView.findViewById(R.id.oc_close_btn);
	    mCloseBtn.setOnClickListener(this);

	    mCreateButton = (Button) mView.findViewById(R.id.oc_create_button);
	    mCreateButton.setOnClickListener(this);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        // request a window without the title
        dialog.setCancelable(false);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.oc_create_button: {
                if(Debug.MODE) {
                    Log.d(LOG_TAG, "Button Synchronization was clicked!");
                }
                if (mAdvancePaymentET.getText().toString().trim().length() == 0 || mAdvancePaymentET.getText().toString() == null) {
                    mOrder.setAdvancePayment(0d);
                } else {
                    mOrder.setAdvancePayment(Double.parseDouble(mAdvancePaymentET.getText().toString()));
                }

                mOrder.setCustomerId(MainCustomer.get(getActivity()).getCustomer().getServerId());

                if (Net.isConnected(activity)) {
                    try {
                        PostThread postThread = new PostThread();
                        postThread.execute();

                    } catch (Exception e) {
                        if (Debug.MODE) {
                            Log.d(LOG_TAG, "" + e);
                        }
                    }

                } else Toast.makeText(activity, getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.oc_close_btn: {
                if(Debug.MODE) Log.d(LOG_TAG, "<--Cancel button clicking...-->");
                dismiss();
                break;
            }

        }

    }

    class PostThread extends AsyncTask<Void, Void, Void> {
        public static final String LOG_TAG = "PostThread";

        @Override
        protected void onPreExecute() {
            if (Debug.MODE) Log.d(LOG_TAG, "<-- OnPreExecute: start! -->");
            activity.dialog.show();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            if (Debug.MODE) Log.d(LOG_TAG, "<-- DoInBackground: start! -->");
            try {
                // 1. create HttpClient
                HttpClient client = new DefaultHttpClient();
                // 2. make POST request to the given URL
                HttpPost post = new HttpPost(Net.SERVER_ORDER_CREATE);
                // 3. build dataJson
                Gson gson = new GsonBuilder()
		                .excludeFieldsWithoutExposeAnnotation()
		                .create();
                // 4. convert JSONObject to JSON to String
                RequestOrder request = new RequestOrder(activity);
                    ArrayList<Order> orders = new ArrayList<Order>();
                    mOrder.setItems(activity.mItems);
                    orders.add(mOrder);
                    request.setOrders(orders);
                //request.setSession(preferences.getSessionId());
                String dataJson = gson.toJson(request);
                if (Debug.MODE) {Log.d(LOG_TAG, "dataJson: " + dataJson);}
                /* 5. set json to sDataLogin
                * A name / value pair parameter used as an element of HTTP messages.
                *   parameter   = attribute "=" value
                *   attribute   = token
                *   value       = token | quoted-string
                * */
                List<NameValuePair> pairs = new ArrayList<NameValuePair>();
                pairs.add(new BasicNameValuePair("data", dataJson));

                if (Debug.MODE) {Log.d(LOG_TAG, "nameValuePairs: " + pairs.toString());}

                // 6. set httpPost Entity
                post.setEntity(new UrlEncodedFormEntity(pairs));

                if (Debug.MODE) {Log.d(LOG_TAG, "Entity: " + post.getEntity().getContent());}

                // Perform the request and check the status code
                // 8. Execute POST request to the given URL
                HttpResponse httpResponse = client.execute(post);
                StatusLine statusLine = httpResponse.getStatusLine();
                if (Debug.MODE) {
                    Log.d(LOG_TAG, "Server's status: " + statusLine);
                }
                if (statusLine.getStatusCode() == 200) {

                    // 9. receive response as inputStream
                    HttpEntity entity = httpResponse.getEntity();
                    InputStream content = entity.getContent();

                    if (Debug.MODE) {
                        Log.d(LOG_TAG, content.toString());
                    }

                    try {
                        // Read the server response and attempt to parse it as
                        // JSON

                        Reader reader = new InputStreamReader(content);
                        if (Debug.MODE) {
                            Log.d(LOG_TAG, reader.toString());
                        }
                        // 10. convert inputStream to string
                        Response response = new Response();
                        response = gson.fromJson(reader, Response.class);
                        // String json = gson.toJson(user);
                        content.close();


                        switch (response.getError().getCode()) {
                            case 0: {
                                if (Debug.MODE) {
                                    Log.d(LOG_TAG, "" + gson.toJson(response));
									activity.mReady = true;
                                }
                                break;
                            }
                            default: if (Debug.MODE) { Log.d(LOG_TAG, activity.getString(R.string.server_no_answer)); }
                        }
                    } catch (JsonParseException ex) {
                        if (Debug.MODE) {
                            Log.e(LOG_TAG, "Failed to parse JSON due to: " + ex);
                        }
                    }
                } else {
                    if (Debug.MODE) {
                        Log.e(LOG_TAG, "Server responded with status code: " + statusLine.getStatusCode());
                    }
                }
            }
            catch (Exception ex) {
                if (Debug.MODE) {
                    Log.e(LOG_TAG, "Failed to send HTTP POST request due to: " + ex);
                }
            }

            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (activity.dialog != null) activity.dialog.dismiss();
	        if (activity.mReady) {
		            DialogCreateOrder.this.dismiss();
			        Intent i = new Intent(activity, SynchronizationActivity.class);
			        startActivity(i);
		            ItemList.get(activity).destroy();
			        activity.finish();
	        };
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}
