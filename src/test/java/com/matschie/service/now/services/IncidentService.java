package com.matschie.service.now.services;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.json.JSONArray;
import org.json.JSONObject;

import com.matschie.service.now.pojos.IncidentRequestPayload;

public class IncidentService extends ServiceNow {
	
	// SOM - Service Object Modal
	
	private static final String TABLE_NAME = "incident";	
	
	public IncidentService() {
		requestBuilder = globalRequest();		
	}	
	
	public void fetchIncidentRecords() {
		response = restAssured.get(requestBuilder.build(), TABLE_NAME);
	}
	
	public void fetchIncidentRecord(String sysId) {
		response = restAssured.get(requestBuilder.build(), TABLE_NAME+"/"+sysId);
	}
	
	public void createIncidentRecord() {
		response = restAssured.post(requestBuilder.build(), TABLE_NAME);
	}
	
	public void createIncidentRecord(IncidentRequestPayload payload) {
		response = restAssured.post(requestBuilder.build(), TABLE_NAME, payload);
	}
	
	public void updateIncidentRecord(IncidentRequestPayload payload, String sysId) {
		response = restAssured.put(requestBuilder.build(),  TABLE_NAME+"/"+sysId, payload);
	}
	
	public void fetchOnlyHardwareCategoryIncidentRecords() {		
		response = restAssured.get(requestBuilder.build().queryParam("sysparm_query", "category=hardware"), TABLE_NAME);
	}
	
	public void deleteIncidentRecord(String sysId) {
		response = restAssured.delete(requestBuilder.build(), TABLE_NAME+"/"+sysId);
	}
	
	public void validateSuccessResponse() {		
		MatcherAssert.assertThat(response.getStatusCode(), Matchers.equalTo(201));
		MatcherAssert.assertThat(response.getStatusMessage(), Matchers.equalToIgnoringCase("OK"));
		MatcherAssert.assertThat(response.getContentType(), Matchers.equalTo("application/json"));
	}
	
	public void validateCreationResponse() {
		MatcherAssert.assertThat(response.getStatusCode(), Matchers.equalTo(201));
		MatcherAssert.assertThat(response.getStatusMessage(), Matchers.equalToIgnoringCase("Created"));
		MatcherAssert.assertThat(response.getContentType(), Matchers.equalTo("application/json"));
	}
	
	public void validateDeletionResponse() {
		MatcherAssert.assertThat(response.getStatusCode(), Matchers.equalTo(204));
		MatcherAssert.assertThat(response.getStatusMessage(), Matchers.equalToIgnoringCase("No Content"));
	}
	
	public void validateNotFoundResponse() {
		MatcherAssert.assertThat(response.getStatusCode(), Matchers.equalTo(404));
		MatcherAssert.assertThat(response.getStatusMessage(), Matchers.equalToIgnoringCase("Not Found"));
		MatcherAssert.assertThat(response.getContentType(), Matchers.equalTo("application/json"));
	}
	
	public String responseAsString() {
		return response.getBody();
	}
	
	public void validateCategories(String expected) {
		// To Get JSONString
		String responseBody = response.getBody();
		// Convert JSONString to JSONObject
		JSONObject json = new JSONObject(responseBody);
		JSONArray jsonArray = json.getJSONArray("result");
		for (Object record : jsonArray) {
			JSONObject jsonObject = (JSONObject) record;
			MatcherAssert.assertThat(jsonObject.getString("category"), Matchers.equalToIgnoringCase(expected));
		}
	}
	
	public void validateSysId(String expected) {
		// JSONString
		String responseBody = response.getBody();
		//Convert JSONString into JSONObject
		JSONObject json = new JSONObject(responseBody);
		String actual = json.getJSONObject("result").getString("sys_id");
		MatcherAssert.assertThat(actual, Matchers.equalTo(expected));
		
		/**
		 * 
		 * JSONObject json = new JSONObject(response.getBody());
		 * MatcherAssert.assertThat(json.getJSONObject("result").getString("sys_id"), Matchers.equalTo(expected));
		 * 
		 */
		
		/**
		 * MatcherAssert.assertThat(new JSONObject(response.getBody()).getJSONObject("result").getString("sys_id"), Matchers.equalTo(expected));
		 * 
		 */
	}

}