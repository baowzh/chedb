package com.forum.model;

public class ModelProviderItem  implements java.io.Serializable {

	private String itemId;// 项目ID
	
	private String providerId;//商家ID
	private String sysItemId;//
	private String sysItemName;
	
	private String title;	// 项目名称
	private String summary;	// 项目摘要
	
//	private int browse;		// 浏览人数
	private int business;	// 成交人数
	
	private float price;	// 价格
	private float priceOld;	// 折扣
	
//	private float score;	// 综合得分	
//	private int scoreCount;	// 打分人数
	
	private String remark;

	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
//	public int getBrowse() {
//		return browse;
//	}
//	public void setBrowse(int browse) {
//		this.browse = browse;
//	}
	public int getBusiness() {
		return business;
	}
	public void setBusiness(int business) {
		this.business = business;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	public float getPriceOld() {
		return priceOld;
	}

	public void setPriceOld(float priceOld) {
		this.priceOld = priceOld;
	}

//	public float getScore() {
//		return score;
//	}
//	public void setScore(float score) {
//		this.score = score;
//	}
//	public int getScoreCount() {
//		return scoreCount;
//	}
//	public void setScoreCount(int scoreCount) {
//		this.scoreCount = scoreCount;
//	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getProviderId() {
		return providerId;
	}
	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
	public String getSysItemId() {
		return sysItemId;
	}
	public void setSysItemId(String sysItemId) {
		this.sysItemId = sysItemId;
	}
	public String getSysItemName() {
		return sysItemName;
	}
	public void setSysItemName(String sysItemName) {
		this.sysItemName = sysItemName;
	}

}

