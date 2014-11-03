package com.sezyakot.sailor;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.sezyakot.sailor.db.DAO;
import com.sezyakot.sailor.db.DBHelper;
import com.sezyakot.sailor.model.*;
import com.sezyakot.sailor.system.Debug;
import com.sezyakot.sailor.system.Net;
import com.sezyakot.sailor.system.Preferences;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.*;
import java.net.SocketTimeoutException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SynchronizationActivity extends DefaultActivity implements View.OnClickListener {
    public static final String LOG_TAG = "SynchronizationActivity";


    Activity mActivity = this;
    Button mSynchBtn;
    ProgressDialog dialog;
    Preferences preferences;

    DAO mDAO = new DAO(this);

    ArrayList<Customer>         mCustomers;
    ArrayList<Product>          mProducts;
    ArrayList<Service>          mServices;
    ArrayList<Currency>         mCurrencies;
    ArrayList<Unit>             mUnits;
    ArrayList<UnitDetail>       mUnitDetails;
    ArrayList<ProductPrice>     mProductPrices;
    ArrayList<ServicePrice>     mServicePrices;
    ArrayList<Department>       mDepartments;
    ArrayList<Division>         mDivisions;
    ArrayList<Plant>            mPlants;
    ArrayList<Warehouse>        mWarehouses;
    ArrayList<Order>            mOrders;
    ArrayList<OrderItem>        mOrderItems;
	ArrayList<Dispatch>         mDispatches;
	ArrayList<DispatchItem>     mDispatchItems;
	ArrayList<Invoice>          mInvoices;
	ArrayList<InvoiceItem>      mInvoiceItems;
    ArrayList<CashPayment>      mCashPayments;
    ArrayList<CreditCardPayment> mCreditCardPayments;
    ArrayList<ChequePayment>    mChequePayments;
    ArrayList<BondPayment>      mBondPayments;

    @Override
    protected void onDestroy() {
        Debug.unlockScreenOrientation(this);
        super.onDestroy();
    }

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.synchronization);

        Debug.lockScreenOrientation(this);

        mSynchBtn = (Button) findViewById(R.id.synchronization);
        mSynchBtn.setOnClickListener(this);

        dialog = new ProgressDialog(this);
        dialog.setMessage(getString(R.string.connecting));
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);

        preferences = new Preferences(this);

	}

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.synchronization: {
                if(Debug.MODE) {
                    Log.d(LOG_TAG, "Button Synchronization was clicked!");
                }
                if (Net.isConnected(this)) {
                    try {
                        PostThread postThread = new PostThread();
                        postThread.execute();
                    } catch (Exception e) {
                        if (Debug.MODE) {
                            Log.d(LOG_TAG, "" + e);
                        }
                    }
                } else Toast.makeText(this, getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
                break;
            }
            default: break;
        }
    }

    class PostThread extends AsyncTask<Void, Void, Void> {
        public static final String LOG_TAG = "PostThread";

        @Override
        protected void onPreExecute() {
            if (Debug.MODE) Log.d(LOG_TAG, "<-- OnPreExecute: start! -->");
            dialog.show();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            if (Debug.MODE) Log.d(LOG_TAG, "<-- DoInBackground: start! -->");
            try {
                // 1. create HttpClient
                HttpClient client = new DefaultHttpClient();
                // 2. make POST request to the given URL
                HttpPost post = new HttpPost(Net.SERVER_SYNCH_URL);
                // 3. build dataJson
                Gson gson = new GsonBuilder()
		                .excludeFieldsWithoutExposeAnnotation()
		                .create();
                // 4. convert JSONObject to JSON to String
                Request request = new Request(SynchronizationActivity.this);
                //request.setSession(preferences.getSessionId());
                String dataJson = gson.toJson(request);
                if (Debug.MODE) {Log.d(LOG_TAG, "dataJson: " + dataJson);}
                /* 5. set json to sDataLogin
                * A name / value pair parameter used as an element of HTTP messages.
                *   parameter   = attribute "=" value
                *   attribute   = token
                *   value       = token | quoted-string
                *
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
                        Response response = gson.fromJson(reader, Response.class);
                        // String json = gson.toJson(user);
                        content.close();


                        switch (response.getError().getCode()) {
                            case 0: {
                                if (Debug.MODE) {
                                    Log.d(LOG_TAG, "Response: " + gson.toJson(response));
                                }
                                mCustomers      = new ArrayList<Customer>       (response.getData().getCustomers());
                                mProducts       = new ArrayList<Product>        (response.getData().getProducts());
                                mServices       = new ArrayList<Service>        (response.getData().getServices());
                                mCurrencies     = new ArrayList<Currency>       (response.getData().getCurrencies());
                                mUnits          = new ArrayList<Unit>           (response.getData().getUnits());
                                mUnitDetails    = new ArrayList<UnitDetail>     (response.getData().getUnitDetails());
                                mProductPrices  = new ArrayList<ProductPrice>   (response.getData().getProductPrices());
                                mServicePrices  = new ArrayList<ServicePrice>   (response.getData().getServicePrices());
                                mDepartments    = new ArrayList<Department>     (response.getData().getDepartments());
                                mDivisions      = new ArrayList<Division>       (response.getData().getDivisions());
                                mPlants         = new ArrayList<Plant>          (response.getData().getPlants());
                                mWarehouses     = new ArrayList<Warehouse>      (response.getData().getWarehouses());
                                mOrders         = new ArrayList<Order>          (response.getData().getOrders());
                                mOrderItems     = new ArrayList<OrderItem>      (response.getData().getOrderItems());
	                            mDispatches     = new ArrayList<Dispatch>       (response.getData().getDispatches());
	                            mDispatchItems  = new ArrayList<DispatchItem>   (response.getData().getDispatchItems());
	                            mInvoices       = new ArrayList<Invoice>        (response.getData().getInvoices());
	                            mInvoiceItems   = new ArrayList<InvoiceItem>    (response.getData().getInvoiceItems());
                                mCashPayments   = new ArrayList<CashPayment>    (response.getData().getCashPayments());
                                mCreditCardPayments = new ArrayList<CreditCardPayment>(response.getData().getCreditCardPayments());
                                mChequePayments = new ArrayList<ChequePayment>  (response.getData().getChequePayments());
                                mBondPayments   = new ArrayList<BondPayment>    (response.getData().getBondPayments());


                                if (Debug.MODE) {
                                    Log.d(LOG_TAG, "mCustomers: "   + mCustomers);
                                    Log.d(LOG_TAG, "mProducts: "    + mProducts);
                                    Log.d(LOG_TAG, "mServices: "    + mServices);
                                    Log.d(LOG_TAG, "mCashPayment.getCurrencyId: " + mCashPayments.get(mCashPayments.size()-1).getCurrencyId() );
                                }
                                try {
	                                long lStartTime = new Date().getTime();
									//FIXME To fix algorithm of synchronization!
                                    mDAO.openToWrite();
                                    {
                                        mDAO.clearTable(DBHelper.CUSTOMERS_TN);
                                        mDAO.clearTable(DBHelper.PRODUCTS_TN);
                                        mDAO.clearTable(DBHelper.SERVICES_TN);
                                        mDAO.clearTable(DBHelper.CURRENCIES_TN);
                                        mDAO.clearTable(DBHelper.PRODUCT_PRICES_TN);
                                        mDAO.clearTable(DBHelper.SERVICE_PRICES_TN);
                                        mDAO.clearTable(DBHelper.UNIT_TN);
                                        mDAO.clearTable(DBHelper.UNIT_DETAIL_TN);
                                        mDAO.clearTable(DBHelper.DEPARTMENT_TN);
                                        mDAO.clearTable(DBHelper.DIVISION_TN);
                                        mDAO.clearTable(DBHelper.PLANT_TN);
                                        mDAO.clearTable(DBHelper.WAREHOUSE_TN);
                                        mDAO.clearTable(DBHelper.ORDERS_TN);
                                        mDAO.clearTable(DBHelper.ORDER_ITEMS_TN);
                                        mDAO.clearTable(DBHelper.DISPATCHES_TN);
                                        mDAO.clearTable(DBHelper.DISPATCH_ITEMS_TN);
                                        mDAO.clearTable(DBHelper.INVOICES_TN);
                                        mDAO.clearTable(DBHelper.INVOICE_ITEMS_TN);
                                        mDAO.clearTable(DBHelper.CASH_PAYMENT_TN);
                                        mDAO.clearTable(DBHelper.CREDIT_CARD_PAYMENT_TN);
                                        mDAO.clearTable(DBHelper.CHEQUE_PAYMENT_TN);
                                        mDAO.clearTable(DBHelper.BOND_PAYMENT_TN);
                                    }
                                    {
                                        mDAO.putProductsToDb        (mProducts);
                                        mDAO.putCustomersToDb       (mCustomers);
                                        mDAO.putServicesToDb        (mServices);
                                        mDAO.putCurrenciesToDb      (mCurrencies);
                                        mDAO.putUnitsToDb           (mUnits);
                                        mDAO.putUnitDetailsToDb     (mUnitDetails);
                                        mDAO.putProductPricesToDb   (mProductPrices);
                                        mDAO.putServicePricesToDb   (mServicePrices);
                                        mDAO.putDepartmentsToDb     (mDepartments);
                                        mDAO.putDivisionsToDb       (mDivisions);
                                        mDAO.putPlantsToDb          (mPlants);
                                        mDAO.putWarehousesToDb      (mWarehouses);
                                        mDAO.putOrdersToDb          (mOrders);
                                        mDAO.putOrderItemsToDb      (mOrderItems);
                                        mDAO.putDispatchesToDb      (mDispatches);
                                        mDAO.putDispatchItemsToDb   (mDispatchItems);
                                        mDAO.putInvoicesToDb        (mInvoices);
                                        mDAO.putInvoiceItemsToDb    (mInvoiceItems);
                                        mDAO.putCashPaymentToDb     (mCashPayments);
                                        mDAO.putCreditCardPaymentToDb(mCreditCardPayments);
                                        mDAO.putChequePaymentToDb   (mChequePayments);
                                        mDAO.putBondPaymentToDb     (mBondPayments);
                                    }
                                    mDAO.close();
	                                long lEndTime = new Date().getTime();

	                                if (Debug.MODE) {
	                                	Log.d(LOG_TAG, "Time was spended to write to DB: " + (lEndTime - lStartTime) + " ms");
	                                }

                                } catch (SQLException e) {
                                    if (Debug.MODE) Log.e(LOG_TAG, "SQL problem: " + e.getMessage());
                                }
                                break;
                            }
                            default: if (Debug.MODE) { Log.d(LOG_TAG, mActivity.getString(R.string.server_no_answer)); }
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
            } catch (UnsupportedEncodingException e) {
                Log.e(LOG_TAG, "UnsupportedEncodingException: "+e);
            } catch (ClientProtocolException e) {
                Log.e(LOG_TAG, "ClientProtocolException: "+e);
            } catch (SocketTimeoutException | ConnectTimeoutException e) {
                Log.e(LOG_TAG, "TimeoutException: "+e);
            } catch (IOException e) {
                Log.e(LOG_TAG, "IOException: "+e);
            }
            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (dialog != null) dialog.dismiss();
	        //finish();
	        Intent i = new Intent(mActivity, MainMenu.class);
	        startActivity(i);

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}

