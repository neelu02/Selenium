package com.actimind.actitime.testcases;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.actimind.actitime.generic.ExcelLibrary;
import com.actimind.actitime.pages.TasksPage;
import com.actimind.actitime.pages.TimeTrackPage;

public class TC003 extends BaseTest {
	
	String customerName;
	String sheetName;
	
	@Test(description="user should be able to create a task")
	public void testTaskCreation() {
		
		sheetName="TC002";
		
		String option=ExcelLibrary.getData(XL_PATH, sheetName, 1, 0);
		customerName=ExcelLibrary.getData(XL_PATH, sheetName, 1, 1);
		String projectName=ExcelLibrary.getData(XL_PATH, sheetName, 1, 2);
		String taskName=ExcelLibrary.getData(XL_PATH, sheetName, 1, 3);
		String estimateHours=ExcelLibrary.getData(XL_PATH, sheetName, 1, 4).split("\\.")[0];
		String day=ExcelLibrary.getData(XL_PATH, sheetName, 1, 5).split("\\.")[0];
		String menuName1=ExcelLibrary.getData(XL_PATH, sheetName, 1, 6);
		
		TimeTrackPage timeTrackPage=(TimeTrackPage)homePage.clickOnMenu(menuName1);
		timeTrackPage.createNewTask(option, customerName, projectName,taskName, estimateHours, day);
		Assert.assertEquals(timeTrackPage.isTaskDisplayed(taskName), true);
		timeTrackPage.hoverOnTaskNameTimeSpentHideAndSaveChanges(taskName);
		Assert.assertEquals(timeTrackPage.isTaskDisplayed(taskName), false);
	}
	
	@AfterMethod(alwaysRun=true)
	public void deleteCustomer() {
		String menuName2=ExcelLibrary.getData(XL_PATH, sheetName, 1, 7);
		TasksPage tasksPage = (TasksPage)homePage.clickOnMenu(menuName2);
		tasksPage.deleteCustomer(customerName);
	}
}
