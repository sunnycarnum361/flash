package org.williamhill.automation.pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.williamhill.automation.constants.ApplicationConstants;
import org.williamhill.automation.core.BaseSharePage;
import org.williamhill.automation.core.SeleniumDriver;
import org.williamhill.automation.exception.PageException;
import org.williamhill.automation.locators.ElementLocators;

/**
 * This page depicts the Competitions and match elements and functionality on that page.
 * 
 * @author chiran
 *
 */
public class CompetitionsPage extends BaseSharePage {

	public CompetitionsPage(SeleniumDriver driverObj) {
		super(driverObj);
	}

	/**
	 * This method selects the betting competition from competitions list
	 * 
	 * @param matchName
	 */
	public void selectBettingCompetition(String competitionName) {
		WebElement actualCompetition = null;
		try {
			// Retrieving the competitions list items.
			for (WebElement competition : driverObj.findAndWaitForAll(ElementLocators.ALL_CONTESTS)) {
				
				// Expanding the Competition list one after another
				if (!(competition.findElement(By.cssSelector(ElementLocators.COMPETITIONS_EXPANSION_BUTTON)).getAttribute(ApplicationConstants.CSS_CLASS).contains(ApplicationConstants.EXPANDED))) {
					competition.findElement(By.cssSelector(ElementLocators.COMPETITIONS_EXPANSION_BUTTON)).click();
				}

				// Verifying the expected competition name is present under expanded betting competition.
				try {
					actualCompetition = driverObj.findAndWait(By.xpath(String.format(ElementLocators.CONTEST, competitionName)));
				
					break;
				
				} catch (TimeoutException te) { }
			}

			actualCompetition.click();
			
		} catch (StaleElementReferenceException sle) {
			selectBettingCompetition(competitionName);
		} catch (TimeoutException te) {
			throw new PageException("Exceeded time to find the Competition Item." + te.getMessage());
		}
	}
	
	/**
	 * This method does the selection of match between given teams under the
	 * competition table.
	 * 
	 * @param expectedTeams
	 */
	public void selectMatch(List<String> expectedTeams) {
		WebElement teamNames = null;
		try {
			teamNames = findTeamsAreDisplayed(expectedTeams);

			// If expected teams didn't find in displayed teams, select show more button if available.
			if (teamNames == null) {
				for (WebElement showMore : driverObj.findAndWaitForAll(ElementLocators.SHOW_MORE)) {
					if (showMore.isDisplayed()) {
						showMore.click();
						break;
					}
				}
				selectMatch(expectedTeams);
			} else {
				teamNames.click();
			}
		} catch (PageException | TimeoutException te) {
			throw new PageException("Exceeded time to find the expected versus teams." + te.getMessage());
		}
	}

	private WebElement findTeamsAreDisplayed(List<String> expectedTeams) {
		List<String> teamNames;
		WebElement aTeams = null;
		try {
			// Retrieving the teams displayed under competition
			List<WebElement> retreivedTeams = driverObj
					.findAndWaitForAll(ElementLocators.TEAMS);
			for (WebElement actualTeams : retreivedTeams) {
				teamNames = new ArrayList<String>();
				for (WebElement team : actualTeams.findElements(By
						.cssSelector(ApplicationConstants.SPAN))) {
					teamNames.add(team.getText().trim());
				}

				// Comparing the expected verses teams with retrieved results
				if (expectedTeams.containsAll(teamNames)) {
					aTeams = actualTeams;
				}

			}
		} catch (TimeoutException te) {
			throw new PageException("Exceeded time to find the expected versus teams." + te.getMessage());
		}

		return aTeams;
	}
	
	/**
	 * This method does the selection of partyname by selecting their odds
	 * @param partyName
	 */
	public void selectBettingOdds(String partyName){
		try{
			driverObj.findAndWait(By.xpath(String.format(ElementLocators.SELECT_TEAM_TO_SUPPORT,partyName))).click();
		} catch (TimeoutException te) {
			throw new PageException(
					"Exceeded time to find the expected Team odds."
						+ te.getMessage());
		}
	}
}
