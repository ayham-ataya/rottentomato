package com.web123.comment.Comments;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Receiver {


    static final Logger logger = LoggerFactory.getLogger(Receiver.class);

    @Autowired 
    private CService commentsService ; 
    
   @RabbitListener(queues = "DeleteCommentQueue")
    public void processOrder(MID movieID) {
	   System.out.println("Deleting Comment for movie("+movieID.getId()+")");	
	   this.commentsService.deleteMovieComments(movieID.getId()); 
	   System.out.println("Comments deleted");
    }
}

