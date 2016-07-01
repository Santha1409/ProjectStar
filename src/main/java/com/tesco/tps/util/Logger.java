package com.tesco.tps.util;

/*#Test
*/
/**
 * @author a483 Rabindra 27 Aug 2015 09:10:37
 * 
 */
public class Logger {
	public static final org.apache.log4j.Logger CAS_CORE = org.apache.log4j.Logger.getLogger("CAS-Service : ");
	public static final org.apache.log4j.Logger CAS_DB = org.apache.log4j.Logger.getLogger("CAS-Database : ");
	public static final org.apache.log4j.Logger CAS_CONFIG = org.apache.log4j.Logger
			.getLogger("TPS-Config : Config : ");
}