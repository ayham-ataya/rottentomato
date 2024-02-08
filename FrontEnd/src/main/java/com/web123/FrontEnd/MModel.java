package com.web123.FrontEnd;

public class MModel {
	int id ;
	String name ; 
	String brief;
	String Category;
	String text ;
	
	
	public MModel() {
		
	}
	
	public MModel(String name,String brief, String text) {
		super();
		this.name = name;
		this.brief=brief;
		this.text = text;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		this.Category = category;
	} 
	
	
}
