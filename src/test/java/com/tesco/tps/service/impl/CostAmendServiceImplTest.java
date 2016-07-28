package com.tesco.tps.service.impl;

import static org.junit.Assert.*;

import java.security.Principal;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.tesco.tps.auth.support.service.v2.UserDetails;
import com.tesco.tps.dto.CostAmendRequestDto;
import com.tesco.tps.model.CostAmendInput;
import com.tesco.tps.repository.CostAmendRepository;

@RunWith(MockitoJUnitRunner.class)
public class CostAmendServiceImplTest {
	private CostAmendServiceImpl costAmendServiceImpl;
	private CostAmendInput costDocumentList = null;
	private UserDetails userDetails = new UserDetails();

	private CostAmendRequestDto costAmendRequestDto;;

	@Before
	public void setUp() throws Exception {
		CostAmendRepository costAmendRepository = Mockito.mock(CostAmendRepository.class);
		costAmendServiceImpl = new CostAmendServiceImpl(costAmendRepository);
		costDocumentList = new CostAmendInput();
		costAmendRequestDto = new CostAmendRequestDto();
		costAmendRequestDto.setTradedUnitUUID("123");
		costDocumentList = new CostAmendInput();
		costDocumentList.setInputList(Collections.singletonList(costAmendRequestDto));

	}

	@Test
	public void shouldReturn200() {
		assertEquals(costAmendServiceImpl.creatingCostDocument(costDocumentList, userDetails), 200);

	}

	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(CostAmendServiceImplTest.class);
	}

}
