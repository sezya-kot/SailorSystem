package com.sezyakot.android.sailorapp.sailor.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.UUID;

public class Item extends DefaultData implements Comparable<Item>{

	@Expose @SerializedName("type")          protected       int         mType;
	@Expose @SerializedName("item")          protected       int         mItemId;
	@Expose @SerializedName("quantity")      protected       double      mQuantity;
	@Expose @SerializedName("price")         protected       double      mItemSubtotalPrice;
	@Expose @SerializedName("unit_detail")   protected       int         mUnitDetailId;


	protected     int             mObjectId;
	protected     UUID            mUUID;

	protected     String          mCode;
	protected     String          mName;
	protected     String          mDescription;
	protected     String          mUnitDetailName;
    protected     double          mUnitPrice;
    protected     int             mUnitId;
	protected     double          mVat;
	protected     String          mCurrency;

	public Item() {};

	public Item(boolean withUUID) {
		if (withUUID == true) mUUID = UUID.randomUUID();
	}

	public Item(
			int     id,
			int     serverId,
			int     type,
			int     itemId,
			double  quantity,
			double  itemSubtotalPrice,
			int     objectId,
			int     unitDetailId,
			int     version,
			int     status
	) {
		super(id, serverId,"", version,status);

		mType               = type;
		mItemId             = itemId;
		mQuantity           = quantity;
		mItemSubtotalPrice  = itemSubtotalPrice;
		mObjectId           = objectId;
		mUnitDetailId       = unitDetailId;
	}

	@Override
	public int compareTo(Item another) {
		return (this.mName).compareToIgnoreCase(another.mName);
	}

	public String getBlueString() {
		String result;
		result = "" + mQuantity +" "+ mUnitDetailName +", "+ (mItemSubtotalPrice/mQuantity) + " " + mCurrency+"/"+mUnitDetailName;
		return result;
	}
	public String getGreenString() {
		String result;
		result = "Subtotal: " + mItemSubtotalPrice + " " + mCurrency;
		return result;
	}

	public String getCurrency() {
		return mCurrency;
	}

	public void setCurrency(String currency) {
		mCurrency = currency;
	}

	public int getType() {
		return mType;
	}

	public void setType(int type) {
		mType = type;
	}

	public int getItemId() {
		return mItemId;
	}

	public void setItemId(int itemId) {
		mItemId = itemId;
	}

	public double getQuantity() {
		return mQuantity;
	}

	public void setQuantity(double quantity) {
		mQuantity = quantity;
	}

	public double getItemSubtotalPrice() {
		return mItemSubtotalPrice;
	}

	public void setItemSubtotalPrice(double itemSubtotalPrice) {
		mItemSubtotalPrice = itemSubtotalPrice;
	}

	public int getObjectId() {
		return mObjectId;
	}

	public void setObjectId(int objectId) {
		mObjectId = objectId;
	}

	public int getUnitDetailId() {
		return mUnitDetailId;
	}

	public void setUnitDetailId(int unitDetailId) {
		mUnitDetailId = unitDetailId;
	}

	public String getCode() {
		return mCode;
	}

	public void setCode(String code) {
		mCode = code;
	}

	public String getName() {
		return mName;
	}

	public void setName(String name) {
		mName = name;
	}

	public String getDescription() {
		return mDescription;
	}

	public void setDescription(String description) {
		mDescription = description;
	}

	public String getUnitDetailName() {
		return mUnitDetailName;
	}

	public void setUnitDetailName(String unitDetailName) {
		mUnitDetailName = unitDetailName;
	}

	public double getVat() {
		return mVat;
	}

	public void setVat(double vat) {
		mVat = vat;
	}

	public UUID getUUID() {
		return mUUID;
	}

	public void setUUID(UUID UUID) {
		mUUID = UUID;
	}

    public int getUnitId() {
        return mUnitId;
    }

    public void setUnitId(int pUnitId) {
        mUnitId = pUnitId;
    }

    public double getUnitPrice() {
        return mUnitPrice;
    }

    public void setUnitPrice(double pUnitPrice) {
        mUnitPrice = pUnitPrice;
    }
}
