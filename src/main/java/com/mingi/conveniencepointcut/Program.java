package com.mingi.conveniencepointcut;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mingi.conveniencepointcut.config.AppConfig2;
import com.mingi.conveniencepointcut.service.SimpleService;

public class Program {

	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(AppConfig2.class);
		
		SimpleService service = (SimpleService) context.getBean("proxyFactoryBean");
		service.setName("Jhon");
		service.getName();
		service.absquatulate();
		service.performTask();
	}

}
