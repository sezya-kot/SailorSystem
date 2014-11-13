package com.sezyakot.android.sailorapp.sailor.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.sezyakot.android.sailorapp.sailor.model.*;
import com.sezyakot.android.sailorapp.sailor.system.Debug;


import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Android on 20.08.2014.
 */
public class DAO {
	public static final String ON = " ON ";
	public static final String INNER_JOIN = " INNER JOIN ";
	public static final String POINT = ".";
	public static final String AS = " AS ";
	public static final String EQUAL = " = ";
	public static final String STATUS_ACTIVE = "1";
	public static final String AND = " AND ";
	public static final String LOG_TAG = "DAO";

    private SQLiteDatabase mDb;
    private DBHelper mDbHelper;

//    private String[] allCustomerColumns = {
//            DBHelper.COL_ID,
//            DBHelper.COL_SERVER_ID,
//            DBHelper.COL_NAME,
//            DBHelper.COL_LONGITUDE,
//            DBHelper.COL_LATITUDE,
//            DBHelper.COL_VERSION,
//            DBHelper.COL_STATUS
//    };

    public DAO(Context c) {
        mDbHelper = new DBHelper(c);
    }

    public void putCustomersToDb(ArrayList<Customer> customers) {
        ContentValues values = new ContentValues();
            for (Customer c : customers) {
                values.put(DBHelper.COL_SERVER_ID, c.getServerId());
                values.put(DBHelper.COL_NAME, c.getName());
                values.put(DBHelper.COL_LONGITUDE, c.getLongitude());
                values.put(DBHelper.COL_LATITUDE, c.getLatitude());
                values.put(DBHelper.COL_STATUS, c.getStatus());
                values.put(DBHelper.COL_VERSION, c.getVersion());
                long rowID = mDb.insert(DBHelper.CUSTOMERS_TN, null, values);
                if (Debug.MODE) {
                    Log.d(LOG_TAG, "ROW ID: " + rowID);
                }
            }
    }

    public void putProductsToDb(ArrayList<Product> products) {
        ContentValues values = new ContentValues();
        for (Product p : products) {
            values.put(DBHelper.COL_SERVER_ID, p.getServerId());
            values.put(DBHelper.COL_NAME, p.getName());
            values.put(DBHelper.COL_DESCRIPTION, p.getDescription());
            values.put(DBHelper.COL_CODE, p.getCode());
            values.put(DBHelper.COL_VAT, p.getVat());
            values.put(DBHelper.COL_UNIT_ID, p.getUnit());
            values.put(DBHelper.COL_STATUS, p.getStatus());
            values.put(DBHelper.COL_VERSION, p.getVersion());
            long rowID =  mDb.insert(DBHelper.PRODUCTS_TN, null, values);
            if (Debug.MODE) {
                Log.d(LOG_TAG, "ROW ID: " + rowID);
            }
        }
    }

    public void putServicesToDb(ArrayList<Service> services) {
        ContentValues values = new ContentValues();
        for (Service s : services) {
            values.put(DBHelper.COL_SERVER_ID, s.getServerId());
            values.put(DBHelper.COL_NAME, s.getName());
            values.put(DBHelper.COL_DESCRIPTION, s.getDescription());
            values.put(DBHelper.COL_CODE, s.getCode());
            values.put(DBHelper.COL_VAT, s.getVat());
            values.put(DBHelper.COL_UNIT_ID, s.getUnit());
            values.put(DBHelper.COL_STATUS, s.getStatus());
            values.put(DBHelper.COL_VERSION, s.getVersion());
            long rowID =  mDb.insert(DBHelper.SERVICES_TN, null, values);
            if (Debug.MODE) {
                Log.d(LOG_TAG, "ROW ID: " + rowID);
            }
        }
    }

    public void putCurrenciesToDb(ArrayList<Currency> currencies) {
        ContentValues values = new ContentValues();
        for (Currency c : currencies) {
            values.put(DBHelper.COL_SERVER_ID, c.getServerId());
            values.put(DBHelper.COL_NAME, c.getName());
            values.put(DBHelper.COL_VERSION, c.getVersion());
            values.put(DBHelper.COL_STATUS, c.getStatus());
            long rowID = mDb.insert(DBHelper.CURRENCIES_TN, null, values);
            if (Debug.MODE) { Log.d(LOG_TAG, "ROW ID: " + rowID); }
        }
    }

    public void putUnitsToDb(ArrayList<Unit> units) {
        ContentValues values = new ContentValues();
        for (Unit u: units) {
            values.put(DBHelper.COL_SERVER_ID, u.getServerId());
            values.put(DBHelper.COL_NAME, u.getName());
            values.put(DBHelper.COL_VERSION, u.getVersion());
            values.put(DBHelper.COL_STATUS, u.getStatus());
            long rowID = mDb.insert(DBHelper.UNIT_TN, null, values);
            if (Debug.MODE) { Log.d(LOG_TAG, "ROW ID: " + rowID); }
        }
    }

    public void putUnitDetailsToDb(ArrayList<UnitDetail> unitDetails){
        ContentValues values = new ContentValues();
        for (UnitDetail u: unitDetails) {
            values.put(DBHelper.COL_SERVER_ID, u.getServerId());
            values.put(DBHelper.COL_NAME, u.getName());
            values.put(DBHelper.COL_UNIT_ID, u.getUnitId());
            values.put(DBHelper.COL_FROM, u.getFrom());
            values.put(DBHelper.COL_TO, u.getTo());
            values.put(DBHelper.COL_MAIN, u.getMain());
            values.put(DBHelper.COL_VERSION, u.getVersion());
            values.put(DBHelper.COL_STATUS, u.getStatus());
            long rowID = mDb.insert(DBHelper.UNIT_DETAIL_TN, null, values);
            if (Debug.MODE) { Log.d(LOG_TAG, "ROW ID: " + rowID); }
        }
    }

    public void putProductPricesToDb(ArrayList<ProductPrice> productPrices) {
        ContentValues values = new ContentValues();
        for (ProductPrice pp: productPrices) {
            values.put(DBHelper.COL_SERVER_ID, pp.getServerId());
            values.put(DBHelper.COL_PRODUCT_ID, pp.getProductId());
            values.put(DBHelper.COL_PRICE, pp.getPrice());
            values.put(DBHelper.COL_CURRENCY_ID, pp.getCurrencyId());
            values.put(DBHelper.COL_VERSION, pp.getVersion());
            long rowID = mDb.insert(DBHelper.PRODUCT_PRICES_TN, null, values);
            if (Debug.MODE) { Log.d(LOG_TAG, "ROW ID: " + rowID); }
        }
    }

    public void putServicePricesToDb(ArrayList<ServicePrice> servicePrices) {
        ContentValues values = new ContentValues();
        for (ServicePrice ss: servicePrices) {
            values.put(DBHelper.COL_SERVER_ID, ss.getServerId());
            values.put(DBHelper.COL_SERVICE_ID, ss.getServiceId());
            values.put(DBHelper.COL_PRICE, ss.getPrice());
            values.put(DBHelper.COL_CURRENCY_ID, ss.getCurrencyId());
            values.put(DBHelper.COL_VERSION, ss.getVersion());
            long rowID = mDb.insert(DBHelper.SERVICE_PRICES_TN, null, values);
            if (Debug.MODE) { Log.d(LOG_TAG, "ROW ID: " + rowID); }
        }
    }

    public void putDepartmentsToDb(ArrayList<Department> departments) {
        ContentValues values = new ContentValues();
        for (Department d: departments) {
            values.put(DBHelper.COL_SERVER_ID, d.getServerId());
            values.put(DBHelper.COL_NAME, d.getName());
            values.put(DBHelper.COL_VERSION, d.getVersion());
            values.put(DBHelper.COL_STATUS, d.getStatus());
            long rowID = mDb.insert(DBHelper.DEPARTMENT_TN, null, values);
            if (Debug.MODE) { Log.d(LOG_TAG, "ROW ID: " + rowID); }
        }
    }

    public void putDivisionsToDb(ArrayList<Division> divisions) {
        ContentValues values = new ContentValues();
        for (Division d: divisions) {
            values.put(DBHelper.COL_SERVER_ID, d.getServerId());
            values.put(DBHelper.COL_NAME, d.getName());
            values.put(DBHelper.COL_VERSION, d.getVersion());
            values.put(DBHelper.COL_STATUS, d.getStatus());
            long rowID = mDb.insert(DBHelper.DIVISION_TN, null, values);
            if (Debug.MODE) { Log.d(LOG_TAG, "ROW ID: " + rowID); }
        }
    }

    public void putPlantsToDb(ArrayList<Plant> plants) {
        ContentValues values = new ContentValues();
        for (Plant p: plants) {
            values.put(DBHelper.COL_SERVER_ID, p.getServerId());
            values.put(DBHelper.COL_NAME, p.getName());
            values.put(DBHelper.COL_VERSION, p.getVersion());
            values.put(DBHelper.COL_STATUS, p.getStatus());
            long rowID = mDb.insert(DBHelper.PLANT_TN, null, values);
            if (Debug.MODE) { Log.d(LOG_TAG, "ROW ID: " + rowID); }
        }
    }

    public void putWarehousesToDb(ArrayList<Warehouse> warehouses) {
        ContentValues values = new ContentValues();
        for (Warehouse w: warehouses) {
            values.put(DBHelper.COL_SERVER_ID, w.getServerId());
            values.put(DBHelper.COL_NAME, w.getName());
            values.put(DBHelper.COL_VERSION, w.getVersion());
            values.put(DBHelper.COL_STATUS, w.getStatus());
            long rowID = mDb.insert(DBHelper.WAREHOUSE_TN, null, values);
            if (Debug.MODE) { Log.d(LOG_TAG, "ROW ID: " + rowID); }
        }
    }

    public void putOrdersToDb(ArrayList<Order> orders) {
        ContentValues values = new ContentValues();
        for (Order o: orders) {
            values.put(DBHelper.COL_SERVER_ID, o.getServerId());
            values.put(DBHelper.COL_CUSTOMER_ID, o.getCustomerId());
            values.put(DBHelper.COL_SLIP_NUMBER, o.getSlipNumber());
            values.put(DBHelper.COL_SPECIAL_CODE, o.getSpecialCode());
            values.put(DBHelper.COL_DATE, o.getDate());
            values.put(DBHelper.COL_DIVISION_ID, o.getDivision());
            values.put(DBHelper.COL_DEPARTMENT_ID, o.getDepartment());
            values.put(DBHelper.COL_PLANT_ID, o.getPlant());
            values.put(DBHelper.COL_WAREHOUSE_ID, o.getWarehouse());
            values.put(DBHelper.COL_CURRENCY_ID, o.getCurrency());
            values.put(DBHelper.COL_TOTAL,o.getTotalPrice());
            values.put(DBHelper.COL_SUBTOTAL, o.getSubtotalPrice());
            values.put(DBHelper.COL_ADV_PAYMENT, o.getAdvancePayment());
            values.put(DBHelper.COL_VERSION, o.getVersion());
            values.put(DBHelper.COL_STATUS, o.getStatus());
            long rowID = mDb.insert(DBHelper.ORDERS_TN, null, values);
            if (Debug.MODE) { Log.d(LOG_TAG, "ROW ID: " + rowID); }
        }
    }

