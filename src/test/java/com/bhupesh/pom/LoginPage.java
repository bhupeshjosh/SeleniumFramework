package com.bhupesh.pom;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
		WebElement ele = waitForElement(btnLogin, "Login Button");
		Actions act = new Actions(driver);
		act.scrollToElement(ele).scrollByAmount(0, 200).build().perform();
		new Actions(driver).pause(Duration.ofSeconds(10));
		//((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView()", ele);
		
		System.out.println("***********login page found*************");
		act.scrollToElement(waitForElement(username, "Username"));
		driver.findElement(username).sendKeys("testuser");
		act.scrollToElement(waitForElement(password, "Password"));
		driver.findElement(password).sendKeys("Password@123");
		clickElement(btnLogin,"Login Button");
		
	}
	
	public void navigateToWebTable() {
		//driver.findElement(element).click();
		clickElement(element,"elements button");
		driver.findElement(webTable).click();
		//clickElement(webTable, "Table");
		waitForElement(deleteRow,"Delete Row button");
	}
	
	public WebElement logout() {
		WebElement logoutButton = waitForElement(logout, "Logout button");
		Assert.assertFalse(logoutButton.equals(null));
		logoutButton.click();
		WebElement userNameElement = waitForElement(username, "UserName");
		
		
		return userNameElement;
	}
	
}
