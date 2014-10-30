package com.sezyakot.sailor.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.sezyakot.sailor.system.Debug;

public class DBHelper extends SQLiteOpenHelper {
    // Log tag for debugging
    public static final String LOG_TAG              = DBHelper.class.getSimpleName();
    // DB information
    public static final String DB_NAME              = "sailor.db";
    public static final int    DB_VERSION           = 1;
    // Tables
    public static final String CUSTOMERS_TN         = "customers";
    public static final String PRODUCTS_TN          = "products";
    public static final String SERVICES_TN          = "services";
    public static final String ORDERS_TN            = "orders";
	public static final String DISPATCHES_TN        = "dispatches";
	public static final String INVOICES_TN          = "invoices";
	public static final String REFUNDS_TN           = "refunds";
    public static final String ORDER_ITEMS_TN       = "order_items";
    public static final String DISPATCH_ITEMS_TN    = "dispatch_items";
	public static final String INVOICE_ITEMS_TN     = "invoice_items";
	public static final String REFUND_ITEMS_TN      = "refund_items";
	public static final String CURRENCIES_TN        = "currencies";
    public static final String PRODUCT_PRICES_TN    = "product_prices";
    public static final String SERVICE_PRICES_TN    = "service_prices";
    public static final String UNIT_TN              = "units";
    public static final String UNIT_DETAIL_TN       = "unit_details";
    public static final String DIVISION_TN          = "divisions";
    public static final String DEPARTMENT_TN        = "departments";
    public static final String WAREHOUSE_TN         = "warehouses";
    public static final String PLANT_TN             = "plants";
    public static final String CASH_PAYMENT_TN      = "cash_payments";
    public static final String CREDIT_CARD_PAYMENT_TN = "credit_card_payments";
    public static final String CHEQUE_PAYMENT_TN    = "cheque_payments";
    public static final String BOND_PAYMENT_TN      = "bond_payments";



    // Fields of Customer table
	public static final String COL_ID               = "_id";
	public static final String COL_NAME             = "name";
    public static final String COL_SERVER_ID        = "server_id";
    public static final String COL_LONGITUDE        = "longitude";
    public static final String COL_LATITUDE         = "latitude";
    public static final String COL_VERSION          = "version";
    public static final String COL_STATUS           = "status";

    // Fields for Product table
    public static final String COL_DESCRIPTION      = "description";
    public static final String COL_CODE             = "code";
    public static final String COL_VAT              = "vat";
    public static final String COL_UNIT_ID 			= "unit_id";
    public static final String COL_QUANTITY         = "quantity";

    // Fields for Order table
    public static final String COL_SLIP_NUMBER      = "slip_number";
    public static final String COL_DATE             = "date";
    public static final String COL_CUSTOMER_ID      = "customer_id";
    public static final String COL_SPECIAL_CODE     = "special_code";
    public static final String COL_DIVISION_ID      = "division_id";
    public static final String COL_DEPARTMENT_ID    = "department_id";
    public static final String COL_PLANT_ID         = "plant_id";
    public static final String COL_WAREHOUSE_ID     = "warehouse_id";
    public static final String COL_CURRENCY_ID      = "currency_id";
    public static final String COL_TOTAL            = "total";
    public static final String COL_SUBTOTAL         = "subtotal";
    public static final String COL_TOTAL_VAT        = "total_vat";
    public static final String COL_ADV_PAYMENT      = "advance_payment";

	// Fields for Refund table
	public static final String COL_REFUND_AMOUNT    = "refund_amount";




    // Fields for OrderItems table
    public static final String COL_ITEM_ID          = "item_id";
    public static final String COL_ORDER_ID         = "order_id";
    public static final String COL_ITEM_PRICE       = "item_price";
    /* COL_TYPE_ID => 1 = product; 2 = service; */
    public static final String COL_TYPE_ID          = "type_id";
    public static final String COL_UNIT_DETAIL_ID   = "unit_detail_id";


    // Fields for Currency table

    // Fields for Product_prices table
    public static final String COL_PRODUCT_ID       = "product_id";
    public static final String COL_PRICE            = "price";

    // Fields for Service_prices table
    public static final String COL_SERVICE_ID       = "service_id";

