package com.mingi.usingautoproxyfacility.bean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.cglib.proxy.Proxy;

import com.mingi.usingautoproxyfacility.bean.config.AppConfig;
import com.mingi.usingautoproxyfacility.bean.service.BusinessService;

public class Main {
	
	public static void printProxyInfo(Object bean) {
		Class<?> targetClass = AopProxyUtils.ultimateTargetClass(bean);
		System.out.println("Bean class: " + bean.getClass().getName());
		System.out.println("Is JDK Dynamic Proxy: " + Proxy.isProxyClass(bean.getClass()));
		System.out.println("Is CGLIB Proxy: " + bean.getClass().getName().contains("$$"));
		System.out.println("Target Class: " + targetClass.getName());
		System.out.println("--------------------------------------------------------------------------");
	}

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(AppConfig.class);
		
		MyBean jdkBean = context.getBean("jdkBean", MyBean.class);
		MyBean onlyBean = context.getBean("onlyBean", MyBean.class);
//		printProxyInfo(jdkBean);
//		printProxyInfo(onlyBean);
		
		BusinessService buzz1 = context.getBean("businessObject1", BusinessService.class);
		BusinessService buzz2 = context.getBean("businessObject2", BusinessService.class);
		printProxyInfo(buzz1);
		printProxyInfo(buzz2);
		
		context.close();
	}

}
