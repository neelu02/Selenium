package com.actimind.actitime.pages;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.actimind.actitime.generic.Utility;
import com.actimind.actitime.generic.WebActionUtil;

public class TasksPage extends BasePage {
	
	@FindBy(xpath="//div[contains(@class,'customerNode')]//div[@class='text']")
	private List<WebElement> customerNamesList;
	
	String cXpath = "//div[contains(@class,'customerNode')]//div[text()='customerName']/../..//div[@class='editButton']";
		
	@FindBy(xpath="//div[@class='editCustomerPanelHeader']//div[text()='ACTIONS']")
	private WebElement actionsButton;
	
	@FindBy(xpath="//div[@class='taskManagement_customerPanel']//div[text()='Delete']")
	private WebElement deleteIcon;
	
	@FindBy(xpath="//span[text()='Delete permanently']")
	private WebElement deletePermanently;
	
	@FindBy(xpath="//div[@class='unfilteredContainer']//div[@class='iScrollIndicator']")
	private WebElement scroller;
	
	
	
	public TasksPage(WebDriver driver, WebActionUtil webActionUtil) {
		super(driver, webActionUtil);
	}	
	
	
	
	public void scrolltoCustomer(String customerName) {
		
		int scrollPosition = 0;
		int newScrollPosition = 1;
		outer:
		for(;scrollPosition!=newScrollPosition;) {
			scrollPosition = scroller.getLocation().getY()+scroller.getSize().getHeight();
			for(WebElement ele:customerNamesList) {
				if(ele.getText().equals(customerName)) {
					break outer;
				}
			}
			webActionUtil.scrollElementDown(scroller, 50);
			newScrollPosition = scroller.getLocation().getY()+scroller.getSize().getHeight();
		}		
		
	}
	
	public void hoverAndClickOnCustomerSettingsIcon(String customerName) {
		for(WebElement ele:customerNamesList) {
			if(ele.getText().equals(customerName)) {
				webActionUtil.mouseHovering(ele);
				break;
			}
		}
		
		cXpath = cXpath.replace("customerName", customerName);
		WebElement settingsIcon=driver.findElement(By.xpath(cXpath));
		webActionUtil.elementClick(settingsIcon);
	}
	
	public void deleteCustomer(String customerName) {
		scrolltoCustomer(customerName);
		hoverAndClickOnCustomerSettingsIcon(customerName);
		Utility.sleepInSeconds(5);
		webActionUtil.elementJSClick(actionsButton);
		webActionUtil.elementJSClick(deleteIcon);
		Utility.sleepInSeconds(5);
		webActionUtil.elementJSClick(deletePermanently);
		Utility.sleepInSeconds(10);
	}
	
	
}






