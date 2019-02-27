package org.williamhill.automation.locators;

import org.openqa.selenium.By;

/**
 * This class contains module level locators.
 * 
 * @author Chiran
 *
 */
public class ElementLocators {

	/**
	 * EXPAND_HOME_PAGE_PRODUCT Selector to open the home page products tab.
	 */
	public static final By EXPAND_HOME_PAGE_PRODUCT = By
			.cssSelector(".product-tab.desktop-hide");

	/**
	 * LOGIN_COMPLETED locator is to verify the Account value is displayed after login completion.
	 */
	public static final By LOGIN_COMPLETED = By.xpath("//span[@class='account-tab__text -account']/span[text()='Account']");
	
	/**
	 * SPORTS_TAB locator to find the sports tab on Home Page.
	 */
	public static final By SPORTS_TAB = By.cssSelector(".localnavigation__button.-primary");
	
	/**
	 * SPORTS_NAME selector string to generate locator based on sports name
	 */
	public static final String SPORTS_NAME = "//div[@class='localnavigation-az-sports']/following-sibling::a//span[text()='%s']";

	/**
	 * CONTEST_NAVIGATION selector string to generate locator based on given
	 * contest name.
	 */
	public static final String CONTEST_NAVIGATION = "//nav[@id='carousel']//span[@class='contextual-nav__title'][text()='%s']";
	
	/**
	 * COMPETITIONS_EXPANSION_BUTTON locator for competitions expansion button
	 */
	public static final String COMPETITIONS_EXPANSION_BUTTON = "div aside a";

	/**
	 * Locator to get ALL_CONTESTS displayed on page
	 */
	public static final By ALL_CONTESTS = By
			.cssSelector("#competitions-tab-content li.header-dropdown.header-dropdown--level1");

	/**
	 * CONTEST locator to find the given contest name element
	 */
	public static final String CONTEST = "//li[@class='-expanded']//li/p[text()='%s']/../div";

	/**
	 * TEAMS locator to locate all teams under contest
	 */
	public static final By TEAMS = By.cssSelector(".btmarket__link-name");

	/**
	 * MATCH_BETWEEN locator to find the exact match under contest.
	 */
	public static final String MATCH_BETWEEN = "//a[@title='%s']";

	/**
	 * SHOW_MORE locator to find the show more button on results list
	 */
	public static final By SHOW_MORE = By.xpath("//a[text()='Show more']");

	/**
	 * SELECT_TEAM_TO_SUPPORT locator to find the supporting to place odds on
	 * it.
	 */
	public static final String SELECT_TEAM_TO_SUPPORT = ".//p[@class='btmarket__name']/span[text()='%s']/../../button";

	/**
	 * BETSLIP_COUNT locator to find the no of bets on slip.
	 */
	public static final By BETSLIP_COUNT = By.id("mobile-betslip-count");

	/**
	 * BETSLIP_LINK locator to find the bet slip link element.
	 */
	public static final By BETSLIP_LINK = By.id("mobile-betslip-link");

	
	//######################### Bet Slip Locators #################################

	/**
	 * Selected Favourite team locator on Betslip
	 */
	public static final String SELECTED_FAVOURITE_TEAM_BETSLIP = "//aside//label[text()='%s']";

	/**
	 * Selected Favourite team odds locator on betslip
	 */
	public static final String SELECTED_FAVOURITE_TEAM_BET = "//aside//label[text()='%s']/../span[@class='price ng-binding']";

	/**
	 * Bet price locator on betslip
	 */
	public static final String BET_PRICE_INPUT = "//aside//label[text()='%s']/../../../..//input[contains(@class,'stake-input')]";

	/**
	 * Estimated returns locator on betslip
	 */
	public static final String ESTIMATED_BET_RETURNS = "//aside//label[text()='%s']/../../../..//span[@class='estimated-returns']/span";

	/**
	 * Place A Bet button locator on betslip
	 */
	public static final By PLACE_A_BET_BUTTON = By.id("place-bet-button");

	
	//############################# BET RECIEPT DETAILS ##################################

	/**
	 * Successful Bet Message locator on betslip
	 */
	public static final By BET_PLACED_SUCCESSFUL_MESSAGE = By
			.cssSelector(".betslip-notice-box>p");

	/**
	 * Reciept Event Name on Successful Bet Message locator on betslip
	 */
	public static final By EVENT_NAME_ON_BET_SUCCESS_MSG = By
			.cssSelector(".receipt-event-name.ng-binding");

	/**
	 * Selection Price locator on Bet Receipt
	 */
	public static final By SELECTION_PRICE_ON_BET_RECIEPT = By
			.cssSelector(".selection-price.ng-binding");

	/**
	 * Stake Price locator on Bet Receipt
	 */
	public static final By STAKE_PRICE_ON_BET_RECIEPT = By
			.cssSelector(".stake-price.ng-binding");

	/**
	 * Estimated Returns locator on Bet Receipt
	 */
	public static final By ESTIMATED_RETURNS_ON_BET_RECIEPT = By
			.cssSelector(".estimated-returns.ng-binding");

	
	//############################ LOGIN LOCATORS #######################################

	/**
	 * Login Tab locator on landing page
	 */
	public static final By LOGIN_TAB = By.className("account-tab__button");

	/**
	 * Username locator on login tab
	 */
	public static final By USERNAME = By
			.cssSelector("form input[name='username']");

	/**
	 * Password locator on landing tab
	 */
	public static final By PASSWORD = By
			.cssSelector("form input[name='password']");

	/**
	 * Login button locator on landing tab
	 */
	public static final By LOGIN = By
			.cssSelector("button span[data-translate='SBHEADER_LOGIN']");
}