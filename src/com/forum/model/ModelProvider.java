package com.forum.model;



public class ModelProvider implements java.io.Serializable {

	private String providerId;
	
	private String title;
	private String summary;
	private String remark;
	
	private int browse;		// 浏览人数
	private int business;	// 成交人数
	
	private float score;	// 综合得分	
	private int scoreCount;	// 打分人数
	
	private String addr;
	private String phone;
	
	private double latitude;// 纬度
	private double longitude;// 经度
	
	private int jl;	// 与当前用户所在位置的距离
	
	private int renzheng;// 认证
	private int s4;
	private int liansuo;
	
	public String[] imgIdList;

	private String imgIdListStr;
	
	public void copyDetailInfo(ModelProvider src) {
		this.summary = src.summary;
		this.remark = src.remark;
		this.imgIdListStr = src.imgIdListStr;
		this.phone = src.phone;
		this.browse = src.browse;
		this.business = src.business;
	}
	
	public String getImgIdListStr() {
		return imgIdListStr;
	}

	public void setImgIdListStr(String imgIdListStr) {
		this.imgIdListStr = imgIdListStr;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getProviderId() {
		return providerId;
	}
	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getBrowse() {
		return browse;
	}
	public void setBrowse(int browse) {
		this.browse = browse;
	}
	public int getBusiness() {
		return business;
	}
	public void setBusiness(int business) {
		this.business = business;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	public int getScoreCount() {
		return scoreCount;
	}
	public void setScoreCount(int scoreCount) {
		this.scoreCount = scoreCount;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public int getRenzheng() {
		return renzheng;
	}
	public void setRenzheng(int renzheng) {
		this.renzheng = renzheng;
	}
	public int getS4() {
		return s4;
	}
	public void setS4(int s4) {
		this.s4 = s4;
	}
	public int getLiansuo() {
		return liansuo;
	}
	public void setLiansuo(int liansuo) {
		this.liansuo = liansuo;
	}
	public int getJl() {
		return jl;
	}
	public void setJl(int jl) {
		this.jl = jl;
	}
	
}
