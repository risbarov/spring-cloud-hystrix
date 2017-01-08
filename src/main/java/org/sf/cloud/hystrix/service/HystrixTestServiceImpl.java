package org.sf.cloud.hystrix.service;

import org.springframework.stereotype.Service;

@Service
public class HystrixTestServiceImpl implements HystrixTestService {

	@Override
	public void success() {

	}

	@Override
	public void fail() {
		throw new RuntimeException("Test fail");
	}

}
