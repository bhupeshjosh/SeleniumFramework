package com.bhupesh.components;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReport {
	
	private static ExtentReports extent ;
	
	private ExtentReport() {}
	
	static ExtentReports getReport(){
		if(extent == null) {
			extent = new ExtentReports();
			String path = System.getProperty("user.dir") +"//Report" ;
			new File(path).mkdir();
			
			ExtentHtmlReporter reporter = new ExtentHtmlReporter(path +"/index.html");
			reporter.config().setReportName("Web Execution Result");
			reporter.config().setDocumentTitle("QA Automation");
			extent.attachReporter(reporter);		
		}
		return extent;
	}
}
