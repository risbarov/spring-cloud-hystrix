package org.sf.cloud.hystrix.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
public class HystrixController {

	private static final Logger LOG = LoggerFactory.getLogger(HystrixController.class);

	@RequestMapping(value = "/executionTimeoutTest")
	@HystrixCommand(
		fallbackMethod = "executionTimeoutFallback",
		commandProperties = {
			@HystrixProperty(name = "execution.timeout.enabled", value = "true"),
			// По умолчанию com.netflix.hystrix.HystrixCommandProperties#executionTimeoutInMilliseconds = 1000
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "900")
		}
	)
	public String executionTimeoutTest() {
		LOG.info("HystrixController#executionTimeoutTest invoked...");

		try {
			Thread.sleep(750L);
		} catch (InterruptedException ignored) {
		}

		return "Success response";
	}

	@SuppressWarnings("unused")
	public String executionTimeoutFallback() {
		LOG.info("HystrixController#executionTimeoutFallback invoked...");

		return "Fallback response";
	}

	@RequestMapping(value = "/executionFaultTest")
	@HystrixCommand(fallbackMethod = "executionFaultFallback")
	public String executionFaultTest(@RequestParam(defaultValue = "false") boolean generateFault) {
		LOG.info("HystrixController#executionFaultTest invoked...");

		if (generateFault) {
			throw new RuntimeException("Test fault");
		}

		return "Success response";
	}

	@SuppressWarnings("unused")
	public String executionFaultFallback(boolean generateFault) {
		LOG.info("HystrixController#executionFaultFallback invoked...");

		return "Fallback response";
	}

}
