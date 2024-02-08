package com.web123.zuul;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ZuulllController {
	
	@Value("${eureka.instance.metadataMap.zone}")
	private String zone;
	
	@RequestMapping(method = RequestMethod.GET , value = "/return")
	public String Ping() {
		return zone;
	}
}

