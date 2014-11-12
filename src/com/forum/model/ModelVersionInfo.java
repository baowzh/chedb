package com.forum.model;


public class ModelVersionInfo implements java.io.Serializable {

	private int localVersionCode;
	
	private String localVersionName;
	
	private int serVersionCode;
	
	private String serVersionName;

	private String serFilePath;
	
	public int getLocalVersionCode() {
		return localVersionCode;
	}

	public void setLocalVersionCode(int localVersionCode) {
		this.localVersionCode = localVersionCode;
	}

	public String getLocalVersionName() {
		return localVersionName;
	}

	public void setLocalVersionName(String localVersionName) {
		this.localVersionName = localVersionName;
	}

	public int getSerVersionCode() {
		return serVersionCode;
	}

	public void setSerVersionCode(int serVersionCode) {
		this.serVersionCode = serVersionCode;
	}

	public String getSerVersionName() {
		return serVersionName;
	}

	public void setSerVersionName(String serVersionName) {
		this.serVersionName = serVersionName;
	}

	public String getSerFilePath() {
		return serFilePath;
	}

	public void setSerFilePath(String serFilePath) {
		this.serFilePath = serFilePath;
	}
	
	

}
