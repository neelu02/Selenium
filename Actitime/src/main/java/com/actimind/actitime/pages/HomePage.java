package com.actimind.actitime.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.actimind.actitime.generic.WebActionUtil;

public class HomePage extends BasePage {
	
	@FindBy(xpath="//td[contains(@class,'notSelected')]/a")
	private List<WebElement> allMenuBarLinks;
	
	@FindBy(id="logoutLink")
	private WebElement logoutLink;
	
	public List<WebElement> getAllMenuBarLinks() {
		return allMenuBarLinks;
	}

	public HomePage(WebDriver driver, WebActionUtil webActionUtil) {
		super(driver, webActionUtil);
	}
	
	public BasePage clickOnMenu(String menuName) {
		for(WebElement ele:allMenuBarLinks) {
			if(ele.getText().equalsIgnoreCase(menuName)) {
				webActionUtil.elementJSClick(ele);
				break;
			}
		}
		
		switch(menuName) {
			case "Time-Track":return new TimeTrackPage(driver, webActionUtil);
			case "Tasks":return new TasksPage(driver, webActionUtil);
			case "Users":return new UsersPage(driver, webActionUtil);
			case "Reports":return new ReportsPage(driver, webActionUtil);
			default:return null;					
		}
		
		
	}

	public void logout() {
		webActionUtil.elementJSClick(logoutLink);
		
	}
}
