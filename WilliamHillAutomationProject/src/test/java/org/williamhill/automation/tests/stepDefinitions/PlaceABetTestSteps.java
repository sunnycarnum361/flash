package org.williamhill.automation.tests.stepDefinitions;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.williamhill.automation.constants.ApplicationConstants;
import org.williamhill.automation.pageobjects.BetRecieptPage;
import org.williamhill.automation.pageobjects.BetSlipPage;
import org.williamhill.automation.pageobjects.CompetitionsPage;
import org.williamhill.automation.pageobjects.HomePage;
import org.williamhill.automation.pageobjects.HomePageNavigation;
import org.williamhill.automation.pageobjects.LoginPage;
import org.williamhill.automation.tests.AbstractTest;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PlaceABetTestSteps extends AbstractTest {

	private HomePage homePage;
	private HomePageNavigation navigation;
	private LoginPage loginPage;
	private CompetitionsPage competitionsPage;
	private BetSlipPage betSlipPage;
	private BetRecieptPage betRecieptPage;
	public String bettingOdds;
	public String betAmountActual;
	
@Given("^User logged into WilliamHill$")
	public void user_logged_into_WilliamHill()throws Exception{
	//userLoggedIntoWilliamHill() 
        getSeleniumDriver();
        
        navigation = getWilliamHillHomePage();
        loginPage = navigation.getLoginPage();
        
        // User logging into application.
        loginPage.enterUserName();
        loginPage.enterPassword();
        loginPage.clickOnLoginButton();
	}
	
	
	@When("^User navigated to WilliamHill Betting Page$")
	public void user_navigated_to_WilliamHill_Page() throws Throwable {
		// User navigated to betting page.
		navigation = loginPage.getNavigation();
		homePage = navigation.selectProduct(ApplicationConstants.BETTING);
	}

	@When("^User selects \"([^\"]*)\" from betting home page$")
	public void user_selects_match(String sportsName) throws Throwable {
	    navigation = homePage.getNavigation();
	    homePage = navigation.selectSport(sportsName);
	}

	@When("^selects the match \"([^\"]*)\" to place bet in \"([^\"]*)\"$")
	public void selectsTheMatchToPlaceToBet(String matchName, String competitionName) throws Throwable {
		competitionsPage = homePage.selectCompetetions();
		competitionsPage.selectBettingCompetition(competitionName);
		
		String[] partyNames = matchName.split("vs");
		List<String> bothTeams = new ArrayList<String>(); 
		
		bothTeams.add(partyNames[0].trim());
		bothTeams.add(partyNames[1].trim());
		
		competitionsPage.selectMatch(bothTeams);
		//competitionsPage.selectMatch(Arrays.asList());
		
	}
	
	@When("^places a bet \"([^\"]*)\" on \"([^\"]*)\" to win from Betslip$")
	public void user_places_a_bet_on_to_win(String betAmount, String favouriteTeam) throws Throwable {
		betAmountActual = betAmount;
		competitionsPage.selectBettingOdds(favouriteTeam);
		betSlipPage = competitionsPage.getNavigation().selectBetSlipLink();
		
		// Retrieving the supporting team odds
		bettingOdds = betSlipPage.getBettingOdds(favouriteTeam);
		
		// Entering the bet stake
		betSlipPage.enterStakeAmount(favouriteTeam, betAmount);
		
		// Clicking on Place A Bet button
		betRecieptPage = betSlipPage.clickOnPlaceABetButton();
	}

	@Then("^Verify that odds and betAmount and returns offered are displayed in bet reciept$")
	public void verify_the_odds_and_returns_offered() throws Throwable {
		Assert.assertTrue(" Bet Placed successful message is not displayed! ",betRecieptPage.isBetPlacedSuccessfulMessageDisplayed());
		Assert.assertTrue(" Bet stake amounts are not matching with the user entered amount!",betRecieptPage.getStakeValueFromReciept().equals(betAmountActual));
		Assert.assertTrue(" Bet Odds are not matching with the user selected odds before confirming the bet!", betRecieptPage.getBetOddsOnReciept().endsWith(bettingOdds));
		Assert.assertTrue(" Bet Estimated value is not present", betRecieptPage.isEstimatedReturnsValuePresent());
	}
}
