package com.tesco.tps.service.impl;

import java.security.Principal;
import java.util.Collections;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import javax.ws.rs.core.Response;

import com.tesco.tps.auth.support.service.v2.UserDetails;
import com.tesco.tps.core.util.ServiceUtils;
import com.tesco.tps.domain.CostAmendRequest;
import com.tesco.tps.dto.CostAmendRequestDto;
import com.tesco.tps.model.CostAmendInput;
import com.tesco.tps.repository.CostAmendRepository;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@RunWith(MockitoJUnitRunner.class)
public class CostAmendServiceImplTest {
	private CostAmendServiceImpl costAmendServiceImpl;
	private ServiceUtils utils;
	private MapperFacade mapper = new DefaultMapperFactory.Builder().build().getMapperFacade();

	@Before
	public void setup() {
		CostAmendRepository mockCostAmendRepository = Mockito.mock(CostAmendRepository.class);
		utils = Mockito.mock(ServiceUtils.class);
		costAmendServiceImpl = new CostAmendServiceImpl(mockCostAmendRepository);
		costAmendServiceImpl.setMapperFaced(mapper);
		costAmendServiceImpl.setUtils(utils);
		Mockito.doNothing().when(utils).copyProperties(inputListOfCostAmend().getInputList().get(0), domainObject());;
		Mockito.when(mockCostAmendRepository.save(domainObject())).thenReturn(domainObject());
	}

	@Test
	public void shouldReturn200WhenInputListOfCostAmendGiven() {
		Response response = costAmendServiceImpl.creatingCostDocument(inputListOfCostAmend(), userDetailsInput());
		
		Assert.assertEquals(response.getStatus(), 200);
	
		//Assert.assertTrue(response.readEntity(String.class).contains("Success"));
	}

	
	private CostAmendInput inputListOfCostAmend() {
		CostAmendRequestDto inputCostAmendRequest = new CostAmendRequestDto();
		inputCostAmendRequest.setTradedUnitUUID("123");
		inputCostAmendRequest.setStatus("Updated");
		CostAmendInput inputList = new CostAmendInput();
		inputList.setInputList(Collections.singletonList(inputCostAmendRequest));
		return inputList;
	}

	private CostAmendRequest domainObject() {
		CostAmendRequest inputCostAmendDomain = new CostAmendRequest();
		inputCostAmendDomain.setTradedUnitUUID("123");
		inputCostAmendDomain.setStatus("Updated");
		return inputCostAmendDomain;
	}

	private Principal userDetailsInput() {
		Principal userDetails = new UserDetails();
		UserDetails.Permission permission = new UserDetails.Permission();
		permission.setName("ABC");
		return userDetails;
	}

}