	public void putDispatchesToDb(ArrayList<Dispatch> dispatches) {
		ContentValues values = new ContentValues();
		for (Dispatch o: dispatches) {
			values.put(DBHelper.COL_SERVER_ID, o.getServerId());
			values.put(DBHelper.COL_CUSTOMER_ID, o.getCustomerId());
			values.put(DBHelper.COL_SLIP_NUMBER, o.getSlipNumber());
			values.put(DBHelper.COL_SPECIAL_CODE, o.getSpecialCode());
			values.put(DBHelper.COL_DATE, o.getDate());
			values.put(DBHelper.COL_DIVISION_ID, o.getDivision());
			values.put(DBHelper.COL_DEPARTMENT_ID, o.getDepartment());
			values.put(DBHelper.COL_PLANT_ID, o.getPlant());
			values.put(DBHelper.COL_WAREHOUSE_ID, o.getWarehouse());
			values.put(DBHelper.COL_CURRENCY_ID, o.getCurrency());
			values.put(DBHelper.COL_TOTAL,o.getTotalPrice());
			values.put(DBHelper.COL_SUBTOTAL, o.getSubtotalPrice());
			values.put(DBHelper.COL_ADV_PAYMENT, o.getAdvancePayment());
			values.put(DBHelper.COL_VERSION, o.getVersion());
			values.put(DBHelper.COL_STATUS, o.getStatus());
			long rowID = mDb.insert(DBHelper.DISPATCHES_TN, null, values);
			if (Debug.MODE) { Log.d(LOG_TAG, "ROW ID: " + rowID); }
		}
	}

	public void putInvoicesToDb(ArrayList<Invoice> invoices) {
		ContentValues values = new ContentValues();
		for (Invoice o: invoices) {
			values.put(DBHelper.COL_SERVER_ID, o.getServerId());
			values.put(DBHelper.COL_CUSTOMER_ID, o.getCustomerId());
			values.put(DBHelper.COL_SLIP_NUMBER, o.getSlipNumber());
			values.put(DBHelper.COL_SPECIAL_CODE, o.getSpecialCode());
			values.put(DBHelper.COL_DATE, o.getDate());
			values.put(DBHelper.COL_DIVISION_ID, o.getDivision());
			values.put(DBHelper.COL_DEPARTMENT_ID, o.getDepartment());
			values.put(DBHelper.COL_PLANT_ID, o.getPlant());
			values.put(DBHelper.COL_WAREHOUSE_ID, o.getWarehouse());
			values.put(DBHelper.COL_CURRENCY_ID, o.getCurrency());
			values.put(DBHelper.COL_TOTAL,o.getTotalPrice());
			values.put(DBHelper.COL_SUBTOTAL, o.getSubtotalPrice());
			values.put(DBHelper.COL_ADV_PAYMENT, o.getAdvancePayment());
			values.put(DBHelper.COL_VERSION, o.getVersion());
			values.put(DBHelper.COL_STATUS, o.getStatus());
			long rowID = mDb.insert(DBHelper.INVOICES_TN, null, values);
			if (Debug.MODE) { Log.d(LOG_TAG, "ROW ID: " + rowID); }
		}
	}

	public void putRefundsToDb(ArrayList<Refund> refunds) {
		ContentValues values = new ContentValues();
		for (Refund o: refunds) {
			values.put(DBHelper.COL_SERVER_ID, o.getServerId());
			values.put(DBHelper.COL_CUSTOMER_ID, o.getCustomerId());
			values.put(DBHelper.COL_SLIP_NUMBER, o.getSlipNumber());
			values.put(DBHelper.COL_SPECIAL_CODE, o.getSpecialCode());
			values.put(DBHelper.COL_DATE, o.getDate());
			values.put(DBHelper.COL_DIVISION_ID, o.getDivision());
			values.put(DBHelper.COL_DEPARTMENT_ID, o.getDepartment());
			values.put(DBHelper.COL_PLANT_ID, o.getPlant());
			values.put(DBHelper.COL_WAREHOUSE_ID, o.getWarehouse());
			values.put(DBHelper.COL_CURRENCY_ID, o.getCurrency());
			values.put(DBHelper.COL_TOTAL,o.getTotalPrice());
			values.put(DBHelper.COL_SUBTOTAL, o.getSubtotalPrice());
			values.put(DBHelper.COL_ADV_PAYMENT, o.getAdvancePayment());
			values.put(DBHelper.COL_VERSION, o.getVersion());
			values.put(DBHelper.COL_STATUS, o.getStatus());
			long rowID = mDb.insert(DBHelper.REFUNDS_TN, null, values);
			if (Debug.MODE) { Log.d(LOG_TAG, "ROW ID: " + rowID); }
		}
	}

    public void putOrderItemsToDb(ArrayList<OrderItem> orderItems) {
        ContentValues values = new ContentValues();
        for (OrderItem o: orderItems) {
            values.put(DBHelper.COL_SERVER_ID, o.getServerId());
            values.put(DBHelper.COL_ITEM_ID, o.getItemId());
            values.put(DBHelper.COL_TYPE_ID, o.getType());
            values.put(DBHelper.COL_OBJECT_ID, o.getOrderId());
            values.put(DBHelper.COL_ITEM_PRICE, o.getItemSubtotalPrice());
            values.put(DBHelper.COL_QUANTITY, o.getQuantity());
            values.put(DBHelper.COL_UNIT_DETAIL_ID, o.getUnitDetailId());
            values.put(DBHelper.COL_VERSION, o.getVersion());
            values.put(DBHelper.COL_STATUS, o.getStatus());
            long rowID = mDb.insert(DBHelper.ORDER_ITEMS_TN, null, values);
            if (Debug.MODE) { Log.d(LOG_TAG, "ROW ID: " + rowID); }
        }
    }

	public void putDispatchItemsToDb(ArrayList<DispatchItem> dispatchItems) {
		ContentValues values = new ContentValues();
		for (DispatchItem o: dispatchItems) {
			values.put(DBHelper.COL_SERVER_ID, o.getServerId());
			values.put(DBHelper.COL_ITEM_ID, o.getItemId());
			values.put(DBHelper.COL_TYPE_ID, o.getType());
			values.put(DBHelper.COL_OBJECT_ID, o.getDispatchId());
			values.put(DBHelper.COL_ITEM_PRICE, o.getItemSubtotalPrice());
			values.put(DBHelper.COL_QUANTITY, o.getQuantity());
			values.put(DBHelper.COL_UNIT_DETAIL_ID, o.getUnitDetailId());
			values.put(DBHelper.COL_VERSION, o.getVersion());
			values.put(DBHelper.COL_STATUS, o.getStatus());
			long rowID = mDb.insert(DBHelper.DISPATCH_ITEMS_TN, null, values);
			if (Debug.MODE) { Log.d(LOG_TAG, "ROW ID: " + rowID); }
		}
	}

	public void putInvoiceItemsToDb(ArrayList<InvoiceItem> invoiceItems) {
		ContentValues values = new ContentValues();
		for (InvoiceItem o: invoiceItems) {
			values.put(DBHelper.COL_SERVER_ID, o.getServerId());
			values.put(DBHelper.COL_ITEM_ID, o.getItemId());
			values.put(DBHelper.COL_TYPE_ID, o.getType());
			values.put(DBHelper.COL_OBJECT_ID, o.getInvoiceId());
			values.put(DBHelper.COL_ITEM_PRICE, o.getItemSubtotalPrice());
			values.put(DBHelper.COL_QUANTITY, o.getQuantity());
			values.put(DBHelper.COL_UNIT_DETAIL_ID, o.getUnitDetailId());
			values.put(DBHelper.COL_VERSION, o.getVersion());
			values.put(DBHelper.COL_STATUS, o.getStatus());
			long rowID = mDb.insert(DBHelper.INVOICE_ITEMS_TN, null, values);
			if (Debug.MODE) { Log.d(LOG_TAG, "ROW ID: " + rowID); }
		}
	}

	public void putRefundItemsToDb(ArrayList<RefundItem> refundItems) {
		ContentValues values = new ContentValues();
		for (RefundItem o: refundItems) {
			values.put(DBHelper.COL_SERVER_ID, o.getServerId());
			values.put(DBHelper.COL_ITEM_ID, o.getItemId());
			values.put(DBHelper.COL_TYPE_ID, o.getType());
			values.put(DBHelper.COL_OBJECT_ID, o.getRefundId());
			values.put(DBHelper.COL_ITEM_PRICE, o.getItemSubtotalPrice());
			values.put(DBHelper.COL_QUANTITY, o.getQuantity());
			values.put(DBHelper.COL_UNIT_DETAIL_ID, o.getUnitDetailId());
			values.put(DBHelper.COL_VERSION, o.getVersion());
			values.put(DBHelper.COL_STATUS, o.getStatus());
			long rowID = mDb.insert(DBHelper.REFUND_ITEMS_TN, null, values);
			if (Debug.MODE) { Log.d(LOG_TAG, "ROW ID: " + rowID); }
		}
	}

    public void putCashPaymentToDb(ArrayList<CashPayment> pPayments) {
        ContentValues values = new ContentValues();
        for (CashPayment cp: pPayments) {
            values.put(DBHelper.COL_SERVER_ID, cp.getServerId());
            values.put(DBHelper.COL_SLIP_NUMBER, cp.getSlipNumber());
            values.put(DBHelper.COL_SPECIAL_CODE, cp.getSpecialCode());
            values.put(DBHelper.COL_DATE, cp.getDate());
            values.put(DBHelper.COL_AMOUNT, cp.getAmount());
            values.put(DBHelper.COL_CURRENCY_ID, cp.getCurrencyId());
            values.put(DBHelper.COL_CUSTOMER_ID, cp.getCustomerId());
            values.put(DBHelper.COL_DESCRIPTION, cp.getDescription());
            values.put(DBHelper.COL_VERSION, cp.getVersion());
            values.put(DBHelper.COL_STATUS, cp.getStatus());
            long rowID = mDb.insert(DBHelper.CASH_PAYMENT_TN, null, values);
            if (Debug.MODE) { Log.d(LOG_TAG, "ROW ID: " + rowID); }
        }
    }

