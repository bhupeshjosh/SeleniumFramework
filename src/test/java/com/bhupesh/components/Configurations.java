package com.bhupesh.components;

public class Configurations {
	public static String getBrowser() {
		return (System.getProperty("browserName") != null ? System.getProperty("browserName") : "chrome").toUpperCase() ;
	}
	
	public static String getBaseURL() {
		return (System.getProperty("baseURL") != null ? System.getProperty("baseURL") : "https://www.naukri.com/") ;		
	}
}
