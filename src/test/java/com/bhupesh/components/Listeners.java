package com.bhupesh.components;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Listeners implements ITestListener{
	
	private ExtentTest test;
	private ThreadLocal<ExtentTest> tl = new ThreadLocal<>();
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		test = ExtentReport.getReport().createTest(result.getMethod().getMethodName());
		tl.set(test);

	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		//test.pass("PASS");
		tl.get().pass(result.toString());
		tl.get().log(Status.PASS, "My test is pass u see");
		try {
			WebDriver d = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			File srcf = ((TakesScreenshot)d).getScreenshotAs(OutputType.FILE);
			String path = System.getProperty("user.dir") +"/Report/" + result.getMethod().getMethodName() + ".png";
			try {
				FileHandler.copy(srcf, new File(path));
				tl.get().addScreenCaptureFromPath(path);				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		tl.get().fail(result.getThrowable());
		try {
			WebDriver d = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			File srcf = ((TakesScreenshot)d).getScreenshotAs(OutputType.FILE);
			String path = System.getProperty("user.dir") +"/Report/" + result.getMethod().getMethodName() + ".png";
			try {
				FileHandler.copy(srcf, new File(path));
				tl.get().addScreenCaptureFromPath(path);				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		ExtentReport.getReport().flush();
		
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}
	

}
