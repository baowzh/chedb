package com.forum.model;

import java.util.List;

public class ModelSysItem implements java.io.Serializable {

	private String parentId;

	private String labelId;

	private String title;

	private int select;
	private List<ModelSysItem> childitems;

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

	public List<ModelSysItem> getChilditems() {
		return childitems;
	}

	public void setChilditems(List<ModelSysItem> childitems) {
		this.childitems = childitems;
	}

}
