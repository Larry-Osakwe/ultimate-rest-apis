package com.skyapi.weatherforecast;

public class GeolocationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GeolocationException(String message, Throwable cause) {
		super(message, cause);
	}

	public GeolocationException(String message) {
		super(message);
	}

}