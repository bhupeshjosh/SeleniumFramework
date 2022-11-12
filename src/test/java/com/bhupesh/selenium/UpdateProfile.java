package com.bhupesh.selenium;

import java.time.Duration;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;


public class UpdateProfile {
	
	By btnLogin = By.cssSelector("a[title='Jobseeker Login']");
	By username = By.xpath("//div[./label[text()='Email ID / Username']]/input");
	By password = By.xpath("//div[./label[text()='Password']]/input");
	By btnLoginnow	= By.xpath("//button[@type='submit']");
	By editProfile = By.cssSelector("div.profile-section>a");
	By editHeadline = By.cssSelector("div.resumeHeadline .edit");
	By headlineText = By.id("resumeHeadlineTxt");
	By save = By.xpath("//button[@type='submit' and text()='Save']");
	
	
	public WebDriver driver =null;
	
	@Test(groups= {"job"})	
	public void updateProfile() {
		WebDriverManager.chromedriver().setup();
        ChromeOptions opt = new ChromeOptions();
        //opt.setPageLoadStrategy(PageLoadStrategy.EAGER);
        
		driver = new ChromeDriver(opt);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		
		
		
		driver.get("https://www.naukri.com/");		
		wait(driver,btnLogin);
		driver.findElement(btnLogin).click();
		System.out.println("***********login page found*************");
		driver.findElement(username).sendKeys("bhupeshait@gmail.com");
		driver.findElement(password).sendKeys("Bhup$123");
		driver.findElement(btnLoginnow).click();	

		
		wait(driver,editProfile);		
		driver.findElement(editProfile).click();
		
		wait(driver,editHeadline);
		driver.findElement(editHeadline).click();		
		
		wait(driver,headlineText);
		String textVal = driver.findElement(headlineText).getText();

		char[] textarr = textVal.toCharArray();
		char temp = 'a';
		int len = textarr.length - 1;
		for(int i=0; i< textarr.length/2 ; i++) {
			temp = textarr[len-i] ;
			textarr[len-i] = textarr[i];
			textarr[i] = temp;
		}
		driver.findElement(headlineText).clear();
		driver.findElement(headlineText).sendKeys(new String(textarr));
		
		wait(driver, save);
		driver.findElement(save).click();

		WebDriverWait waits = new WebDriverWait(driver, Duration.ofSeconds(5));
		waits.until(ExpectedConditions.invisibilityOfElementLocated(headlineText));
		
	}
	
	private WebElement wait(WebDriver driver, By by) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
		.pollingEvery(Duration.ofMillis(10))
		.withTimeout(Duration.ofSeconds(30))
		.ignoring(Exception.class)
		.withMessage("Page not loaded after 30 seconds");
		
		Function<WebDriver , WebElement> f = (d) -> {
			System.out.println("ˍˍˍˍˍˍˍˍelement search start----------");
			WebElement e = d.findElement(by);
			if (e == null) {
				System.out.println("element not found");
			}

			if (e != null) {
				System.out.println("ˍˍˍˍˍˍˍˍelement search end----------");
			}			
			return e;			
		};
		
		wait.until(f);		
		return null;
	}

	@AfterTest(alwaysRun = true)
	private void closeSession() {
		driver.quit();
	}
}
