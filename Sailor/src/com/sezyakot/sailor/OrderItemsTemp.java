package com.sezyakot.sailor;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Android on 04.09.2014.
 */
public class OrderItemsTemp {
	@SerializedName("type")         private     int                 mType;
	@SerializedName("item")         private     int                 mItemId;
	@SerializedName("unit_detail")  private     int                 mUnitDetailId;
	@SerializedName("price")        private     double              mPrice;
	@SerializedName("quantity")     private     int                 mQuantity;

}
