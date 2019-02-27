package org.williamhill.automation.pageobjects;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.williamhill.automation.core.BaseSharePage;
import org.williamhill.automation.core.SeleniumDriver;
import org.williamhill.automation.exception.PageException;
import org.williamhill.automation.locators.ElementLocators;

/**
 * 
 * This Page object depicts the elements and functionality present on Bet Slip Tab Page.
 * 
 * @author chiran
 *
 */
public class BetSlipPage extends BaseSharePage{

	private static Log logger = LogFactory.getLog(HomePage.class);
	
	public BetSlipPage(SeleniumDriver driverObj) {
		super(driverObj);
	}
	

	/**
	 * This method verifies the selected supporting team is on betslip or not and returns boolean value accordingly.
	 * 
	 * @return boolean
	 */
	public boolean isSupportingTeamDisplayedOnBetSlip(String teamName){
		try{
			return driverObj.findAndWait(By.xpath(String.format(ElementLocators.SELECTED_FAVOURITE_TEAM_BETSLIP,teamName))).isDisplayed();
		} catch (TimeoutException te) {
			logger.info("Exceeded time to find the supporting Team on betslips.");
		}
		return false;
	}
	
	/**
	 * This method gets selected supporting team betting odds is on betslip.
	 * 
	 * @return String
	 */
	public String getBettingOdds(String teamName){
		try{
			return driverObj.findAndWait(By.xpath(String.format(ElementLocators.SELECTED_FAVOURITE_TEAM_BET,teamName))).getText();
		} catch (TimeoutException te) {
			logger.info("Exceeded time to find the supporting Team bet value on betslips.");
			return StringUtils.EMPTY;
		}
	}
	

	/**
	 * This method enters the given betting stake amount into given teamName's stake field on betslip .
	 * 
	 * @param teamName
	 * @param amount
	 */
	public void enterStakeAmount(String teamName, String amount){
		try{
			driverObj.findAndWait(By.xpath(String.format(ElementLocators.BET_PRICE_INPUT,teamName))).sendKeys(amount);
		} catch (TimeoutException te) {
			throw new PageException("Exceeded time to find the stake input field on betslips.");
		}
	}
	
	/**
	 * This method clicks on Place a bet button on betslip page.
	 * 
	 * @return BetRecieptPage
	 */
	public BetRecieptPage clickOnPlaceABetButton(){
		try{
			driverObj.findAndWait(ElementLocators.PLACE_A_BET_BUTTON).click();
			return new BetRecieptPage(driverObj);
		} catch (TimeoutException te) {
			throw new PageException("Exceeded time to find the Place A Bet button on betslip.");
		}
	}

}