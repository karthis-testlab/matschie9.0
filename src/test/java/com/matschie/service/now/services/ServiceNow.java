package com.matschie.service.now.services;

import com.matschie.api.rest.assured.lib.RequestSpecBuilder;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static com.matschie.general.utils.PropertiesHandlers.*;

public class ServiceNow {
	
	public RequestSpecification globalRequestSpec() {
		return new RequestSpecBuilder()
				   .setBaseUri(config("service.now.base.uri"))		
				   .setBasePath(config("service.now.base.path"))
				   .setContentType(ContentType.JSON)
				   .setUsername(config("sevice.now.username"))
			       .setPassword(secret("service.now.password"))
				   .setAccept(ContentType.JSON)
				   .build();
	}
	
}