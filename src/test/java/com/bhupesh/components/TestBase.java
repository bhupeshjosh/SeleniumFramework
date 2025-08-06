package com.bhupesh.components;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.logging.Level;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public abstract class TestBase {
	
	protected WebDriver driver;
	
	public TestBase() {
		System.out.println("Base constructor called");
	/*
	 *  DOnt use this code. It was written to test multithreding execution by a TestRun class
	 *  */
	/*	WebDriverManager.chromedriver().setup();			
		ChromeOptions opt = new ChromeOptions();
		opt.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(opt);
	  */		
	}
	@BeforeMethod(alwaysRun = true)
	public void setup(Method m) {
		System.out.println("<<<<<<<<<<<<<Before Test :" + m.getName()+ ">>>>>>>>>>>>>");
	}

	public WebDriver getDriver() {
		return driver;
	}
	
	@AfterMethod(alwaysRun = true)
	public void teardown(Method m, ITestResult r) {
		Reporter.log(m.getName() + " method ends");
		System.out.println("<<<<<<<<<<<<<Test Status :" + r.getStatus() + ">>>>>>>>>>>>>");		
		System.out.println("<<<<<<<<<<<<<Before Test :" + m.getName()+ ">>>>>>>>>>>>>");	
		
		File srcf = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileHandler.copy(srcf, new File("/Users/bhupeshjoshi/SeleniumTestResult/" + m.getName() + ".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	@BeforeSuite(alwaysRun = true)
	public void Initialize() {
		if(Configurations.getBrowser().contains("CHROME")) {
			WebDriverManager.chromedriver().setup();			
		}
		else if(Configurations.getBrowser().contains("FIREFOX")) {
			WebDriverManager.firefoxdriver().setup();			
		}
		java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.SEVERE);		
	}
	
	@BeforeClass(alwaysRun = true)
	public void PrepareTest() {
		if(Configurations.getBrowser().contains("CHROME")) {
			ChromeOptions opt = new ChromeOptions();
			opt.addArguments("--remote-allow-origins=*");
			System.setProperty("webdriver.chrome.silentOutput", "true");
			driver = new ChromeDriver(opt);		
		}
		else if(Configurations.getBrowser().contains("FIREFOX")) {
			driver = new FirefoxDriver();		
		}		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
	}
	
	@AfterClass(alwaysRun = true)
	public void ExitClass() {
		driver.quit();
		System.out.println("<<<<<<<<<<<<<<<<<driver instance closed>>>>>>>>>>>>>>>>>");
	}
	
	
}
