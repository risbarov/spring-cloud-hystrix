package org.sf.cloud.hystrix.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexPageController {

	@RequestMapping(value = "/")
	public String index() {
		return "Spring Cloud Hystrix is running...";
	}

}
