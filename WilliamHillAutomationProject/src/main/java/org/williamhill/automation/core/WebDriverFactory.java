package org.williamhill.automation.core;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.springframework.beans.factory.FactoryBean;

/**
 * This is the WebDriver factory class to create the driver instances based on
 * the test requests.
 * 
 * @author chiran
 *
 */
public class WebDriverFactory implements FactoryBean<SeleniumDriver>{
	private static Log logger = LogFactory.getLog(WebDriverFactory.class);

	private String url;
	private Long waitTime;
	private Long pollingInterval;
	private String username;	
	private String password;
	private String browser;
	private static WebDriver driver;
	
	public WebDriverFactory(String url,
	Long waitTime,
	Long pollingInterval,
	String username,	
	String password,
	String browser)
	{
		logger.info("Loading WebDriverFactory Configuration.");
		
		this.url = url;
		this.username = username;
		this.password = password;
		this.browser = browser;
		this.waitTime = waitTime;
		this.pollingInterval = pollingInterval;
		
	}
	
	public synchronized SeleniumDriver getObject() throws Exception {
		logger.info("Initializing the WebDriver Configuration.");
		
		WebDriver driver = getDriverInstance();
		driver.manage().window().maximize();
		
		SeleniumDriver seleniumDriver = new SeleniumDriver(driver,this.url,this.username,this.password,this.browser,this.waitTime,this.pollingInterval);
		return seleniumDriver;
	}

	private WebDriver getDriverInstance() {
		logger.info("Initializing WebDriver Object for " + browser);
		try{
			
			if(browser.equalsIgnoreCase("firefox"))
			{
				
				driver = new FirefoxDriver();
				 return driver;
			} 
		}finally{
        	Runtime.getRuntime().addShutdownHook(new Thread(
        			new BrowserCleanup()));
        	}
		throw new UnsupportedOperationException("BrowserType for " + browser + " has not found.");
	}
	
	private static class BrowserCleanup implements Runnable {
		public void run() {
			logger.info("closing the browser");
			close();
		}
	}

	public static void close() {
		try {
			driver.quit();
			driver = null;
		} catch (UnreachableBrowserException e) {
			logger.info("cannot close browser: unreachable browser");
		}
	}
	
	public Class<? extends SeleniumDriver> getObjectType() {
		return null;
	}

	public boolean isSingleton() {
		return false;
	}

}
