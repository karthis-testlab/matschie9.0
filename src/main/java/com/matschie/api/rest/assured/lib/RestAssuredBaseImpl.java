package com.matschie.api.rest.assured.lib;

import java.util.Map;

import com.google.gson.Gson;
import com.matschie.api.design.ApiClient;
import com.matschie.api.design.ResponseAPI;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredBaseImpl implements ApiClient {	
	
	private Response response;
	
	private RequestSpecification given(RequestSpecification request) {
		return RestAssured.given()
				.spec(request)
				.filter(new RestAssuredListener());
	}	

	@Override
	public ResponseAPI get(RequestSpecification request) {		
		return new RestAssuredResponseImpl(given(request).get());
	}
	
	@Override
	public ResponseAPI get(RequestSpecification request, String endPoint) {		
		response = given(request).get(endPoint);		
		return new RestAssuredResponseImpl(response);		
	}

	@Override
	public ResponseAPI post(RequestSpecification request, String endPoint, Object body) {
		response = given(request)
				   .body(new Gson().toJson(body))
				   .post(endPoint);
		return new RestAssuredResponseImpl(response);
	}
	
	@Override
	public ResponseAPI post(RequestSpecification request, Map<String, String> formParams) {		
		return new RestAssuredResponseImpl(
				   given(request)
				   .contentType(ContentType.URLENC)
				   .formParams(formParams)
				   .post());
	}

	@Override
	public ResponseAPI put(RequestSpecification request, String endPoint, Object body) {
		return new RestAssuredResponseImpl(
				   given(request)
				   .contentType(ContentType.JSON)
				   .body(new Gson().toJson(body))
				   .put(endPoint));
	}

	@Override
	public ResponseAPI delete(RequestSpecification request, String endPoint) {
		return new RestAssuredResponseImpl(given(request).delete(endPoint));
	}	

}