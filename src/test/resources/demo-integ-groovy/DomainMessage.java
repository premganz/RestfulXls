package org.trs.itf.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DomainMessage {
	//analogous to active mq messages. though nothing to do with pub sub architecture
	private String handler;// a named topic 
	//supported:blankforms, reports, general, 
	
	private String fileName;// a named message, 
	private String meta;
	
	public String getMeta() {
		return meta;
	}
	@XmlElement
	public void setMeta(String meta) {
		this.meta = meta;
	}
	public String getHandler() {
		return handler;
	}
	@XmlElement
	public void setHandler(String handler) {
		this.handler = handler;
	}
	public String getFileName() {
		return fileName;
	}
	@XmlElement
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
