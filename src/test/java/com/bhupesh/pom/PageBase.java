package com.bhupesh.pom;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
	
	WebElement waitForElement(By by  ){
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
		.pollingEvery(Duration.ofMillis(10))
		.withTimeout(Duration.ofSeconds(10))
		.ignoring(Exception.class)
		.withMessage("Element not loaded after 10 seconds");
		
		WebElement ele = null;
		
		ele = wait.until(d1 -> {
			WebElement ele1 = d1.findElement(by);
			if(ele1 == null) {
				System.out.println("Element not found");
			}else {
				System.out.println("Element found");
			}
			return ele1;
		});
		
		return ele;
	}
}
