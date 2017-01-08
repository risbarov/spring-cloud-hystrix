package org.sf.cloud.hystrix.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
public class HystrixTestPageController {

	private static final Logger LOG = LoggerFactory.getLogger(HystrixTestPageController.class);

	@RequestMapping(value = "/hystrixTest")
	@HystrixCommand(
		fallbackMethod = "hystrixFail",
		commandProperties = {
			@HystrixProperty(name = "execution.timeout.enabled", value = "true")
		}
	)
	public String hystrixTest() {
		LOG.info("HystrixTestPageController#hystrixTest invoked...");

		try {
			// По умолчанию com.netflix.hystrix.HystrixCommandProperties#executionTimeoutInMilliseconds = 1000
			Thread.sleep(750L);
		} catch (InterruptedException ignored) {
		}

		return "Hystrix success response";
	}

	@SuppressWarnings("unused")
	public String hystrixFail() {
		LOG.info("HystrixTestPageController#hystrixFail invoked...");

		return "Hystrix fail response";
	}

}