    // Fields for unit_detail table
    public static final String COL_FROM             = "from_id";
    public static final String COL_TO               = "to_id";
    public static final String COL_MAIN             = "main_id";

	public static final String COL_UNIT_DETAIL_NAME = "unit_detail_name";
	public static final String COL_CURRENCY_NAME    = "currency_name";

    public static final String COL_AMOUNT           = "amount";

    // Fields for finance
    public static final String COL_HOLDER_NAME = "holder_name";
    public static final String COL_CARD_NUMBER = "card_number";
    public static final String COL_EXPIRY_DATE = "expiry_date";
    public static final String COL_CVV_CODE = "cvv_code";
    public static final String COL_ISSUER_NAME = "issuer_name";
    public static final String COL_SERIAL_NUMBER = "serial_number";
    public static final String COL_DUE_DATE = "due_date";
    public static final String COL_GUARANTOR_NAME = "guarantor_name";


	private static final String END                  = ");";
	private static final String BEGIN                = " (";

	private static final String SQL_CREATE_CUSTOMERS_TABLE = "CREATE TABLE " + CUSTOMERS_TN + BEGIN
            + COL_ID            + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, "
            + COL_SERVER_ID     + " INTEGER(11) NOT NULL UNIQUE, "
            + COL_NAME          + " VARCHAR(50), "
            + COL_LONGITUDE     + " DOUBLE, "
            + COL_LATITUDE      + " DOUBLE, "
            + COL_STATUS        + " INTEGER, "
            + COL_VERSION       + " INTEGER(11)"
            + END;

    private static final String SQL_CREATE_PRODUCTS_TABLE = "CREATE TABLE " + PRODUCTS_TN + BEGIN
            + COL_ID            + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, "
            + COL_SERVER_ID     + " INTEGER(11) NOT NULL UNIQUE, "
            + COL_NAME          + " VARCHAR(50), "
            + COL_DESCRIPTION   + " TEXT, "
            + COL_CODE          + " VARCHAR(25), "
            + COL_QUANTITY      + " INTEGER(10), "
            + COL_VAT           + " INTEGER(10), "
            + COL_UNIT_ID       + " INTEGER(10), "
            + COL_VERSION       + " INTEGER(10), "
            + COL_STATUS        + " INTEGER(10)"
            + END;

    public static final String SQL_CREATE_SERVICES_TABLE = "CREATE TABLE " + SERVICES_TN + BEGIN
            + COL_ID            + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, "
            + COL_SERVER_ID     + " INTEGER(11) NOT NULL UNIQUE, "
            + COL_NAME          + " VARCHAR(50), "
            + COL_DESCRIPTION   + " TEXT, "
            + COL_CODE          + " VARCHAR(25), "
            + COL_VAT           + " INTEGER, "
            + COL_UNIT_ID 		+ " INTEGER, "
            + COL_STATUS        + " INTEGER(11), "
            + COL_VERSION       + " INTEGER(11)"
            + END;

    public static final String SQL_CREATE_ORDERS_TABLE = "CREATE TABLE " + ORDERS_TN + BEGIN
            + COL_ID            + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, "
            + COL_SERVER_ID     + " INTEGER(11) NOT NULL UNIQUE, "
            + COL_CUSTOMER_ID   + " INTEGER(11), "
            + COL_SLIP_NUMBER   + " VARCHAR(50), "
            + COL_SPECIAL_CODE  + " VARCHAR(50), "
            + COL_DATE          + " VARCHAR(50), "
            + COL_DIVISION_ID   + " INTEGER(11), "
            + COL_DEPARTMENT_ID + " INTEGER(11), "
            + COL_PLANT_ID      + " INTEGER(11), "
            + COL_WAREHOUSE_ID  + " INTEGER(11), "
            + COL_CURRENCY_ID   + " INTEGER(11), "
            + COL_SUBTOTAL      + " DOUBLE, "
            + COL_TOTAL         + " DOUBLE, "
            + COL_TOTAL_VAT     + " DOUBLE, "
            + COL_ADV_PAYMENT   + " DOUBLE, "
            + COL_VERSION       + " INTEGER(11), "
            + COL_STATUS        + " INTEGER(2)"
            + END;

