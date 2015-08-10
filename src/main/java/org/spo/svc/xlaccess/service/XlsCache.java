package org.spo.svc.xlaccess.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.w3c.dom.Document;


public class XlsCache {
	//For reusing drivers but not presently used due to unconfirmed basic auth issues.
	private static Map<String, String>  cache_ElementValues = new LinkedHashMap<String, String>();
	private static Map<String, Document > xmlCache = new LinkedHashMap<String, Document>();
	


	public static void putElemInCache(String elemId, String value){
		cache_ElementValues.put(elemId, value);
	}
	
	public static boolean isCacheElemValid(String elemId, String value){
		if(!cache_ElementValues.containsKey(elemId)){
			return false;
		}
		if(cache_ElementValues.get(elemId).equals(value)){
			return true;
		}
		return false;
		
	}
	public static void resetElemCache(){
		cache_ElementValues = new LinkedHashMap<String, String>();
		xmlCache = new LinkedHashMap<String, Document>();
	}


	public static Map<String, Document > getXmlCache() {
		return xmlCache;
	}


	public static void setXmlCache(Map<String, Document > xmlCache) {
		XlsCache.xmlCache = xmlCache;
	}

}
