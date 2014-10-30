package com.sezyakot.sailor.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Android on 20.08.2014.
 */
public class Service extends DefaultData {

	@Expose @SerializedName("description")  protected String      mDescription;
	@Expose @SerializedName("vat")          protected int         mVat;
	@Expose @SerializedName("unit")         protected int         mUnit;
	@Expose @SerializedName("code")         protected String      mCode;

    public Service() {};

    public Service(int id,
                   int serverId,
                   String name,
                   String description,
                   String code,
                   int vat,
                   int unit,
                   int status,
                   int version) {
        super(id, serverId, name, version, status);
        mDescription = description;
        mCode = code;
        mVat = vat;
        mUnit = unit;
    };



    public String getCode() {
        return mCode;
    }

    public String getPrice(int server_id) {

        return null;
    }

    public void setCode(String code) {
        mCode = code;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public int getVat() {
        return mVat;
    }

    public void setVat(int vat) {
        mVat = vat;
    }

    public int getUnit() {
        return mUnit;
    }

    public void setUnit(int unit) {
        mUnit = unit;
    }
}
