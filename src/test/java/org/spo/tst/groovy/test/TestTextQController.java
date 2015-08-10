package org.spo.tst.groovy.test;

import org.junit.Test;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;



public class TestTextQController {

	
	@Test 
	public void testAsController() {		
		RestTemplate restTemplate = new RestTemplate();
		String result = (String)restTemplate.getForObject("http://localhost:8080/pages/x/y" , String.class );
		System.out.println(result);
		Assert.isTrue(result.length()>0);
		
	}
	
}
