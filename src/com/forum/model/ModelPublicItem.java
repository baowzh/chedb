package com.forum.model;


public class ModelPublicItem implements java.io.Serializable {

	private String id;
	
	private String title;
	
	private int select;

	public boolean equal(ModelPublicItem label) {
		return this.id.equals(label.id);
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
