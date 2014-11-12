package com.forum.model;


public class ModelSysItem implements java.io.Serializable {

	private String parentId;
	
	private String labelId;
	
	private String title;
	
	private int select;

	public boolean equal(ModelSysItem label) {
		return this.labelId.equals(label.getLabelId());
	}
	
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getLabelId() {
		return labelId;
	}

	public void setLabelId(String labelId) {
		this.labelId = labelId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getSelect() {
		return select;
	}

	public void setSelect(int select) {
		this.select = select;
	}
	
	
}
