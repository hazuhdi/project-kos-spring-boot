package id.ist.fileio.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomErrorException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4562498202822327091L;
	
	private String errCode;
	private String message;

	protected CustomErrorException() {
	}


	public CustomErrorException(String errCode, String message) {
		this.errCode = errCode;
		this.message = message;
	}
}
