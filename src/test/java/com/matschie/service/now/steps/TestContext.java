package com.matschie.service.now.steps;

import java.util.HashMap;
import java.util.Map;

public class TestContext {
	
	Map<String, String> context;
	
	public TestContext() {
		context = new HashMap<>();
	}
	
	public void setContext(String key, String value) {
		context.put(key, value);
	}
	
	public String getContext(String key) {
		return context.get(key);
	}

}