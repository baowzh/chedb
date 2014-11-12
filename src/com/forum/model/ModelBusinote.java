package com.forum.model;


public class ModelBusinote implements java.io.Serializable {
	private String id;
	
	private String buynoteNo;// 流水号
	private int payType;//支付类型：1现金支付；2第三方支付；3积分抵付
	private int price;
	private int priceJifen;
	private int returnJifen;
	private String date;// 支付时间
	
	private String serviceClassId;
	private String serviceId;
	private String serviceName;
	
	private int status;// 当前状态，1支付；2商家确认；3完成；4进行了评分；0用户取消
	
	private String providerId;
	private String providerName;
	private int score; // 商家打分
	
	private String userId;
	private String userName;
	
	private int enable;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBuynoteNo() {
		return buynoteNo;
	}

	public void setBuynoteNo(String buynoteNo) {
		this.buynoteNo = buynoteNo;
	}

	public int getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getServiceClassId() {
		return serviceClassId;
	}

	public void setServiceClassId(String serviceClassId) {
		this.serviceClassId = serviceClassId;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

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

	public int getEnable() {
		return enable;
	}

	public void setEnable(int enable) {
		this.enable = enable;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}


	public int getPriceJifen() {
		return priceJifen;
	}

	public void setPriceJifen(int priceJifen) {
		this.priceJifen = priceJifen;
	}

	public int getReturnJifen() {
		return returnJifen;
	}

	public void setReturnJifen(int returnJifen) {
		this.returnJifen = returnJifen;
	}

}
