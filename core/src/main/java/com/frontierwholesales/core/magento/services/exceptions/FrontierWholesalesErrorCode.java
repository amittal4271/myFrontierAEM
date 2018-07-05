package com.frontierwholesales.core.magento.services.exceptions;

public enum FrontierWholesalesErrorCode {

	INVALID_REQUEST(0, "The request is invalid"),
	MISSING_PARAMETER(1, "Required query parameter is missing"),
	MISSING_HEADER(2, "Required header is missing"),
	IO_ERROR (3, "An IO error occurred"),
	RELEASE_REDIRECT (5, "Could not release redirect"),
	NAVIGATION_MODE (6, "Error in navigation mode");	
	
	// - Unauthorized

	private final int id;
	private final String message;

	FrontierWholesalesErrorCode(int id, String msg) {
		this.id = id;
		this.message = msg;
	}

	public int getId() {
		return this.id;
	}

	public String getMessage() {
		return this.message;
	}
}	

