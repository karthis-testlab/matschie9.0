package com.matschie.servicenow.som;

import com.matschie.api.design.ResponseAPI;
import com.matschie.api.rest.assured.lib.RequestSpecBuilder;
import com.matschie.api.rest.assured.lib.RestAssuredBaseImpl;


public class ServiceNowBase {
	
	RestAssuredBaseImpl base = new RestAssuredBaseImpl();
	RequestSpecBuilder builder = new RequestSpecBuilder();
	protected ResponseAPI response;
	
    public RequestSpecBuilder preReq() {
    	return builder.setBaseUri("https://dev265761.service-now.com")
    	       .setBasePath("/api/now/table")
    	       .setUsername("admin")
    	       .setPassword("d@9IvhOh5DR*");    	      
    }
	
}