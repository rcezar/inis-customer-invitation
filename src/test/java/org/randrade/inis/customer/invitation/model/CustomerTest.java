package org.randrade.inis.customer.invitation.model;

import org.junit.Test;

import java.math.BigDecimal;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


public class CustomerTest {

    private static String DO_LATITUDE = "53.339428";
    private static String DO_LONGITUDE = "-6.257664";

    @Test
    public void testCalculateDistance(){

        double latB = Double.parseDouble(DO_LATITUDE);
        double lonB = Double.parseDouble(DO_LONGITUDE);

        BigDecimal expected = new BigDecimal("162.30");

        Customer c = new Customer(1, "Alice Cahill", "51.8856167", "-6.043701");

        c.calculateDistance(latB, lonB );

        assertEquals(c.getDistance(),expected );

    }

    @Test
    public void testCalculateDistanceWithInvertedLatLong(){

        double latB = Double.parseDouble(DO_LATITUDE);
        double lonB = Double.parseDouble(DO_LONGITUDE);

        BigDecimal expected = new BigDecimal("162.30");

        Customer c = new Customer(1, "Alice Cahill", "51.8856167", "-6.043701");

        c.calculateDistance(lonB, latB );

        assertNotEquals(c.getDistance(),expected );

    }

    @Test(expected = NumberFormatException.class)
    public void testCalculateDistanceWithInvalidLat(){

        double latB = Double.parseDouble(DO_LATITUDE);
        double lonB = Double.parseDouble(DO_LONGITUDE);

        BigDecimal expected = new BigDecimal("162.30");

        Customer c = new Customer(1, "Alice Cahill", "q51.8856167", "-6.043701");

        c.calculateDistance( latB, lonB );
    }
}
