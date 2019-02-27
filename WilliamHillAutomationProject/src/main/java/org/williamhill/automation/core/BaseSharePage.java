package org.williamhill.automation.core;

import org.williamhill.automation.pageobjects.HomePageNavigation;

/**
 *  This is the top level page object.
 *  
 * @author chiran
 *
 */
public abstract class BaseSharePage {

	public SeleniumDriver driverObj;

	public BaseSharePage(SeleniumDriver driverObj) {
		this.driverObj = driverObj;
	}

	public HomePageNavigation getNavigation() {
		return new HomePageNavigation(driverObj);
	}
}