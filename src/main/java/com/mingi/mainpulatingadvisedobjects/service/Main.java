package com.mingi.mainpulatingadvisedobjects.service;

import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mingi.mainpulatingadvisedobjects.config.AppConfig;

public class Main {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext (AppConfig.class);
		
		MyService myServiceProxy = (MyService) context.getBean("myServiceProxy");
		
		Advised advised = (Advised) myServiceProxy;
		
		System.out.println("Advisors count : " + advised.getAdvisorCount());
		
		DefaultPointcutAdvisor myAdvisor = 
				(DefaultPointcutAdvisor) context.getBean("myAdvisor");
		
		advised.addAdvisor(myAdvisor);
		System.out.println("Advisors count : " + advised.getAdvisorCount());
		
		DefaultPointcutAdvisor anotherAdvisor = 
				(DefaultPointcutAdvisor) context.getBean("anotherAdvisor");
		advised.replaceAdvisor(myAdvisor, anotherAdvisor);
		
		myServiceProxy.performOperation();
		
		advised.removeAdvisor(anotherAdvisor);
	}
}
