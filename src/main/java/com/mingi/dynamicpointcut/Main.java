package com.mingi.dynamicpointcut;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mingi.conveniencepointcut.service.MyService;

public class Main {

public static void main(String[] args) {
		
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(AppConfig.class);
		
		TaskCaller caller = context.getBean(TaskCaller.class);
		caller.callTask();
		
		SimpleService service = (SimpleService) context.getBean("proxyFactoryBean");
		service.performTask();
		service.internalTask();
		
		context.close();
	}

}
