package com.sezyakot.android.sailorapp.sailor;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import com.sezyakot.android.sailorapp.sailor.system.Debug;
public class CashCreateNew extends DefaultFinancialsCreate implements TaskFragment.TaskCallbacks{
    public static final String FRAG_TAG = "task_fragment";
    private static final String LOG_TAG = "CashCreateNew";
	@Override
	protected void setTitle() {
		mTitleName.setText(getString(R.string.new_cash_payment));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		FragmentManager fm = getFragmentManager();
		Fragment fragment = fm.findFragmentById(R.id.fragment_container);

		if (fragment == null) {
			fragment = new NCPFragment();
			fm.beginTransaction()
              .add(R.id.fragment_container, fragment)
              .commit();
		}
	}

    @Override
    public void onPreExecute() {
        if (Debug.MODE) {
            Log.d(LOG_TAG, "CashCreateNew: onPreExecute()");
        }
    }

    @Override
    public void onProgressUpdate(String msg) {
        Log.v(LOG_TAG, "Message: " + msg);
    }

    @Override
    public void onCancelled() {
        if (Debug.MODE) {
            Log.d(LOG_TAG, "CashCreateNew: onCancelled()");
        }
    }

    @Override
    public void onPostExecute() {
        if (Debug.MODE) {
            Log.d(LOG_TAG, "CashCreateNew: onPostExecute()");
        }
    }
}
