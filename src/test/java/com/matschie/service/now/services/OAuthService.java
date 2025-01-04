package com.matschie.service.now.services;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import static com.matschie.general.utils.PropertiesHandlers.*;

public class OAuthService extends ServiceNow {	
	
	public OAuthService() {
		requestBuilder = globalRequest()
				         .setBasePath("/oauth_token.do");
	}	
	
	public void createOAuthToken() {
		Map<String, String> forms = new HashMap<String, String>();
		forms.put("grant_type", config("service.now.grant.type"));
		forms.put("client_id", secret("service.now.client.id"));
		forms.put("client_secret", secret("service.now.client.secret"));
		forms.put("username", config("sevice.now.username"));
		forms.put("password", secret("service.now.password"));
		response = restAssured.post(requestBuilder.build(), forms);
	}
	
	public String extractToken() {
		JSONObject json = new JSONObject(response.getBody());
		return json.getString("access_token");
	}

}