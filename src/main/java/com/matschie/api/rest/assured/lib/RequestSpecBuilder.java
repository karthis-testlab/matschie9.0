package com.matschie.api.rest.assured.lib;

import java.util.HashMap;
import java.util.Map;

public class RequestSpecBuilder {

	private String baseUri;
	private String basePath;
	private String contentType;
	private Map<String, String> queryParams = new HashMap<String, String>();
	private String queryParamKey;
	private String queryParamValue;

	public String getBaseUri() {
		return baseUri;
	}

	public void setBaseUri(String baseUri) {
		this.baseUri = baseUri;
	}

	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Map<String, String> getQueryParams() {
		return queryParams;
	}

	public void setQueryParams(Map<String, String> queryParams) {
		this.queryParams = queryParams;
	}

	public String getQueryParamKey() {
		return queryParamKey;
	}

	public void setQueryParamKey(String queryParamKey) {
		this.queryParamKey = queryParamKey;
	}

	public String getQueryParamValue() {
		return queryParamValue;
	}

	public void setQueryParamValue(String queryParamValue) {
		this.queryParamValue = queryParamValue;
	}

}