	public static final String SQL_CREATE_DISPATCHES_TABLE = "CREATE TABLE " + DISPATCHES_TN + BEGIN
			+ COL_ID            + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, "
			+ COL_SERVER_ID     + " INTEGER(11) NOT NULL UNIQUE, "
			+ COL_CUSTOMER_ID   + " INTEGER(11), "
			+ COL_SLIP_NUMBER   + " VARCHAR(50), "
			+ COL_SPECIAL_CODE  + " VARCHAR(50), "
			+ COL_DATE          + " VARCHAR(50), "
			+ COL_DIVISION_ID   + " INTEGER(11), "
			+ COL_DEPARTMENT_ID + " INTEGER(11), "
			+ COL_PLANT_ID      + " INTEGER(11), "
			+ COL_WAREHOUSE_ID  + " INTEGER(11), "
			+ COL_CURRENCY_ID   + " INTEGER(11), "
			+ COL_SUBTOTAL      + " DOUBLE, "
			+ COL_TOTAL         + " DOUBLE, "
			+ COL_TOTAL_VAT     + " DOUBLE, "
			+ COL_ADV_PAYMENT   + " DOUBLE, "
			+ COL_VERSION       + " INTEGER(11), "
			+ COL_STATUS        + " INTEGER(2)"
			+ END;

	public static final String SQL_CREATE_INVOICES_TABLE = "CREATE TABLE " + INVOICES_TN + BEGIN
			+ COL_ID            + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, "
			+ COL_SERVER_ID     + " INTEGER(11) NOT NULL UNIQUE, "
			+ COL_CUSTOMER_ID   + " INTEGER(11), "
			+ COL_SLIP_NUMBER   + " VARCHAR(50), "
			+ COL_SPECIAL_CODE  + " VARCHAR(50), "
			+ COL_DATE          + " VARCHAR(50), "
			+ COL_DIVISION_ID   + " INTEGER(11), "
			+ COL_DEPARTMENT_ID + " INTEGER(11), "
			+ COL_PLANT_ID      + " INTEGER(11), "
			+ COL_WAREHOUSE_ID  + " INTEGER(11), "
			+ COL_CURRENCY_ID   + " INTEGER(11), "
			+ COL_SUBTOTAL      + " DOUBLE, "
			+ COL_TOTAL         + " DOUBLE, "
			+ COL_TOTAL_VAT     + " DOUBLE, "
			+ COL_ADV_PAYMENT   + " DOUBLE, "
			+ COL_VERSION       + " INTEGER(11), "
			+ COL_STATUS        + " INTEGER(2)"
			+ END;

	public static final String SQL_CREATE_REFUNDS_TABLE = "CREATE TABLE " + REFUNDS_TN + BEGIN
			+ COL_ID            + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, "
			+ COL_SERVER_ID     + " INTEGER(11) NOT NULL UNIQUE, "
			+ COL_CUSTOMER_ID   + " INTEGER(11), "
			+ COL_SLIP_NUMBER   + " VARCHAR(50), "
			+ COL_SPECIAL_CODE  + " VARCHAR(50), "
			+ COL_DATE          + " VARCHAR(50), "
			+ COL_DIVISION_ID   + " INTEGER(11), "
			+ COL_DEPARTMENT_ID + " INTEGER(11), "
			+ COL_PLANT_ID      + " INTEGER(11), "
			+ COL_WAREHOUSE_ID  + " INTEGER(11), "
			+ COL_CURRENCY_ID   + " INTEGER(11), "
			+ COL_SUBTOTAL      + " DOUBLE, "
			+ COL_TOTAL         + " DOUBLE, "
			+ COL_TOTAL_VAT     + " DOUBLE, "
			+ COL_ADV_PAYMENT   + " DOUBLE, "
			+ COL_VERSION       + " INTEGER(11), "
			+ COL_STATUS        + " INTEGER(2)"
			+ END;


