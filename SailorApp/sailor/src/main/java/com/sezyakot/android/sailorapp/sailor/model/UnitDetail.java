package com.sezyakot.android.sailorapp.sailor.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UnitDetail extends DefaultData {
	@Expose
    @SerializedName("unit")         private int         mUnitId;
	@Expose @SerializedName("from")         private int         mFrom;
	@Expose @SerializedName("to")           private int         mTo;
	@Expose @SerializedName("main")         private String      mMain;

    public UnitDetail() {}

    public UnitDetail(       int id,
                      int serverId,
                      String name,
                      int unitId,
                      int from,
                      int to,
                      String main,
                      int version,
                      int status) {
        super(id, serverId, name, version, status);

        mUnitId     = unitId;
        mFrom       = from;
        mTo         = to;
        mMain       = main;
    }

    public int getUnitId() {
        return mUnitId;
    }

    public void setUnitId(int unitId) {
        mUnitId = unitId;
    }

    public int getFrom() {
        return mFrom;
    }

    public void setFrom(int from) {
        mFrom = from;
    }

    public int getTo() {
        return mTo;
    }

    public void setTo(int to) {
        mTo = to;
    }

    public String getMain() {
        return mMain;
    }

    public void setMain(String main) {
        mMain = main;
    }
}
