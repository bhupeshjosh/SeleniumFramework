package com.bhupesh.components;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;

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
	
/**
 * Listener constructor is called once before the execution.
 * The No Arg constructor
 * When a test will start it will instantiate the instance variable inside the 
 * Object created for Listener. 
 * obj ---> { test,}
 * 
 * obj.test = assigned.
 * in case of multiple threads this value will get overridden
 * hence we can save them using ThreadLocal class
 * Now even if obj.test value will be overridden
 * but
 * the test object will be stored in the thread as a local variable.
 * 
 */
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		test = ExtentReport.getReport().createTest(result.getMethod().getMethodName());
		tl.set(test);

	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		//System.out.println("Test To string" + test.toString());
		//System.out.println("CUrrent Test To string" + tl.get().toString());		
		// TODO Auto-generated method stub
		//test.pass("PASS");
		tl.get().pass(result.toString());
		tl.get().log(Status.PASS, "My test is pass u see");
		try {
			//Object obj = result.getInstance();			
			// get the driver using getField 
			// WebDriver d = (WebDriver)obj.getClass().getField("driver").get(obj);
			/*
			 * Or Create a method getDriver in base class extended by all test classes
			 * get this method using getMethod
			 * then invoke this method. which will return the driver instance.
			 *
			Method getDriverMethod = obj.getClass().getMethod("getDriver", null);
			WebDriver d1 = (WebDriver)getDriverMethod.invoke(obj, null);
			if(d1 == d) {
				System.out.println("@@@@@@@@@ BOTH DRIVER EQUAL@@@@@@@@@");
			}
			*/
			//Method getDriverMethod = obj.getClass().getMethod("getDriver");
			//WebDriver d = (WebDriver)getDriverMethod.invoke(obj);
			
			// Or use below . Result.getInstance() will provide the test class instance
			// upcaste it to TestBase and call getDriver() method defined there.
			TestBase obj = (TestBase)result.getInstance();
			WebDriver d = obj.getDriver();
			obj = null;
			
			File srcf = ((TakesScreenshot)d).getScreenshotAs(OutputType.FILE);
			String path = System.getProperty("user.dir") +"/Report/" + result.getMethod().getMethodName() + ".png";			
			try {
				//FileHandler.copy(srcf, new File(path));
				Files.copy(srcf.toPath(), Paths.get(path));
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
			//WebDriver d = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			WebDriver d = null;
			d = ((TestBase)result.getInstance()).getDriver();
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
