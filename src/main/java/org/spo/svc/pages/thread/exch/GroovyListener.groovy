package org.spo.svc.pages.thread.exch



class GroovyListener extends Thread {




	public  ThreadExchanger exchanger;



	void run(){
		
		
		println "my exchanger is "+exchanger.toString()
		sleep(9000)
		exchanger=ThreadExchanger.instance();
		println "my exchanger is "+exchanger.toString()
		while(true){
			//println "listening"

			
			
			if(!exchanger.getRequest().isEmpty()){
				println "handling"
				String response = handler.getByScenarioName("",exchanger.getRequest());
				exchanger.setResponse(response);
			}


			sleep(500)
		}
	}

}
