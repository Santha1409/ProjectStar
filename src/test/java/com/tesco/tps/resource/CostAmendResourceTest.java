package com.tesco.tps.resource;

import static org.junit.Assert.fail;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.tesco.tps.model.CostAmendInput;
import com.tesco.tps.service.CostAmendService;
import com.tesco.tps.util.AbstractResourceTest;

import io.dropwizard.testing.junit.ResourceTestRule;

@RunWith(MockitoJUnitRunner.class)
public class CostAmendResourceTest extends AbstractResourceTest{
	
@Mock
static
CostAmendService costAmendService;

@ClassRule
public static final ResourceTestRule resources = getResourceTestRuleUtils()
		.getResourceTestRule(new CostAmendResource(costAmendService));

	@Test
	public void shouldReturn200(CostAmendInput listOfCostCreate) {
		
		fail("Not yet implemented");
	}

}
