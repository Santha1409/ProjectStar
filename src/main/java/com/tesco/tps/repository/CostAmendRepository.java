package com.tesco.tps.repository;


import com.tesco.tps.core.annotation.Repository;
import com.tesco.tps.couchbase.repository.CouchbaseRepository;
import com.tesco.tps.domain.CostAmendRequest;

@Repository
public interface CostAmendRepository extends CouchbaseRepository<CostAmendRequest, String>{

}
