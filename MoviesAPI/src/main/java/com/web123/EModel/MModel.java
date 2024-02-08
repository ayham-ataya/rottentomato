package com.web123.EModel;

import javax.persistence.*;


@Entity
@Table(name = "Movies")
public class MModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    String brief;
    String Category;
    @Column(columnDefinition = "varchar(5000)")
    String text;

    public MModel() {

    }

    public MModel(String subject, String brief, String category, String text) {
        this.name = subject;
        this.brief = brief;
        Category = category;
        this.text = text;
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
}
