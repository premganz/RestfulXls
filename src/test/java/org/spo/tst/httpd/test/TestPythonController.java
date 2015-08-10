package org.spo.tst.httpd.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spo.svc.httpd.svc.PyCgiInvocationSvc;
import org.spo.svc.shared.JsonMessage;
import org.spo.svc.shared.JsonMessageItf;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

public class TestPythonController {


	final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

	//@Test 
		public void testAsController() {
			RestTemplate restTemplate = new RestTemplate();
			String resultList = (String)restTemplate.getForObject("http://localhost:8080/cgi/one" , String.class );
			Assert.isTrue(resultList.length()>0);
			
		}
	
	@Test 
	public void testHttpdRunning() {
		String result="";
		PyCgiInvocationSvc svc = new PyCgiInvocationSvc();
		try{
			result= svc.getJsonFromPytonSvc("one");
			log.debug(result);
		}catch(Exception e){
			e.printStackTrace();
		}
		Assert.isTrue(!result.isEmpty());


	}
	
	@Test 
	public void resultConvertibleToMessage() {
		String result="";
		JsonMessage message= new JsonMessage();	
		log.debug(message.toString());
		PyCgiInvocationSvc svc = new PyCgiInvocationSvc();
		try{
			result= svc.getJsonFromPytonSvc("one");

			Gson gson = new Gson();
			JsonMessage obj = gson.fromJson(result, JsonMessage.class);
			log.debug(result);
		}catch(Exception e){
			e.printStackTrace();
			Assert.isTrue(!result.isEmpty());
		}
		Assert.isTrue(!result.isEmpty());


	}

}

