package com.forum.model;

import java.util.Date;

public class ModelUserAppraise {

	private String theId;
	private String userId;
	private String userName;
	private String serviceClassId;
	private String serviceId;
	private String appraise;
	
	private String dateStr; // ÈÕÆÚ
	private int agreeNum;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getServiceClassId() {
		return serviceClassId;
	}
	public void setServiceClassId(String serviceClassId) {
		this.serviceClassId = serviceClassId;
	}
	public String getAppraise() {
		return appraise;
	}
	public void setAppraise(String appraise) {
		this.appraise = appraise;
	}
	
	public String getDateStr() {
		return dateStr;
	}
	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}
	public int getAgreeNum() {
		return agreeNum;
	}
	public void setAgreeNum(int agreeNum) {
		this.agreeNum = agreeNum;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public String getTheId() {
		return theId;
	}
	public void setTheId(String theId) {
		this.theId = theId;
	}
	
	
	
}
