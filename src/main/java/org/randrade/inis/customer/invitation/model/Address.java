package org.randrade.inis.customer.invitation.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Address {

	private String latitude;
	private String longitude;

	public Address(String latitude, String longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public BigDecimal calculateDistance(Address origem) {

		double toLat = Double.parseDouble(origem.getLatitude());
		double toLon = Double.parseDouble(origem.getLongitude());

		double fromLat = Double.parseDouble(latitude);
		double fromLon = Double.parseDouble(longitude);

		double p1 = Math.toRadians(toLat);
		double p2 = Math.toRadians(fromLat);
		double dp = Math.toRadians(fromLat - toLat);
		double dlambda = Math.toRadians(fromLon - toLon);

		double a = (Math.sin(dp / 2) * Math.sin(dp / 2))
				+ (Math.cos(p1) * Math.cos(p2) * Math.sin(dlambda / 2) * Math.sin(dlambda / 2));
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double d = 6371.01 * c;

		BigDecimal distance = new BigDecimal(d);
		distance = distance.setScale(2, RoundingMode.HALF_UP);

		return distance;

	}

}