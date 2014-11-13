package com.sezyakot.sailor;

import android.app.ActionBar;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.sezyakot.sailor.adapters.MainMenuArrayAdapter;
import com.sezyakot.sailor.system.Net;

public class MainMenu extends ListActivity {

	private ProgressDialog dialog;
	private String[] itemsOfMainMenu;
	public static final String LOG_TAG = "MainMenu";
	Context context;
	

	private void setupActionBar() {
		ActionBar ab = getActionBar();
		ab.setDisplayShowCustomEnabled(true);
		ab.setDisplayShowTitleEnabled(false);
		ab.setDisplayShowHomeEnabled(false);
        //noinspection ResourceType
        ab.setCustomView(R.layout.action_bar_title);
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_menu);
		setupActionBar();
		context = this.getApplication();

		// Get string array from string.xml
		itemsOfMainMenu = getResources().getStringArray(R.array.main_menu_array);
		
		// Create adapter and fill menu
		MainMenuArrayAdapter adapter = new MainMenuArrayAdapter(this, itemsOfMainMenu);
		setListAdapter(adapter);

	}


    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Fill menu
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_context_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
			case R.id.about :
				return true;
			case R.id.exit :
				logout();

				return true;
			default :
				return super.onOptionsItemSelected(item);
		}
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		switch (position) {
            // Sales
			case 0 : {
				Intent i = new Intent(this, SalesActivity.class);
				runActivity(i, position);
				break;
			}
            // Financial
			case 1 : {
				Intent i = new Intent(this, FinancialsActivity.class);
				runActivity(i, position);
				break;
			}
            // CRM
			case 2 : {
				Intent i = new Intent(this, CrmActivity.class);
				runActivity(i, position);
				break;
			}
            //Reports
			case 3 : {
				Intent i = new Intent(this, ReportsActivity.class);
				runActivity(i, position);
				break;
			}
            //GPS
			case 4 : {
//				Toast.makeText(this, getString(R.string.buy_module), Toast.LENGTH_SHORT).show();
				Intent i = new Intent(this, MapPane.class);
				runActivity(i, position);
				break;
			}
            // Calendar
			case 5 : {
				Toast.makeText(this, getString(R.string.buy_module), Toast.LENGTH_SHORT).show();
				break;
			}
            // Others
			case 6 : {
				break;
			}
            // Communications
			case 7 : {
				Toast.makeText(this, getString(R.string.buy_module), Toast.LENGTH_SHORT).show();
				break;
			}
            // Synchronizations
			case 8 : {
				Intent i = new Intent(this, SynchronizationActivity.class);
				runActivity(i, position);
				break;
			}
            // About
			case 9 : {
				Intent i = new Intent(this, AboutActivity.class);
				runActivity(i, position);
				break;
			}

			default : {
				break;
			}
		}
	}

	void runActivity(Intent i,int position) {
		i.putExtra(SalesActivity.EXTRA_TITLE, itemsOfMainMenu[position]);
		startActivity(i);
	};

	void logout() {
		PostFetcher fetcher = new PostFetcher();
		fetcher.execute();
	}

	@Override
	protected void onPause() {
		if (dialog != null) dialog.dismiss();
		super.onPause();
	}

	private class PostFetcher extends AsyncTask<Void, Void, Boolean> {

		@Override
		protected Boolean doInBackground(Void... params) {
			return Net.logout(context);

		}

		@Override
		protected void onPreExecute() {
			dialog = new ProgressDialog(MainMenu.this);
			dialog.setMessage(getString(R.string.connecting));
			dialog.setIndeterminate(true);
			dialog.setCancelable(true);
			dialog.show();
			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(Boolean result) {
			if (dialog != null)	dialog.dismiss();
			super.onPostExecute(result);
			if (result) {
				Toast.makeText(context, getString(R.string.logout_info), Toast.LENGTH_SHORT).show();
				Intent i = new Intent(context, AuthorizationActivity.class);
				startActivity(i);
				finish();
			} else {
				Toast.makeText(context, "You're not logout, some problem with server!", Toast.LENGTH_SHORT).show();
			}
		}
	}

}
