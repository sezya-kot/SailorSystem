package com.sezyakot.android.sailorapp.sailor;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.*;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;
import com.sezyakot.android.sailorapp.sailor.adapters.ItemAdapter;
import com.sezyakot.android.sailorapp.sailor.db.DAO;
import com.sezyakot.android.sailorapp.sailor.db.DBHelper;
import com.sezyakot.android.sailorapp.sailor.model.*;
import com.sezyakot.android.sailorapp.sailor.system.Debug;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public abstract class DefaultSalesNew extends DefaultActivity implements OnClickListener, OnFocusChangeListener ,OnEditorActionListener, TextWatcher{
	
	public static final String LOG_TAG              = "DefaultSalesNew";

    public static final String PRODUCT_ID           = "com.sezyakot.android.sailorapp.sailor.PRODUCT_ID";
    public static final String SERVICE_ID           = "com.sezyakot.android.sailorapp.sailor.SERVICE_ID";
	public static final String ITEM_CODE 			= "com.sezyakot.android.sailorapp.sailor.ITEM_CODE";
	public static final String ITEM_DESCRIPTION 	= "com.sezyakot.android.sailorapp.sailor.ITEM_DESCRIPTION";
	public static final String UNIT 				= "com.sezyakot.android.sailorapp.sailor.ITEM_UNIT";
	public static final String UNIT_PRICE 			= "com.sezyakot.android.sailorapp.sailor.ITEM_CODE";

	public static final String PREF_KEY_CURRENT_TAB = "com.sezyakot.android.sailorapp.sailor.PREF_KEY_CURRENT_TAB";

    public static final String DSI                  = "DialogSelectItem";
    public static final String DSC                  = "DialogSelectCurrency";
    public static final String DSDIV                = "DialogSelectDivision";
    public static final String DSDEP                = "DialogSelectDepartment";
    public static final String DSPL                 = "DialogSelectPlant";
    public static final String DSWH                 = "DialogSelectWarehouse";
    public static final String DSDU                 = "DialogSelectUnitDetail";
    public static final String DCO                  = "DialogCreateOrder";

	public static final String DAY_OF_MONTH 		= "day_of_month";
	public static final String MONTH 				= "month";
	public static final String YEAR 				= "year";
	public static final String CURRENCY 			= "currency";
	public static final String CURRENT_TAB 			= "current_tab";
    public static final String SLIP_NUMBER          = "slip_number";
	public static final String SPECIAL_CODE        = "special_code";
    public static final String DIVISION_ID          = "division_id";
    public static final String DEPARTMENT_ID        = "department_id";
    public static final String PLANT_ID             = "plant_id";
    public static final String WAREHOUSE_ID         = "warehouse_id";
    public static final String UNIT_DETAIL_ID       = "unit_detail_id";
    public static final String UNIT_ID              = "unit_id";
    public static final String ITEM_SUBTOTAL_PRICE  = "item_subtotal_price";
    public static final String TYPE_ID              = "type_id";
    public static final String ITEM_ID              = "item_id";
	public static final int    ZERO                 = 0;
	public static final String EMPTY                = "";
	public static final String CURRENCY_STR         = "currency_str";

	protected static final int AMOUNT_OF_SLIP_NUMBER_GEN = 10;

	public static final String TAG_1 = "tag1";
	public static final String TAG_2 = "tag2";
	public static final String TAG_3 = "tag3";

	protected DAO mDao = new DAO(this);

    protected boolean 			enabled = true;
	protected   int 				mDay;
	protected   int 				mMonth;
	protected   int 				mYear;

	protected boolean           mReady = false;

    protected DefaultObject mObject;
    protected Item mItem;

    protected  String           mSlipNumber;
    protected  String           mSpecialCode;
    protected  String           mDate;
    protected  int              mDivisionId;
    protected  int              mDepartmentId;
    protected  int              mPlantId;
    protected  int              mWarehouseId;
    protected  int              mCurrencyId;
    protected  int              mItemId;
    protected  int              mUnitId;
    protected  int              mProductId;
    protected  int              mServiceId;
    protected  int              mUnitDetailId;
    protected  int              mTypeId;
    protected  double           mUnitPrice;
    protected  double           mItemSubtotalPrice;
    protected  double           mSubtotal;
    protected  double           mVAT;
    protected  double           mGTotal;

    protected ProgressDialog    dialog;

	protected UnitDetail mUnitDetail;


	protected String                    mCurrencyStr;
	protected Calendar   			    mCalendar;
//	protected Bundle 			        mBundle;
	protected ArrayList<EditText>       mEdits = new ArrayList<EditText>();
	protected TabHost 			        tabs;
	protected Button 			        mAddBtn;
	protected Button 			        mSaveBtn;
    protected Button                    mCancelBtn;
    protected Button                    mDeleteItem;
    protected Button                    mCreateBtn;
	protected EditText 			        mDateET;
    protected EditText                  mSlipNumberET;
	protected EditText                  mSpecialCodeET;
	protected EditText 			        mCurrencyET;
	protected EditText 			        mItemCodeET;
	protected EditText 			        mQuantityET;
	protected EditText 			        mUnitET;
	protected EditText 			        mUnitPriceET;
	protected EditText                  mItemSubtotalPriceET;
	protected EditText			        mDivisionET;
	protected EditText 			        mDepartmentET;
	protected EditText 			        mPlantET;
	protected EditText 			        mWarehouseET;
    protected TextView                  mSubtotalTV;
    protected TextView                  mVATTV;
    protected TextView                  mGTotalTV;
	protected TextView                  mTitleOfCommand;

	protected   ListView		 	    mListOfItemsTemp;
	protected ItemAdapter mAdapter;

    protected ArrayList<Currency>       mCurrencies;
    protected ArrayList<Division>       mDivisions;
    protected ArrayList<Department>     mDepartments;
    protected ArrayList<Plant>          mPlants;
    protected ArrayList<Warehouse>      mWarehouses;
    protected ArrayList<ProductPrice>   mProductPrices;
    protected ArrayList<UnitDetail>     mUnitDetails;
    protected ArrayList<ServicePrice>   mServicePrices;

    protected List<Item> mItems;

	protected FragmentManager mFm = getFragmentManager();
	protected DialogSelectCurrency mDSC;


	public String mUnitDetailName;
	public String mItemName;
	public String mItemDescription;
	public String mItemCode;
    private boolean mEditOn = false;


    @SuppressWarnings({"unchecked", "deprecation"})
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);



	    if (Debug.MODE) Debug.lockScreenOrientation(this);

        dialog = new ProgressDialog(this);
        dialog.setMessage(getString(R.string.connecting));
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);

        try {
            mDao.openToRead();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (savedInstanceState != null) mItem = (Item) getLastNonConfigurationInstance();

		setWrapper();
		setContentView(wrapper);
		setCompanyName();
		setTabs();
		setWidgetValue();
		setListeners();
		setTitleOfCommand();
		restoreInstance(savedInstanceState);
		setDate();
        mAddBtn.setEnabled(isAllEntered(mEdits));

	}

	abstract protected void setTitleOfCommand();


	/**
     * Save a chosen date
     * @param savedInstanceState
     */


    protected void restoreInstance(Bundle savedInstanceState) {
        mAddBtn.setEnabled(isAllEntered(mEdits));

        if (savedInstanceState == null) {
            mSlipNumberET       .setText(genSlipNumber(AMOUNT_OF_SLIP_NUMBER_GEN));
            mSlipNumber         = mSlipNumberET.getText().toString();
	        mSpecialCode        = EMPTY;
            mCalendar           = Calendar.getInstance();
            mDay                = mCalendar.get(Calendar.DAY_OF_MONTH);
            mMonth              = mCalendar.get(Calendar.MONTH);
            mYear               = mCalendar.get(Calendar.YEAR);
            mDate               = mDateET.getText().toString();
            mCurrencyId         = mCurrencies.get(ZERO).getServerId();
            tabs                .setCurrentTab(ZERO);

            mDivisionId         = mDivisions.get(ZERO).getServerId();
            mDepartmentId       = mDepartments.get(ZERO).getServerId();
            mPlantId            = mPlants.get(ZERO).getServerId();
            mWarehouseId        = mWarehouses.get(ZERO).getServerId();

	        mCurrencyStr        = mCurrencies.get(ZERO).getName();
            mCurrencyET         .setText(mCurrencyStr);
            mDivisionET         .setText(mDivisions.get(ZERO).getName());
            mDepartmentET       .setText(mDepartments.get(ZERO).getName());
            mPlantET            .setText(mPlants.get(ZERO).getName());
            mWarehouseET        .setText(mWarehouses.get(ZERO).getName());

            mUnitET             .setText(EMPTY);

	        mUnitDetails        = mDao.getUnitDetails(null);

        }
        else {
            mDay                = savedInstanceState.getInt(DAY_OF_MONTH);
            mMonth              = savedInstanceState.getInt(MONTH);
            mYear               = savedInstanceState.getInt(YEAR);
            mDate               = mDateET.getText().toString();
            mProductId          = savedInstanceState.getInt(PRODUCT_ID);
            mServiceId          = savedInstanceState.getInt(SERVICE_ID);

            tabs                .setCurrentTab(savedInstanceState.getInt(CURRENT_TAB));

            mSlipNumber         = savedInstanceState.getString(SLIP_NUMBER);
            mSlipNumberET       .setText(mSlipNumber);

	        mSpecialCode        = savedInstanceState.getString(SPECIAL_CODE);
	        mSpecialCodeET      .setText(mSpecialCode);

            mDivisionId         = savedInstanceState.getInt(DIVISION_ID);
            mDepartmentId       = savedInstanceState.getInt(DEPARTMENT_ID);
            mPlantId            = savedInstanceState.getInt(PLANT_ID);
            mWarehouseId        = savedInstanceState.getInt(WAREHOUSE_ID);
            mCurrencyId         = savedInstanceState.getInt(CURRENCY);
	        mCurrencyStr        = savedInstanceState.getString(CURRENCY_STR);

            mUnitDetailId       = savedInstanceState.getInt(UNIT_DETAIL_ID);
            mUnitId             = savedInstanceState.getInt(UNIT_ID);
            mItemSubtotalPrice  = savedInstanceState.getDouble(ITEM_SUBTOTAL_PRICE);
            mTypeId             = savedInstanceState.getInt(TYPE_ID);
            mItemId             = savedInstanceState.getInt(ITEM_ID);

	        mUnitPrice          = savedInstanceState.getDouble(UNIT_PRICE);

	        mUnitDetails        = mDao.getUnitDetails(""+mUnitId);

	        mUnitDetail         = mDao.getUnitDetail(mUnitDetailId);

            mAddBtn.setEnabled(isAllEntered(mEdits));

            setSubtotaVatGTotal();
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
	    super.onSaveInstanceState(savedInstanceState);

	    if (Debug.MODE) {
		    Log.d(LOG_TAG, "<-- onSaveInstanceState() -->");
	    }

	    savedInstanceState.putInt(DAY_OF_MONTH, mDay);
	    savedInstanceState.putInt(MONTH, mMonth);
	    savedInstanceState.putInt(YEAR, mYear);
	    savedInstanceState.putInt(CURRENCY, mCurrencyId);
	    savedInstanceState.putInt(CURRENT_TAB, tabs.getCurrentTab());
	    savedInstanceState.putString(SLIP_NUMBER, mSlipNumber);
	    savedInstanceState.putString(SPECIAL_CODE, mSpecialCode);
	    savedInstanceState.putInt(UNIT_DETAIL_ID, mUnitDetailId);
	    savedInstanceState.putInt(UNIT_ID, mUnitId);
	    savedInstanceState.putDouble(ITEM_SUBTOTAL_PRICE, mItemSubtotalPrice);
	    savedInstanceState.putInt(TYPE_ID, mTypeId);
	    savedInstanceState.putInt(ITEM_ID, mItemId);
	    savedInstanceState.putString(CURRENCY_STR, mCurrencyStr);
	    savedInstanceState.putDouble(UNIT_PRICE, mUnitPrice);
	    savedInstanceState.putInt(DIVISION_ID, mDivisionId);
	    savedInstanceState.putInt(DEPARTMENT_ID, mDepartmentId);
	    savedInstanceState.putInt(PLANT_ID, mPlantId);
	    savedInstanceState.putInt(WAREHOUSE_ID, mWarehouseId);


	    if (Debug.MODE) {
		    Log.d(LOG_TAG, "Bundle: " + savedInstanceState);
	    }
    }


    void setWidgetValue() {
        mSlipNumberET           = (EditText) findViewById(R.id.slip_number);
		mAddBtn					= (Button) 	 findViewById(R.id.add_button);
		mSaveBtn 				= (Button)	 findViewById(R.id.save_button);
        mCancelBtn              = (Button)   findViewById(R.id.cancel_button);
        mCreateBtn              = (Button)   findViewById(R.id.create_button);
	    mSpecialCodeET          = (EditText) findViewById(R.id.special_code);
		mDateET					= (EditText) findViewById(R.id.date_et);
		mCurrencyET 			= (EditText) findViewById(R.id.currency);
		mDivisionET				= (EditText) findViewById(R.id.division);
		mDepartmentET			= (EditText) findViewById(R.id.department);
		mPlantET				= (EditText) findViewById(R.id.plant);
		mWarehouseET			= (EditText) findViewById(R.id.warehouse);
 		mItemCodeET 			= (EditText) findViewById(R.id.item_code_sno);
		mQuantityET 			= (EditText) findViewById(R.id.quantity);
		mUnitET 				= (EditText) findViewById(R.id.unit);
		mUnitPriceET			= (EditText) findViewById(R.id.unit_price);
		mItemSubtotalPriceET    = (EditText) findViewById(R.id.item_subtotal_price);
        mSubtotalTV             = (TextView) findViewById(R.id.subtotal_tv);
        mVATTV                  = (TextView) findViewById(R.id.vat_tv);
        mGTotalTV               = (TextView) findViewById(R.id.g_total_tv);
	    mTitleOfCommand         = (TextView) findViewById(R.id.title_of_command);

        mCurrencies  = mDao.getCurrencies(DBHelper.COL_NAME);
        mDivisions   = mDao.getDivisions(DBHelper.COL_NAME);
        mDepartments = mDao.getDepartments(DBHelper.COL_NAME);
        mPlants      = mDao.getPlants(DBHelper.COL_NAME);
        mWarehouses  = mDao.getWarehouses(DBHelper.COL_NAME);
	    mItems       = ItemList.get(getBaseContext()).getItems();

        mListOfItemsTemp 		= (ListView) findViewById(R.id.list_items_in_order);
		mAdapter 				= new ItemAdapter(this, mItems);

		mListOfItemsTemp.setAdapter(mAdapter);
	    mListOfItemsTemp.setLongClickable(true);
        registerForContextMenu(mListOfItemsTemp);
	    mListOfItemsTemp.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
		    @Override
		    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
			    if (Debug.MODE) {

			    	Log.d(LOG_TAG, "position: " + position);
			    }
			    return false;
		    }
	    });

		mEdits.add(mItemCodeET);
		mEdits.add(mQuantityET);
		mEdits.add(mUnitET);
		mEdits.add(mUnitPriceET);
		mEdits.add(mItemSubtotalPriceET);

		mAddBtn.setEnabled(isAllEntered(mEdits));
	}

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.item_menu, menu);
        menu.setHeaderIcon(R.drawable.ic_edit);
        menu.setHeaderTitle("Check option!");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
                .getMenuInfo();
        switch (item.getItemId()) {
            case R.id.remove_item:
                deleteDataItemFromOrder(info.position);
                setSubtotaVatGTotal();
                mAdapter.notifyDataSetChanged();
                return true;
            case R.id.edit_item:
                Item i = mItems.get(info.position);
            {
                mItemId = i.getItemId();
                mTypeId = i.getType();
                mItem = ItemList.get(this).getItem(i.getItemId(), i.getType());
                mItemDescription = i.getDescription();
                mItemCode = i.getCode();
                mItemName = i.getName();
                mUnitPrice = i.getUnitPrice();
                mUnitId = i.getUnitId();
                mUnitDetailId = mItem.getUnitDetailId();
                mUnitDetails = mDao.getUnitDetails(null, "" + DBHelper.COL_UNIT_ID + " = " + mUnitId);
                mUnitDetail   = mDao.getUnitDetail(mUnitDetailId);
                mUnitDetailName = mItem.getUnitDetailName();
                mItemCodeET.setText(mItemCode + " - " + mItemName + " - " + mItemDescription);
                mUnitET.setText(mUnitDetailName);
                mQuantityET.setText(""+mItem.getQuantity());
                mUnitPriceET.setText(Double.toString(mUnitPrice) + " " + mCurrencyStr);
                mItemSubtotalPrice = i.getItemSubtotalPrice();
                mItemSubtotalPriceET.setText(mItemSubtotalPrice + " " + mCurrencyStr);
            }
                mEditOn = true;
                tabs.setCurrentTabByTag(TAG_2);


        }
        return false;

    }

    /**
	 * 764533
	 */
	protected void setListeners() {
		mItemCodeET     .setOnClickListener(this);
		mDateET         .setOnClickListener(this);
		mCurrencyET     .setOnClickListener(this);
		mDivisionET     .setOnClickListener(this);
		mDepartmentET   .setOnClickListener(this);
		mUnitET         .setOnClickListener(this);
		mPlantET        .setOnClickListener(this);
		mWarehouseET    .setOnClickListener(this);
		mAddBtn         .setOnClickListener(this);
		mSaveBtn        .setOnClickListener(this);
        mCancelBtn      .setOnClickListener(this);
        mCreateBtn      .setOnClickListener(this);
        mUnitPriceET    .setOnClickListener(this);
		mQuantityET     .setOnEditorActionListener(this);
		mQuantityET     .addTextChangedListener(this);
	}


	@Override
	protected ViewGroup setWrapper() {
		super.setWrapper();
		getLayoutInflater().inflate(R.layout.sales_new_order, wrapper, true);
		return wrapper;
	}

	protected static View createTabView(final Context context, final String text) {

		View view = LayoutInflater.from(context).inflate(R.layout.tab_bg, null);
		TextView tv = (TextView) view.findViewById(R.id.tabsText);
		tv.setText(text);
		return view;
	}

