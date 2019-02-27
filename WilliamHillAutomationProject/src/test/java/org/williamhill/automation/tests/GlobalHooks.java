package org.williamhill.automation.tests;

import cucumber.api.java.Before;

/**
 * Cucumber hooks
 * 
 * @author chiran
 *
 */
public class GlobalHooks extends AbstractTest {
	private static boolean dunit = false;

	@Before
	public void beforeAll() {
		if (!dunit) {

			try {
				setupContext();
			} catch (Exception e) {
				e.printStackTrace();
			}
			dunit = true;
		}
	}
}
