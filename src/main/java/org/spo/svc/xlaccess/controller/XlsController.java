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
	@RequestMapping(value="{xlsName}", method=RequestMethod.GET)
	public List<Map<String,String>> handleFullSheetQuery(@PathVariable String xlsName) {

		XlsReader reader1=xlsName.equals("Q2-2014_Data_Elements_forQA")?reader:reader_node_1;
		try {
			return reader1.queryAbstractElementDoc_List(xlsName.toString(),null,null);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Map<String,String>>();
		}
	}

	@ResponseBody
	@RequestMapping(value="/{xlsName}/{requiredField}", method=RequestMethod.GET , params={"fld", "val"} )
	public List<Map<String,String>> queryByParams(@PathVariable String xlsName,
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
			
			return reader.queryAbstractElementDoc_List(xlsName.toString(),buf.toString(),requiredField );
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
			
			return reader.queryAbstractElementDoc_List("Script7",buf.toString(),requiredField );
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Map<String,String>>();
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/map/{xmlName}/{requiredField}", method=RequestMethod.GET , params={"fld", "val"} )
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
			
			
			return reader.queryAbstractElementDoc_List(sheetName.toString(),buf.toString(),requiredField );
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Map<String,String>>();
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/string/{xmlName}/{requiredField}", method=RequestMethod.GET , params={"fld", "val"} )
	public String queryAnyFileValue(@PathVariable String xmlName,
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
			
			
			return reader.queryAbstractElementDoc(xmlName.toString(),buf.toString(),requiredField );
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
}
