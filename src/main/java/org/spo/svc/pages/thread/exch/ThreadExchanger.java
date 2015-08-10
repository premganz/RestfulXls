
package org.spo.svc.pages.thread.exch;

import org.python.modules.synchronize;
import org.springframework.stereotype.Component;


public class ThreadExchanger {
	public static ThreadExchanger exchanger=new ThreadExchanger();

	private ThreadExchanger(){
		
	}
	public static  ThreadExchanger instance(){
		System.err.println("class naem is "+exchanger.getClass().hashCode());
		return exchanger;
		
	}
	
	
	private String request;
	private String response;
	public  boolean requestPending;
	public  boolean responseReady;
	
	public  void setRequest(String request){
		if(requestPending){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}else{
			this.request=request;
			requestPending=true;
		}
		
		
	}
	
	public  String getRequest(){
		if(!requestPending){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "";
		}else{
			requestPending=false;
			return request;
		}
		
	}

	public  String getResponse(){
		if(!responseReady){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "";
		}else{
			return response;
		}
		
	}
	
	public  void setResponse(String response){
		if(responseReady){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}else{
			this.response=response;
			responseReady=true;
		}
		
		
	}
	
}
