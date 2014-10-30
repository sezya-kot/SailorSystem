/*
 * Copyright (c) 2014.
 */

package com.sezyakot.sailor;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import android.widget.Toast;
import com.sezyakot.sailor.model.Currency;
import com.sezyakot.sailor.system.Debug;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Android on 19.09.2014.
 */
public class NCPFragment extends Fragment implements View.OnClickListener, FDEValue.OnButtonOkListener{

	protected static final String LOG_TAG         = "NCPFragment";
    protected static final String SLIP_NUMBER     = "slip_number";
    protected static final String DATE            = "date";
    protected static final String SPECIAL_CODE    = "special_code";
    protected static final String CURRENCY_STR    = "currency_str";
	protected static final String CURRENCY_ID     = "currency_id";
	protected static final String AMOUNT          = "amount";
    protected static final String DESCRIPTION     = "description";
    protected static final int    SN_AMOUNT       = 10;

	protected static final String FDSC            = "financials_dialog_select_currency";
    protected static final String FDCCP           = "financials_dialog_create_cash_payment" ;
    protected static final String FDEA            = "financials_dialog_enter_amount";
    protected static final String FDESC           = "financials_dialog_enter_special_code";
    protected static final String FDED            = "financials_dialog_enter_description";

    protected EditText        mSlipNumberET;
	protected EditText        mDateET;
	protected EditText        mSpecialCodeET;
	protected EditText        mCurrencyET;
	protected EditText        mAmountET;
	protected EditText        mDescriptionET;

    protected ArrayList<Currency> mCurrencies;

    protected String        mSlipNumber;
    protected Calendar      mDate;
    protected String        mDateStr;
    protected String        mSpecialCode = "";
    protected String        mCurrencyStr;
    protected int           mCurrencyId;
    public    double        mAmount = 0;
    protected String        mDescription;

	private Button          mCancelBtn;
    private Button          mCreateButton;
	protected DefaultFinancialsCreate   mActivity;