	public static final String COL_OBJECT_ID = "object_id";
	public static final String SQL_CREATE_ORDER_ITEMS_TABLE = "CREATE TABLE " + ORDER_ITEMS_TN + BEGIN
            + COL_ID            + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, "
            + COL_SERVER_ID     + " INTEGER(11) NOT NULL UNIQUE, "
            + COL_ITEM_ID       + " INTEGER(11), "
            + COL_TYPE_ID       + " INTEGER(2), "
            + COL_OBJECT_ID      + " INTEGER(11), "
            + COL_ITEM_PRICE    + " DOUBLE, "
            + COL_QUANTITY      + " DOUBLE, "
            + COL_UNIT_DETAIL_ID+ " INTEGER(11), "
            + COL_VERSION       + " INTEGER(11), "
            + COL_STATUS        + " INTEGER(2)"
            + END;

	public static final String COL_DISPATCH_ID = "dispatch_id";
	public static final String SQL_CREATE_DISPATCH_ITEMS_TABLE = "CREATE TABLE " + DISPATCH_ITEMS_TN + BEGIN
			+ COL_ID            + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, "
			+ COL_SERVER_ID     + " INTEGER(11) NOT NULL UNIQUE, "
			+ COL_ITEM_ID       + " INTEGER(11), "
			+ COL_TYPE_ID       + " INTEGER(2), "
			+ COL_OBJECT_ID   + " INTEGER(11), "
			+ COL_ITEM_PRICE    + " DOUBLE, "
			+ COL_QUANTITY      + " DOUBLE, "
			+ COL_UNIT_DETAIL_ID+ " INTEGER(11), "
			+ COL_VERSION       + " INTEGER(11), "
			+ COL_STATUS        + " INTEGER(2)"
			+ END;

	public static final String COL_INVOICE_ID = "invoice_id";
	public static final String SQL_CREATE_INVOICE_ITEMS_TABLE = "CREATE TABLE " + INVOICE_ITEMS_TN + BEGIN
			+ COL_ID            + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, "
			+ COL_SERVER_ID     + " INTEGER(11) NOT NULL UNIQUE, "
			+ COL_ITEM_ID       + " INTEGER(11), "
			+ COL_TYPE_ID       + " INTEGER(2), "
			+ COL_OBJECT_ID    + " INTEGER(11), "
			+ COL_ITEM_PRICE    + " DOUBLE, "
			+ COL_QUANTITY      + " DOUBLE, "
			+ COL_UNIT_DETAIL_ID+ " INTEGER(11), "
			+ COL_VERSION       + " INTEGER(11), "
			+ COL_STATUS        + " INTEGER(2)"
			+ END;

	public static final String COL_REFUND_ID = "refund_id";
	public static final String SQL_CREATE_REFUND_ITEMS_TABLE = "CREATE TABLE " + REFUND_ITEMS_TN + BEGIN
			+ COL_ID            + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, "
			+ COL_SERVER_ID     + " INTEGER(11) NOT NULL UNIQUE, "
			+ COL_ITEM_ID       + " INTEGER(11), "
			+ COL_TYPE_ID       + " INTEGER(2), "
			+ COL_OBJECT_ID     + " INTEGER(11), "
			+ COL_ITEM_PRICE    + " DOUBLE, "
			+ COL_QUANTITY      + " DOUBLE, "
			+ COL_UNIT_DETAIL_ID+ " INTEGER(11), "
			+ COL_VERSION       + " INTEGER(11), "
			+ COL_STATUS        + " INTEGER(2)"
			+ END;
	
	

    public static final String SQL_CREATE_CURRENCY_TABLE = "CREATE TABLE " + CURRENCIES_TN + BEGIN
            + COL_ID            + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, "
            + COL_SERVER_ID     + " INTEGER(11) NOT NULL UNIQUE, "
            + COL_NAME          + " VARCHAR(50), "
            + COL_VERSION       + " INTEGER(11), "
            + COL_STATUS        + " INTEGER(2)"
            + END;


    public static final String SQL_CREATE_PRODUCT_PRICES_TABLE = "CREATE TABLE " + PRODUCT_PRICES_TN + BEGIN
            + COL_ID            + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, "
            + COL_SERVER_ID     + " INTEGER(11) NOT NULL UNIQUE, "
            + COL_PRODUCT_ID    + " INTEGER(11), "
            + COL_PRICE         + " DOUBLE(9,2), "
            + COL_CURRENCY_ID   + " INTEGER(11), "
            + COL_VERSION       + " INTEGER(11)"
            + END;

