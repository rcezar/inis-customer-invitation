package org.randrade.inis.customer.invitation.model;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

public class CustomerTest {

	private static String DO_LATITUDE = "53.339428";
	private static String DO_LONGITUDE = "-6.257664";

	@Test
	public void testCalculateDistance() {

		Address origimAddress = new Address(DO_LATITUDE, DO_LONGITUDE);

		BigDecimal expected = new BigDecimal("162.30");

		Customer c = new Customer(1, "Alice Cahill", "51.8856167", "-6.043701");

		BigDecimal actual = c.calculate(origimAddress);

		Assert.assertEquals(actual, expected);

	}

	@Test
	public void testCalculateDistanceWithInvertedLatLong() {

		Address origimAddress = new Address(DO_LONGITUDE, DO_LATITUDE);

		BigDecimal expected = new BigDecimal("162.30");

		Customer c = new Customer(1, "Alice Cahill", "51.8856167", "-6.043701");

		BigDecimal actual = c.calculate(origimAddress);

		Assert.assertNotEquals(actual, expected);

	}

	@Test(expected = NumberFormatException.class)
	public void testCalculateDistanceWithInvalidLat() {

		Address origimAddress = new Address(DO_LONGITUDE, DO_LATITUDE);

		Customer c = new Customer(1, "Alice Cahill", "q51.8856167", "-6.043701");

		c.calculate(origimAddress);
	}
}
