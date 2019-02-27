package org.williamhill.automation.pageobjects;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.williamhill.automation.constants.ApplicationConstants;
import org.williamhill.automation.core.BaseSharePage;
import org.williamhill.automation.core.SeleniumDriver;
import org.williamhill.automation.exception.PageException;
import org.williamhill.automation.locators.ElementLocators;

/**
 * This page objects depicts the landing or home page of WilliamHill application.
 * And it holds elements and functions to access the home page functionality.
 * 
 * @author chiran
 *
 */
public class HomePage extends BaseSharePage{
	
	private static Log logger = LogFactory.getLog(HomePage.class);

	public HomePage(SeleniumDriver driverObj) {
		super(driverObj);
		logger.info("Accessing William Hill HomePage");
	}

	/*
	 * Navigates to the betting page by selecting from the Betting product appearing in navigation.
	 * 
	 */
	public void navigateToBettingPage() {
		
		getNavigation().selectProduct(ApplicationConstants.BETTING);
	}
	
	/**
	 * This method is to select the Competitions from home page
	 * 
	 * @return CompetitionsPage
	 */
	public CompetitionsPage selectCompetetions(){
		try {
			driverObj.findAndWait(By.xpath(String.format(ElementLocators.CONTEST_NAVIGATION,ApplicationConstants.COMPETETIONS)))
					.click();
			
			return new CompetitionsPage(driverObj);
		} catch (TimeoutException te) {
			throw new PageException(
					"Exceeded time to find the Competetions Item on home page."
							+ te.getMessage());
		}

	}
}