package com.bhupesh.pom;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class PageBase {
	
	private WebDriver driver ;
	
	PageBase(WebDriver d){
		this.driver = d;
	}
	
	public String GetPageTitle() {
		return driver.getTitle();
	}
	
	WebElement waitForElement(By by  , String elementDescName){
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
		.pollingEvery(Duration.ofMillis(10))
		.withTimeout(Duration.ofSeconds(10))
		.ignoring(Exception.class)
		.withMessage(String.format("Element '%s'not loaded after 10 seconds",elementDescName));
		
		WebElement ele = null;
		
		ele = wait.until(d1 -> {
			WebElement ele1 = d1.findElement(by);
			if(ele1 == null) {
				System.out.println(String.format("Element '%s' not found",elementDescName));
			}else {
				System.out.println(String.format("Element '%s' found",elementDescName));
			}
			return ele1;
		});
		
		return ele;
	}
	
	public void clickElement(By by, String name){
		WebElement e = waitForElement(by, name);
		Actions act = new Actions(driver);
		act.scrollToElement(e).build().perform();	
		act.pause(Duration.ofSeconds(2)).build().perform();
		System.out.println("Element "+ name +" is scrolled");
		if(e.isDisplayed() && e.isEnabled()) {
			e.click();
		}
		else {
			act.scrollByAmount(0, 200);
			//throw new ElementNotInteractableException("Element "+name +"not interactable");
		}		
		System.out.println("Element "+ name +" is clicked");
	}
}
