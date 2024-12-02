package com.mingi.pointcutAPI.service;

import static java.lang.System.out;

public class MyService {
	
	@CustomAnnotation
	public void myMethod() {
		out.println("Executing myMethod");
	}
	
	@CustomAnnotation
	public void anotherMethod(String arg) {
		out.println("Executing anotherMethod with args : " + arg);
	}
	
	public void methodWithException() throws Exception {
		throw new Exception("Executing in methodWithException");
	}
}
