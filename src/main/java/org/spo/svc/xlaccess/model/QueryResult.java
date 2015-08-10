package org.spo.svc.xlaccess.model;


import java.util.List;
import java.util.Map;

public interface QueryResult{
	public List<Map<String, String>> getResult();
	public void setResult(List<Map<String, String>> result);
}