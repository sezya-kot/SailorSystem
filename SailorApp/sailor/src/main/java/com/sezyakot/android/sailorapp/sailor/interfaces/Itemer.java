package com.sezyakot.android.sailorapp.sailor.interfaces;



import com.sezyakot.android.sailorapp.sailor.model.Item;

import java.util.List;

/**
 * Created by Android on 11.09.2014.
 */
public interface Itemer {
	public List<Item> getItems();
	public void setItems(List<Item> items);

	public double getAdvancePayment();
}
