package com.sezyakot.android.sailorapp.sailor.model;

import com.google.gson.annotations.Expose;

public class Error {
	@Expose private int code;
	@Expose private String msg;
	
	public Error() {
	}
	
	public String toString() {
		return "Code: "+code+" Msg: "+msg;
	}

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
