package com.tesco.tps.resource;

import java.security.Principal;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response;
import com.google.inject.Inject;
import com.tesco.tps.auth.constant.RoleType;
import com.tesco.tps.auth.support.annotation.v2.Secured;
import com.tesco.tps.core.annotation.Resource;
import com.tesco.tps.core.resource.AbstractSimpleResource;
import com.tesco.tps.core.service.GenericService;
import com.tesco.tps.domain.CostAmendRequest;
import com.tesco.tps.dto.CostAmendRequestDto;
import com.tesco.tps.model.CostAmendInput;
import com.tesco.tps.service.CostAmendService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

import io.dropwizard.auth.Auth;

@Resource
@Path("/v1/costamendment")
@Api(value = "CostAmend Templates", description = " Operations on costAmend Template")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class CostAmendResource extends AbstractSimpleResource<CostAmendRequest, String, CostAmendRequestDto> {

	CostAmendService costAmendService;

	@Context
	private UriInfo uriInfo;

	@Inject
	public CostAmendResource(CostAmendService costAmendService) {
		this.costAmendService = costAmendService;
	}

	@PUT
	@ApiOperation(value = "Create Cost Document", produces = MediaType.APPLICATION_JSON)
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "Ok"), @ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 201, message = "Created") })
	@Secured(RoleType.ROLE_CREATE)
	public Response postResource(@Auth Principal principal, @Context UriInfo uriInfo,
			@ApiParam("ListOfCostCreate") CostAmendInput listOfCostCreate) {
		/*listOfCostCreate.getInputList()
				.forEach(costDocument -> costAmendService.creatingCostDocument(costDocument, principal));*/
		return  costAmendService.creatingCostDocument(listOfCostCreate,principal);
		
	}

	@Override
	protected GenericService<CostAmendRequest, String> getService() {
		// TODO Auto-generated method stub
		return costAmendService;
	}

}
