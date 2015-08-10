package org.spo.svc.pages.gateway.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.spo.svc.config.AppConstants;
import org.spo.svc.pages.gateway.model.QMessage;
import org.spo.svc.pages.gateway.svc.MQConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/event")
public class ActorQMessageController {

	@Autowired
	private MQConnector connector;

	
	
	
	@ResponseBody
	@RequestMapping(value="/{actorName}/{eventExpression}", method=RequestMethod.GET,   params={"meta"} )
	public List<String> handlePageRequest_List( @PathVariable String actorName,@RequestParam(value="meta", required=false) String metaValue,
			@PathVariable String eventExpression) {

		try {
			QMessage message = new QMessage();
			message.setHandler("event");
			message.setFileName(actorName+"/"+eventExpression);
			if(metaValue!=null){
			message.setMeta(metaValue);
			}
			return connector.getResponseAsList(message);
	
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<String>();
		}
	}
}
