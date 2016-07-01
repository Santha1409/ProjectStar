package com.tesco.tps.service;

import com.tesco.tps.model.TestDetails;
import com.tesco.tps.core.service.GenericService;


public interface TestService extends GenericService<TestDetails,String> {

	void postData(TestDetails testDetails);

	TestDetails getData(String id);

	}
