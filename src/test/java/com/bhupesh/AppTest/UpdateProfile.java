package com.bhupesh.AppTest;

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
import org.testng.annotations.AfterClass;
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
	By uploadResume  = By.id("attachCV");
	By successMsg = By.xpath("//p[text()='Success']");
	
	public WebDriver driver =null;
	
	public WebDriver getDriver() {
		return driver;
	}
	
	@Test(groups= {"job"})	
	public void updateProfile() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
        ChromeOptions opt = new ChromeOptions();
        //opt.setPageLoadStrategy(PageLoadStrategy.EAGER);
        opt.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(opt);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
				
		driver.get("https://www.naukri.com/");		
		wait(driver,btnLogin, "Login button");
		driver.findElement(btnLogin).click();
		System.out.println("***********login page found*************");
		driver.findElement(username).sendKeys("bhupeshait@gmail.com");
		driver.findElement(password).sendKeys("Bhup$123");
		driver.findElement(btnLoginnow).click();	

		
		wait(driver,editProfile, "editProfile");		
		driver.findElement(editProfile).click();
		
		wait(driver,editHeadline, "editHeadline");
		driver.findElement(editHeadline).click();		
		
		wait(driver,headlineText, "headlineText");
		Thread.sleep(4000);
		String textVal = driver.findElement(headlineText).getText();
		
		// Check if Headline text passed as system property
		textVal = System.getProperty("Headline") == null?ReverseText(textVal) : System.getProperty("Headline");

		driver.findElement(headlineText).clear();
		driver.findElement(headlineText).sendKeys(textVal);
		
		wait(driver, save , "save button");
		driver.findElement(save).click();

		WebDriverWait waits = new WebDriverWait(driver, Duration.ofSeconds(10));
		waits.until(ExpectedConditions.invisibilityOfElementLocated(headlineText));
		
	}
	
	@Test(groups= {"job"})
	public void updateResume() {
		wait(driver, uploadResume , "uploadResume");
		driver.findElement(uploadResume).sendKeys("/Users/bhupeshjoshi/Downloads/Bhupesh_Joshi_QA.docx");
		//driver.findElement(updateResume).sendKeys("/Users/bhupeshjoshi/Downloads/BHUPESH JOSHI_React_Developer.docx");
		wait(driver, successMsg, "successMsg");
	
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(successMsg));
	}
	
	private WebElement wait(WebDriver driver, By by , String name) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
		.pollingEvery(Duration.ofMillis(10))
		.withTimeout(Duration.ofSeconds(30))
		.ignoring(Exception.class)
		.withMessage("Page not loaded after 30 seconds");
		
		Function<WebDriver , WebElement> f = (d) -> {
			System.out.println(String.format("ˍˍˍˍˍˍˍˍ%s element search start----------",name));
			WebElement e = d.findElement(by);
			if (e == null) {
				System.out.println("element not found");
			}

			if (e != null) {
				System.out.println(String.format("ˍˍˍˍˍˍˍˍ%s element search end----------",name));
			}			
			return e;			
		};
		
		wait.until(f);		
		return null;
	}

	
	@AfterClass(alwaysRun = true)
	private void closeSession() {
		driver.quit();
	}
	
	private String ReverseText(String argtext) {
		char[] textarr = argtext.toCharArray();
		char temp = 'a';
		int len = textarr.length - 1;
		for(int i=0; i< textarr.length/2 ; i++) {
			temp = textarr[len-i] ;
			textarr[len-i] = textarr[i];
			textarr[i] = temp;
		}		
		return new String(textarr);
	}
}
