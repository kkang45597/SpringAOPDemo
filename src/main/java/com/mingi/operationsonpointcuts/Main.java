package com.mingi.operationsonpointcuts;

import org.springframework.context.ApplicationContext; 
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mingi.pointcutAPI.config.AppConfig;
import com.mingi.pointcutAPI.config.AppConfigForEnableAspectJAutoProxy;

public class Main {

	public static void main(String[] args) {
		
		ApplicationContext context = 
				new AnnotationConfigApplicationContext(AppConfig.class);
		
		SimpleService simpleService = (SimpleService) context.getBean("simpleService");

		simpleService.methodA();
		simpleService.methodB();
	}

}