    public static final String SQL_CREATE_SERVICE_PRICES_TABLE = "CREATE TABLE " + SERVICE_PRICES_TN + BEGIN
            + COL_ID            + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, "
            + COL_SERVER_ID     + " INTEGER(11) NOT NULL UNIQUE, "
            + COL_SERVICE_ID    + " INTEGER(11), "
            + COL_PRICE         + " DOUBLE(9,2), "
            + COL_CURRENCY_ID   + " INTEGER(11), "
            + COL_VERSION       + " INTEGER(11)"
            + END;

    public static final String SQL_CREATE_UNIT_TABLE = "CREATE TABLE "  + UNIT_TN + BEGIN
            + COL_ID            + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, "
            + COL_SERVER_ID     + " INTEGER(11) NOT NULL UNIQUE, "
            + COL_NAME          + " VARCHAR(50), "
            + COL_VERSION       + " INTEGER(11), "
            + COL_STATUS        + " INTEGER(2)"
            + END;

    public static final String SQL_CREATE_UNIT_DETAIL_TABLE = "CREATE TABLE " + UNIT_DETAIL_TN + BEGIN
            + COL_ID            + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, "
            + COL_SERVER_ID     + " INTEGER(11) NOT NULL UNIQUE, "
            + COL_UNIT_ID       + " INTEGER(11), "
            + COL_NAME          + " VARCHAR(50), "
            + COL_FROM          + " INTEGER(11), "
            + COL_TO            + " INTEGER(11), "
            + COL_MAIN          + " BOOLEAN, "
            + COL_VERSION       + " INTEGER(11), "
            + COL_STATUS        + " INTEGER(2)"
            + END;

    public static final String SQL_CREATE_DIVISION_TABLE = "CREATE TABLE "  + DIVISION_TN + BEGIN
            + COL_ID            + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, "
            + COL_SERVER_ID     + " INTEGER(11) NOT NULL UNIQUE, "
            + COL_NAME          + " VARCHAR(50), "
            + COL_VERSION       + " INTEGER(11), "
            + COL_STATUS        + " INTEGER(2)"
            + END;

    public static final String SQL_CREATE_DEPARTMENT_TABLE = "CREATE TABLE "  + DEPARTMENT_TN + BEGIN
            + COL_ID            + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, "
            + COL_SERVER_ID     + " INTEGER(11) NOT NULL UNIQUE, "
            + COL_NAME          + " VARCHAR(50), "
            + COL_VERSION       + " INTEGER(11), "
            + COL_STATUS        + " INTEGER(2)"
            + END;

    public static final String SQL_CREATE_WAREHOUSE_TABLE = "CREATE TABLE "  + WAREHOUSE_TN + BEGIN
            + COL_ID            + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, "
            + COL_SERVER_ID     + " INTEGER(11) NOT NULL UNIQUE, "
            + COL_NAME          + " VARCHAR(50), "
            + COL_VERSION       + " INTEGER(11), "
            + COL_STATUS        + " INTEGER(2)"
            + END;

    public static final String SQL_CREATE_PLANT_TABLE = "CREATE TABLE "  + PLANT_TN + BEGIN
            + COL_ID            + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, "
            + COL_SERVER_ID     + " INTEGER(11) NOT NULL UNIQUE, "
            + COL_NAME          + " VARCHAR(50), "
            + COL_VERSION       + " INTEGER(11), "
            + COL_STATUS        + " INTEGER(2)"
            + END;

    public static final String SQL_CREATE_CASH_PAYMENT_TABLE = "CREATE TABLE " + CASH_PAYMENT_TN + BEGIN
            + COL_ID            + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, "
            + COL_SERVER_ID     + " INTEGER(11) NOT NULL UNIQUE, "
            + COL_SLIP_NUMBER   + " VARCHAR(50), "
            + COL_SPECIAL_CODE  + " VARCHAR(50), "
            + COL_DATE          + " VARCHAR(20), "
            + COL_AMOUNT        + " DOUBLE(15,3), "
            + COL_CURRENCY_ID   + " INTEGER(11), "
            + COL_CUSTOMER_ID   + " INTEGER(11), "
            + COL_DESCRIPTION   + " TEXT, "
            + COL_VERSION       + " INTEGER(11), "
            + COL_STATUS        + " INTEGER(2)"
            + END;

