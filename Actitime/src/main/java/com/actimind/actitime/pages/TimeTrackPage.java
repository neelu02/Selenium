package com.actimind.actitime.pages;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.actimind.actitime.generic.WebActionUtil;

public class TimeTrackPage extends BasePage {
	
	@FindBy(xpath="//span[text()='New']")
	private WebElement newLink;
	
	@FindBy(xpath="//div[contains(@class,'customerSelector')]//div[@class='comboboxButton']")
	private WebElement selectCustomerListBox;
	
	@FindBy(xpath="//div[contains(@class,'customerSelector')]//div[contains(@class,'itemRow ')]")
	private List<WebElement> customerListBoxItems;
	
	@FindBy(xpath="//input[@placeholder='Enter Customer Name']")
	private WebElement customerNameTextField;
	
	@FindBy(xpath="//input[@placeholder='Enter Project Name']")
	private WebElement projectNameTextField;
		
	@FindBy(xpath="//input[@placeholder='Enter task name']")
	private WebElement taskNameTextField;
	
	@FindBy(xpath="//input[@placeholder='not needed']")
	private WebElement estimateHoursTextField;
	
	@FindBy(xpath="//button[text()='set deadline']")
	private WebElement deadLineDateButton;
	
	@FindBy(xpath="//td[@class='x-date-active' or contains(@class,'x-date-activex-date-weekend') or contains(@class,'x-date-selected')]")
	private List<WebElement> selectDeadLineDate;
	
	@FindBy(xpath="//div[text()='Create Tasks']")
	private WebElement createTasksButton;
	
	String tXpath = "//div[text()='taskName']/ancestor::tr[contains(@id,'taskRow')]//a[contains(text(),'Hide')]";
	String sXpath = "//div[text()='taskName']/ancestor::tr[contains(@id,'taskRow')]//input[contains(@id,'spent')]";
	@FindBy(xpath="//div[@class='task']")
	private List<WebElement> taskNamesList;
	
	@FindBy(id="SubmitTTButton")
	private WebElement saveChangesButton;
	
	public TimeTrackPage(WebDriver driver, WebActionUtil webActionUtil) {
		super(driver, webActionUtil);
	}
	
	public void clickOnNewLink() {
		webActionUtil.elementClick(newLink);
	}
	
	public void selectOptionInCustomerListBox(String option) {
		webActionUtil.elementClick(selectCustomerListBox);
		for(WebElement ele:customerListBoxItems) {
			if(ele.getText().equalsIgnoreCase(option)) {
				webActionUtil.elementClick(ele);
				break;
			}
		}
	}
	
	public void selectDeadlineDate(String deadLineDate) {
		for(WebElement ele:selectDeadLineDate) {
			if(ele.getText().equals(deadLineDate)) {
				webActionUtil.elementClick(ele);
			}
		}
	}
	
	public void hoverOnTaskNameTimeSpentHideAndSaveChanges(String taskName) {
		for(WebElement ele:taskNamesList) {
			if(ele.getText().equalsIgnoreCase(taskName)) {
				webActionUtil.mouseHovering(ele);
				break;
			}
		}
		
		sXpath = sXpath.replace("taskName", taskName);
		WebElement timeSpent = driver.findElement(By.xpath(sXpath));
		webActionUtil.enterKeys(timeSpent, "3");
		
		tXpath = tXpath.replace("taskName", taskName);
		WebElement hideIcon=driver.findElement(By.xpath(tXpath));
		webActionUtil.elementClick(hideIcon);
		
		webActionUtil.verifyTextAndSwitchToAlertAndAccept("Time and comments reported");
		
		webActionUtil.elementClick(saveChangesButton);		
	}
	
	public void createNewTask(String option, String customerName, String projectName,
						      String taskName, String estimateHours, String deadLineDate) {
		clickOnNewLink();
		selectOptionInCustomerListBox(option);
		webActionUtil.enterKeys(customerNameTextField, customerName);
		webActionUtil.enterKeys(projectNameTextField, projectName);
		webActionUtil.enterKeys(taskNameTextField, taskName);
		webActionUtil.enterKeys(estimateHoursTextField, estimateHours);
		webActionUtil.elementClick(deadLineDateButton);
		selectDeadlineDate(deadLineDate);
		webActionUtil.elementClick(createTasksButton);
	}
	
	public boolean isTaskDisplayed(String taskName) {
		webActionUtil.waitUntilUrlContains("date");
		for(WebElement ele:taskNamesList) {
			if(ele.getText().equals(taskName)) {
				return true;
			}
		}
		return false;
	}
}
