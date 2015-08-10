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
@RequestMapping("/debug")
public class DebugPageController {

	

	

	@ResponseBody
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String handlePageRequest_String( ) {
			return "debug";
		
	}
	
	
}
