package com.mingi.springadvice.advice;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mingi.springadvice.config.AppConfig;
import com.mingi.springadvice.service.SimpleService;

public class Main {

	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(AppConfig.class);
		
		SimpleService service = (SimpleService) context.getBean("proxyFactoryBean");
		service.performTask();
		String greeting = service.returnGreeting("Me");
		System.out.println("Greeting : " + greeting);
		
		try {
			service.threowException();
		} 
		catch(Exception e) {
			System.out.println("Exception caught in main : " + e.getMessage());
		}
	}

}
