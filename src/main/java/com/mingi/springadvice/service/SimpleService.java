package com.mingi.springadvice.service;

public class SimpleService {

	public void performTask() {
		System.out.println("Performing a task");
	}
	
	public String returnGreeting(String name) {
		return "Hello, " + name;
	}
	
	public void threowException() throws Exception {
		throw new Exception("This is a test exception");
	}
}
