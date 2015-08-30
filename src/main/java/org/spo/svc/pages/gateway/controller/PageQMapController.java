package org.spo.svc.pages.gateway.controller;

import java.util.ArrayList;
import java.util.List;

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
@RequestMapping("/pages")
public class PageQMapController {

	@Autowired
	private MQConnector connector;

	

	@ResponseBody
	@RequestMapping(value="/old/{moduleName}/{pageName1}", method=RequestMethod.GET)
	public String handlePageRequest_String( @PathVariable String pageName1,@PathVariable String moduleName) {

		try {
			QMessage message = new QMessage();
			message.setHandler(moduleName);
			message.setFileName(pageName1);
			String response = connector.getResponse(message);
			//return "hello";
			//return reader.readUpPage("blankforms", pageName);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return "ERROR";
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/{mppName}/{functionCode}", method=RequestMethod.GET,   params={"meta"} )
	public List<String> handlePageRequest_List( @PathVariable String mppName,@RequestParam(value="meta", required=false) String metaValue
			, @PathVariable String functionCode) {

		try {
			QMessage message = new QMessage();
			message.setHandler("pages");
			message.setFileName(mppName+"/"+functionCode);
			message.setMeta(metaValue);
			return connector.getResponseAsList(message);
	
		} catch (Exception e) {
			e.printStackTrace();
			List<String> list = new ArrayList<String>();
			list.add("ERROR");
			return list;
		}
	}
}
