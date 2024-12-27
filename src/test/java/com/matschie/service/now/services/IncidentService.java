package com.matschie.service.now.services;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.json.JSONArray;
import org.json.JSONObject;

import com.matschie.api.design.ResponseAPI;
import com.matschie.api.rest.assured.lib.RestAssuredBaseImpl;

import static com.matschie.general.utils.PropertiesHandlers.*;
import com.matschie.service.now.pojos.IncidentRequestPayload;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class IncidentService {
	
	private ResponseAPI response;
	private RestAssuredBaseImpl restAssured = new RestAssuredBaseImpl();
	
	private static final String BASE_URI = config("service.now.base.uri");
	private static final String BASE_PATH = config("service.now.base.path");
	private static final String USER_NAME = config("sevice.now.username");
	private static final String PASSWORD = secret("service.now.password");
	private static final String TABLE_NAME = "incident";
	
	private RequestSpecification requestSpec() {
		return new RequestSpecBuilder()
				.setBaseUri(BASE_URI)
			    .setBasePath(BASE_PATH)
			    .setAuth(restAssured.basicAuth(USER_NAME, PASSWORD))
			    .build();
	}
	
	public void fetchIncidentRecords() {
		response = restAssured.get(requestSpec(), TABLE_NAME);
	}
	
	public void fetchIncidentRecord(String sysId) {
		response = restAssured.get(requestSpec(), TABLE_NAME+"/"+sysId);
	}
	
	public void createIncidentRecord(IncidentRequestPayload payload) {
		response = restAssured.post(requestSpec(), TABLE_NAME, payload);
	}
	
	public void fetchOnlyHardwareCategoryIncidentRecords() {		
		response = restAssured.get(requestSpec().queryParam("sysparm_query", "category=hardware"), TABLE_NAME);
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
	
	public void validateCategories(String expected) {
		JSONObject json = new JSONObject(response.getBody());
		JSONArray jsonArray = json.getJSONArray("result");
		for (Object record : jsonArray) {
			JSONObject jsonObject = (JSONObject) record;
			MatcherAssert.assertThat(jsonObject.getString("category"), Matchers.equalToIgnoringCase(expected));
		}		
	}
	
	public void validateSysId(String expected) {
		JSONObject json = new JSONObject(response.getBody());
		JSONObject jsonObject = json.getJSONObject("result");
		MatcherAssert.assertThat(jsonObject.getString("sys_id"), Matchers.equalTo(expected));
	}

}