    public void putCreditCardPaymentToDb(ArrayList<CreditCardPayment> pPayments) {
        ContentValues values = new ContentValues();
        for (CreditCardPayment cp: pPayments) {
            values.put(DBHelper.COL_SERVER_ID, cp.getServerId());
            values.put(DBHelper.COL_SLIP_NUMBER, cp.getSlipNumber());
            values.put(DBHelper.COL_SPECIAL_CODE, cp.getSpecialCode());
            values.put(DBHelper.COL_DATE, cp.getDate());
            values.put(DBHelper.COL_AMOUNT, cp.getAmount());
            values.put(DBHelper.COL_CURRENCY_ID, cp.getCurrencyId());
            values.put(DBHelper.COL_CUSTOMER_ID, cp.getCustomerId());
            values.put(DBHelper.COL_DESCRIPTION, cp.getDescription());
            values.put(DBHelper.COL_HOLDER_NAME, cp.getHolderName());
            values.put(DBHelper.COL_CARD_NUMBER, cp.getNumber());
            values.put(DBHelper.COL_EXPIRY_DATE, cp.getExpiryDate());
            values.put(DBHelper.COL_CVV_CODE, cp.getCVVCode());
            values.put(DBHelper.COL_VERSION, cp.getVersion());
            values.put(DBHelper.COL_STATUS, cp.getStatus());
            long rowID = mDb.insert(DBHelper.CREDIT_CARD_PAYMENT_TN, null, values);
            if (Debug.MODE) { Log.d(LOG_TAG, "ROW ID: " + rowID); }
        }
    }
    public void putChequePaymentToDb(ArrayList<ChequePayment> pPayments) {
        ContentValues values = new ContentValues();
        for (ChequePayment cp: pPayments) {
            values.put(DBHelper.COL_SERVER_ID, cp.getServerId());
            values.put(DBHelper.COL_SLIP_NUMBER, cp.getSlipNumber());
            values.put(DBHelper.COL_SPECIAL_CODE, cp.getSpecialCode());
            values.put(DBHelper.COL_DATE, cp.getDate());
            values.put(DBHelper.COL_AMOUNT, cp.getAmount());
            values.put(DBHelper.COL_CURRENCY_ID, cp.getCurrencyId());
            values.put(DBHelper.COL_CUSTOMER_ID, cp.getCustomerId());
            values.put(DBHelper.COL_DESCRIPTION, cp.getDescription());
            values.put(DBHelper.COL_ISSUER_NAME, cp.getIssuerName());
            values.put(DBHelper.COL_SERIAL_NUMBER, cp.getSerialNumber());
            values.put(DBHelper.COL_DUE_DATE, cp.getDueDate());
            values.put(DBHelper.COL_VERSION, cp.getVersion());
            values.put(DBHelper.COL_STATUS, cp.getStatus());
            long rowID = mDb.insert(DBHelper.CHEQUE_PAYMENT_TN, null, values);
            if (Debug.MODE) { Log.d(LOG_TAG, "ROW ID: " + rowID); }
        }
    }
    public void putBondPaymentToDb(ArrayList<BondPayment> pPayments) {
        ContentValues values = new ContentValues();
        for (BondPayment cp: pPayments) {
            values.put(DBHelper.COL_SERVER_ID, cp.getServerId());
            values.put(DBHelper.COL_SLIP_NUMBER, cp.getSlipNumber());
            values.put(DBHelper.COL_SPECIAL_CODE, cp.getSpecialCode());
            values.put(DBHelper.COL_DATE, cp.getDate());
            values.put(DBHelper.COL_AMOUNT, cp.getAmount());
            values.put(DBHelper.COL_CURRENCY_ID, cp.getCurrencyId());
            values.put(DBHelper.COL_CUSTOMER_ID, cp.getCustomerId());
            values.put(DBHelper.COL_DESCRIPTION, cp.getDescription());
            values.put(DBHelper.COL_ISSUER_NAME, cp.getIssuerName());
            values.put(DBHelper.COL_GUARANTOR_NAME, cp.getGuarantorName());
            values.put(DBHelper.COL_SERIAL_NUMBER, cp.getSerialNumber());
            values.put(DBHelper.COL_DUE_DATE, cp.getDueDate());
            values.put(DBHelper.COL_VERSION, cp.getVersion());
            values.put(DBHelper.COL_STATUS, cp.getStatus());
            long rowID = mDb.insert(DBHelper.BOND_PAYMENT_TN, null, values);
            if (Debug.MODE) { Log.d(LOG_TAG, "ROW ID: " + rowID); }
        }
    }

    public void clearTable(String tableName) {
        mDb.delete(tableName, null, null);
    }

    public void openToRead() throws SQLException {
        mDb = mDbHelper.getReadableDatabase();
    }

    public void openToWrite() throws SQLException {
        mDb = mDbHelper.getWritableDatabase();
    }
    public void close() {
        mDbHelper.close();
    }

	public Cursor getCustomers(String whereClause) {
		Cursor cursor = mDb.query(DBHelper.CUSTOMERS_TN,
				null, DBHelper.COL_STATUS + EQUAL + 1 , null, null, null, null);
		return cursor;
	}


