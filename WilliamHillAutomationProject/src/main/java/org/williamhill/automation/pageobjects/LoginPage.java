package org.williamhill.automation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.williamhill.automation.core.BaseSharePage;
import org.williamhill.automation.core.SeleniumDriver;
import org.williamhill.automation.exception.PageException;
import org.williamhill.automation.locators.ElementLocators;

/**
 * This page object depicts the login page elements and functionality.
 * 
 * @author chiran
 *
 */
public class LoginPage extends BaseSharePage{

	public LoginPage(SeleniumDriver driverObj) {
		super(driverObj);
	}
	
	/**
	 * This method is to enter the username, which has retrieved from properties file.
	 */
	public void enterUserName(){
		try {
			driverObj.findAndWait(ElementLocators.USERNAME).sendKeys(driverObj.username);
		} catch (TimeoutException te) {
			throw new PageException("Exceeded time to find the username."
					+ te.getMessage());
		}
	}


	/**
	 * This method is to enter the password, which has retrieved from properties file.
	 */
	public void enterPassword(){
		try {
			driverObj.findAndWait(ElementLocators.PASSWORD).sendKeys(driverObj.password);
		} catch (TimeoutException te) {
			throw new PageException("Exceeded time to find the password."
					+ te.getMessage());
		}
	}
	
	/**
	 * This method is to click on login button.
	 */
	public HomePage clickOnLoginButton(){
		try {
			driverObj.findAndWait(ElementLocators.LOGIN).click();
			driverObj.waitUntilElementDisplayed(ElementLocators.LOGIN_COMPLETED);
			return new HomePage(driverObj);
		} catch (TimeoutException te) {
			throw new PageException("Exceeded time to find the login button."
					+ te.getMessage());
		}
	}
}
