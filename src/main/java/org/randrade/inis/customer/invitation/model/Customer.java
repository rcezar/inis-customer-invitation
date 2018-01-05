package org.randrade.inis.customer.invitation.model;

import java.math.BigDecimal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Customer {

    @Expose
    @SerializedName("user_id")
    private Integer id;

    @Expose
    private String name;
    
    private Address address;   
    
    private String latitude;
    private String longitude;

    public Customer(Integer id, String name, String latitude, String longitude) {
        this.id = id;
        this.name = name;
        address = new Address(latitude, longitude);        
    }

    public Integer getId(){
        return id;
    }
    
    public BigDecimal calculate(Address origem){
    	
    	if (address == null){
    		address = new Address(latitude, longitude);
    	}
    	
    	return address.calculateDistance(origem);
    }
    
}