package com.mingi.pointcutAPI;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mingi.pointcutAPI.config.AppConfig;
import com.mingi.pointcutAPI.config.AppConfigForEnableAspectJAutoProxy;
import com.mingi.pointcutAPI.service.AnotherService;
import com.mingi.pointcutAPI.service.MyService;

import static java.lang.System.out;

public class Main {
	
	public static void execAppConfig() {
		AnnotationConfigApplicationContext context = 
		new AnnotationConfigApplicationContext (AppConfig.class);

		MyService myService = (MyService) context.getBean("myServiceProxy");
		AnotherService anotherService = (AnotherService) context.getBean("anotherServiceProxy");
	
		myService.myMethod();
		myService.anotherMethod("test");
		
		anotherService.myMethod();
		anotherService.differentMethod(123);
		
		try {
			myService.methodWithException();
		}
		catch(Exception e) {
			out.println("Exception handled in main");
		}
		
		context.close();
	}
	
	public static void execAppConfigForEnableAspectJAutoProxy() {
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext (AppConfigForEnableAspectJAutoProxy.class);
		
		MyService myService = (MyService) context.getBean("myService");
		AnotherService anotherService = (AnotherService) context.getBean("anotherService");
	
		myService.myMethod();
		myService.anotherMethod("test");
		
		anotherService.myMethod();
		anotherService.differentMethod(123);
		
		try {
			myService.methodWithException();
		}
		catch(Exception e) {
			out.println("Exception handled in main");
		}
		
		context.close();
	}

	public static void main(String[] args) {
		execAppConfig();
//		execAppConfigForEnableAspectJAutoProxy();
	}
}
