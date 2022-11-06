package com.bhupesh.selenium;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.bhupesh.components.TestBase;
import com.bhupesh.pom.LoginPage;

public class HomePage extends TestBase{

	@Test(retryAnalyzer = com.bhupesh.components.Retry.class)
	public void LaunchTest() {
		// TODO Auto-generated method stub		
		new LoginPage(driver).Login();
		String title = driver.getTitle();
		Assert.assertEquals(title, "ToolsQA");
	}
	
	@Test(dependsOnMethods = {"LaunchTest"} )
	public void Logout() {
		WebElement userName = new LoginPage(driver).logout();
		Assert.assertFalse(userName.equals(null));		
	}
		
}