//	protected Customer getCustomerFromIntent() {
//		mCustomerId = this.getIntent().getIntExtra(EXTRA_CUSTOMER_ID, 0);
//        Customer customer = mDao.getCustomer(mCustomerId);
//		return customer;
//	}

	protected void setBackClickable() {
		LinearLayout titleLL = (LinearLayout) findViewById(R.id.grey_title_ll);
		titleLL.setOnClickListener(this);
	}

	protected void setCompanyName() {
//		mCustomer = MainCustomer.get(this).getCustomer();
		TextView tv = (TextView) findViewById(R.id.company_name);
		tv.setText(MainCustomer.get(this).getCustomer().getName());
		setBackClickable();
	};

	protected void setTabs() {
		View view;
		tabs = (TabHost) findViewById(android.R.id.tabhost);

		tabs.setup();

		TabHost.TabSpec spec = tabs.newTabSpec(TAG_1);
		view = createTabView(tabs.getContext(), getResources().getString(R.string.general));
		spec.setContent(R.id.tab1);
		spec.setIndicator(view);
		tabs.addTab(spec);

		spec = tabs.newTabSpec(TAG_2);
		view = createTabView(tabs.getContext(), getResources().getString(R.string.add_item));
		spec.setContent(R.id.tab2);
		spec.setIndicator(view);
		tabs.addTab(spec);

		spec = tabs.newTabSpec(TAG_3);
		view = createTabView(tabs.getContext(), getResources().getString(R.string.create_new));
		spec.setContent(R.id.tab3);
		spec.setIndicator(view);
		tabs.addTab(spec);

        tabs.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if (!mEditOn) {
                    clearAddItemEditTexts();
                }
                mEditOn = false;
                        
	            if (Debug.MODE) { Log.d(LOG_TAG, "TabId: "+ tabId);}
	            if (tabId == TAG_2 || tabId == TAG_3) {
		            saveData();
	            }
            }
        });
	}

	protected void clearAddItemEditTexts() {
		mItemCodeET.setText("");
		mUnitET.setText("");
		mQuantityET.setText("");
		mUnitPriceET.setText("");
		mItemSubtotalPriceET.setText("");
	}

	public void dateDialog() {
		OnDateSetListener listener = new OnDateSetListener() {
			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				mDay = dayOfMonth;
				mMonth = monthOfYear;
				mYear = year;
				setDate();
			}
		};
		DatePickerDialog dpDialog = new DatePickerDialog(this, listener, mYear,
				mMonth, mDay);
		dpDialog.show();
	}

	protected void setDate() {
		String month;
		if ( mMonth < 9 ) month = "0" + ( mMonth + 1 );
		else month = "" + ( mMonth + 1 );
		mDateET.setText( mDay + "." + month + "." + mYear );
	};
	
	protected boolean isAllEntered(ArrayList<EditText> edits) {

		for (EditText edit: edits) {
			if (edit.getText().toString().trim().length() == 0) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void onClick( View v ) {

		switch ( v.getId() ) {
			case R.id.grey_title_ll:
            case R.id.cancel_button: {
                setAskDialog();
                break;
            }

			case R.id.save_button: {
				if (Debug.MODE) {Log.d(LOG_TAG, "<---- Save Button onClick() ---->");}

				saveData();
				tabs.setCurrentTabByTag(TAG_2);
				if (Debug.MODE) {
					Log.d(LOG_TAG, "Current tab: " +tabs.getCurrentTabTag());
				}
				Toast.makeText(this, getString(R.string.data_saved), Toast.LENGTH_SHORT).show();

				break;
			}

			case R.id.item_code_sno: {

				if (Debug.MODE) { Log.d(LOG_TAG, "<----- Select Services Dialog() ----->"); }

				DialogSelectItem dsi = new DialogSelectItem();
				dsi.show(getFragmentManager(), DSI);
				break;
			}

            case R.id.unit: {

                if (Debug.MODE) {Log.d(LOG_TAG, "Push to UnitEditText!!!");}

                DialogSelectUnitDetail dsdu = new DialogSelectUnitDetail();
                dsdu.show(getFragmentManager(), DSDU);

	            if (Debug.MODE) {Log.d(LOG_TAG, "UnitDetailId: " + mUnitDetailId);}

                break;
            }

			case R.id.add_button: {
				if (isAllEntered(mEdits)) {
					addDataItemToOrder();
                    clearTempData();
                    setSubtotaVatGTotal();
				}
				else {
					Toast.makeText(getApplicationContext(), "You must fill all fields!", Toast.LENGTH_SHORT).show();
				}
				break;
			}
			case R.id.date_et:{
				dateDialog();
				break;
			}

			case R.id.currency: {
				if (mItems == null || mItems.size() == 0){

					mDSC = new DialogSelectCurrency();
					mDSC.show(mFm, DSC);

					if (Debug.MODE) {
						Log.d(LOG_TAG, "mDSC: " + mDSC);
					}


				} else {
					Toast.makeText(this,getString(R.string.not_change_currency),Toast.LENGTH_SHORT).show();
					if(Debug.MODE) {
						Log.d(LOG_TAG, "mCurrencyId: " + mCurrencyId);
						Log.d(LOG_TAG, "mCurrencyStr: " + mCurrencyStr);
					}
				}
				break;
			}
			case R.id.division: {
                DialogSelectDivision dsDiv = new DialogSelectDivision();
                dsDiv.show(getFragmentManager(), DSDIV);
				break;
			}
			case R.id.department: {
                DialogSelectDepartment dsDep = new DialogSelectDepartment();
                dsDep.show(getFragmentManager(), DSDEP);
				break;
			}
			case R.id.plant: {
                DialogSelectPlant dsPl = new DialogSelectPlant();
                dsPl.show(getFragmentManager(), DSPL);
				break;
			}
			case R.id.warehouse: {
                DialogSelectWarehouse dsWh = new DialogSelectWarehouse();
                dsWh.show(getFragmentManager(), DSWH);
				break;
			}
            case R.id.create_button:{

                if (Debug.MODE) Log.d(LOG_TAG, "mItems.size(): " + mItems.size());

                if (mItems != null && mItems.size() > 0) {

                    createObject();

                } else {
                    Toast.makeText(this, "You must add one or more items to order!", Toast.LENGTH_SHORT).show();
                }

                break;
            }

			default: break;
		}
	}

	protected void saveData() {
		mSlipNumber = mSlipNumberET.getText().toString();
		mSpecialCode = mSpecialCodeET.getText().toString().trim();

		if(Debug.MODE) {Log.d(LOG_TAG, "mSlipNumber: " + mSlipNumber + "\n" + "mSpecialCode: " + mSpecialCode);}
	}


	@Deprecated
	@Override
	public Item onRetainNonConfigurationInstance() {
		return mItem;
	}

    protected void deleteDataItemFromOrder(int position) {
        if (mItems != null) {
            mItems.remove(position);
        }
    }

    protected void clearTempData() {
        mTypeId = 0;
        mItemId = 0;
        mProductPrices = null;
        mUnitPrice = 0;
        mUnitDetails = null;
    }

	protected void addDataItemToOrder() {

		if (ItemList.get(getBaseContext()).hasItem(mItem)){
			if (Debug.VERBOSE) {
				Log.v(LOG_TAG, "ItemList has Item)");
			}
			mItem.setUnitDetailId(mUnitDetailId);
            mItem.setUnitId(mUnitId);
			mItem.setQuantity(Double.parseDouble(mQuantityET.getText().toString()));
			mItem.setItemSubtotalPrice(mItemSubtotalPrice);
			mItem.setName(mItemName);
			mItem.setDescription(mItemDescription);
			mItem.setUnitDetailName(mUnitET.getText().toString());
			mItem.setCode(mItemCode);
			mItem.setVat(mVAT);
            mItem.setUnitPrice(mUnitPrice);
			mItem.setCurrency(mCurrencyStr);
			mItem.setUnitDetailName(mUnitDetailName);

		} else {
			mItem.setItemId(mItemId);
			mItem.setType(mTypeId);
			mItem.setUnitDetailId(mUnitDetailId);
			mItem.setQuantity(Double.parseDouble(mQuantityET.getText().toString()));
			mItem.setItemSubtotalPrice(mItemSubtotalPrice);
			mItem.setName(mItemName);
			mItem.setDescription(mItemDescription);
			mItem.setUnitDetailName(mUnitET.getText().toString());
			mItem.setCode(mItemCode);
            mItem.setUnitPrice(mUnitPrice);
            mItem.setVat(mVAT);
			mItem.setCurrency(mCurrencyStr);
			mItem.setUnitDetailName(mUnitDetailName);
            mItem.setUnitId(mUnitId);
		}


		ItemList.get(getBaseContext()).addItem(mItem);

		Collections.sort(mItems);
		
		if (Debug.MODE) {
			Log.d(LOG_TAG, "<--mItem: " + mItem + "-->");
			Log.d(LOG_TAG, "<--mItems.size: " + mItems.size() + "-->");
		}
		
		mAdapter.notifyDataSetChanged();
		
		Toast.makeText(this, "The item was added!", Toast.LENGTH_SHORT).show();
		
		for(EditText edit: mEdits) {
			edit.setText("");
		}
	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) { 
		mAddBtn.setEnabled(isAllEntered(mEdits));
		if (Debug.MODE) {
			Log.d(LOG_TAG, "< ----- OnFocusChange() --> " + isAllEntered(mEdits) + " ----- >");
		}
	}

	@Override
	protected void onRestart() {
//		mBundle = getIntent().getExtras();
//		if (Debug.MODE) {
//			Log.d(LOG_TAG, "< --- onRestart --- >" + mBundle.getString("item_code"));
//		}
		super.onRestart();

	}

	@Override
	public void afterTextChanged(Editable s) {
		// TODO Enable AddButton in current enabling state
		mAddBtn.setEnabled(isAllEntered(mEdits));
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {
		// TODO Enable AddButton in current enabling state
		mAddBtn.setEnabled(isAllEntered(mEdits));
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		mAddBtn.setEnabled(isAllEntered(mEdits));

		if (mQuantityET.getText().toString().trim().length() != 0) {
			mItemSubtotalPrice =countSubtotalPrice(
                                    Double.parseDouble(mQuantityET.getText().toString()),
									getConvertedUnitPrice());

            if (Debug.MODE) {
                Log.d(LOG_TAG, "mUnitPrice: " + mUnitPrice);
                Log.d(LOG_TAG, "mCurrencyId: " + mCurrencyId);
            }

            mItemSubtotalPriceET.setText(mItemSubtotalPrice + " " + mCurrencyStr);
		}
	}
	
	protected double countSubtotalPrice(double quantity, double unitPrice) {
		return quantity*unitPrice;
	}

	@Override
	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		boolean handled = false;
		switch (actionId) {
			case EditorInfo.IME_ACTION_DONE: {
				if (Debug.MODE) {
					Log.d(LOG_TAG, "< ---- onEditorAction() -->  EditorInfo.IME_ACTION_DONE" + isAllEntered(mEdits) + "---- >");
				}
			}
			case EditorInfo.IME_ACTION_NEXT: {
				if (Debug.MODE) {
					Log.d(LOG_TAG, "< ---- onEditorAction() -->  EditorInfo.IME_ACTION_NEXT" + isAllEntered(mEdits) + "---- >");
				}
				mAddBtn.setEnabled(isAllEntered(mEdits));
				handled = true;
				break;
			}
			default: break;
		}
		return handled;
	}


    @Override
    protected void onResume() {
        super.onResume();
        if (Debug.MODE) Log.d(LOG_TAG, "<--- onResume() --->");
	    if (!mReady) {
		    try {
		        mDao.openToWrite();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
	    } else {
		    Intent i = new Intent(this, SynchronizationActivity.class);
		    startActivity(i);
		    finish();
	    }
    }

    @Override
    protected void onPause() {
        super.onPause();
	    if(Debug.MODE) {
		    Log.d(LOG_TAG, "<-- onPause() -->");
	    }
        mDao.close();
    }

	@Override
	protected void onStop() {
		super.onStop();
		if(Debug.MODE) {
			Log.d(LOG_TAG, "<-- onStop() -->");
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(Debug.MODE) {
			Log.d(LOG_TAG, "<-- onDestroy() -->");
		}
		if(Debug.MODE) Debug.unlockScreenOrientation(this);
		mQuantityET.removeTextChangedListener(this);
	}

	@Override
	public void onBackPressed() {
		setAskDialog();
	}
	protected void setAskDialog() {
		DialogExit de = new DialogExit();
		de.show(getFragmentManager(), "de");
	}



	protected void setSubtotaVatGTotal() {
		mSubtotal       = 0;
		mGTotal         = 0;
		double    vat;
		for (Item item: mItems) {
			vat = item.getVat();

			if(Debug.MODE) { Log.d(LOG_TAG, "<-- setSubtotalVatGTotal(); --> \n vat: " + vat); };

			mGTotal += item.getItemSubtotalPrice() + (item.getItemSubtotalPrice() * (vat/100));

			mSubtotal += item.getItemSubtotalPrice();
		}
		mVAT = mGTotal - mSubtotal;

		mSubtotalTV .setText("" + mSubtotal + " " +mCurrencyStr);
		mVATTV      .setText("" + mVAT + " " + mCurrencyStr);
		mGTotalTV   .setText("" + mGTotal + " " + mCurrencyStr);
	}

	/**
	 * Create of order!
	 */

    protected abstract void createObject();

	/**
	 * getSlipNumber - generator of slip number
	 * @param amountOfNumber  - amount of generate number
	 */

	public static String genSlipNumber(int amountOfNumber) {
		Random random       = new Random();
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < amountOfNumber; i++) {
			str.append(""+random.nextInt(amountOfNumber));
		}
		return str.toString();
	};

	protected double getConvertedUnitPrice() {
		return (mUnitPrice / mUnitDetail.getFrom()) * mUnitDetail.getTo();
	}
}
