package org.spo.svc.shared;

public class TestResourceException extends Exception {
	
	public TestResourceException(Throwable e){
		wrappedException=e;
		message="Connection to TestResource server failed";

	}
	
	
	
	String message;
	Throwable wrappedException;
	@Override
	public String getMessage() {
		if(wrappedException!=null){
			message = message +'\n'+" Original Exception type "+wrappedException.getClass().getName();
			if(wrappedException.getMessage()!=null){
				message = message +'\n'+"Orginal Message: "+wrappedException.getMessage();
			}
		}
		return "An Application Exception was thrown"+ " "+message;
	}

	


	public void setCause(Throwable e){
		wrappedException = e;
	}

}
