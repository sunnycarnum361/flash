package org.williamhill.automation.tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.williamhill.automation.core.SeleniumDriver;
import org.williamhill.automation.pageobjects.HomePageNavigation;

/**
 * This is the abstract test class, 
 * used to invoke the services of driver invocation and destruction. 
 * 
 * @author chiran
 *
 */
public abstract class AbstractTest {
	private static Log logger = LogFactory.getLog(AbstractTest.class);
	public SeleniumDriver driverObj;
	public static ApplicationContext ctx;
	public static Map<String, SeleniumDriver> drivers = new HashMap<String, SeleniumDriver>();

	public SeleniumDriver getSeleniumDriver() throws Exception {
		
		if (driverObj == null) {
			
			if (drivers.size() == 0) {

				if (ctx == null) {
					setupContext();
				}

				driverObj = (SeleniumDriver) ctx.getBean("webDriver");
				drivers.put("webDriver", driverObj);
				logger.info(driverObj.toString());

			} else {
			
				driverObj = drivers.get("webDriver");
			
			}
		}
		return driverObj;
	}

	public void setupContext() throws Exception {
		logger.info("Starting test context");

		List<String> contextXMLList = new ArrayList<String>();

		contextXMLList.add("application-context.xml");
		ctx = new ClassPathXmlApplicationContext(
				contextXMLList.toArray(new String[contextXMLList.size()]));
	}

	public HomePageNavigation getWilliamHillHomePage()
	{
		driverObj.loadURL();
		return new HomePageNavigation(driverObj);
	}

}