    protected TaskFragment mTaskFragment;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = setInflate(inflater, container);
		setUI(v);
		setListeners();
		init(savedInstanceState);
		return v;
	}

    protected View setInflate(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_ncp, container, false);
    }

    protected void init(Bundle b) {
        mCurrencies     = mActivity.mDao.getCurrencies(null);
		if (b == null) {
            mSlipNumber     = DefaultSalesNew.genSlipNumber(SN_AMOUNT);
            mDate           = Calendar.getInstance();
            mSpecialCode    = "";
            if (mCurrencies.size() == 0) {
                mCurrencyId = 0;
                mCurrencyStr = "No currency!";
            }
            mCurrencyId     = mCurrencies.get(0).getServerId();
            mCurrencyStr    = mCurrencies.get(0).getName();
            mAmount         = 0d;
            mDescription    = "";

		} else {
            mSlipNumber     = b.getString(SLIP_NUMBER);
            mDate           = (Calendar) b.getSerializable(DATE);
            mSpecialCode    = b.getString(SPECIAL_CODE);
            mCurrencyId     = b.getInt(CURRENCY_ID);
            mCurrencyStr    = mActivity.mDao.getCurrency(mCurrencyId).getName();
            mAmount         = b.getDouble(AMOUNT);
            mDescription    = b.getString(DESCRIPTION);
		}
        mCurrencyET.setText(mCurrencyStr);
        mDateStr = setDate(mDate);
		mSlipNumberET.setText(mSlipNumber);
		mDateET.setText(mDateStr);
		mAmountET.setText(Double.toString(mAmount));
		mDescriptionET.setText(mDescription);


	}

    @Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		outState.putString(SLIP_NUMBER, mSlipNumber);
		outState.putSerializable(DATE, mDate);
		outState.putString(SPECIAL_CODE, mSpecialCode);
		outState.putString(CURRENCY_STR, mCurrencyStr);
		outState.putInt   (CURRENCY_ID, mCurrencyId);
		outState.putDouble(AMOUNT, mAmount);
		outState.putString(DESCRIPTION, mDescription);
	}

	protected void setUI(View v) {
		mCancelBtn      = (Button)      v.findViewById(R.id.cancel_button);
        mCreateButton   = (Button)      v.findViewById(R.id.create_button);
		mSlipNumberET   = (EditText)    v.findViewById(R.id.slip_number);
		mDateET         = (EditText)    v.findViewById(R.id.date_et);
		mSpecialCodeET  = (EditText)    v.findViewById(R.id.special_code);
		mCurrencyET     = (EditText)    v.findViewById(R.id.currency);
		mAmountET       = (EditText)    v.findViewById(R.id.amount);
		mDescriptionET  = (EditText)    v.findViewById(R.id.description);

	}

	protected void setListeners() {

        mCreateButton.setOnClickListener(this);
		mCancelBtn.setOnClickListener(this);
		mDateET.setOnClickListener(this);
		mSpecialCodeET.setOnClickListener(this);
		mCurrencyET.setOnClickListener(this);
		mAmountET.setOnClickListener(this);
		mDescriptionET.setOnClickListener(this);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        mActivity = setActivity();
    }

    protected DefaultFinancialsCreate setActivity() {
        return  (CashCreateNew) getActivity();
    }

    @Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.cancel_button: {
				if (Debug.VERBOSE) {
					Log.v(LOG_TAG, "onCancelButton clicked!");
				}
				mActivity.setAskDialog();
				break;
			}
			case R.id.date_et: {
				if (Debug.VERBOSE) {
					Log.v(LOG_TAG, "onDateEditText clicked!");
				}
				//TODO Create date dialog!!!
				dateDialog();
				break;
			}
			case R.id.special_code: {
				if (Debug.VERBOSE) {
					Log.v(LOG_TAG, "onSpecialCodeET clicked!");
				}
                FinancialsDialogEnterSpecialCode fdesc = new FinancialsDialogEnterSpecialCode();
                Bundle b = new Bundle();
                b.putString(SPECIAL_CODE, ""+mSpecialCode);
                fdesc.setArguments(b);
                fdesc.show(getFragmentManager(), FDESC);
				//TODO Create dialog to enter special code!

				break;
			}
			case R.id.currency: {
				if (Debug.VERBOSE) {
					Log.v(LOG_TAG, "onCurrencyET clicked!");
				}
                FinancialsDialogSelectCurrency fdsc = new FinancialsDialogSelectCurrency();
                Bundle b = new Bundle();
                b.putInt(CURRENCY_ID, mCurrencyId);
                fdsc.setArguments(b);
                fdsc.show(getFragmentManager(), FDSC);
				//TODO Select dialog currency!
                break;

            }
			case R.id.amount: {
				if (Debug.VERBOSE) {
					Log.v(LOG_TAG, "onAmountET clicked!");
				}
                FinancialsDialogEnterAmount fdea = new FinancialsDialogEnterAmount();
                Bundle b = new Bundle();
                b.putString(AMOUNT, ""+mAmount);
                fdea.setArguments(b);
                fdea.show(getFragmentManager(), FDEA);
				//TODO Dialog fragment to enter amount!
				break;
			}
			case R.id.description: {
				if (Debug.VERBOSE) {
					Log.v(LOG_TAG, "onDescriptionET clicked!");
				}
                FinancialsDialogEnterDescription fded = new FinancialsDialogEnterDescription();
                Bundle b = new Bundle();
                b.putString(DESCRIPTION, mDescription);
                fded.setArguments(b);
                fded.show(getFragmentManager(), FDED);
				//TODO Dialog fragment to enter description
				break;
			}
			case R.id.create_button: {
				if (Debug.VERBOSE) {
					Log.v(LOG_TAG, "onCreateButton clicked!");
				}
                if (allEmpty()) {
                    setCreatePayment();
                    //TODO Dialog fragment to create cash payment!



                } else {
                    Toast.makeText(mActivity, getString(R.string.fill_fields), Toast.LENGTH_SHORT).show();
                }
				break;
			}
		}
	}

    protected boolean allEmpty() {
            if (    mSlipNumberET.getText().toString().trim().length() > 0 &&
                    mDateET.getText().toString().trim().length() > 0 &&
                    mSpecialCodeET.getText().toString().trim().length() > 0 &&
                    mCurrencyET.getText().toString().trim().length() > 0 &&
                    mAmountET.getText().toString().trim().length() > 0 &&
                    mDescriptionET.getText().toString().trim().length() > 0 &&
                    mSlipNumberET != null &&
                    mDateET != null&&
                    mSpecialCodeET != null &&
                    mCurrencyET != null &&
                    mAmountET != null &&
                    mDescriptionET != null
                ) {
                return true;
            } else {
                return false;
            }


    }

    protected void setCreatePayment() {
        FinancialsDialogCreateCashPayment fdccp = new FinancialsDialogCreateCashPayment();
        Bundle b = new Bundle();
        {
            b.putString(DATE, mDateStr);
            b.putString(SLIP_NUMBER, mSlipNumber);
            b.putString(SPECIAL_CODE, mSpecialCode);
            b.putString(DESCRIPTION, mDescription);
            b.putDouble(AMOUNT, mAmount);
            b.putInt(CURRENCY_ID, mCurrencyId);
            b.putString(CURRENCY_STR, mCurrencyStr);
        }
        fdccp.setArguments(b);

        fdccp.show(getFragmentManager(), FDCCP);
    }

    public void dateDialog() {

        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                mDate.set(year,monthOfYear,dayOfMonth);
                mDateStr = setDate(mDate);
                mDateET.setText(mDateStr);
            }
        };
        DatePickerDialog dpDialog = new DatePickerDialog(
                mActivity,
                listener,
                mDate.get(Calendar.YEAR),
                mDate.get(Calendar.MONTH),
                mDate.get(Calendar.DAY_OF_MONTH));
        dpDialog.show();
	}

	protected String setDate(Calendar date) {
		String month;
		if ( date.get(Calendar.MONTH) < 9 ) month = "0" + ( date.get(Calendar.MONTH) + 1 );
		else month = "" + ( date.get(Calendar.MONTH) + 1 );
		return "" + date.get(Calendar.DAY_OF_MONTH) + "." + month + "." + date.get(Calendar.YEAR);
	};

    @Override
    public void onButtonOkPressed(String element, String value) {
        switch (element) {
            case AMOUNT: {
                mAmount = Double.parseDouble(value);
                if (mAmountET != null) mAmountET.setText(""+mAmount);
                break;
            }
            case SPECIAL_CODE: {
                mSpecialCode = value;
                if (mSpecialCodeET != null) mSpecialCodeET.setText(mSpecialCode);
                break;
            }
            case DESCRIPTION: {
                mDescription = value;
                if (mDescriptionET != null) mDescriptionET.setText(mDescription);
                break;
            }
            case CURRENCY_ID: {
                mCurrencyId = Integer.parseInt(value);
                if (mCurrencyET != null) {
                    mCurrencyStr = mActivity.mDao.getCurrency(mCurrencyId).getName();
                    mCurrencyET.setText(mCurrencyStr);
                }
            }
        }

    }
}
