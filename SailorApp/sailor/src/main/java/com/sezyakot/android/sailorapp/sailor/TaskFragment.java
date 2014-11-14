package com.sezyakot.android.sailorapp.sailor;
import android.app.Activity;
import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sezyakot.android.sailorapp.sailor.model.Payment;
import com.sezyakot.android.sailorapp.sailor.model.RequestPayment;
import com.sezyakot.android.sailorapp.sailor.system.Net;
import org.apache.http.HttpResponse;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This Fragment manages a single background task and retains
 * itself across configuration changes.
 */
public class TaskFragment extends Fragment{
	/**
	 * Callback interface through which the fragment will report the
	 * task's progress and results back to the Activity.
	 */
    Payment mPayment;

	static interface TaskCallbacks {
		void onPreExecute();
		void onProgressUpdate(String msg);
		void onCancelled();
		void onPostExecute();
	}

	private TaskCallbacks mCallbacks;
    private TaskCallbacks mFragmentCallbacks;
	private DummyTask mTask;
	/**
	 * Hold a reference to the parent Activity so we can report the
	 * task's current progress and results. The Android framework
	 * will pass us a reference to the newly created Activity after
	 * each configuration change.
	 */
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mCallbacks = (TaskCallbacks) activity;
        mFragmentCallbacks = (TaskCallbacks) activity.getFragmentManager().findFragmentByTag(NCPFragment.FDCCP);
	}

	/**
	 * This method will only be called once when the retained
	 * Fragment is first created.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Retain this fragment across configuration changes.
		setRetainInstance(true);

		// Create and execute the background task.
		mTask = new DummyTask();
		mTask.execute((Payment) getArguments().getParcelable(Payment.PAYMENT));
	}

    public void onCancel() {
       if (mTask != null){
           mTask.cancel(true);
       }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
	 * Set the callback to null so we don't accidentally leak the
	 * Activity instance.
	 */
	@Override
	public void onDetach() {
		super.onDetach();
		mCallbacks = null;
        mFragmentCallbacks = null;
	}

	/**
	 * A dummy task that performs some (dumb) background work and
	 * proxies progress updates and results back to the Activity.
	 *
	 * Note that we need to check if the callbacks are null in each
	 * method in case they are invoked after the Activity's and
	 * Fragment's onDestroy() method have been called.
	 */
	private class DummyTask extends AsyncTask<Payment, String, Void> {

		@Override
		protected void onPreExecute() {
			if (mCallbacks != null && mFragmentCallbacks !=null) {
				mCallbacks.onPreExecute();
                mFragmentCallbacks.onPreExecute();
			}
		}

		/**
		 * Note that we do NOT call the callback object's methods
		 * directly from the background thread, as this could result
		 * in a race condition.
		 */
		@Override
		protected Void doInBackground(Payment... pPayments) {
            Net lNet = new Net();
            publishProgress("Packing data!");
            Gson gson = new GsonBuilder()
                    .excludeFieldsWithoutExposeAnnotation()
                    .create();
            RequestPayment lRequestPayment = new RequestPayment(getActivity());
            lRequestPayment.setPayments(Arrays.asList(pPayments));
            String dataGson = gson.toJson(lRequestPayment);
            publishProgress("Connecting...");
            HttpResponse response = lNet.paymentSendToServer(dataGson, Net.SERVER_CASH_PAYMENT_CREATE);

            if (response.getStatusLine().getStatusCode() == 200) {
                publishProgress("All ok!");

            } else {
                publishProgress("Server Error!");
            }
			return null;
		}

		@Override
		protected void onProgressUpdate(String... msg) {
			if (mCallbacks != null && mFragmentCallbacks !=null) {
				mCallbacks.onProgressUpdate(msg[0]);
                mFragmentCallbacks.onProgressUpdate(msg[0]);
			}
		}

		@Override
		protected void onCancelled() {
			if (mCallbacks != null && mFragmentCallbacks !=null) {
				mCallbacks.onCancelled();
                mFragmentCallbacks.onCancelled();
			}
		}

		@Override
		protected void onPostExecute(Void ignore) {
			if (mCallbacks != null && mFragmentCallbacks !=null) {
				mCallbacks.onPostExecute();
                mFragmentCallbacks.onPostExecute();
			}
		}
	}


}