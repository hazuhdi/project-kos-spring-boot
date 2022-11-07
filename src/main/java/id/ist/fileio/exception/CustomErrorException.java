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
	private Object data;

	public CustomErrorException() {
		super();
	}

	public CustomErrorException(String message) {
		super(message);
	}

	public CustomErrorException(String errCode, String message) {
		this(message);
		this.errCode = errCode;
	}

	public CustomErrorException(String errCode, String message, Object data) {
		this(errCode, message);
		this.data = data;
	}
}
