package com.tesco.tps.repository;

import com.tesco.tps.core.annotation.Repository;
import com.tesco.tps.couchbase.repository.CouchbaseRepository;
import com.tesco.tps.model.TestDetails;

@Repository
public interface TestDetailsRepository extends CouchbaseRepository<TestDetails, String> {

}