    ;
    public static final String SQL_CREATE_CREDIT_CARD_PAYMENT_TABLE = "CREATE TABLE " + CREDIT_CARD_PAYMENT_TN + BEGIN
            + COL_ID            + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, "
            + COL_SERVER_ID     + " INTEGER(11) NOT NULL UNIQUE, "
            + COL_SLIP_NUMBER   + " VARCHAR(50), "
            + COL_SPECIAL_CODE  + " VARCHAR(50), "
            + COL_DATE          + " VARCHAR(20), "
            + COL_AMOUNT        + " DOUBLE(15,3), "
            + COL_CURRENCY_ID   + " INTEGER(11), "
            + COL_CUSTOMER_ID   + " INTEGER(11), "
            + COL_DESCRIPTION   + " TEXT, "
            + COL_HOLDER_NAME   + " VARCHAR(50), "
            + COL_CARD_NUMBER   + " VARCHAR(16), "
            + COL_EXPIRY_DATE   + " VARCHAR(5), "
            + COL_CVV_CODE      + " VARCHAR(3), "
            + COL_VERSION       + " INTEGER(11), "
            + COL_STATUS        + " INTEGER(2)"
            + END;

    public static final String SQL_CREATE_CHEQUE_PAYMENT_TABLE = "CREATE TABLE " + CHEQUE_PAYMENT_TN + BEGIN
            + COL_ID            + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, "
            + COL_SERVER_ID     + " INTEGER(11) NOT NULL UNIQUE, "
            + COL_SLIP_NUMBER   + " VARCHAR(50), "
            + COL_SPECIAL_CODE  + " VARCHAR(50), "
            + COL_DATE          + " VARCHAR(20), "
            + COL_AMOUNT        + " DOUBLE(15,3), "
            + COL_CURRENCY_ID   + " INTEGER(11), "
            + COL_CUSTOMER_ID   + " INTEGER(11), "
            + COL_DESCRIPTION   + " TEXT, "
            + COL_ISSUER_NAME   + " VARCHAR(50), "
            + COL_SERIAL_NUMBER + " VARCHAR(50), "
            + COL_DUE_DATE      + " VARCHAR(50), "
            + COL_VERSION       + " INTEGER(11), "
            + COL_STATUS        + " INTEGER(2)"
            + END;

    public static final String SQL_CREATE_BOND_PAYMENT_TABLE = "CREATE TABLE " + BOND_PAYMENT_TN + BEGIN
            + COL_ID            + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, "
            + COL_SERVER_ID     + " INTEGER(11) NOT NULL UNIQUE, "
            + COL_SLIP_NUMBER   + " VARCHAR(50), "
            + COL_SPECIAL_CODE  + " VARCHAR(50), "
            + COL_DATE          + " VARCHAR(20), "
            + COL_AMOUNT        + " DOUBLE(15,3), "
            + COL_CURRENCY_ID   + " INTEGER(11), "
            + COL_CUSTOMER_ID   + " INTEGER(11), "
            + COL_DESCRIPTION   + " TEXT, "
            + COL_ISSUER_NAME   + " VARCHAR(50), "
            + COL_GUARANTOR_NAME+ " VARCHAR(50), "
            + COL_SERIAL_NUMBER + " VARCHAR(50), "
            + COL_DUE_DATE      + " VARCHAR(50), "
            + COL_VERSION       + " INTEGER(11), "
            + COL_STATUS        + " INTEGER(2)"
            + END;





