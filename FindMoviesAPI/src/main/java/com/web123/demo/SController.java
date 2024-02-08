package com.web123.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SController {

	@Autowired 
	private SService searchService ; 

	@Value("${eureka.instance.metadataMap.zone}")
	private String zone ; 
	
	@RequestMapping("/return")
	public String ping() {
		return zone ; 
	}
	
    @RequestMapping(method = RequestMethod.GET, value = "/Search/{subject}")
    public MList Search(@PathVariable String subject) {
       return  this.searchService.searchForMovies(subject);
    }
}
