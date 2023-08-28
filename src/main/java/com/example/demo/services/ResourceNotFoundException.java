package com.example.demo.services;

public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException() {
	}

	public ResourceNotFoundException(final String message) {
		super(message);
	}
}
