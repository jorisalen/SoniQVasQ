package api;


public class ApiException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5040533206967781826L;
	
	public ApiException(String message){
		super(message);
	}
	public ApiException(Exception e){
		super(e);
	}
	public ApiException(String message, Exception e){
		super(message,e);
	}
}
