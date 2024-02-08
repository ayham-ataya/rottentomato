package com.web123.comment.Comments;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CModel {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private int id;
	
	@Column(columnDefinition = "varchar(1000)")
	private String commentContent ; 
	
	private int movieId ;

	public CModel() {}
	
	public CModel(String commentContent, int movieId) {
		super();
		this.commentContent = commentContent;
		this.movieId = movieId;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	} 	
	
}
