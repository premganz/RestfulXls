package org.spo.tst.xlaccess.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

public class TestXlsController {

	
	@Test 
	public void queryWholeSheet() {}
		
	@Test 
	public void queryWholeSheet_1() {
		RestTemplate restTemplate = new RestTemplate();
		List resultList = (List)restTemplate.getForObject("http://localhost:8080/readxl/x/" , List.class );
		Assert.isTrue(resultList.size()==4);
		
	}
	
	@Test 
	public void queryWholeSheet_2() {
		RestTemplate restTemplate = new RestTemplate();
		ArrayList<Map<String, String>> resultList = (ArrayList<Map<String, String>>)restTemplate.getForObject("http://localhost:8080/readxl/x/y?fld=a&val=b" , ArrayList.class );
		Assert.isTrue(resultList.size()==4);
		
	}
	
	@Test 
	public void queryWholeSheet_3() {
		RestTemplate restTemplate = new RestTemplate();
		ArrayList<Map<String, String>> resultList = (ArrayList<Map<String, String>>)restTemplate.getForObject("http://localhost:8080/readxl/x/y?fld=a&val=b" , ArrayList.class );
		Assert.isTrue(resultList.size()==2);
		
	}
	}