    private static final String SQL_DELETE_ENTRIES = "  DROP TABLE IF EXISTS " + CUSTOMERS_TN +
                                                     "; DROP TABLE IF EXISTS " + PRODUCTS_TN +
                                                     "; DROP TABLE IF EXISTS " + SERVICES_TN +
                                                     "; DROP TABLE IF EXISTS " + ORDERS_TN +
                                                     "; DROP TABLE IF EXISTS " + INVOICES_TN +
                                                     "; DROP TABLE IF EXISTS " + DISPATCHES_TN +
                                                     "; DROP TABLE IF EXISTS " + ORDER_ITEMS_TN +
                                                     "; DROP TABLE IF EXISTS " + INVOICE_ITEMS_TN +
                                                     "; DROP TABLE IF EXISTS " + DISPATCH_ITEMS_TN +
                                                     "; DROP TABLE IF EXISTS " + CURRENCIES_TN +
                                                     "; DROP TABLE IF EXISTS " + PRODUCT_PRICES_TN +
                                                     "; DROP TABLE IF EXISTS " + SERVICE_PRICES_TN +
                                                     "; DROP TABLE IF EXISTS " + UNIT_TN +
                                                     "; DROP TABLE IF EXISTS " + UNIT_DETAIL_TN +
                                                     "; DROP TABLE IF EXISTS " + DIVISION_TN +
                                                     "; DROP TABLE IF EXISTS " + DEPARTMENT_TN +
                                                     "; DROP TABLE IF EXISTS " + WAREHOUSE_TN +
                                                     "; DROP TABLE IF EXISTS " + PLANT_TN +
                                                     "; DROP TABLE IF EXISTS " + CASH_PAYMENT_TN +
                                                     "; DROP TABLE IF EXISTS " + CREDIT_CARD_PAYMENT_TN +
                                                     "; DROP TABLE IF EXISTS " + CHEQUE_PAYMENT_TN +
                                                     "; DROP TABLE IF EXISTS " + BOND_PAYMENT_TN
                                                      ;


	/*Constructor*/
	public DBHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		if (Debug.MODE) { Log.d(LOG_TAG, "<--- onCreate database --->" );
        //delete before create
            db.execSQL(SQL_DELETE_ENTRIES);
        }
		// create DB
		db.execSQL(SQL_CREATE_CUSTOMERS_TABLE);
        db.execSQL(SQL_CREATE_PRODUCTS_TABLE);
        db.execSQL(SQL_CREATE_SERVICES_TABLE);
        db.execSQL(SQL_CREATE_ORDERS_TABLE);
        db.execSQL(SQL_CREATE_DISPATCHES_TABLE);
        db.execSQL(SQL_CREATE_INVOICES_TABLE);
        db.execSQL(SQL_CREATE_REFUNDS_TABLE);
        db.execSQL(SQL_CREATE_ORDER_ITEMS_TABLE);
        db.execSQL(SQL_CREATE_DISPATCH_ITEMS_TABLE);
        db.execSQL(SQL_CREATE_INVOICE_ITEMS_TABLE);
        db.execSQL(SQL_CREATE_REFUND_ITEMS_TABLE);
        db.execSQL(SQL_CREATE_CURRENCY_TABLE);
        db.execSQL(SQL_CREATE_PRODUCT_PRICES_TABLE);
        db.execSQL(SQL_CREATE_SERVICE_PRICES_TABLE);
        db.execSQL(SQL_CREATE_UNIT_TABLE);
        db.execSQL(SQL_CREATE_UNIT_DETAIL_TABLE);
        db.execSQL(SQL_CREATE_DIVISION_TABLE);
        db.execSQL(SQL_CREATE_DEPARTMENT_TABLE);
        db.execSQL(SQL_CREATE_WAREHOUSE_TABLE);
        db.execSQL(SQL_CREATE_PLANT_TABLE);
        db.execSQL(SQL_CREATE_CASH_PAYMENT_TABLE);
        db.execSQL(SQL_CREATE_CREDIT_CARD_PAYMENT_TABLE);
        db.execSQL(SQL_CREATE_CHEQUE_PAYMENT_TABLE);
        db.execSQL(SQL_CREATE_BOND_PAYMENT_TABLE);
    }


	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		if (Debug.MODE) {
			Log.d(LOG_TAG, "<--- onCreate database --->");
			Log.w(LOG_TAG, "Old version:  " + oldVersion + " New version: " + newVersion); }
		// drop old DB
		db.execSQL(SQL_DELETE_ENTRIES);
		// create new DB
		onCreate(db);
	}

}
