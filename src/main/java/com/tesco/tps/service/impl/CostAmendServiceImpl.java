package com.tesco.tps.service.impl;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Response;
import com.google.inject.Inject;
import com.tesco.tps.constant.CostAmendHttpResponse;
import com.tesco.tps.core.annotation.Service;
import com.tesco.tps.couchbase.service.AbstractCouchbaseService;
import com.tesco.tps.domain.CostAmendRequest;
import com.tesco.tps.dto.CostAmendRequestDto;
import com.tesco.tps.dto.CostAmendResponseDto;
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

	private List<MissingCostAmends> missingList=new ArrayList<MissingCostAmends>();

	@Override
	public Response creatingCostDocument(CostAmendInput costDocumentList, Principal userDetails) {
		CostAmendResponseDto costAmendResponseDto = new CostAmendResponseDto();
		costDocumentList.getInputList()
				.forEach(costDocument -> inseringCostDocumentAndAddingMissingListForResponseDocument(costDocument,
						userDetails, costAmendResponseDto, missingList));
		return settingResponseBody(costAmendResponseDto, costDocumentList);
	}

	private void inseringCostDocumentAndAddingMissingListForResponseDocument(CostAmendRequestDto costDocument,
			Principal userDetails, CostAmendResponseDto costAmendResponseDto, List<MissingCostAmends> missingList) {
		costDocument.setCreatedBy(userDetails.getName());
		costDocument.setModifiedBy(userDetails.getName());
		try {
			super.create(convert(costDocument, CostAmendRequest.class));

		} catch (RuntimeException exception) {
			MissingCostAmends costAmendFailure = convert(costDocument, MissingCostAmends.class);
			costAmendFailure.setReasonForFailure(exception.getMessage());
			missingList.add(costAmendFailure);
		}
	}

	private Response settingResponseBody(CostAmendResponseDto costAmendResponseDto, CostAmendInput costDocumentList) {
		costAmendResponseDto.setMissingSet(missingList);
		if (missingList.size() == 0) {
			costAmendResponseDto.setMessage("Success");
			return CostAmendHttpResponse.ok(costAmendResponseDto);
		} else if (missingList.size() == costDocumentList.getInputList().size()) {
			costAmendResponseDto.setMessage("Failure");
			return CostAmendHttpResponse.couchbaseError(costAmendResponseDto);
		} else
			costAmendResponseDto.setMessage("PartialSuccess");
		return CostAmendHttpResponse.partialOk(costAmendResponseDto);
	}
}
