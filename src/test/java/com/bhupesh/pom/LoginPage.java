package com.bhupesh.pom;

import java.time.Duration;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;



public class LoginPage extends PageBase{
	private WebDriver driver ;
	
	private By username = By.id("userName");
	private By password = By.id("password");
	private By btnLogin = By.id("login");
	private By deleteRow = By.xpath("//div[./div[text()='Kierra']]//span[@title='Delete']");
	private By element = By.xpath("//div[text()='Elements']");
	private By webTable = By.xpath("//span[text()='Web Tables']");
	private By logout = By.xpath("//button[@id='submit']");
	
	public LoginPage(WebDriver d) {
		// TODO Auto-generated constructor stub
		super(d);
		this.driver = d;
	}
	
	public void Login() {
		driver.get("https://demoqa.com/login");		
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
		.pollingEvery(Duration.ofMillis(10))
		.withTimeout(Duration.ofSeconds(10))
		.ignoring(Exception.class)
		.withMessage("Page not loaded after 10 seconds");
		
		Function<WebDriver , WebElement> f = (d) -> {
			System.out.println("ˍˍˍˍˍˍˍˍelement search start----------");
			WebElement e = d.findElement(btnLogin);
			if (e == null) {
				System.out.println("login btn not found");
			}

			if (e != null) {
				System.out.println("ˍˍˍˍˍˍˍˍelement search end----------");
			}			
			return e;			
		};
		WebElement ele = null;
		ele = wait.until(f);
		
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView()", ele);
		
		System.out.println("***********login page found*************");
		driver.findElement(username).sendKeys("testuser");
		driver.findElement(password).sendKeys("Password@123");
		driver.findElement(btnLogin).click();
		
	}
	
	public void navigateToWebTable() {
		driver.findElement(element).click();
		driver.findElement(webTable).click();
		waitForElement(deleteRow);
	}
	
	public WebElement logout() {
		WebElement logoutButton = waitForElement(logout);
		Assert.assertFalse(logoutButton.equals(null));
		logoutButton.click();
		WebElement userNameElement = waitForElement(username);
		
		
		return userNameElement;
	}
	
}
