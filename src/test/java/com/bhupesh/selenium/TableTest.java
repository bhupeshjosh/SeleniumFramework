package com.bhupesh.selenium;

import org.testng.annotations.Test;

import com.bhupesh.components.TestBase;
import com.bhupesh.pom.LoginPage;
import com.bhupesh.pom.TablePage;

public class TableTest extends TestBase{
	
	@Test(groups = {"smoke"})
	public void DeleteTableRecordsTest() {
		LoginPage lp = new LoginPage(driver);
		lp.Login();
		lp.navigateToWebTable();
		System.out.println(lp.GetPageTitle());
		
		TablePage tp = new TablePage(driver);
		tp.deleteRow();
	}
}
