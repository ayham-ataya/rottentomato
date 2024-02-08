package com.web123.EModel;


public class CModel {

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
