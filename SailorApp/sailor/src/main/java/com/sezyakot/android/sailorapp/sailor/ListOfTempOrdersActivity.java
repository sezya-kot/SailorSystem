package com.sezyakot.android.sailorapp.sailor;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Contacts.People;
import android.widget.SimpleCursorAdapter;

/**
 * Created by Android on 04.09.2014.
 */
public class ListOfTempOrdersActivity extends ListActivity {
	@Override
	public void onCreate(Bundle savedInstance) {
		super.onCreate(savedInstance);
		setContentView(R.layout.list_example);

		// SOME CODE

		Cursor cursor = getContentResolver().query(People.CONTENT_URI, new String[] {People._ID, People.NAME, People.NUMBER}, null, null, null);
		startManagingCursor(cursor);

		// THE DESIRED COLUMNS TO BE BOUND
		String[] columns = new String[] { People.NAME, People.NUMBER};
		// THE XML DEFINED VIEWS WHICH THE DATA WILL BE BOUND TO
		int[] to = new int[] { R.id.name_entry, R.id.number_entry };

		// CREATE THE ADAPTER USING THE CURSOR POINTING TO THE DESIRED DATA AS WELL AS THE LAYOUT INFORMATION
		SimpleCursorAdapter mAdapter = new SimpleCursorAdapter(this, R.layout.list_example_entry, cursor, columns, to);

		// SET THIS ADAPTER AS YOUR LISTACTIVITY'S ADAPTER
		this.setListAdapter(mAdapter);
	}

}
