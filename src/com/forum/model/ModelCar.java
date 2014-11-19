package com.forum.model;

public class ModelCar implements java.io.Serializable {

	private String id;
	private String brandId;
	private String seriseId;
	private String name;
	private String maintainPath; // 官方保养手册图片路径
	private Integer selected;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getSeriseId() {
		return seriseId;
	}

	public void setSeriseId(String seriseId) {
		this.seriseId = seriseId;
	}

	public String getMaintainPath() {
		return maintainPath;
	}

	public void setMaintainPath(String maintainPath) {
		this.maintainPath = maintainPath;
	}

	public Integer getSelected() {
		return selected;
	}

	public void setSelected(Integer selected) {
		this.selected = selected;
	}
	

}
