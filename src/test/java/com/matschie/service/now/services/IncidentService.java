package com.matschie.service.now.services;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.json.JSONArray;
import org.json.JSONObject;

import com.matschie.api.design.ResponseAPI;
import com.matschie.api.rest.assured.lib.RestAssuredBaseImpl;

import com.matschie.service.now.pojos.IncidentRequestPayload;

import io.restassured.specification.RequestSpecification;

public class IncidentService extends ServiceNow {
	
	private ResponseAPI response;
	private RestAssuredBaseImpl restAssured = new RestAssuredBaseImpl();
	private RequestSpecification requestSpecification;	
		
	private static final String TABLE_NAME = "incident";	
	
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
	
	public void fetchIncidentRecords() {
		response = restAssured.get(getRequestSpec(), TABLE_NAME);
	}
	
	public void fetchIncidentRecord(String sysId) {
		response = restAssured.get(getRequestSpec(), TABLE_NAME+"/"+sysId);
	}
	
	public void createIncidentRecord(IncidentRequestPayload payload) {
		response = restAssured.post(getRequestSpec(), TABLE_NAME, payload);
	}
	
	public void updateIncidentRecord(IncidentRequestPayload payload, String sysId) {
		response = restAssured.put(getRequestSpec(),  TABLE_NAME+"/"+sysId, payload);
	}
	
	public void fetchOnlyHardwareCategoryIncidentRecords() {		
		response = restAssured.get(getRequestSpec().queryParam("sysparm_query", "category=hardware"), TABLE_NAME);
	}
	
	public void deleteIncidentRecord(String sysId) {
		response = restAssured.delete(getRequestSpec(), TABLE_NAME+"/"+sysId);
	}
	
	public void validateSuccessResponse() {		
		MatcherAssert.assertThat(response.getStatusCode(), Matchers.equalTo(200));
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