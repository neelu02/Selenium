package com.actimind.actitime.generic;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class WebActionUtil {
	WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;
	Actions actions;
	
	public WebActionUtil(WebDriver driver, long ETO) {
		this.driver=driver;
		wait = new WebDriverWait(driver,ETO);
		js = (JavascriptExecutor)driver;
		actions = new Actions(driver);
	}
	
	public void enterKeys(WebElement target, String keyToEnter) {
		target.clear();
		target.sendKeys(keyToEnter);
	}
	
	public void elementClick(WebElement target) {
		wait.until(ExpectedConditions.elementToBeClickable(target)).click();
	}
	
	public void elementJSClick(WebElement target) {
		js.executeScript("arguments[0].click();", target);
	}
	
	public void scrollDown(int pixels) {
		js.executeScript("window.scrollBy(0,arguments[0]);", pixels);
	}
	
	public void enterJSKeys(WebElement target, String keyToEnter) {
		js.executeScript("arguments[0].value=''", target);
		js.executeScript("arguments[0].value=arguments[1]", target, keyToEnter);
	}
	
	public void mouseHovering(WebElement target) {
		actions.moveToElement(target).perform();
	}
	
	public void dragAndDrop(WebElement source, WebElement target) {
		actions.dragAndDrop(source,target).perform();
	}
	
	public void selectByVisibleText(WebElement target, String visibleText) {
		Select s = new Select(target);
		s.selectByVisibleText(visibleText);
	}
	
	public void switchToFrame(String indexNameOrId) {
		try {
			int index = Integer.parseInt(indexNameOrId);
			driver.switchTo().frame(index);
		} catch(NumberFormatException e) {
			driver.switchTo().frame(indexNameOrId);
		}		
	}
	
	public void scrollElementDown(WebElement target, int pixels) {
		actions.dragAndDropBy(target, 0, pixels).perform();
	}
	
	public void waitUntilUrlContains(String text) {
		wait.until(ExpectedConditions.urlContains(text));
	}
	
	public void verifyTextAndSwitchToAlertAndAccept(String alertText) {
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert=driver.switchTo().alert();
		if(!alert.getText().contains(alertText)) {
			throw new IllegalArgumentException();
		}
		alert.accept();
	}
}




