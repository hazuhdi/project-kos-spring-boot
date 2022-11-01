package id.ist.fileio.exception;

public class FacilityException extends RuntimeException{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6073153715386106994L;
	
	String message;
	
	public FacilityException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
