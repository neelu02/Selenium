package com.actimind.actitime.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.actimind.actitime.generic.WebActionUtil;

public class LoginPage extends BasePage {
	
	@FindBy(id="username")private WebElement usernameTextField;
	@FindBy(name="pwd")private WebElement passwordTextField;
	@FindBy(id="loginButton")private WebElement loginButton;
	
	public WebElement getUsernameTextField() {
		return usernameTextField;
	}

	public WebElement getPasswordTextField() {
		return passwordTextField;
	}

	public WebElement getLoginButton() {
		return loginButton;
	}

	public LoginPage(WebDriver driver, WebActionUtil webActionUtil) {
		super(driver, webActionUtil);
	}
	
	public HomePage login(String username, String password) {
		webActionUtil.enterKeys(usernameTextField, username);
		webActionUtil.enterKeys(passwordTextField, password);
		webActionUtil.elementClick(loginButton);
		return new HomePage(driver, webActionUtil);
	}
}
