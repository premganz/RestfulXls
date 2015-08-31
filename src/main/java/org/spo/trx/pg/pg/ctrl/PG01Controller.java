package org.spo.trx.pg.ctrl;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.spo.svc.mq.model.QMessage;
import org.spo.svc.mq.svc.MQConnector;
import org.spo.trx.pg.cmd.PG01O;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


@Controller
public class PG01Controller {

	@Autowired
	private MQConnector connector;


	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String handlePageRequest_String(Model model ) {		
			QMessage message = new QMessage();
			message.setHandler("pages");
			message.setFileName("PG01O/01");
			String response ="";
			try {
				response = connector.getResponse(message);
				
			} catch (Exception e) {			
				e.printStackTrace();
			}
			try{
				Gson gson = new Gson();
				Type typ = new TypeToken<PG01O>(){}.getType();//FIXME right now only string works
				message= gson.fromJson(response,typ);		
				model.addAttribute(message);
				
			}catch(Exception e){
				System.out.println("Error during messagePayload processing from  TestResourceServerException on" );
				e.printStackTrace();
			}
			return "index";
		
	}
	
	
	@ResponseBody
	@RequestMapping(value="/{moduleName}/{pageName1}", method=RequestMethod.GET)
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
	@RequestMapping(value="/addon/{mppName}/{functionCode}", method=RequestMethod.GET,   params={"meta"} )
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
