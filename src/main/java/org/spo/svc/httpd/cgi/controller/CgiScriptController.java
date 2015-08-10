package org.spo.svc.httpd.cgi.controller;

import org.spo.svc.httpd.svc.PyCgiInvocationSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/cgi")
public class CgiScriptController {

	@Autowired
	private PyCgiInvocationSvc reader;


	/**
	 * The url of the module is /python/mod/methodname?args=hi,hi1
	 * the query would be name of pythonmodule and the methodfollowed by query params of args
	 * rightnow there are two methods one 
	 * 
	 * @return
	 */
	
	@ResponseBody
	@RequestMapping(value="/{pycgi}", method=RequestMethod.GET)
	public String handlePageRequest_List(@PathVariable String pycgi) {

		try {
			return reader.getJsonFromPytonSvc(pycgi);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	@ResponseBody
	@RequestMapping(value="/measureCacheIns", method=RequestMethod.GET)
	public String measureCacheInsert(
			@RequestParam(value="Measure", required=false) String measureVal,
			@RequestParam(value="Ctrls", required=false) String ctrls) {

		try {
			return reader.insertMeasureCache(measureVal,ctrls);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/measureCacheFind", method=RequestMethod.GET)
	public String measureCacheFind_List(
			@RequestParam(value="Measure", required=false) String measureVal		) {

		try {
			return reader.findMeasureCache(measureVal);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
}
