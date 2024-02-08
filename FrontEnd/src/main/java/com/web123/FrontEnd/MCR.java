package com.web123.FrontEnd;

import java.util.List;

public class MCR {

	 public MModel movie ;
	    public List<CModel> comment;
	    public float rating;

	    public MCR() {
	    }

	    public MCR(MModel movie, List<CModel> comment, float rating) {
	        this.movie = movie;
	        this.comment = comment;
	        this.rating = rating;
	    }

	    public MModel getMovie() {
	        return movie;
	    }

	    public List<CModel> getComment() {
	        return comment;
	    }

	    public float getRating() {
	        return rating;
	    }

	    public void setMovie(MModel movie) {
	        this.movie = movie;
	    }

	    public void setComment(List<CModel> comment) {
	        this.comment = comment;
	    }

	    public void setRating(float rating) {
	        this.rating = rating;
	    }
	
}
