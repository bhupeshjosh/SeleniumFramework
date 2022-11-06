package com.bhupesh.components;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{
	private int count = 0;
	private int maxcount = 1;
	
	@Override
	public boolean retry(ITestResult result) {
		if(count < maxcount) {
			++count ;
			return true;
		}
		return false;
	}

}
