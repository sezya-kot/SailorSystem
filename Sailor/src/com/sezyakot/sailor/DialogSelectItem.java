package com.sezyakot.sailor;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import com.sezyakot.sailor.adapters.ProductAdapter;
import com.sezyakot.sailor.adapters.ServiceAdapter;
import com.sezyakot.sailor.db.DAO;
import com.sezyakot.sailor.db.DBHelper;
import com.sezyakot.sailor.model.Item;
import com.sezyakot.sailor.model.ItemList;
import com.sezyakot.sailor.model.Product;
import com.sezyakot.sailor.model.Service;
import com.sezyakot.sailor.system.Debug;

import java.sql.SQLException;
import java.util.ArrayList;


public class DialogSelectItem extends DialogFragment implements OnItemClickListener ,OnClickListener, OnCheckedChangeListener, OnEditorActionListener {

    public static final int PRODUCT_TYPE = 1;
    public static final int SERVICE_TYPE = 2;

    public static final int LIST_BY_CODE = 0;
    public static final int LIST_BY_DESCRIPTION = 1;

	public final static String LOG_TAG = "DialogSelectItem";
	private static final String KEY_LIST = "key_list";
	private static final String KEY_TYPE = "key_type";
	private View view;
    private ArrayList<Product> mProducts;
    private ArrayList<Service> mServices;
	private ProductAdapter mProductAdapter;
    private ServiceAdapter mServiceAdapter;
	private ListView list;
	private DefaultSalesNew a;
	private ImageButton searchBtn;
	private EditText searchEt;
	private RadioGroup typeRG;
	private RadioGroup sortRG;
	private int mType;
	private int mList;

    private DAO mDao;
    private Product mProduct;
    private Service mService;

    @Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		a = (DefaultSalesNew) getActivity();
        mDao = new DAO(a);
	    restore(savedInstanceState);
        init();
        if (Debug.MODE) { Log.d(LOG_TAG, "<---- onCreateDialog() ---->");}
		
