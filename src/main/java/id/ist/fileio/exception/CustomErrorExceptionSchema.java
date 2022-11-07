package id.ist.fileio.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomErrorExceptionSchema {

	private String errCode;
	private String message;

	protected CustomErrorExceptionSchema() {
	}

	public CustomErrorExceptionSchema(String errCode, String message) {
		this.errCode = errCode;
		this.message = message;
	}

}
