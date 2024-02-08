package com.web123.demo;


public class MModel {
    int id;
    String name;
    String text;
    String Category;
    public MModel() {

    }
    public MModel(String name, String text, String category) {
        this.name = name;
        this.text = text;
        Category = category;
    }
    public String getCategory() {
        return Category;
    }
    public void setCategory(String category) {
        Category = category;
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
    public void setName(String subject) {
        this.name = subject;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
}