		Dialog dialog = super.onCreateDialog(savedInstanceState);
		// request a window without the title
		dialog.setCancelable(false);
		dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		return dialog;
	}

	private void restore(Bundle savedInstanceState) {
		if (savedInstanceState == null) {
			mType = PRODUCT_TYPE;
			mList = LIST_BY_CODE;
		} else {
			mType = savedInstanceState.getInt(KEY_TYPE);
			mList = savedInstanceState.getInt(KEY_LIST);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
		
		if (Debug.MODE) { Log.d(LOG_TAG, "<---- onCreateView() ---->"); }

		view        = inflater.inflate(R.layout.dialog_select_item, container, false);
        searchBtn   = (ImageButton) view.findViewById(R.id.find_search);
		searchEt    = (EditText) view.findViewById(R.id.search_item_code_description);
		typeRG      = (RadioGroup) view.findViewById(R.id.choose_type_item);
		sortRG      = (RadioGroup) view.findViewById(R.id.choose_list_by_item);
		list        = (ListView) view.findViewById(R.id.item_lv);
		
		if (Debug.MODE) { Log.d(LOG_TAG, "<--mType: " + mType + "-->"); }
		if (Debug.MODE) { Log.d(LOG_TAG, "<--mList: " + mList + "-->"); }

		typeRG      .setOnCheckedChangeListener(this);
		sortRG      .setOnCheckedChangeListener(this);
        searchBtn   .setOnClickListener(this);
        searchEt    .setOnEditorActionListener(this);

        setDefaultListAdapter();


		list.setOnItemClickListener(this);
		return view;
	}



	private void setAddButtonEnable() {
		a.mAddBtn.setEnabled(a.isAllEntered(a.mEdits));
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.find_search: {
				setSearcher();
				break;
			}
		}
	}

	private void setSearcher() {
		String cv;
		cv = searchEt.getText().toString();
        if (mType == PRODUCT_TYPE) {
            mProductAdapter.getFilter().filter(cv);
        } else
        if (mType == SERVICE_TYPE) {
            mServiceAdapter.getFilter().filter(cv);
        }
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
			case -1: 
				mType = 0;
				Toast.makeText(a, "No choice!", Toast.LENGTH_SHORT).show();
				break;
			case R.id.material:
                if (Debug.MODE) { Log.d(LOG_TAG, "<== Material ==>"); }
				mType = PRODUCT_TYPE;
//                searchEt.setText("");
                if (mList == LIST_BY_DESCRIPTION) {
                    getProducts(DBHelper.COL_NAME);
                } else
                if (mList == LIST_BY_CODE) {
                    getProducts(DBHelper.COL_CODE);
                }
                setProductAdapter();
				break;
			case R.id.service:
                if (Debug.MODE) { Log.d(LOG_TAG, "<== Services ==>"); }
				mType = SERVICE_TYPE;
//                searchEt.setText("");
                if (mList == LIST_BY_DESCRIPTION) {
                    getServices(DBHelper.COL_NAME);
                } else
                if (mList == LIST_BY_CODE) {
                    getServices(DBHelper.COL_CODE);
                }
                setServiceAdapter();
				break;
			case R.id.list_by_code:
                if (Debug.MODE) { Log.d(LOG_TAG, "<== List by code ==>"); }
				mList = LIST_BY_CODE;
                if (mType == PRODUCT_TYPE) {
                    getProducts(DBHelper.COL_CODE);
                    setProductAdapter();
                } else
                if (mType == SERVICE_TYPE) {
                    getServices(DBHelper.COL_CODE);
                    setServiceAdapter();
                }
				break;
			case R.id.list_by_description:
                if (Debug.MODE) { Log.d(LOG_TAG, "<== List by description ==>"); }
				mList = LIST_BY_DESCRIPTION;
                if (mType == PRODUCT_TYPE) {
                    getProducts(DBHelper.COL_NAME);
                    setProductAdapter();
                } else
                if (mType == SERVICE_TYPE) {
                    getServices(DBHelper.COL_NAME);
                    setServiceAdapter();
                }
				break;
			default:
				mType = 0;
				break;
		}
	}

    private void setServiceAdapter() {
        mServiceAdapter = new ServiceAdapter(getActivity(), mServices);
        list.setAdapter(mServiceAdapter);
    }

    private void setProductAdapter() {
        mProductAdapter = new ProductAdapter(getActivity(), mProducts);
        list.setAdapter(mProductAdapter);
    }

    private void setDefaultListAdapter() {
//        if (Debug.MODE) { Log.d(LOG_TAG, "mProducts: " + mProducts ); }
//        if (Debug.MODE) { Log.d(LOG_TAG, "mServices: " + mServices ); }
	    if (mType == PRODUCT_TYPE) setProductAdapter();
	    else if (mType == SERVICE_TYPE) setServiceAdapter();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(KEY_LIST, mList);
		outState.putInt(KEY_TYPE, mType);
	}

	private void init() {
        try {
            mDao.openToRead();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		if (mList == LIST_BY_CODE) {
			getServices(DBHelper.COL_CODE);
			getProducts(DBHelper.COL_CODE);
		} else if (mList == LIST_BY_DESCRIPTION) {
			getServices(DBHelper.COL_NAME);
			getProducts(DBHelper.COL_NAME);
		}
    }

    private void getServices(String orderBy) {
        if (Debug.MODE) Log.d(LOG_TAG, "<- getServices() => mCurrencyId:  ->" + a.mCurrencyId);
        mServices = mDao.getServices(orderBy, a.mCurrencyId);
    }

    private void getProducts(String orderBy) {
        if (Debug.MODE) Log.d(LOG_TAG, "<- getProducts() => mCurrencyId:  ->" + a.mCurrencyId);
        mProducts = mDao.getProducts(orderBy, a.mCurrencyId);
    }



    @Override
	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		boolean handled = false;
		if (actionId == EditorInfo.IME_ACTION_SEARCH) {
			setSearcher();
			handled = true;
		}
		return handled;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// what item was selected

		a.mItem = null;

		if (mType == PRODUCT_TYPE) {
			mProduct = (Product) list.getItemAtPosition(position);
			a.mItemId = mProduct.getServerId();
			a.mTypeId = PRODUCT_TYPE;
			a.mUnitId = mProduct.getUnit();
			a.mProductPrices = mDao.getProductPrices(null, "" + DBHelper.COL_PRODUCT_ID + " = " + a.mItemId + " AND "+ DBHelper.COL_CURRENCY_ID + " = " + a.mCurrencyId);
			a.mUnitPrice = a.mProductPrices.get(0).getPrice();
			a.mItemName = mProduct.getName();
			a.mItemDescription = mProduct.getDescription();
			a.mItemCode = mProduct.getCode();
			a.mVAT = mProduct.getVat();
		} else
		if (mType == SERVICE_TYPE) {
			mService = (Service) list.getItemAtPosition(position);
			a.mItemId = mService.getServerId();
			a.mTypeId = SERVICE_TYPE;
			a.mUnitId = mService.getUnit();
			a.mServicePrices = mDao.getServicePrices(null, "" + DBHelper.COL_SERVICE_ID + " = " + a.mItemId + " AND "+ DBHelper.COL_CURRENCY_ID + " = " + a.mCurrencyId);
			a.mUnitPrice = a.mServicePrices.get(0).getPrice();
			a.mItemName = mService.getName();
			a.mItemDescription = mService.getDescription();
			a.mItemCode = mService.getCode();
			a.mVAT = mService.getVat();
		}

		a.mItemCodeET.setText(a.mItemCode + " - " + a.mItemName + " - " + a.mItemDescription);
		a.mUnitPriceET.setText(Double.toString(a.mUnitPrice) + " " + a.mCurrencyStr);
		a.mUnitDetails = mDao.getUnitDetails(null, "" + DBHelper.COL_UNIT_ID + " = " + a.mUnitId);
		a.mUnitDetailId = a.mUnitDetails.get(0).getServerId();
		a.mUnitDetail   = mDao.getUnitDetail(a.mUnitDetailId);
		a.mUnitDetailName = a.mUnitDetails.get(0).getName();
		a.mUnitET.setText(a.mUnitDetailName);

		if (ItemList.get(a).hasItem(a.mItemId, a.mTypeId)) {

			a.mItem = ItemList.get(a).getItem(a.mItemId, a.mTypeId);
			a.mQuantityET.setText(""+a.mItem.getQuantity());
			a.mUnitDetailId = a.mItem.getUnitDetailId();
			a.mUnitDetailName = a.mItem.getUnitDetailName();
			a.mUnitET.setText(a.mUnitDetailName);

			if (Debug.MODE) {
				Log.d(LOG_TAG, "ItemList has item!");
			}
			Toast.makeText(a, "This item is present in item list, now U change it!",Toast.LENGTH_SHORT).show();

		} else {
			a.mItem = new Item();
		}

		dismiss();
	}

	@Override
	public void onDismiss(DialogInterface dialog) {
		mDao.close();
		super.onDismiss(dialog);
	}

	@Override
	public void onCancel(DialogInterface dialog) {
		super.onCancel(dialog);
		setAddButtonEnable();
	}

	@Override
	public void onDetach() {
		super.onDetach();
		if (Debug.MODE) {Log.d(LOG_TAG, "< --- onDetach() --- >");}
	}

	@Override
	public void onStop() {
		super.onStop();
		if (Debug.MODE) {Log.d(LOG_TAG, "< --- onStop() --- >");}
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		if (Debug.MODE) {Log.d(LOG_TAG, "< --- onDestroyView() --- >");}
	}

	@Override
	public void onStart() {
		super.onStart();
		if (Debug.MODE) {Log.d(LOG_TAG, "< --- onStart() --- >");}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (Debug.MODE) {Log.d(LOG_TAG, "< --- onCreate() --- >");}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if (Debug.MODE) {Log.d(LOG_TAG, "< --- onAttach() --- >");}
	}

	@Override
	public void dismiss() {
		super.dismiss();
		if (Debug.MODE) {Log.d(LOG_TAG, "< --- dismiss() --- >");}
		setAddButtonEnable();
	}
}
