package com.web123.Rate.Rates;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class ReceiverR {

	
	private static final Logger log = LoggerFactory.getLogger(ReceiverR.class);

    static final Logger logger = LoggerFactory.getLogger(ReceiverR.class);

    @Autowired
    RService ratingService ;
    
    @RabbitListener(queues = "DeleteRateQueue")
    public void processOrder(MID movieID) {
    	
 	   System.out.println("Delete Rate request recieved on queue #"+movieID.getId());	   
        this.ratingService.deleteMovieRating(movieID.getId());
        log.info("Inside DeleteRateQueue for deleting rating");
 	   System.out.println("Rating deleted");
    }
}


