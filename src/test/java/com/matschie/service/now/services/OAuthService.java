package com.matschie.service.now.services;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.matschie.api.design.ResponseAPI;
import com.matschie.api.rest.assured.lib.RestAssuredBaseImpl;

import static com.matschie.general.utils.PropertiesHandlers.*;
import io.restassured.specification.RequestSpecification;

public class OAuthService extends ServiceNow {
	
	private ResponseAPI response;
	private RestAssuredBaseImpl restAssured = new RestAssuredBaseImpl();
	private RequestSpecification requestSpecification;
	
	public void setRequestSpec(RequestSpecification requestSpecification) {
		this.requestSpecification = requestSpecification;		
	}
	
	private RequestSpecification getRequestSpec() {
		if (requestSpecification != null) {
			return requestSpecification;
		} else {
			return globalRequestSpec();
		}
	}
	
	public void createOAuthToken() {
		Map<String, String> forms = new HashMap<String, String>();
		forms.put("grant_type", config("service.now.grant.type"));
		forms.put("client_id", secret("service.now.client.id"));
		forms.put("client_secret", secret("service.now.client.secret"));
		forms.put("username", config("sevice.now.username"));
		forms.put("password", secret("service.now.password"));
		response = restAssured.post(getRequestSpec(), forms);
	}
	
	public String extractToken() {
		JSONObject json = new JSONObject(response.getBody());
		return json.getString("access_token");
	}

}