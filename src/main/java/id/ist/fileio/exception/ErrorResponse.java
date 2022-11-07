package id.ist.fileio.exception;


import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    // customizing timestamp serialization format
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date timestamp;

    private String code;

    private String status;

    private String message;

    private String stackTrace;

    private Object data;

    public ErrorResponse() {
        timestamp = new Date();
    }

    public ErrorResponse(
            String status2,
            String message
    ) {
        this();
    
        this.code = status2;
        this.status = status2;
        this.message = message;
    }

    public ErrorResponse(
            String status2,
            String message,
            String stackTrace
    ) {
        this(
                status2,
                message        
        );

        this.stackTrace = stackTrace;
    }

    public ErrorResponse(
            String status2,
            String message,
        String stackTrace,
            Object data
    ) {
        this(
                status2,
                message,
        stackTrace
        );

        this.data = data;
    }
}
