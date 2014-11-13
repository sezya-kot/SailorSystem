package com.sezyakot.android.sailorapp.sailor.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

public class ItemList {

	// Static member holds only one instance of the ItemList class
	private static ItemList sItemList;
	private ArrayList<Item> mItems;
	private Context mAppContext;
	// ItemList prevents any other class from instantiating
	private ItemList (Context appContext) {
		mAppContext = appContext;
		mItems = new ArrayList<Item>();
	}

	public static ItemList get(Context c) {
		/**
		 * In the above code snippet imagine that multiple threads comes concurrently
		 * and tries to create the new instance. In such situation the may be three
		 * or more threads are waiting on the synchronized block to get access.
		 * Since we have used synchronized only one thread will be given access.
		 * All the remaining threads which were waiting on the synchronized block
		 * will be given access when first thread exits this block. However when
		 * the remaining concurrent thread enters the synchronized block they are prevented
		 * to enter further due to the double check : null check. Since the first thread
		 * has already created an instance no other thread will enter this loop.

		 * All the remaining threads that were not lucky to enter the synchronized block
		 * along with the first thread will be blocked at the first null check.
		 * This mechanism is called <b>double checked locking</b> and it provides significant
		 * performance benefit and also it is cost effective solution.
		 */

		if (sItemList == null) {
			synchronized (ItemList.class) {
				if (sItemList == null) {
					sItemList = new ItemList(c.getApplicationContext());
				}
			}
		}
		return sItemList;
	}

	public ArrayList<Item> getItems() {
		return mItems;
	}

	public Item getItem(UUID uuid) {
        for (Item i: mItems) {
			if (i.getUUID().equals(uuid)) {
				return i;
			}
		}
		return null;
	}

	public Item getItem(int itemId, int typeId) {
		for (Item i: mItems) {
			if (i.getItemId() ==  itemId && i.getType() == typeId) {
				return i;
			}
		}
		return null;
	}

	public boolean remove(int itemId, int typeId) {
		Item i = getItem(itemId,typeId);
		if ( i != null ) {
			mItems.remove(i);
			return true;
		} return false;
	}

	public boolean hasItem(int itemId, int typeId) {
		return (getItem(itemId,typeId) != null) ? true : false;
	}

	public boolean hasItem(Item i) {
		return mItems.contains(i);
	}

	public boolean addItem(Item i) {
		if (mItems.contains(i)) {
			mItems.set(mItems.indexOf(i), i);
		} else {
			mItems.add(i);
		} return true;
	}

	public void destroy(){
		sItemList = null;
	}
}
