package db;

public class DbException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5040533206967781826L;
	
	public DbException(String message){
		super(message);
	}
	public DbException(Exception e){
		super(e);
	}
	public DbException(String message, Exception e){
		super(message,e);
	}
}
