package com.web123.Rate.Rates;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/Rate")
public class RController {
	@Autowired
	RService ratingService ;
	
	
	private static final Logger log = LoggerFactory.getLogger(RController.class);

	
	@RequestMapping(method = RequestMethod.POST ,value = "/addRate")
	public void addRating (@RequestBody RModel ratingModel )
	{
		log.info("Inside RController for Add rating");
		this.ratingService.addRate(ratingModel.getRateValue(), ratingModel.getMovieId());
	}
	
	@RequestMapping(method = RequestMethod.GET , value = "/getRate/{movieId}")
	public float getRatingToMovie(@PathVariable int movieId)
	{
		log.info("Inside RController for get rating");
		 return this.ratingService.avgRateForMovie(movieId);
	} 
	
	@RequestMapping("/test")
	public RModel getData() {
		return new RModel(3,100);
	}
	
}
