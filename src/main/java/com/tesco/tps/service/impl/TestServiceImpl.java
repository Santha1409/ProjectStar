package com.tesco.tps.service.impl;


import com.google.inject.Inject;
import com.tesco.tps.model.TestDetails;
import com.tesco.tps.repository.TestDetailsRepository;
import com.tesco.tps.service.TestService;
import com.tesco.tps.core.annotation.Service;
import com.tesco.tps.core.service.RootLookupDocumentService;
import com.tesco.tps.core.util.ServiceUtils;
import com.tesco.tps.couchbase.CouchbaseResource;
import com.tesco.tps.couchbase.service.AbstractCouchbaseService;

import ma.glasnost.orika.MapperFacade;


@Service
public class TestServiceImpl extends AbstractCouchbaseService<TestDetails,String>
implements TestService {


	
	private CouchbaseResource couchbaseResource;
	/**
	 * @param mapperFaced
	 * @param repo
	 * @param serviceUtils
	 */
	@Inject
	public  TestServiceImpl(MapperFacade mapperFaced, TestDetailsRepository repo, ServiceUtils serviceUtils, CouchbaseResource couchbaseResource) {
		super(mapperFaced, repo, serviceUtils);
		
		this.couchbaseResource = couchbaseResource;

	}
	
	@Override
	public void postData(TestDetails testDetails) {
		System.out.println(testDetails.getUniqueKey());
		System.out.println(testDetails.getId());
		
		super.create(testDetails);
	}
	@Override
	public TestDetails getData(String id) {
		return super.get(id);
	}


}


