package org.williamhill.automation.pageobjects;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.williamhill.automation.core.SeleniumDriver;
import org.williamhill.automation.exception.PageException;
import org.williamhill.automation.locators.ElementLocators;

/**
 * This page object depicts the navigation elements and functionality.
 * 
 * @author chiran
 *
 */
public class HomePageNavigation {
	private static Log logger = LogFactory.getLog(HomePageNavigation.class);

	private SeleniumDriver driverObj;

	public HomePageNavigation(SeleniumDriver driverObj) {
		logger.info("Accessing HomePage Navigation");
		this.driverObj = driverObj;
	}

	/**
	 * Gets the login tab.
	 * 
	 * @return LoginPage
	 */
	public LoginPage getLoginPage() {
		try {
			driverObj.findAndWait(ElementLocators.LOGIN_TAB).click();
			return new LoginPage(driverObj);
		} catch (TimeoutException te) {
			throw new PageException("Exceeded time to find the Login Tab."
					+ te.getMessage());
		}
	}

	/**
	 * Selects the product by expanding Products tab
	 * 
	 * @param string
	 * @return HomePage
	 */
	public HomePage selectProduct(String string) {
		try {
			driverObj.findAndWait(ElementLocators.EXPAND_HOME_PAGE_PRODUCT)
					.click();
			
			driverObj.findAndWait(By.linkText(string))
					.click();
			return new HomePage(driverObj);
		}catch(StaleElementReferenceException se){
			return selectProduct(string);
		}catch (TimeoutException te) {
			throw new PageException(
					"Exceeded time to find the Product Tab or item."
							+ te.getMessage());
		}

	}

	/**
	 * Selects the Sports by expanding Sports tab
	 * 
	 * @param string
	 * @return HomePage
	 */
	public HomePage selectSport(String string) { 
		try {
			driverObj.findAndWait(ElementLocators.SPORTS_TAB)
					.click();
			driverObj.findAndWait(By.xpath(String.format(ElementLocators.SPORTS_NAME, string)))
					.click();
			return new HomePage(driverObj);
		} catch (TimeoutException te) {
			throw new PageException(
					"Exceeded time to find the Sports Item."
							+ te.getMessage());
		}

	}
	
	/**
	 * Selects the BetSlip page
	 * 
	 * @return BetSlipPage
	 */
	public BetSlipPage selectBetSlipLink() {
		try {
			driverObj.findAndWait(ElementLocators.BETSLIP_LINK).click();
			return new BetSlipPage(driverObj);
		} catch (TimeoutException te) {
			throw new PageException(
					"Exceeded time to find the BetSlip Link."
							+ te.getMessage());
		}

	}

}