    public ArrayList<Customer> getCustomers() {
        ArrayList<Customer> customers = new ArrayList<Customer>();
        Cursor cursor = mDb.query(DBHelper.CUSTOMERS_TN,
                null, DBHelper.COL_STATUS + EQUAL + 1 , null, null, null, null);
        Customer customer;
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                customer = cursorToCustomer(cursor);
                customers.add(customer);
                cursor.moveToNext();
            }
        }
        // make sure to close the cursor
        cursor.close();

        return customers;
    }

    public ArrayList<Product> getProducts(String orderBy) {
        ArrayList<Product> products = new ArrayList<Product>();
        String whereClause = DBHelper.COL_STATUS + EQUAL + 1;
        Cursor cursor = mDb.query(DBHelper.PRODUCTS_TN,
                null, whereClause, null, null, null, orderBy);
        Product product;
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                product = cursorToProduct(cursor);
                products.add(product);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return products;
    }


    public ArrayList<Service> getServices (String orderByArg, int currencyId) {
        ArrayList<Service> services = new ArrayList<Service>();
	    String allColumns[] = {
			    DBHelper.SERVICES_TN + POINT + DBHelper.COL_ID          + AS + DBHelper.COL_ID,
			    DBHelper.SERVICES_TN + POINT + DBHelper.COL_SERVER_ID   + AS + DBHelper.COL_SERVER_ID,
			    DBHelper.SERVICES_TN + POINT + DBHelper.COL_NAME        + AS + DBHelper.COL_NAME,
			    DBHelper.SERVICES_TN + POINT + DBHelper.COL_DESCRIPTION + AS + DBHelper.COL_DESCRIPTION,
			    DBHelper.SERVICES_TN + POINT + DBHelper.COL_CODE        + AS + DBHelper.COL_CODE,
			    DBHelper.SERVICES_TN + POINT + DBHelper.COL_VAT         + AS + DBHelper.COL_VAT,
			    DBHelper.SERVICES_TN + POINT + DBHelper.COL_UNIT_ID     + AS + DBHelper.COL_UNIT_ID,
			    DBHelper.SERVICES_TN + POINT + DBHelper.COL_VERSION     + AS + DBHelper.COL_VERSION,
			    DBHelper.SERVICES_TN + POINT + DBHelper.COL_STATUS      + AS + DBHelper.COL_STATUS
			     };

	    String from = DBHelper.SERVICES_TN +
			    INNER_JOIN + DBHelper.SERVICE_PRICES_TN + ON + DBHelper.SERVICES_TN     + POINT + DBHelper.COL_SERVER_ID + EQUAL + DBHelper.SERVICE_PRICES_TN + POINT + DBHelper.COL_SERVICE_ID +
			    INNER_JOIN + DBHelper.CURRENCIES_TN + ON + DBHelper.CURRENCIES_TN + POINT + DBHelper.COL_SERVER_ID + EQUAL + DBHelper.SERVICE_PRICES_TN + POINT + DBHelper.COL_CURRENCY_ID +
			    INNER_JOIN + DBHelper.UNIT_TN           + ON + DBHelper.UNIT_TN         + POINT + DBHelper.COL_SERVER_ID + EQUAL + DBHelper.SERVICES_TN       + POINT + DBHelper.COL_UNIT_ID +
			    INNER_JOIN + DBHelper.UNIT_DETAIL_TN    + ON + DBHelper.UNIT_DETAIL_TN  + POINT + DBHelper.COL_UNIT_ID   + EQUAL + DBHelper.SERVICES_TN       + POINT + DBHelper.COL_UNIT_ID
			    ;

	    String where =  DBHelper.SERVICES_TN        + POINT + DBHelper.COL_STATUS      + EQUAL + "?" + AND +
			            DBHelper.CURRENCIES_TN + POINT + DBHelper.COL_STATUS      + EQUAL + "?" + AND +
			            DBHelper.UNIT_TN            + POINT + DBHelper.COL_STATUS      + EQUAL + "?" + AND +
			            DBHelper.SERVICE_PRICES_TN  + POINT + DBHelper.COL_CURRENCY_ID + EQUAL + "?";

	    String[] whereArgs = {STATUS_ACTIVE, STATUS_ACTIVE, STATUS_ACTIVE, ""+currencyId};

	    String orderBy = DBHelper.SERVICES_TN+POINT+orderByArg;

        Cursor cursor = mDb.query(  true,
		                            from,
		                            allColumns,
			                        where,
                                    whereArgs,
                                    null,
                                    null,
                                    orderBy,
		                            null);
        Service service;
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                service = cursorToService(cursor);
                services.add(service);
                cursor.moveToNext();
            }
        }
        return services;
    }

	public ArrayList<Product> getProducts (String orderByArg, int currencyId) {
		ArrayList<Product> products = new ArrayList<Product>();
		String allColumns[] = {
				DBHelper.PRODUCTS_TN + POINT + DBHelper.COL_ID          + AS + DBHelper.COL_ID,
				DBHelper.PRODUCTS_TN + POINT + DBHelper.COL_SERVER_ID   + AS + DBHelper.COL_SERVER_ID,
				DBHelper.PRODUCTS_TN + POINT + DBHelper.COL_NAME        + AS + DBHelper.COL_NAME,
				DBHelper.PRODUCTS_TN + POINT + DBHelper.COL_DESCRIPTION + AS + DBHelper.COL_DESCRIPTION,
				DBHelper.PRODUCTS_TN + POINT + DBHelper.COL_CODE        + AS + DBHelper.COL_CODE,
				DBHelper.PRODUCTS_TN + POINT + DBHelper.COL_QUANTITY    + AS + DBHelper.COL_QUANTITY,
				DBHelper.PRODUCTS_TN + POINT + DBHelper.COL_VAT         + AS + DBHelper.COL_VAT,
				DBHelper.PRODUCTS_TN + POINT + DBHelper.COL_UNIT_ID     + AS + DBHelper.COL_UNIT_ID,
				DBHelper.PRODUCTS_TN + POINT + DBHelper.COL_VERSION     + AS + DBHelper.COL_VERSION,
				DBHelper.PRODUCTS_TN + POINT + DBHelper.COL_STATUS      + AS + DBHelper.COL_STATUS
		};

		String from = DBHelper.PRODUCTS_TN +
				INNER_JOIN + DBHelper.PRODUCT_PRICES_TN + ON + DBHelper.PRODUCTS_TN     + POINT + DBHelper.COL_SERVER_ID + EQUAL + DBHelper.PRODUCT_PRICES_TN + POINT + DBHelper.COL_PRODUCT_ID +
				INNER_JOIN + DBHelper.CURRENCIES_TN + ON + DBHelper.CURRENCIES_TN + POINT + DBHelper.COL_SERVER_ID + EQUAL + DBHelper.PRODUCT_PRICES_TN + POINT + DBHelper.COL_CURRENCY_ID +
				INNER_JOIN + DBHelper.UNIT_TN           + ON + DBHelper.UNIT_TN         + POINT + DBHelper.COL_SERVER_ID + EQUAL + DBHelper.PRODUCTS_TN       + POINT + DBHelper.COL_UNIT_ID +
				INNER_JOIN + DBHelper.UNIT_DETAIL_TN    + ON + DBHelper.UNIT_DETAIL_TN  + POINT + DBHelper.COL_UNIT_ID   + EQUAL + DBHelper.PRODUCTS_TN       + POINT + DBHelper.COL_UNIT_ID
				;

		String where =  DBHelper.PRODUCTS_TN        + POINT + DBHelper.COL_STATUS      + EQUAL + "?" + AND +
						DBHelper.CURRENCIES_TN + POINT + DBHelper.COL_STATUS      + EQUAL + "?" + AND +
						DBHelper.UNIT_TN            + POINT + DBHelper.COL_STATUS      + EQUAL + "?" + AND +
						DBHelper.PRODUCT_PRICES_TN  + POINT + DBHelper.COL_CURRENCY_ID + EQUAL + "?";

		String[] whereArgs = {STATUS_ACTIVE, STATUS_ACTIVE, STATUS_ACTIVE, ""+currencyId};

		String orderBy = DBHelper.PRODUCTS_TN+POINT+orderByArg;

		Cursor cursor = mDb.query(  true,
				from,
				allColumns,
				where,
				whereArgs,
				null,
				null,
				orderBy,
				null);
		Product product;
		if (cursor.moveToFirst()) {
			while (!cursor.isAfterLast()) {
				product = cursorToProduct(cursor);
				products.add(product);
				cursor.moveToNext();
			}
		}
		return products;
	}

    public ArrayList<Currency> getCurrencies (String orderBy) {
        ArrayList<Currency> currencies = new ArrayList<Currency>();
        Cursor cursor = mDb.query(DBHelper.CURRENCIES_TN,
                null, null, null, null, null, orderBy);
        Currency currency;
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                currency = cursorToCurrency(cursor);
                currencies.add(currency);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return currencies;
    }

    public ArrayList<Unit> getUnits (String orderBy) {
        ArrayList<Unit> units = new ArrayList<Unit>();
        Cursor cursor = mDb.query(DBHelper.UNIT_TN,
                null, null, null, null, null, orderBy);
        Unit unit;
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                unit = cursorToUnit(cursor);
                units.add(unit);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return units;
    }

    public ArrayList<UnitDetail> getUnitDetails (String unitId) {
        ArrayList<UnitDetail> unitDetails = new ArrayList<UnitDetail>();

	    String where = DBHelper.COL_UNIT_ID + DAO.EQUAL + unitId;

        Cursor cursor = mDb.query(DBHelper.UNIT_DETAIL_TN,
                null, where, null, null, null, null);
        UnitDetail unitDetail;
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                unitDetail = cursorToUnitDetail(cursor);
                unitDetails.add(unitDetail);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return unitDetails;
    }

    public ArrayList<UnitDetail> getUnitDetails (String orderBy, String whereClause) {
        ArrayList<UnitDetail> unitDetails = new ArrayList<UnitDetail>();
        Cursor cursor = mDb.query(DBHelper.UNIT_DETAIL_TN,
                null, whereClause, null, null, null, orderBy);
        UnitDetail unitDetail;
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                unitDetail = cursorToUnitDetail(cursor);
                unitDetails.add(unitDetail);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return unitDetails;
    }

    public ArrayList<ProductPrice> getProductPrices (String orderBy) {
        ArrayList<ProductPrice> productPrices = new ArrayList<ProductPrice>();
        Cursor c = mDb.query(DBHelper.PRODUCT_PRICES_TN,
                null, null, null, null, null, orderBy);
        ProductPrice productPrice;
        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                productPrice = cursorToProductPrice(c);
                productPrices.add(productPrice);
                c.moveToNext();
            }
        } else {
            productPrices = null;
        }
        c.close();
        return productPrices;
    }

    public ArrayList<ProductPrice> getProductPrices (String orderBy, String whereClause) {
        ArrayList<ProductPrice> productPrices = new ArrayList<ProductPrice>();
        Cursor c = mDb.query(DBHelper.PRODUCT_PRICES_TN,
                null, whereClause, null, null, null, orderBy);
        ProductPrice productPrice;
        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                productPrice = cursorToProductPrice(c);
                productPrices.add(productPrice);
                c.moveToNext();
            }
        }
        c.close();
        return productPrices;
    }

    public ArrayList<ServicePrice> getServicePrices (String orderBy, String whereClause) {
        ArrayList<ServicePrice> servicePrices = new ArrayList<ServicePrice>();
        Cursor cursor = mDb.query(DBHelper.SERVICE_PRICES_TN,
                null, whereClause, null, null, null, orderBy);
        ServicePrice servicePrice;
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                servicePrice = cursorToServicePrice(cursor);
                servicePrices.add(servicePrice);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return servicePrices;
    }

    public ArrayList<Division> getDivisions (String orderBy) {
        ArrayList<Division> divisions = new ArrayList<Division>();
        Cursor cursor = mDb.query(DBHelper.DIVISION_TN,
                null, null, null, null, null, orderBy);
        Division division;
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                division = cursorToDivision(cursor);
                divisions.add(division);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return divisions;
    }

    public ArrayList<Department> getDepartments (String orderBy) {
        ArrayList<Department> departments = new ArrayList<Department>();
        Cursor cursor = mDb.query(DBHelper.DEPARTMENT_TN,
                null, null, null, null, null, orderBy);
        Department department;
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                department = cursorToDepartment(cursor);
                departments.add(department);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return departments;
    }

    public ArrayList<Plant> getPlants (String orderBy) {
        ArrayList<Plant> plants = new ArrayList<Plant>();
        Cursor cursor = mDb.query(DBHelper.PLANT_TN,
                null, null, null, null, null, orderBy);
        Plant plant;
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                plant = cursorToPlant(cursor);
                plants.add(plant);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return plants;
    }

    public ArrayList<Warehouse> getWarehouses (String orderBy) {
        ArrayList<Warehouse> warehouses = new ArrayList<Warehouse>();
        Cursor cursor = mDb.query(DBHelper.WAREHOUSE_TN,
                null, null, null, null, null, orderBy);
        Warehouse warehouse;
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                warehouse = cursorToWarehouse(cursor);
                warehouses.add(warehouse);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return warehouses;
    }

    public ArrayList<DefaultObject> getOrders (String orderBy) {
        ArrayList<DefaultObject> orders = new ArrayList<DefaultObject>();
        Cursor cursor = mDb.query(DBHelper.ORDERS_TN,
                null, null, null, null, null, orderBy);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                orders.add(cursorToOrder(cursor));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return orders;
    }

	public ArrayList<DefaultObject> getDispatches (String orderBy) {
		ArrayList<DefaultObject> dispatches = new ArrayList<DefaultObject>();
		Cursor cursor = mDb.query(DBHelper.DISPATCHES_TN,
				null, null, null, null, null, orderBy);
		if (cursor.moveToFirst()) {
			while (!cursor.isAfterLast()) {
				dispatches.add(cursorToDispatch(cursor));
				cursor.moveToNext();
			}
		}
		cursor.close();
		return dispatches;
	}

	public ArrayList<DefaultObject> getInvoices (String orderBy) {
		ArrayList<DefaultObject> invoices = new ArrayList<DefaultObject>();
		Cursor cursor = mDb.query(DBHelper.INVOICES_TN,
				null, null, null, null, null, orderBy);
		if (cursor.moveToFirst()) {
			while (!cursor.isAfterLast()) {
				invoices.add(cursorToInvoice(cursor));
				cursor.moveToNext();
			}
		}
		cursor.close();
		return invoices;
	}

	public ArrayList<DefaultObject> getRefunds (String orderBy) {
        ArrayList<DefaultObject> refunds = new ArrayList<DefaultObject>();
        Cursor cursor = mDb.query(DBHelper.REFUNDS_TN,
                null, null, null, null, null, orderBy);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                refunds.add(cursorToRefund(cursor));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return refunds;
    }

    public ArrayList<CashPayment> getCashPayments (String orderBy) {
        ArrayList<CashPayment> lPayments = new ArrayList<CashPayment>();
        Cursor cursor = mDb.query(DBHelper.CASH_PAYMENT_TN,
                null, null, null, null, null, orderBy);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                lPayments.add(cursorToCashPayment(cursor));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return lPayments;
    }

    public ArrayList<CreditCardPayment> getCreditCardPayments (String orderBy) {
        ArrayList<CreditCardPayment> lPayments = new ArrayList<CreditCardPayment>();
        Cursor cursor = mDb.query(DBHelper.CREDIT_CARD_PAYMENT_TN,
                null, null, null, null, null, orderBy);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                lPayments.add(cursorToCreditCardPayment(cursor));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return lPayments;
    }

    public ArrayList<ChequePayment> getChequePayments (String orderBy) {
        ArrayList<ChequePayment> lPayments = new ArrayList<ChequePayment>();
        Cursor cursor = mDb.query(DBHelper.CHEQUE_PAYMENT_TN,
                null, null, null, null, null, orderBy);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                lPayments.add(cursorToChequePayment(cursor));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return lPayments;
    }
    public ArrayList<BondPayment> getBondPayments (String orderBy) {
        ArrayList<BondPayment> lPayments = new ArrayList<BondPayment>();
        Cursor cursor = mDb.query(DBHelper.BOND_PAYMENT_TN,
                null, null, null, null, null, orderBy);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                lPayments.add(cursorToBondPayment(cursor));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return lPayments;
    }

    public ArrayList<OrderItem> getOrderItems(String orderBy, String whereClause) {
        ArrayList<OrderItem> orderItems = new ArrayList<OrderItem>();
        Cursor cursor = mDb.query(DBHelper.ORDER_ITEMS_TN,
                null, whereClause, null, null, null, orderBy);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                orderItems.add(cursorToOrderItem(cursor));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return orderItems;
    }



	public ArrayList<Item> getItems(String whatObject,String whatItem, int objectId, int typeId) {
		ArrayList<Item> items = new ArrayList<Item>();

		String[] whereArgs = {STATUS_ACTIVE, STATUS_ACTIVE, STATUS_ACTIVE, STATUS_ACTIVE, ""+objectId, "" + typeId};

		String typeStr = (typeId == 1)?DBHelper.PRODUCTS_TN:DBHelper.SERVICES_TN;

		String[] allColumns = {
						whatItem                    + POINT + DBHelper.COL_ID               + AS + DBHelper.COL_ID,
						whatItem                    + POINT + DBHelper.COL_SERVER_ID        + AS + DBHelper.COL_SERVER_ID,
						whatItem                    + POINT + DBHelper.COL_TYPE_ID          + AS + DBHelper.COL_TYPE_ID,
						whatItem                    + POINT + DBHelper.COL_ITEM_ID          + AS + DBHelper.COL_ITEM_ID,
						whatItem                    + POINT + DBHelper.COL_QUANTITY         + AS + DBHelper.COL_QUANTITY,
						whatItem                    + POINT + DBHelper.COL_ITEM_PRICE       + AS + DBHelper.COL_ITEM_PRICE,
						whatItem                    + POINT + DBHelper.COL_OBJECT_ID        + AS + DBHelper.COL_OBJECT_ID,
						whatItem                    + POINT + DBHelper.COL_UNIT_DETAIL_ID   + AS + DBHelper.COL_UNIT_DETAIL_ID,
						typeStr                     + POINT + DBHelper.COL_CODE             + AS + DBHelper.COL_CODE,
						typeStr                     + POINT + DBHelper.COL_NAME             + AS + DBHelper.COL_NAME,
						typeStr                     + POINT + DBHelper.COL_DESCRIPTION      + AS + DBHelper.COL_DESCRIPTION,
						typeStr                     + POINT + DBHelper.COL_VAT              + AS + DBHelper.COL_VAT,
						DBHelper.UNIT_DETAIL_TN     + POINT + DBHelper.COL_NAME             + AS + DBHelper.COL_UNIT_DETAIL_NAME,
						whatItem                    + POINT + DBHelper.COL_VERSION          + AS + DBHelper.COL_VERSION,
						whatItem                    + POINT + DBHelper.COL_STATUS           + AS + DBHelper.COL_STATUS,
						DBHelper.CURRENCIES_TN      + POINT + DBHelper.COL_NAME             + AS + DBHelper.COL_CURRENCY_NAME
		};

		String	from = whatItem +
					INNER_JOIN + typeStr                 + ON + typeStr                 + POINT + DBHelper.COL_SERVER_ID + EQUAL + whatItem + POINT + DBHelper.COL_ITEM_ID +
					INNER_JOIN + DBHelper.UNIT_DETAIL_TN + ON + DBHelper.UNIT_DETAIL_TN + POINT + DBHelper.COL_SERVER_ID + EQUAL + whatItem + POINT + DBHelper.COL_UNIT_DETAIL_ID +
					INNER_JOIN + whatObject              + ON + whatObject              + POINT + DBHelper.COL_SERVER_ID + EQUAL + whatItem + POINT + DBHelper.COL_OBJECT_ID+
					INNER_JOIN + DBHelper.CURRENCIES_TN  + ON + DBHelper.CURRENCIES_TN  + POINT + DBHelper.COL_SERVER_ID + EQUAL + whatObject      + POINT + DBHelper.COL_CURRENCY_ID;

		String	where =
					whatItem                    + POINT + DBHelper.COL_STATUS      + EQUAL + "?" + AND +
					typeStr                     + POINT + DBHelper.COL_STATUS      + EQUAL + "?" + AND +
					DBHelper.UNIT_DETAIL_TN     + POINT + DBHelper.COL_STATUS      + EQUAL + "?" + AND +
					whatObject                  + POINT + DBHelper.COL_STATUS      + EQUAL + "?" + AND +
					whatItem                    + POINT + DBHelper.COL_OBJECT_ID   + EQUAL + "?" + AND +
					whatItem                    + POINT + DBHelper.COL_TYPE_ID     + EQUAL + "?";


		String	orderBy = typeStr + POINT + DBHelper.COL_NAME;

			Cursor cursor = mDb.query(true, from,
					allColumns, where, whereArgs, null, null, orderBy, null);
			if (cursor.moveToFirst()) {
				while (!cursor.isAfterLast()) {
					items.add(cursorToItem(cursor));
					cursor.moveToNext();
				}
			}
			cursor.close();

		return items;
	}



	public ArrayList<DispatchItem> getDispatchItems(String orderBy, String whereClause) {
		ArrayList<DispatchItem> dispatchItems = new ArrayList<DispatchItem>();
		Cursor cursor = mDb.query(DBHelper.ORDER_ITEMS_TN,
				null, whereClause, null, null, null, orderBy);
		if (cursor.moveToFirst()) {
			while (!cursor.isAfterLast()) {
				dispatchItems.add(cursorToDispatchItem(cursor));
				cursor.moveToNext();
			}
		}
		cursor.close();
		return dispatchItems;
	}

    private Product cursorToProduct(Cursor c) {
        Product product = new Product(
                c.getInt(c.getColumnIndex(DBHelper.COL_ID)),
                c.getInt(c.getColumnIndex(DBHelper.COL_SERVER_ID)),
                c.getString(c.getColumnIndex(DBHelper.COL_NAME)),
                c.getString(c.getColumnIndex(DBHelper.COL_DESCRIPTION)),
                c.getString(c.getColumnIndex(DBHelper.COL_CODE)),
                c.getInt(c.getColumnIndex(DBHelper.COL_VAT)),
                c.getInt(c.getColumnIndex(DBHelper.COL_UNIT_ID)),
                c.getInt(c.getColumnIndex(DBHelper.COL_VERSION)),
                c.getInt(c.getColumnIndex(DBHelper.COL_STATUS))
        );
        return product;
    }

    private Customer cursorToCustomer(Cursor c) {
        Customer customer = new Customer(
                c.getInt(c.getColumnIndex(DBHelper.COL_ID)),
                c.getInt(c.getColumnIndex(DBHelper.COL_SERVER_ID)),
                c.getString(c.getColumnIndex(DBHelper.COL_NAME)),
                c.getDouble(c.getColumnIndex(DBHelper.COL_LONGITUDE)),
                c.getDouble(c.getColumnIndex(DBHelper.COL_LATITUDE)),
                c.getInt(c.getColumnIndex(DBHelper.COL_STATUS)),
                c.getInt(c.getColumnIndex(DBHelper.COL_VERSION))
        );
        return customer;
    }

    private Service cursorToService(Cursor c) {
        Service service = new Service(
                c.getInt(c.getColumnIndex(DBHelper.COL_ID)),
                c.getInt(c.getColumnIndex(DBHelper.COL_SERVER_ID)),
                c.getString(c.getColumnIndex(DBHelper.COL_NAME)),
                c.getString(c.getColumnIndex(DBHelper.COL_DESCRIPTION)),
                c.getString(c.getColumnIndex(DBHelper.COL_CODE)),
                c.getInt(c.getColumnIndex(DBHelper.COL_VAT)),
                c.getInt(c.getColumnIndex(DBHelper.COL_UNIT_ID)),
                c.getInt(c.getColumnIndex(DBHelper.COL_VERSION)),
                c.getInt(c.getColumnIndex(DBHelper.COL_STATUS))
        );
        return service;
    }

    private Currency cursorToCurrency(Cursor c) {
        Currency currency = new Currency(
                c.getInt(c.getColumnIndex(DBHelper.COL_ID)),
                c.getInt(c.getColumnIndex(DBHelper.COL_SERVER_ID)),
                c.getString(c.getColumnIndex(DBHelper.COL_NAME)),
                c.getInt(c.getColumnIndex(DBHelper.COL_VERSION)),
                c.getInt(c.getColumnIndex(DBHelper.COL_STATUS))
        );
        return currency;
    }

    private Unit cursorToUnit(Cursor c) {
        Unit unit = new Unit(
                c.getInt(c.getColumnIndex(DBHelper.COL_ID)),
                c.getInt(c.getColumnIndex(DBHelper.COL_SERVER_ID)),
                c.getString(c.getColumnIndex(DBHelper.COL_NAME)),
                c.getInt(c.getColumnIndex(DBHelper.COL_VERSION)),
                c.getInt(c.getColumnIndex(DBHelper.COL_STATUS))
        );
        return unit;
    }

    private Department cursorToDepartment(Cursor c) {
        Department department = new Department(
                c.getInt(c.getColumnIndex(DBHelper.COL_ID)),
                c.getInt(c.getColumnIndex(DBHelper.COL_SERVER_ID)),
                c.getString(c.getColumnIndex(DBHelper.COL_NAME)),
                c.getInt(c.getColumnIndex(DBHelper.COL_VERSION)),
                c.getInt(c.getColumnIndex(DBHelper.COL_STATUS))
        );
        return department;
    }

    private Division cursorToDivision(Cursor c) {
        Division division = new Division(
                c.getInt(c.getColumnIndex(DBHelper.COL_ID)),
                c.getInt(c.getColumnIndex(DBHelper.COL_SERVER_ID)),
                c.getString(c.getColumnIndex(DBHelper.COL_NAME)),
                c.getInt(c.getColumnIndex(DBHelper.COL_VERSION)),
                c.getInt(c.getColumnIndex(DBHelper.COL_STATUS))
        );
        return division;
    }

    private Plant cursorToPlant(Cursor c) {
        Plant plant = new Plant(
                c.getInt(c.getColumnIndex(DBHelper.COL_ID)),
                c.getInt(c.getColumnIndex(DBHelper.COL_SERVER_ID)),
                c.getString(c.getColumnIndex(DBHelper.COL_NAME)),
                c.getInt(c.getColumnIndex(DBHelper.COL_VERSION)),
                c.getInt(c.getColumnIndex(DBHelper.COL_STATUS))
        );
        return plant;
    }

    private Warehouse cursorToWarehouse(Cursor c) {
        Warehouse warehouse = new Warehouse(
                c.getInt(c.getColumnIndex(DBHelper.COL_ID)),
                c.getInt(c.getColumnIndex(DBHelper.COL_SERVER_ID)),
                c.getString(c.getColumnIndex(DBHelper.COL_NAME)),
                c.getInt(c.getColumnIndex(DBHelper.COL_VERSION)),
                c.getInt(c.getColumnIndex(DBHelper.COL_STATUS))
        );
        return warehouse;
    }

    private ProductPrice cursorToProductPrice(Cursor c) {
        ProductPrice productPrice = new ProductPrice(
                c.getInt(c.getColumnIndex(DBHelper.COL_ID)),
                c.getInt(c.getColumnIndex(DBHelper.COL_SERVER_ID)),
                c.getInt(c.getColumnIndex(DBHelper.COL_PRODUCT_ID)),
                c.getDouble(c.getColumnIndex(DBHelper.COL_PRICE)),
                c.getInt(c.getColumnIndex(DBHelper.COL_CURRENCY_ID)),
                c.getInt(c.getColumnIndex(DBHelper.COL_VERSION))
        );
        return productPrice;
    }

    private ServicePrice cursorToServicePrice(Cursor c) {
        ServicePrice servicePrice = new ServicePrice(
                c.getInt(c.getColumnIndex(DBHelper.COL_ID)),
                c.getInt(c.getColumnIndex(DBHelper.COL_SERVER_ID)),
                c.getInt(c.getColumnIndex(DBHelper.COL_SERVICE_ID)),
                c.getDouble(c.getColumnIndex(DBHelper.COL_PRICE)),
                c.getInt(c.getColumnIndex(DBHelper.COL_CURRENCY_ID)),
                c.getInt(c.getColumnIndex(DBHelper.COL_VERSION))
        );
        return servicePrice;
    }

    private UnitDetail cursorToUnitDetail(Cursor c) {
        UnitDetail  unitDetail = new UnitDetail(
                c.getInt(c.getColumnIndex(DBHelper.COL_ID)),
                c.getInt(c.getColumnIndex(DBHelper.COL_SERVER_ID)),
                c.getString(c.getColumnIndex(DBHelper.COL_NAME)),
                c.getInt(c.getColumnIndex(DBHelper.COL_UNIT_ID)),
                c.getInt(c.getColumnIndex(DBHelper.COL_FROM)),
                c.getInt(c.getColumnIndex(DBHelper.COL_TO)),
                c.getString(c.getColumnIndex(DBHelper.COL_MAIN)),
                c.getInt(c.getColumnIndex(DBHelper.COL_VERSION)),
                c.getInt(c.getColumnIndex(DBHelper.COL_STATUS))
        );
        return unitDetail;
    }

    private Order cursorToOrder(Cursor c) {
        Order  order = new Order(
                c.getInt(c.getColumnIndex(DBHelper.COL_ID)),
                c.getInt(c.getColumnIndex(DBHelper.COL_SERVER_ID)),
                c.getInt(c.getColumnIndex(DBHelper.COL_CUSTOMER_ID)),
                c.getString(c.getColumnIndex(DBHelper.COL_SLIP_NUMBER)),
                c.getString(c.getColumnIndex(DBHelper.COL_SPECIAL_CODE)),
                c.getString(c.getColumnIndex(DBHelper.COL_DATE)),
                c.getInt(c.getColumnIndex(DBHelper.COL_DIVISION_ID)),
                c.getInt(c.getColumnIndex(DBHelper.COL_DEPARTMENT_ID)),
                c.getInt(c.getColumnIndex(DBHelper.COL_PLANT_ID)),
                c.getInt(c.getColumnIndex(DBHelper.COL_WAREHOUSE_ID)),
                c.getInt(c.getColumnIndex(DBHelper.COL_CURRENCY_ID)),
                c.getDouble(c.getColumnIndex(DBHelper.COL_SUBTOTAL)),
                c.getDouble(c.getColumnIndex(DBHelper.COL_TOTAL)),
                c.getDouble(c.getColumnIndex(DBHelper.COL_ADV_PAYMENT)),
                c.getInt(c.getColumnIndex(DBHelper.COL_VERSION)),
                c.getInt(c.getColumnIndex(DBHelper.COL_STATUS))
        );
        return order;
    }

	private Dispatch cursorToDispatch(Cursor c) {
		Dispatch  dispatch = new Dispatch(
				c.getInt(c.getColumnIndex(DBHelper.COL_ID)),
				c.getInt(c.getColumnIndex(DBHelper.COL_SERVER_ID)),
				c.getInt(c.getColumnIndex(DBHelper.COL_CUSTOMER_ID)),
				c.getString(c.getColumnIndex(DBHelper.COL_SLIP_NUMBER)),
				c.getString(c.getColumnIndex(DBHelper.COL_SPECIAL_CODE)),
				c.getString(c.getColumnIndex(DBHelper.COL_DATE)),
				c.getInt(c.getColumnIndex(DBHelper.COL_DIVISION_ID)),
				c.getInt(c.getColumnIndex(DBHelper.COL_DEPARTMENT_ID)),
				c.getInt(c.getColumnIndex(DBHelper.COL_PLANT_ID)),
				c.getInt(c.getColumnIndex(DBHelper.COL_WAREHOUSE_ID)),
				c.getInt(c.getColumnIndex(DBHelper.COL_CURRENCY_ID)),
				c.getDouble(c.getColumnIndex(DBHelper.COL_SUBTOTAL)),
				c.getDouble(c.getColumnIndex(DBHelper.COL_TOTAL)),
				c.getDouble(c.getColumnIndex(DBHelper.COL_ADV_PAYMENT)),
				c.getInt(c.getColumnIndex(DBHelper.COL_VERSION)),
				c.getInt(c.getColumnIndex(DBHelper.COL_STATUS))
		);
		return dispatch;
	}

	private Invoice cursorToInvoice(Cursor c) {
		Invoice  invoice = new Invoice(
				c.getInt(c.getColumnIndex(DBHelper.COL_ID)),
				c.getInt(c.getColumnIndex(DBHelper.COL_SERVER_ID)),
				c.getInt(c.getColumnIndex(DBHelper.COL_CUSTOMER_ID)),
				c.getString(c.getColumnIndex(DBHelper.COL_SLIP_NUMBER)),
				c.getString(c.getColumnIndex(DBHelper.COL_SPECIAL_CODE)),
				c.getString(c.getColumnIndex(DBHelper.COL_DATE)),
				c.getInt(c.getColumnIndex(DBHelper.COL_DIVISION_ID)),
				c.getInt(c.getColumnIndex(DBHelper.COL_DEPARTMENT_ID)),
				c.getInt(c.getColumnIndex(DBHelper.COL_PLANT_ID)),
				c.getInt(c.getColumnIndex(DBHelper.COL_WAREHOUSE_ID)),
				c.getInt(c.getColumnIndex(DBHelper.COL_CURRENCY_ID)),
				c.getDouble(c.getColumnIndex(DBHelper.COL_SUBTOTAL)),
				c.getDouble(c.getColumnIndex(DBHelper.COL_TOTAL)),
				c.getDouble(c.getColumnIndex(DBHelper.COL_ADV_PAYMENT)),
				c.getInt(c.getColumnIndex(DBHelper.COL_VERSION)),
				c.getInt(c.getColumnIndex(DBHelper.COL_STATUS))
		);
		return invoice;
	}

	private Refund cursorToRefund(Cursor c) {
		Refund  refund = new Refund(
				c.getInt(c.getColumnIndex(DBHelper.COL_ID)),
				c.getInt(c.getColumnIndex(DBHelper.COL_SERVER_ID)),
				c.getInt(c.getColumnIndex(DBHelper.COL_CUSTOMER_ID)),
				c.getString(c.getColumnIndex(DBHelper.COL_SLIP_NUMBER)),
				c.getString(c.getColumnIndex(DBHelper.COL_SPECIAL_CODE)),
				c.getString(c.getColumnIndex(DBHelper.COL_DATE)),
				c.getInt(c.getColumnIndex(DBHelper.COL_DIVISION_ID)),
				c.getInt(c.getColumnIndex(DBHelper.COL_DEPARTMENT_ID)),
				c.getInt(c.getColumnIndex(DBHelper.COL_PLANT_ID)),
				c.getInt(c.getColumnIndex(DBHelper.COL_WAREHOUSE_ID)),
				c.getInt(c.getColumnIndex(DBHelper.COL_CURRENCY_ID)),
				c.getDouble(c.getColumnIndex(DBHelper.COL_SUBTOTAL)),
				c.getDouble(c.getColumnIndex(DBHelper.COL_TOTAL)),
				c.getDouble(c.getColumnIndex(DBHelper.COL_ADV_PAYMENT)),
				c.getInt(c.getColumnIndex(DBHelper.COL_VERSION)),
				c.getInt(c.getColumnIndex(DBHelper.COL_STATUS))
		);
		return refund;
	}

	private Item cursorToItem(Cursor c) {
		Item  item = new Item(
				c.getInt(c.getColumnIndex(DBHelper.COL_ID)),
				c.getInt(c.getColumnIndex(DBHelper.COL_SERVER_ID)),
				c.getInt(c.getColumnIndex(DBHelper.COL_TYPE_ID)),
				c.getInt(c.getColumnIndex(DBHelper.COL_ITEM_ID)),
				c.getDouble(c.getColumnIndex(DBHelper.COL_QUANTITY)),
				c.getDouble(c.getColumnIndex(DBHelper.COL_ITEM_PRICE)),
				c.getInt(c.getColumnIndex(DBHelper.COL_OBJECT_ID)),
				c.getInt(c.getColumnIndex(DBHelper.COL_UNIT_DETAIL_ID)),
				c.getInt(c.getColumnIndex(DBHelper.COL_VERSION)),
				c.getInt(c.getColumnIndex(DBHelper.COL_STATUS))
		);
		item.setUnitDetailName(c.getString(c.getColumnIndex(DBHelper.COL_UNIT_DETAIL_NAME)));
		item.setName(c.getString(c.getColumnIndex(DBHelper.COL_NAME)));
		item.setCode(c.getString(c.getColumnIndex(DBHelper.COL_CODE)));
		item.setDescription(c.getString(c.getColumnIndex(DBHelper.COL_DESCRIPTION)));
		item.setVat(c.getDouble(c.getColumnIndex(DBHelper.COL_VAT)));
		item.setCurrency(c.getString(c.getColumnIndex(DBHelper.COL_CURRENCY_NAME)));

		return item;
	}

    private OrderItem cursorToOrderItem(Cursor c) {
        OrderItem  orderItem = new OrderItem(
                c.getInt(c.getColumnIndex(DBHelper.COL_ID)),
                c.getInt(c.getColumnIndex(DBHelper.COL_SERVER_ID)),
                c.getInt(c.getColumnIndex(DBHelper.COL_TYPE_ID)),
                c.getInt(c.getColumnIndex(DBHelper.COL_ITEM_ID)),
                c.getDouble(c.getColumnIndex(DBHelper.COL_QUANTITY)),
                c.getDouble(c.getColumnIndex(DBHelper.COL_ITEM_PRICE)),

                c.getInt(c.getColumnIndex(DBHelper.COL_OBJECT_ID)),

                c.getInt(c.getColumnIndex(DBHelper.COL_UNIT_DETAIL_ID)),
                c.getInt(c.getColumnIndex(DBHelper.COL_VERSION)),
                c.getInt(c.getColumnIndex(DBHelper.COL_STATUS))
        );
	    orderItem.setId             (c.getInt(c.getColumnIndex(DBHelper.COL_ID)));
	    orderItem.setServerId       (c.getInt(c.getColumnIndex(DBHelper.COL_SERVER_ID)));
	    orderItem.setType           (c.getInt(c.getColumnIndex(DBHelper.COL_TYPE_ID)));
	    orderItem.setItemId         (c.getInt(c.getColumnIndex(DBHelper.COL_ITEM_ID)));
	    orderItem.setQuantity       (c.getDouble(c.getColumnIndex(DBHelper.COL_QUANTITY)));
	    orderItem.setItemSubtotalPrice(c.getDouble(c.getColumnIndex(DBHelper.COL_ITEM_PRICE)));

	    orderItem.setUnitDetailId   (c.getInt(c.getColumnIndex(DBHelper.COL_UNIT_DETAIL_ID)));
	    orderItem.setVersion(c.getInt(c.getColumnIndex(DBHelper.COL_VERSION)));
	    orderItem.setStatus         (c.getInt(c.getColumnIndex(DBHelper.COL_STATUS)));
	    orderItem.setUnitDetailName(c.getString(c.getColumnIndex(DBHelper.COL_UNIT_DETAIL_NAME)));
	    orderItem.setName           (c.getString(c.getColumnIndex(DBHelper.COL_NAME)));
	    orderItem.setCode           (c.getString(c.getColumnIndex(DBHelper.COL_CODE)));
	    orderItem.setDescription    (c.getString(c.getColumnIndex(DBHelper.COL_DESCRIPTION)));
	    orderItem.setVat            (c.getDouble(c.getColumnIndex(DBHelper.COL_VAT)));
	    orderItem.setCurrency       (c.getString(c.getColumnIndex(DBHelper.COL_CURRENCY_NAME)));

        return orderItem;
    }

	private DispatchItem cursorToDispatchItem(Cursor c) {
		DispatchItem  dispatchItem = new DispatchItem(
				c.getInt(c.getColumnIndex(DBHelper.COL_ID)),
				c.getInt(c.getColumnIndex(DBHelper.COL_SERVER_ID)),
				c.getInt(c.getColumnIndex(DBHelper.COL_TYPE_ID)),
				c.getInt(c.getColumnIndex(DBHelper.COL_ITEM_ID)),
				c.getDouble(c.getColumnIndex(DBHelper.COL_QUANTITY)),
				c.getDouble(c.getColumnIndex(DBHelper.COL_ITEM_PRICE)),
				c.getInt(c.getColumnIndex(DBHelper.COL_OBJECT_ID)),
				c.getInt(c.getColumnIndex(DBHelper.COL_UNIT_DETAIL_ID)),
				c.getInt(c.getColumnIndex(DBHelper.COL_VERSION)),
				c.getInt(c.getColumnIndex(DBHelper.COL_STATUS))
		);
		dispatchItem.setUnitDetailName(c.getString(c.getColumnIndex(DBHelper.COL_UNIT_DETAIL_NAME)));
		dispatchItem.setName(c.getString(c.getColumnIndex(DBHelper.COL_NAME)));
		dispatchItem.setCode(c.getString(c.getColumnIndex(DBHelper.COL_CODE)));
		dispatchItem.setDescription(c.getString(c.getColumnIndex(DBHelper.COL_DESCRIPTION)));
		dispatchItem.setVat(c.getDouble(c.getColumnIndex(DBHelper.COL_VAT)));
		dispatchItem.setCurrency(c.getString(c.getColumnIndex(DBHelper.COL_CURRENCY_NAME)));

		return dispatchItem;
	}

	private InvoiceItem cursorToInvoiceItem(Cursor c) {
		InvoiceItem  invoiceItem = new InvoiceItem(
				c.getInt(c.getColumnIndex(DBHelper.COL_ID)),
				c.getInt(c.getColumnIndex(DBHelper.COL_SERVER_ID)),
				c.getInt(c.getColumnIndex(DBHelper.COL_TYPE_ID)),
				c.getInt(c.getColumnIndex(DBHelper.COL_ITEM_ID)),
				c.getDouble(c.getColumnIndex(DBHelper.COL_QUANTITY)),
				c.getDouble(c.getColumnIndex(DBHelper.COL_ITEM_PRICE)),
				c.getInt(c.getColumnIndex(DBHelper.COL_OBJECT_ID)),
				c.getInt(c.getColumnIndex(DBHelper.COL_UNIT_DETAIL_ID)),
				c.getInt(c.getColumnIndex(DBHelper.COL_VERSION)),
				c.getInt(c.getColumnIndex(DBHelper.COL_STATUS))
		);
		invoiceItem.setUnitDetailName(c.getString(c.getColumnIndex(DBHelper.COL_UNIT_DETAIL_NAME)));
		invoiceItem.setName(c.getString(c.getColumnIndex(DBHelper.COL_NAME)));
		invoiceItem.setCode(c.getString(c.getColumnIndex(DBHelper.COL_CODE)));
		invoiceItem.setDescription(c.getString(c.getColumnIndex(DBHelper.COL_DESCRIPTION)));
		invoiceItem.setVat(c.getDouble(c.getColumnIndex(DBHelper.COL_VAT)));
		invoiceItem.setCurrency(c.getString(c.getColumnIndex(DBHelper.COL_CURRENCY_NAME)));

		return invoiceItem;
	}

	private RefundItem cursorToRefundItem(Cursor c) {
		RefundItem  refundItem = new RefundItem(
				c.getInt(c.getColumnIndex(DBHelper.COL_ID)),
				c.getInt(c.getColumnIndex(DBHelper.COL_SERVER_ID)),
				c.getInt(c.getColumnIndex(DBHelper.COL_TYPE_ID)),
				c.getInt(c.getColumnIndex(DBHelper.COL_ITEM_ID)),
				c.getDouble(c.getColumnIndex(DBHelper.COL_QUANTITY)),
				c.getDouble(c.getColumnIndex(DBHelper.COL_ITEM_PRICE)),
				c.getInt(c.getColumnIndex(DBHelper.COL_OBJECT_ID)),
				c.getInt(c.getColumnIndex(DBHelper.COL_UNIT_DETAIL_ID)),
				c.getInt(c.getColumnIndex(DBHelper.COL_VERSION)),
				c.getInt(c.getColumnIndex(DBHelper.COL_STATUS))
		);
		refundItem.setUnitDetailName(c.getString(c.getColumnIndex(DBHelper.COL_UNIT_DETAIL_NAME)));
		refundItem.setName(c.getString(c.getColumnIndex(DBHelper.COL_NAME)));
		refundItem.setCode(c.getString(c.getColumnIndex(DBHelper.COL_CODE)));
		refundItem.setDescription(c.getString(c.getColumnIndex(DBHelper.COL_DESCRIPTION)));
		refundItem.setVat(c.getDouble(c.getColumnIndex(DBHelper.COL_VAT)));
		refundItem.setCurrency(c.getString(c.getColumnIndex(DBHelper.COL_CURRENCY_NAME)));

		return refundItem;
	}
    private CashPayment cursorToCashPayment(Cursor c) {
		CashPayment  lCashPayment = new CashPayment();
        {
            lCashPayment.setId(c.getInt(c.getColumnIndex(DBHelper.COL_ID)));
            lCashPayment.setServerId(c.getInt(c.getColumnIndex(DBHelper.COL_SERVER_ID)));
            lCashPayment.setSlipNumber(c.getString(c.getColumnIndex(DBHelper.COL_SLIP_NUMBER)));
            lCashPayment.setSpecialCode(c.getString(c.getColumnIndex(DBHelper.COL_SPECIAL_CODE)));
            lCashPayment.setDate(c.getString(c.getColumnIndex(DBHelper.COL_DATE)));
            lCashPayment.setAmount(c.getDouble(c.getColumnIndex(DBHelper.COL_AMOUNT)));
            lCashPayment.setCurrencyId(c.getInt(c.getColumnIndex(DBHelper.COL_CURRENCY_ID)));
            lCashPayment.setCustomerId(c.getInt(c.getColumnIndex(DBHelper.COL_CUSTOMER_ID)));
            lCashPayment.setDescription(c.getString(c.getColumnIndex(DBHelper.COL_DESCRIPTION)));
            lCashPayment.setVersion(c.getInt(c.getColumnIndex(DBHelper.COL_VERSION)));
            lCashPayment.setStatus(c.getInt(c.getColumnIndex(DBHelper.COL_STATUS)));
        }
		return lCashPayment;
	}

    private Payment cursorToPayment(Cursor c) {
        Payment p = new Payment();
        {
            p.setId(c.getInt(c.getColumnIndex(DBHelper.COL_ID)));
            p.setServerId(c.getInt(c.getColumnIndex(DBHelper.COL_SERVER_ID)));
            p.setCustomerId(c.getInt(c.getColumnIndex(DBHelper.COL_CUSTOMER_ID)));
            p.setCurrencyId(c.getInt(c.getColumnIndex(DBHelper.COL_CURRENCY_ID)));
            p.setDate(c.getString(c.getColumnIndex(DBHelper.COL_DATE)));
            p.setSlipNumber(c.getString(c.getColumnIndex(DBHelper.COL_SLIP_NUMBER)));
            p.setSpecialCode(c.getString(c.getColumnIndex(DBHelper.COL_SPECIAL_CODE)));
            p.setDescription(c.getString(c.getColumnIndex(DBHelper.COL_DESCRIPTION)));
            p.setAmount(c.getDouble(c.getColumnIndex(DBHelper.COL_AMOUNT)));
            p.setVersion(c.getInt(c.getColumnIndex(DBHelper.COL_VERSION)));
            p.setStatus(c.getInt(c.getColumnIndex(DBHelper.COL_STATUS)));
        }
        return p;
    };
    private CreditCardPayment cursorToCreditCardPayment(Cursor c) {
        CreditCardPayment p = new CreditCardPayment();
        {
            p.setHolderName(c.getString(c.getColumnIndex(DBHelper.COL_HOLDER_NAME)));
            p.setNumber(c.getString(c.getColumnIndex(DBHelper.COL_CARD_NUMBER)));
            p.setCVVCode(c.getString(c.getColumnIndex(DBHelper.COL_CVV_CODE)));
            p.setExpiryDate(c.getString(c.getColumnIndex(DBHelper.COL_EXPIRY_DATE)));

            p.setId(c.getInt(c.getColumnIndex(DBHelper.COL_ID)));
            p.setServerId(c.getInt(c.getColumnIndex(DBHelper.COL_SERVER_ID)));
            p.setCustomerId(c.getInt(c.getColumnIndex(DBHelper.COL_CUSTOMER_ID)));
            p.setCurrencyId(c.getInt(c.getColumnIndex(DBHelper.COL_CURRENCY_ID)));
            p.setDate(c.getString(c.getColumnIndex(DBHelper.COL_DATE)));
            p.setSlipNumber(c.getString(c.getColumnIndex(DBHelper.COL_SLIP_NUMBER)));
            p.setSpecialCode(c.getString(c.getColumnIndex(DBHelper.COL_SPECIAL_CODE)));
            p.setDescription(c.getString(c.getColumnIndex(DBHelper.COL_DESCRIPTION)));
            p.setAmount(c.getDouble(c.getColumnIndex(DBHelper.COL_AMOUNT)));
            p.setVersion(c.getInt(c.getColumnIndex(DBHelper.COL_VERSION)));
            p.setStatus(c.getInt(c.getColumnIndex(DBHelper.COL_STATUS)));
        }
        return p;
    }

    private ChequePayment cursorToChequePayment(Cursor c) {
        ChequePayment p = new ChequePayment();
        {
            p.setId(c.getInt(c.getColumnIndex(DBHelper.COL_ID)));
            p.setServerId(c.getInt(c.getColumnIndex(DBHelper.COL_SERVER_ID)));
            p.setCustomerId(c.getInt(c.getColumnIndex(DBHelper.COL_CUSTOMER_ID)));
            p.setCurrencyId(c.getInt(c.getColumnIndex(DBHelper.COL_CURRENCY_ID)));
            p.setDate(c.getString(c.getColumnIndex(DBHelper.COL_DATE)));
            p.setSlipNumber(c.getString(c.getColumnIndex(DBHelper.COL_SLIP_NUMBER)));
            p.setSpecialCode(c.getString(c.getColumnIndex(DBHelper.COL_SPECIAL_CODE)));
            p.setDescription(c.getString(c.getColumnIndex(DBHelper.COL_DESCRIPTION)));
            p.setAmount(c.getDouble(c.getColumnIndex(DBHelper.COL_AMOUNT)));
            p.setVersion(c.getInt(c.getColumnIndex(DBHelper.COL_VERSION)));
            p.setStatus(c.getInt(c.getColumnIndex(DBHelper.COL_STATUS)));

            p.setIssuerName(c.getString(c.getColumnIndex(DBHelper.COL_ISSUER_NAME)));
            p.setSerialNumber(c.getString(c.getColumnIndex(DBHelper.COL_SERIAL_NUMBER)));
            p.setDueDate(c.getString(c.getColumnIndex(DBHelper.COL_DUE_DATE)));

        }
        return p;
    };

    private BondPayment cursorToBondPayment(Cursor c) {
        BondPayment p = new BondPayment();
        {
            p.setId(c.getInt(c.getColumnIndex(DBHelper.COL_ID)));
            p.setServerId(c.getInt(c.getColumnIndex(DBHelper.COL_SERVER_ID)));
            p.setCustomerId(c.getInt(c.getColumnIndex(DBHelper.COL_CUSTOMER_ID)));
            p.setCurrencyId(c.getInt(c.getColumnIndex(DBHelper.COL_CURRENCY_ID)));
            p.setDate(c.getString(c.getColumnIndex(DBHelper.COL_DATE)));
            p.setSlipNumber(c.getString(c.getColumnIndex(DBHelper.COL_SLIP_NUMBER)));
            p.setSpecialCode(c.getString(c.getColumnIndex(DBHelper.COL_SPECIAL_CODE)));
            p.setDescription(c.getString(c.getColumnIndex(DBHelper.COL_DESCRIPTION)));
            p.setAmount(c.getDouble(c.getColumnIndex(DBHelper.COL_AMOUNT)));
            p.setVersion(c.getInt(c.getColumnIndex(DBHelper.COL_VERSION)));
            p.setStatus(c.getInt(c.getColumnIndex(DBHelper.COL_STATUS)));

            p.setIssuerName(c.getString(c.getColumnIndex(DBHelper.COL_ISSUER_NAME)));
            p.setSerialNumber(c.getString(c.getColumnIndex(DBHelper.COL_SERIAL_NUMBER)));
            p.setDueDate(c.getString(c.getColumnIndex(DBHelper.COL_DUE_DATE)));

            p.setGuarantorName(c.getString(c.getColumnIndex(DBHelper.COL_GUARANTOR_NAME)));

        }
        return p;
    };



    public Product getProduct(int serverId) {
        Product product = null;
        String whereClause = DBHelper.COL_SERVER_ID + DAO.EQUAL + serverId;
        Cursor cursor = mDb.query(DBHelper.PRODUCTS_TN,
                null, whereClause, null, null, null, null);
        if (cursor.moveToFirst()) {
            product = cursorToProduct(cursor);
        }
        return product;
    }

    public Currency getCurrency(int serverId) {
        Currency currency = null;
        String whereClause = DBHelper.COL_SERVER_ID + DAO.EQUAL + serverId;
        Cursor cursor = mDb.query(DBHelper.CURRENCIES_TN,
                null, whereClause, null, null, null, null);
        if (cursor.moveToFirst()) {
            currency = cursorToCurrency(cursor);
        }
        return currency;
    }

    public Service getService(int serverId) {
        Service result = null;
        String whereClause = DBHelper.COL_SERVER_ID + DAO.EQUAL + serverId;
        Cursor cursor = mDb.query(DBHelper.SERVICES_TN,
                null, whereClause, null, null, null, null);
        if (cursor.moveToFirst()) {
            result = cursorToService(cursor);
        }
        return result;
    }

    public Division getDivision(int serverId) {
        Division result = null;
        String whereClause = DBHelper.COL_SERVER_ID + DAO.EQUAL + serverId;
        Cursor cursor = mDb.query(DBHelper.DIVISION_TN,
                null, whereClause, null, null, null, null);
        if (cursor.moveToFirst()) {
            result = cursorToDivision(cursor);
        }
        return result;
    }

    public Department getDepartment(int serverId) {
        Department result = null;
        String whereClause = DBHelper.COL_SERVER_ID + DAO.EQUAL + serverId;
        Cursor cursor = mDb.query(DBHelper.DEPARTMENT_TN,
                null, whereClause, null, null, null, null);
        if (cursor.moveToFirst()) {
            result = cursorToDepartment(cursor);
        }
        return result;
    }

    public Plant getPlant(int serverId) {
        Plant result = null;
        String whereClause = DBHelper.COL_SERVER_ID + DAO.EQUAL + serverId;
        Cursor cursor = mDb.query(DBHelper.PLANT_TN,
                null, whereClause, null, null, null, null);
        if (cursor.moveToFirst()) {
            result = cursorToPlant(cursor);
        }
        return result;
    }

    public Warehouse getWarehouse(int serverId) {
        Warehouse result = null;
        String whereClause = DBHelper.COL_SERVER_ID + DAO.EQUAL + serverId;
        Cursor cursor = mDb.query(DBHelper.WAREHOUSE_TN,
                null, whereClause, null, null, null, null);
        if (cursor.moveToFirst()) {
            result = cursorToWarehouse(cursor);
        }
        return result;
    }



    public int getMaxVersion(String tableName) {
        int version = 0;
        Cursor cursor = mDb.query(tableName,
                null , "version=(SELECT MAX(version))", null, null, null, null);
        if (cursor.moveToFirst()){
            version = cursor.getInt(cursor.getColumnIndex(DBHelper.COL_VERSION));
        }
        return version;
    }

    public Customer getCustomer(int customerID) {
        Customer customer = null;
        String whereClause = DBHelper.COL_ID + DAO.EQUAL + customerID;
        Cursor cursor = mDb.query(DBHelper.CUSTOMERS_TN,
                null, whereClause, null, null, null, null);
        if (cursor.moveToFirst()) {
            customer = cursorToCustomer(cursor);
        } else customer = null;
        return customer;
    }



    public Order getOrder(int serverId) {
        Order order = null;
        String whereClause = DBHelper.COL_SERVER_ID + DAO.EQUAL + serverId;
        Cursor cursor = mDb.query(DBHelper.ORDERS_TN,
                null, whereClause, null, null, null, null);
        if (cursor.moveToFirst()) {
            order = cursorToOrder(cursor);
        }
        return order;
    }

	public Dispatch getDispatch(int serverId) {
		Dispatch dispatch = null;
		String whereClause = DBHelper.COL_SERVER_ID + DAO.EQUAL + serverId;
		Cursor cursor = mDb.query(DBHelper.DISPATCHES_TN,
				null, whereClause, null, null, null, null);
		if (cursor.moveToFirst()) {
			dispatch = cursorToDispatch(cursor);
		}
		return dispatch;
	}

	public Invoice getInvoice(int serverId) {
		Invoice invoice = null;
		String whereClause = DBHelper.COL_SERVER_ID + DAO.EQUAL + serverId;
		Cursor cursor = mDb.query(DBHelper.INVOICES_TN,
				null, whereClause, null, null, null, null);
		if (cursor.moveToFirst()) {
			invoice = cursorToInvoice(cursor);
		}
		return invoice;
	}

	public Refund getRefund(int serverId) {
		Refund refund = null;
		String whereClause = DBHelper.COL_SERVER_ID + DAO.EQUAL + serverId;
		Cursor cursor = mDb.query(DBHelper.REFUNDS_TN,
				null, whereClause, null, null, null, null);
		if (cursor.moveToFirst()) {
			refund = cursorToRefund(cursor);
		}
		return refund;
	}



	public UnitDetail getUnitDetail(int unitDetailId) {
		UnitDetail unitDetail = null;
		String where = DBHelper.COL_SERVER_ID + DAO.EQUAL + unitDetailId;

		Cursor cursor = mDb.query(DBHelper.UNIT_DETAIL_TN,
				null, where, null , null, null, null, null);

		if (cursor.moveToFirst()) {
			unitDetail = cursorToUnitDetail(cursor);
		}
		return unitDetail;


	}
}
