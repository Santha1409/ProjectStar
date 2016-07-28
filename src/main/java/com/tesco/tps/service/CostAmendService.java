package com.tesco.tps.service;

import java.security.Principal;

import javax.ws.rs.core.Response;
import com.tesco.tps.core.service.GenericService;
import com.tesco.tps.domain.CostAmendRequest;
import com.tesco.tps.model.CostAmendInput;

public interface CostAmendService extends GenericService<CostAmendRequest, String> {


	Response creatingCostDocument(CostAmendInput costDocumentList, Principal userDetails);

}
