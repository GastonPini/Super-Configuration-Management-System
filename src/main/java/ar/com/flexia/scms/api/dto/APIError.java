package ar.com.flexia.scms.api.dto;

public class APIError {

	private String code;
    private String message;
    private String stacktrace;

    public APIError(String code, String message, String stacktrace) {
        this.code = code;
        this.message = message;
        this.stacktrace = stacktrace;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getStacktrace() {
        return stacktrace;
    }
    
}