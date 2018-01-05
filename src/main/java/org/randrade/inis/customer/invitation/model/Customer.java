package org.randrade.inis.customer.invitation.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Customer {

    @Expose
    @SerializedName("user_id")
    private Integer id;

    @Expose
    private String name;
    private String latitude;
    private String longitude;
    private BigDecimal distance;

    public Customer(Integer id, String name, String latitude, String longitude) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Integer getId(){
        return id;
    }

    public BigDecimal getDistance() {
        return distance;
    }

    public void calculateDistance(double  latA, double  lonA){

        double latB = Double.parseDouble(latitude);
        double lonB = Double.parseDouble(longitude);

        double p1 = Math.toRadians(latA);
        double p2 = Math.toRadians(latB);
        double dp = Math.toRadians(latB-latA);
        double dlambda = Math.toRadians(lonB-lonA);

        double a = (Math.sin(dp/2) * Math.sin(dp/2)) + (Math.cos(p1) * Math.cos(p2) * Math.sin(dlambda/2) * Math.sin(dlambda/2));
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double d = 6371.01 * c;

        distance = new BigDecimal(d);
        distance = distance.setScale(2, RoundingMode.HALF_UP);

    }
}
