package com.matschie.api.design;

import java.util.Map;

import io.restassured.specification.RequestSpecification;

public interface ApiClient {
	
	ResponseAPI get(RequestSpecification request);
	ResponseAPI get(RequestSpecification request, String endPoint);
	ResponseAPI post(RequestSpecification request, String endPoint, Object body);
	ResponseAPI post(RequestSpecification request, String endPoint, Map<String, String> formParams);
	ResponseAPI put(RequestSpecification request, Object body);
	ResponseAPI delete(RequestSpecification request, String endPoint);

}