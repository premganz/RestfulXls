package org.spo.svc.xlaccess.model;

import java.util.List;
import java.util.Map;

public class QueryResultImpl implements QueryResult {
	
	private List<Map<String, String>> result;

	public List<Map<String, String>> getResult() {
		return result;
	}

	public void setResult(List<Map<String, String>> result) {
		this.result = result;
	}

	

	
}

