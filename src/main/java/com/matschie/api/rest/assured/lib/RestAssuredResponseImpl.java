package com.matschie.api.rest.assured.lib;

import java.util.HashMap;
import java.util.Map;

import com.matschie.api.design.ResponseAPI;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class RestAssuredResponseImpl implements ResponseAPI {
	
	private Response response;
	
	public RestAssuredResponseImpl(Response response) {
		this.response = response;
	}

	@Override
	public int getStatusCode() {		
		return response.getStatusCode();
	}	

	@Override
	public String getStatusMessage() {		
		String[] strings = response.getStatusLine().split(" ");
		return strings[strings.length - 1];
	}

	@Override
	public String getBody() {		
		return response.getBody().asString();
	}

	@Override
	public Map<String, String> getHeaders() {		
		Map<String, String> headers = new HashMap<>();
		Headers allHeaders = response.headers(); 
		for (Header header : allHeaders) {			
			if(headers.containsKey(header.getName())){
				headers.merge(header.getName(), header.getValue(), String::concat);
			} else {
				headers.put(header.getName(), header.getValue());
			}			
		}
		return headers;
	}

	@Override
	public String getContentType() {
		String[] values = response.getContentType().split(";");
		return values[0];
	}	

}