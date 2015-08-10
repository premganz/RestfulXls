package org.spo.svc.httpd.svc;

import java.net.URISyntaxException;
import java.net.URL;

import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spo.svc.shared.HttpRestConnector;
import org.spo.svc.shared.JsonMessageItf;
import org.spo.svc.shared.TestResourceException;
import org.springframework.stereotype.Component;
@Component
public class PyCgiInvocationSvc {
	/**
	 * Service throws an exception 
	 * The PYTHON_PATH is needed to contain pywinauto, 
	 * CLASS_PATH should contain path to python 2.7.6 32 bit windows version for ctypes support.
	 */
	 final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());
	
	public String getJsonFromPytonSvc(String pycgi) {
		String message= "";
		try {
			String url = "http://localhost:8083/cgi-bin/"+pycgi+".py";
			log.debug("calling server on url "+url);
			
			HttpRestConnector<String> con = new HttpRestConnector<String>(message);
			message = con.queryServer(url, "");
		message=message.replaceAll("\\\\", "/");
			
		} catch (TestResourceException e) {		
			log.debug("Continuing Execution silently",e);
			log.debug("Continuing Execution silently");
		}
		return message;
	}
	
	
	public String insertMeasureCache(String measure,String ctrls) {
		String message= "";
		try {
			String url = "http://localhost:8083/cgi-bin/"+"insertMeasureData"+".py"+"?"+"Measure="+measure+"&"+"Ctrls="+ctrls;
			log.debug("calling httpd on url "+url);
			
			HttpRestConnector<String> con = new HttpRestConnector<String>(message);
			message = con.queryServer(url, "");
		message=message.replaceAll("\\\\", "/");
			
		} catch (TestResourceException e) {		
			log.debug("Continuing Execution silently",e);
			log.debug("Continuing Execution silently");
		}
		return message;
	}
	
	
	public String findMeasureCache(String measure) {
		String message= "";
		try {
			String url = "http://localhost:8083/cgi-bin/"+"findMeasureData"+".py"+"?"+"Measure="+measure;
			log.debug("calling server on url "+url);
			
			HttpRestConnector<String> con = new HttpRestConnector<String>(message);
			message = con.queryServer(url, "");
		message=message.replaceAll("\\\\", "/");
			
		} catch (TestResourceException e) {		
			log.debug("Continuing Execution silently",e);
			log.debug("Continuing Execution silently");
		}
		return message;
	}
	public String invokePythonviaJython(){

		URL resourceUrl = getClass().getResource("/python");
		String resourcePath = "";
		try {
			resourcePath = resourceUrl.toURI().getPath();
			//log.debug("Trying to load resource "+resourcePath);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}PythonInterpreter interpreter = new PythonInterpreter();
		interpreter.exec("\nimport sys");
		//sys.path.extend(['C:\\CDQM\\Python\\pywinauto-0.4.2']
		interpreter.exec("sys.path.extend(['C:/CDQM/Python/pywinauto-0.4.2'])\n");
		interpreter.exec("\nsys.path.extend(['C://python/Tester'])\n");
		//interpreter.exec("\nsys.path.append('"+System.getenv( "PATH").replaceAll("\\\\", "/")+"')\n");
		interpreter.exec("\nsys.path.extend(['C:/Python27-32/Lib'])\n");
		interpreter.exec("\nsys.path.extend(['C:/Python27-32/Lib/ctypes'])\n");
		System.out.println(System.getenv( "PATH"));


		interpreter.exec("\nimport Tester");
		interpreter.exec("\nfrom Tester import RunnerRobot");

		//	interpreter.execfile(resourcePath+"/TESTER/RUNNERROBOT.PY");

		// execute a function that takes a string and returns a string
		PyObject someFunc = interpreter.get("Get_Latest_Build_Dir");
		PyObject result = someFunc.__call__(new PyString("Test!"));
		String realResult = (String) result.__tojava__(String.class);
		return "";
	}
}
