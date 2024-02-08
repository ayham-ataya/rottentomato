package com.web123.EModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Rid ; 
	
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
