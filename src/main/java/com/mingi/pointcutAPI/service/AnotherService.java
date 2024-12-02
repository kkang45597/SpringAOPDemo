package com.mingi.pointcutAPI.service;

import static java.lang.System.out;

public class AnotherService {

	@CustomAnnotation
	public void myMethod() {
		out.println("Executing myMethod");
	}
	
	@CustomAnnotation
	public void differentMethod(int number) {
		out.println("Excuting differntMethod with Number : " + number);
	}
}
