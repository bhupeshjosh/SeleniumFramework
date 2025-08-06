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
			File directory = new File(path);
			if(new File(path).exists()) {
				for(File x : directory.listFiles()) {
					x.delete();
				}
			}
			directory.mkdir();
			
			ExtentHtmlReporter reporter = new ExtentHtmlReporter(path +"/index.html");
			reporter.config().setReportName("Web Execution Result");
			reporter.config().setDocumentTitle("QA Automation");
			extent.attachReporter(reporter);		
		}
		return extent;
	}
}
