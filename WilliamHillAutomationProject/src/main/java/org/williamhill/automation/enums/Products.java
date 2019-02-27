package org.williamhill.automation.enums;

/**
 * Enums for products displayed on William Hill Product tab.
 * 
 * @author chiran
 *
 */
public enum Products {

	BETTING("Betting");

	// TODO: Remaining products can be added here as enums.

	private String value;

	private Products(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

	public static Products getProduct(String val) {
		for (Products d : Products.values()) {
			if (d.value.equalsIgnoreCase(val)) {
				return d;
			}
		}
		return null;
	}
}
