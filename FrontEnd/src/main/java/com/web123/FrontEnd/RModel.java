package com.web123.FrontEnd;

public class RModel {

	private int rateValue ;
	private int movieId ;
	
	public RModel() {
		// TODO Auto-generated constructor stub
	}
	
	public RModel(int rateValue, int movieId) {
		super();
		this.rateValue = rateValue;
		this.movieId = movieId;
	}

	public int getRateValue() {
		return rateValue;
	}

	public void setRateValue(int rateValue) {
		this.rateValue = rateValue;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
}
