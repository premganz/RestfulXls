package org.spo.svc.pages.gateway.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class QMessage {
	private String handler;
	private String fileName;
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
