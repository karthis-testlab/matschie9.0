package com.matschie.api.rest.assured.lib;

import java.util.Map;

import com.google.gson.Gson;
import com.matschie.api.design.ApiClient;
import com.matschie.api.design.ResponseAPI;

import io.restassured.RestAssured;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RestAssuredBaseImpl implements ApiClient {	
	
	private RequestSpecification given(RequestSpecification request) {
		return RestAssured.given()
				.spec(request)
				.filter(new RestAssuredListener());
	}
	
	public AuthenticationScheme basicAuth(String username, String password) {
		return RestAssured.basic(username, password);
	}

	@Override
	public ResponseAPI get(RequestSpecification request) {		
		return new RestAssuredResponseImpl(given(request).get());
	}
	
	@Override
	public ResponseAPI get(RequestSpecification request, String endPoint) {
		return new RestAssuredResponseImpl(given(request).get(endPoint));
	}

	@Override
	public ResponseAPI post(RequestSpecification request, String endPoint, Object body) {
		return new RestAssuredResponseImpl(
				   given(request)
				   .contentType(ContentType.JSON)				   
				   .body(new Gson().toJson(body))
				   .post());
	}
	
	@Override
	public ResponseAPI post(RequestSpecification request, String endPoint, Map<String, String> formParams) {		
		return new RestAssuredResponseImpl(
				   given(request)
				   .contentType(ContentType.URLENC)
				   .formParams(formParams)
				   .post());
	}

	@Override
	public ResponseAPI put(RequestSpecification request, Object body) {
		return new RestAssuredResponseImpl(
				   given(request)
				   .contentType(ContentType.JSON)
				   .body(new Gson().toJson(body))
				   .put());
	}

	@Override
	public ResponseAPI delete(RequestSpecification request, String endPoint) {
		return new RestAssuredResponseImpl(given(request).delete(endPoint));
	}	

}