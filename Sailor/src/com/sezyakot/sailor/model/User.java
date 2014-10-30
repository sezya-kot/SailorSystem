package com.sezyakot.sailor.model;

import com.google.gson.annotations.SerializedName;

public class User {
	@SerializedName("error")    private Error error;
	@SerializedName("data")     private DataFetch data;
	
	public User() {
		//constructor
	}
	
	public Error getError() {
		return error;
	}

	public void setError(Error error) {
		this.error = error;
	}

	public DataFetch getData() {
		return data;
	}

	public void setData(DataFetch data) {
		this.data = data;
	}

	public String toString () {
		String str;
		str = ""+ error + data;
		return str;
	}

//	public String toJson() {
//		Gson gson = new Gson();
//		String json = gson.toJson(this);
//		return json;
//	}
}
