package org.spo.svc.shared;



public class JsonMessage<T> implements JsonMessageItf<T> {

	private String header;
	private T payload;
	
	public JsonMessage(T payload){
		this.payload=payload;

	}
	
	public JsonMessage(){
		this.payload=payload;

	}
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public T getPayload() {
		return payload;
	}
	public void setPayload(T payload) {
		this.payload = payload;
	}
	

	@Override
	public String toString() {

		return "JsonMessageItf:{\"header\"=\"\""+header+", \"payload\"=\""+payload.getClass().getSimpleName()+":"+payload+"\"}";
	}
}
