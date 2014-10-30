/*
 * Copyright (c) 2014.
 */

package com.sezyakot.sailor.interfaces;

import com.sezyakot.sailor.model.Item;

import java.util.List;

/**
 * Created by Android on 11.09.2014.
 */
public interface Itemer {
	public List<Item> getItems();
	public void setItems(List<Item> items);

	public double getAdvancePayment();
}
