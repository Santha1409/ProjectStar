package com.tesco.tps.service.impl;

import com.google.inject.Inject;
import com.tesco.tps.core.annotation.Service;
import com.tesco.tps.couchbase.service.AbstractCouchbaseService;
import com.tesco.tps.model.TestDetails;
import com.tesco.tps.repository.TestDetailsRepository;
import com.tesco.tps.service.TestService;

@Service
public class TestServiceImpl extends AbstractCouchbaseService<TestDetails, String> implements TestService {

	/**
	 * @param mapperFaced
	 * @param repo
	 * @param serviceUtils
	 */
	@Inject
	public TestServiceImpl(TestDetailsRepository repo) {
		super(repo);
	}

	@Override
	public void postData(TestDetails testDetails) {
		System.out.println(testDetails.getUniqueKey());
		System.out.println(testDetails.getId());

		super.create(testDetails);
	}

	@Override
	public TestDetails getData(String id) {
		return findOne(id);
	}

}
