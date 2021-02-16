package com.example.demo;

public class ApplicationNotFoundException extends RuntimeException {
	
	{
		System.out.println("in custom exception file");
	}
	

	public ApplicationNotFoundException() {
		super("THis is my own exception");
	}
	
}
