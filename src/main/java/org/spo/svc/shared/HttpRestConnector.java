package org.spo.svc.shared;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

public class HttpRestConnector<T> {

	T result;
	 final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

	public HttpRestConnector(T result) {
		this.result=result;
	}


	
	@SuppressWarnings("unchecked")
	public T queryServer(String url, String query ) throws TestResourceException{
		try {
			
			RestTemplate restTemplate = new RestTemplate();
			log.debug("calling server on url "+url);
			result= (T)restTemplate.getForObject(url , result.getClass());
		} catch (Exception e) {			
			throw new TestResourceException(e);
		}
		return result;

	}


}
