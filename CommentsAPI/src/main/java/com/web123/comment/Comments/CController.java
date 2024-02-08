package com.web123.comment.Comments;

import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("/Comments")
public class CController {
	
	private static final Logger log = LoggerFactory.getLogger(CController.class);

	
	@Autowired
	private CService commentsService ; 
	
	@Value("${eureka.instance.metadataMap.zone}")
	private String zone ; 
	
	@RequestMapping("/return")
	public String re() {
		return zone ; 
	}
	
	@RequestMapping(method = RequestMethod.POST , value = "/addComment")
	public void addCommentToMovie(@RequestBody CModel comment) {
		this.commentsService.addComment(comment.getCommentContent(),comment.getMovieId());
		log.info("Inside CController for Add Comments");
	}

	@RequestMapping(method = RequestMethod.GET , value = "/getComments/{movieId}")
	public MC getMovieComments(@PathVariable int movieId){
		MC movieComment = new MC();
		movieComment.setAllCommentForThisMovie(this.commentsService.getMovieComments(movieId));
		log.info("Inside CController for get Comments");
		return movieComment;
	}
	
	@RequestMapping("/inject")
	public ResponseEntity<HttpStatus> injectData() {
		this.commentsService.injectData();
		return ResponseEntity.ok(HttpStatus.OK);
	}
	
	@RequestMapping("/test")
	public CModel getData() {
		log.info("Inside CController for test Comments");
		return new CModel("Ayham movie",100);
	}
	
}
