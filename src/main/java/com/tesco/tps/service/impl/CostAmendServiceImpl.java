package com.tesco.tps.service.impl;

import java.security.Principal;
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

	private static List<MissingCostAmends> missingList;

	/*
	 * @Override public void creatingCostDocument(CostAmendRequestDto
	 * costDocument, Principal userDetails) {
	 * costDocument.setCreatedBy(userDetails.getName());
	 * costDocument.setModifiedBy(userDetails.getName()); try {
	 * super.create(convert(costDocument, CostAmendRequest.class)); } catch
	 * (RuntimeException exception) {
	 * 
	 * 
	 * //convert(costDocument, MissingCostAmends.class); }
	 * 
	 * // costAmendResponseDto.setMissingSet(); }
	 */
	@Override
	public Response creatingCostDocument(CostAmendInput costDocumentList, Principal userDetails) {
		// List<MissingCostAmends> missingList = null;
		CostAmendResponseDto costAmendResponseDto = new CostAmendResponseDto();
		costDocumentList.getInputList().forEach(costDocument -> inseringAndSettingCostDocument(costDocument,
				userDetails, costAmendResponseDto, missingList));
		costAmendResponseDto.setMissingSet(missingList);
		 return CostAmendHttpResponse.ok(costAmendResponseDto);
	}

	private void inseringAndSettingCostDocument(CostAmendRequestDto costDocument, Principal userDetails,
			CostAmendResponseDto costAmendResponseDto, List<MissingCostAmends> missingList) {
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
	/*
	 * costDocumentList.getInputList().forEach(costDocument->{
	 * costDocument.setCreatedBy(userDetails.getName());
	 * costDocument.setModifiedBy(userDetails.getName()); } try {
	 * super.create(convert(costDocument, CostAmendRequest.class)); } catch
	 * (RuntimeException exception) {
	 * 
	 * 
	 * //convert(costDocument, MissingCostAmends.class); }
	 * 
	 * // costAmendResponseDto.setMissingSet(); }
	 */

}
