package com.tesco.tps.util;

/**
 * @author a483 Rabindra 9 Dec 2015 09:04:36
 * 
 */
public abstract class AbstractResourceTest {
	private static ResourceTestRuleUtils utils = new ResourceTestRuleUtils();

	public static ResourceTestRuleUtils getResourceTestRuleUtils() {
		return utils;
	}
}
