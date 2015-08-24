package org.spo.svc.xlaccess.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.spo.svc.config.AppConstants;
import org.spo.svc.xlaccess.model.QueryResult;
import org.spo.svc.xlaccess.model.QueryResultImpl;
import org.spo.svc.xlaccess.service.XlsReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/readxl")
public class XlsController {

	@Autowired
	private XlsReader reader;
	@Autowired
	private XlsReader reader_node_1;

	

	@ResponseBody
	@RequestMapping(value="/{bookName}/{requiredField}", method=RequestMethod.GET , params={"fld", "val"} )
	public List<Map<String,String>> queryByParams(@PathVariable String bookName, 
			@RequestParam(value="fld", required=false) String[] queryFlds, 
			@RequestParam(value="val", required=false) String[] queryVals,
			@PathVariable String requiredField) {

		try {
			StringBuffer buf = new StringBuffer();
			for(int idx=0;idx<queryFlds.length;idx++){
				buf.append(queryFlds[idx].replaceAll("%20"," ")+AppConstants.QUERY_EQUALS_EXP+queryVals[idx].replaceAll("%20"," "));
				if(idx!=(queryFlds.length-1)){
					buf.append(AppConstants.QUERY_CONCAT_EXP);
				}
				
			}
			reader.changeXmlDataSource(bookName);
			//Currently only single sheet xls is supported, 97-2003 format
			return reader.queryAbstractElementDoc_List("".toString(),buf.toString(),requiredField );
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Map<String,String>>();
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/test/{sheetName}/{requiredField}", method=RequestMethod.GET , params={"fld", "val"} )
	public List<Map<String,String>> queryByParamsTest(@PathVariable String sheetName,
			@RequestParam(value="fld", required=false) String[] queryFlds, 
			@RequestParam(value="val", required=false) String[] queryVals,
			@PathVariable String requiredField) {

		try {
			StringBuffer buf = new StringBuffer();
			for(int idx=0;idx<queryFlds.length;idx++){
				buf.append(queryFlds[idx].replaceAll("%20"," ")+AppConstants.QUERY_EQUALS_EXP+queryVals[idx].replaceAll("%20"," "));
				if(idx!=(queryFlds.length-1)){
					buf.append(AppConstants.QUERY_CONCAT_EXP);
				}
				
			}
			reader.setTestMode();
			return reader.queryAbstractElementDoc_List(sheetName.toString(),buf.toString(),requiredField );
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Map<String,String>>();
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/map/{xmlName}/{sheetName}/{requiredField}", method=RequestMethod.GET , params={"fld", "val"} )
	public List<Map<String,String>> queryAnyFile(@PathVariable String sheetName,@PathVariable String xmlName,
			@RequestParam(value="fld", required=false) String[] queryFlds, 
			@RequestParam(value="val", required=false) String[] queryVals,
			@PathVariable String requiredField) {

		try {
			StringBuffer buf = new StringBuffer();
			for(int idx=0;idx<queryFlds.length;idx++){
				buf.append(queryFlds[idx].replaceAll("%20"," ")+AppConstants.QUERY_EQUALS_EXP+queryVals[idx].replaceAll("%20"," "));
				if(idx!=(queryFlds.length-1)){
					buf.append(AppConstants.QUERY_CONCAT_EXP);
				}
				
			}
			reader_node_1.changeXmlDataSource(xmlName);
			
			return reader.queryAbstractElementDoc_List(sheetName.toString(),buf.toString(),requiredField );
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Map<String,String>>();
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/string/{xmlName}/{sheetName}/{requiredField}", method=RequestMethod.GET , params={"fld", "val"} )
	public String queryAnyFileValue(@PathVariable String sheetName,@PathVariable String xmlName,
			@RequestParam(value="fld", required=false) String[] queryFlds, 
			@RequestParam(value="val", required=false) String[] queryVals,
			@PathVariable String requiredField) {

		try {
			StringBuffer buf = new StringBuffer();
			for(int idx=0;idx<queryFlds.length;idx++){
				buf.append(queryFlds[idx].replaceAll("%20"," ")+AppConstants.QUERY_EQUALS_EXP+queryVals[idx].replaceAll("%20"," "));
				if(idx!=(queryFlds.length-1)){
					buf.append(AppConstants.QUERY_CONCAT_EXP);
				}
				
			}
			reader_node_1.changeXmlDataSource(xmlName);
			
			return reader.queryAbstractElementDoc(sheetName.toString(),buf.toString(),requiredField );
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
}
