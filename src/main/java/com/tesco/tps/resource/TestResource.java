package com.tesco.tps.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import com.tesco.tps.constant.CostAmendHttpResponse;
import com.tesco.tps.core.annotation.Resource;
import com.tesco.tps.model.TestDetails;
import com.tesco.tps.service.TestService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@Path("/v1/tradedunits/test")
@Api(value = "Test Resource", description = "Test Information")
@Resource
public class TestResource {

	@Inject
	TestService testService;

	@GET
	@Timed
	@Produces({ MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Fetch Traded Unit", produces = MediaType.APPLICATION_JSON)

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 500, message = "Server error") })

	public Response tradedUnitByTpnd(@QueryParam("id") String id) {

		// testService.getData(id);
		// return CostAmendHttpResponse.ok("success");

		TestDetails testDetails = testService.getData(id);
		return CostAmendHttpResponse.ok(testDetails);

	}

	@POST
	@Timed
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Fetch Multiple Traded Units", produces = MediaType.APPLICATION_JSON)

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Ok"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 500, message = "Server error") })

	public Response tradedUnitByMultipleTpnd(@ApiParam("TestDetails") TestDetails testDetails) {

		/* return CostAmendHttpResponse.ok("success"); */
		testService.postData(testDetails);
		return CostAmendHttpResponse.ok(testDetails);

	}// method

}