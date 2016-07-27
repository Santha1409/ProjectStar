package com.tesco.tps.service.impl;

import java.security.Principal;

import com.google.inject.Inject;
import com.tesco.tps.core.annotation.Service;
import com.tesco.tps.couchbase.service.AbstractCouchbaseService;
import com.tesco.tps.domain.CostAmendRequest;
import com.tesco.tps.dto.CostAmendRequestDto;
import com.tesco.tps.dto.CostAmendResponseDto.MissingCostAmends;
import com.tesco.tps.model.CostAmendInput;
import com.tesco.tps.repository.CostAmendRepository;
import com.tesco.tps.service.CostAmendService;

@Service
public class CostAmendServiceImpl extends AbstractCouchbaseService<CostAmendRequest, String>
		implements CostAmendService {
    @Inject
	public CostAmendServiceImpl(CostAmendRepository repo) {
		super(repo);
	}

	/*@Override
	public void creatingCostDocument(CostAmendRequestDto costDocument, Principal userDetails) {
		costDocument.setCreatedBy(userDetails.getName());
		costDocument.setModifiedBy(userDetails.getName());
		try {
			super.create(convert(costDocument, CostAmendRequest.class));
		} catch (RuntimeException exception) {
			
			
			//convert(costDocument, MissingCostAmends.class);
		}

		// costAmendResponseDto.setMissingSet();
	}*/
    public void creatingCostDocument(CostAmendInput costDocumentList, Principal userDetails) {
    	costDocumentList.getInputList().forEach(costDocument->{
    		costDocument.setCreatedBy(userDetails.getName());
    		costDocument.setModifiedBy(userDetails.getName());}
		try {
			super.create(convert(costDocument, CostAmendRequest.class));
		} catch (RuntimeException exception) {
			
			
			//convert(costDocument, MissingCostAmends.class);
		}

		// costAmendResponseDto.setMissingSet();
    }
    
}
