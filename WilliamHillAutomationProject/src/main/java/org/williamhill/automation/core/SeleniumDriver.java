package org.williamhill.automation.core;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;

/**
 * This is the wrapper on webdriver methods.
 * 
 * @author chiran
 *
 */
public class SeleniumDriver {
	private static Log logger = LogFactory.getLog(SeleniumDriver.class);
	private WebDriver driver;
	private long pollingInterval;
	private long waitTime;
	public String username;
	public String password;
	@SuppressWarnings("unused")
	private String browser;
	private String url;

	public SeleniumDriver(WebDriver driver, String url, String username,
			String password, String browser, Long waitTime, Long pollingInterval) {
		
		logger.info("Intializing the Selenium Driver");

		this.driver = driver;
		this.url = url;
		this.username = username;
		this.password = password;
		this.browser = browser;
		this.waitTime = waitTime;
		this.pollingInterval = pollingInterval;
		
		logger.info("Browser : " + browser + "/n URL :" + url + "/n USERNAME :"
				+ username + "/n WAIT_TIME :" + waitTime
				+ "\n POLLING_INTERVAL :" + pollingInterval);
	}

	public void maximize() {
		driver.manage().window().maximize();
	}

	public void loadURL() {
		driver.get(this.url);
	}

	public void sendKeys(By locator, String str) {
		driver.findElement(locator).sendKeys(str);
	}

	public List<WebElement> findAll(By locator) {
		return driver.findElements(locator);
	}

	public WebElement findAndWait(final By locator) {

		return findAndWait(locator,waitTime);
	}
	
	public WebElement findAndWait(final By locator, final long userWaitTime) {

		FluentWait<By> wait = new FluentWait<By>(locator);
		wait.withTimeout(userWaitTime, TimeUnit.MILLISECONDS);
		wait.pollingEvery(pollingInterval, TimeUnit.MILLISECONDS);
		wait.ignoring(org.openqa.selenium.NoSuchElementException.class);
		wait.until(new Predicate<By>() {
			public boolean apply(By by) {
				try {
					return driver.findElement(by).isDisplayed();
				} catch (NoSuchElementException nse) {
					return false;
				}
			}
		});
		return driver.findElement(locator);
	}

	public List<WebElement> findAndWaitForAll(final By locator) {
		findAndWait(locator);
		return findAll(locator);
	}

	public WebElement find(By locator) {
		return driver.findElement(locator);
	}

	public void click(By locator) {
		driver.findElement(locator).click();
	}

	public void quit() {
		driver.quit();
	}

	public void implicitWait() {
		driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
	}

	public void waitUntilElementDisplayed(final By locator) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver webDriver) {
					System.out.println("Loading ...");
					return webDriver.findElement(locator).isDisplayed();
				}
			});
		} catch (Exception e) {
			throw new RuntimeException("Error in page rendering " + locator
					+ ".", e);
		}
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	public void mouseOver(String xpath) {
		Actions actions = new Actions(driver);
		WebElement we = driver.findElement(By.xpath(xpath));
		actions.moveToElement(we)
				.moveToElement(driver.findElement(By.xpath(xpath))).click()
				.build().perform();
	}
}