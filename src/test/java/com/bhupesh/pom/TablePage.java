package com.bhupesh.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TablePage extends PageBase{
	private WebDriver driver ;
	
	public TablePage(WebDriver d) {
		super(d);
		this.driver = d;		
	}
	
	private By activerows = By.xpath("//span[@title='Delete']");
	private By deleteRow = By.xpath("//div[./div[text()='Kierra']]//span[@title='Delete']");
	
	public void deleteRow(){
		System.out.println("############Row count Before = "+ getActiveRowsCount());
		driver.findElement(deleteRow).click();
		System.out.println("#############Row count AFter delete = "+ getActiveRowsCount());		
	}
	
	private int getActiveRowsCount() {
		return driver.findElements(activerows).size();
	}

}
