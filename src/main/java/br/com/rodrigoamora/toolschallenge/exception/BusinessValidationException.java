package br.com.rodrigoamora.toolschallenge.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class BusinessValidationException extends RuntimeException {

	private Map<String, List<String>> fieldErrors;
	private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
	
	public BusinessValidationException() {}
	
	public BusinessValidationException(String stringCode) {
		super(stringCode);
	}
	
	public BusinessValidationException(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	
	public void addFieldErrors(String field, String error) {
		if (fieldErrors == null) {
			fieldErrors = new HashMap<>();
		}
		
		List<String> errors = fieldErrors.get(field);
		
		if (errors == null) {
			errors = new ArrayList<>();
			fieldErrors.put(field, errors);
		}
		
		errors.add(error);
	}
	
	public void throwIfExistsErrors() {
		if (getMessage() != null || (fieldErrors != null && !fieldErrors.isEmpty())) {
			throw this;
		}
	}

	public void fieldNotNull(String fieldName, String value) {
		fieldValidate(fieldName, value != null, "is_empty");
	}

	public void fieldValidate(String fieldName, boolean isValid, String message) {
		if (!isValid) {
			addFieldErrors(fieldName, message);
		}
	}
	
	public String toJsonString() {
		JSONObject errorResponse = new JSONObject();
		
		if (getMessage() != null) {
			errorResponse.put("errors", Arrays.asList(getMessage()));
		}
		
		if (fieldErrors != null) {
			errorResponse.put("fieldErrors", new JSONObject(fieldErrors));
		}
		
		return errorResponse.toString();
	}
	
	@Override
	public String toString() {
		return toJsonString();
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
}
