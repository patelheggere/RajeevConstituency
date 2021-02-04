package com.patelheggere.rajeevconstituency.model;

import java.util.List;

public class APIResponseModel {
	String message;
	boolean status;
	int statusCode;
	int count;
	String nextURL;
	String beatOfficerName;
	
	public APIResponseModel() {
		super();
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public int getErrorCode() {
		return statusCode;
	}
	public void setErrorCode(int errorCode) {
		this.statusCode = errorCode;
	}
	public String getNextURL() {
		return nextURL;
	}
	public void setNextURL(String nextURL) {
		this.nextURL = nextURL;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}


	public String getBeatOfficerName() {
		return beatOfficerName;
	}

	public void setBeatOfficerName(String beatOfficerName) {
		this.beatOfficerName = beatOfficerName;
	}
}
