package com.forum.model;

public class BsiRecordValue {
	private int type = 1;
	private int price_jifen = 0;
	private int return_jifen = 0;
	private String serviceId = "";
	private String serviceClassId = "";
	private String providerId = "";
	private String user_id = "";

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getPrice_jifen() {
		return price_jifen;
	}

	public void setPrice_jifen(int price_jifen) {
		this.price_jifen = price_jifen;
	}

	public int getReturn_jifen() {
		return return_jifen;
	}

	public void setReturn_jifen(int return_jifen) {
		this.return_jifen = return_jifen;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceClassId() {
		return serviceClassId;
	}

	public void setServiceClassId(String serviceClassId) {
		this.serviceClassId = serviceClassId;
	}

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

}
