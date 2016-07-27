package com.tesco.tps.service;

import java.security.Principal;

import com.tesco.tps.core.service.GenericService;
import com.tesco.tps.domain.CostAmendRequest;
import com.tesco.tps.dto.CostAmendRequestDto;
import com.tesco.tps.dto.CostAmendResponseDto;

public interface CostAmendService extends GenericService<CostAmendRequest, String> {

	void creatingCostDocument(CostAmendRequestDto costDocument, Principal principal);

}
