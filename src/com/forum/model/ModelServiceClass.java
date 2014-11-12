package com.forum.model;


public class ModelServiceClass implements java.io.Serializable {
	private String classId;	
	private String className;
	
	private int businessNum;
	
	private String workdocPath;
	
	private int needCar;
	private String remark;
	
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public int getBusinessNum() {
		return businessNum;
	}
	public void setBusinessNum(int businessNum) {
		this.businessNum = businessNum;
	}
	public int getNeedCar() {
		return needCar;
	}
	public void setNeedCar(int needCar) {
		this.needCar = needCar;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getWorkdocPath() {
		return workdocPath;
	}
	public void setWorkdocPath(String workdocPath) {
		this.workdocPath = workdocPath;
	}
	
	
}
