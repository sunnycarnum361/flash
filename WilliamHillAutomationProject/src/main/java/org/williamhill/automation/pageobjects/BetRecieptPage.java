package org.williamhill.automation.pageobjects;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.TimeoutException;
import org.williamhill.automation.core.BaseSharePage;
import org.williamhill.automation.core.SeleniumDriver;
import org.williamhill.automation.locators.ElementLocators;

/**
 * This page object depicts the elements and functionalities on Bet reciept page.
 * 
 * @author chiran
 *
 */
public class BetRecieptPage extends BaseSharePage{

	private static Log logger = LogFactory.getLog(HomePage.class);
	
	public BetRecieptPage(SeleniumDriver driverObj) {
		super(driverObj);
	}

	/**
	 * This method verifies the successful message(Bet placed, good luck!) is displayed or not and returns the boolean value accordingly.
	 * 
	 * @return boolean
	 */
	public boolean isBetPlacedSuccessfulMessageDisplayed(){
		try{
			return driverObj.findAndWait(ElementLocators.BET_PLACED_SUCCESSFUL_MESSAGE).isDisplayed();
		} catch (TimeoutException te) {
			logger.info("Exceeded time to find the bet placed successful message on bet reciept.");
		}
		return false;
	}
	
	/**
	 * This method gets selected supporting team stake value is on bet reciept.
	 * 
	 * @return String
	 */
	public String getStakeValueFromReciept(){
		try{
			return driverObj.findAndWait(ElementLocators.STAKE_PRICE_ON_BET_RECIEPT).getText();
		} catch (TimeoutException te) {
			logger.info("Exceeded time to find the stake value on bet reciept.");
			return StringUtils.EMPTY;
		}
	}
	
	/**
	 * This method gets selected supporting team betting odds is on bet reciept.
	 * 
	 * @return String
	 */
	public String getBetOddsOnReciept(){
		try{
			return driverObj.findAndWait(ElementLocators.SELECTION_PRICE_ON_BET_RECIEPT).getText();
		} catch (TimeoutException te) {
			logger.info("Exceeded time to find the bet odds on bet reciept.");
			return StringUtils.EMPTY;
		}
	}
	
	/**
	 * This method verifies the estimated returns value is displayed or not and returns the boolean value accordingly.
	 * 
	 * @return boolean
	 */
	public boolean isEstimatedReturnsValuePresent(){
		try{
			return driverObj.findAndWait(ElementLocators.ESTIMATED_RETURNS_ON_BET_RECIEPT).isDisplayed();
		} catch (TimeoutException te) {
			logger.info("Exceeded time to find the bet estimated returns on bet reciept.");
		}
		return false;
	}
	
}
