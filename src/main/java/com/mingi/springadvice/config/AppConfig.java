package com.mingi.springadvice.config;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mingi.springadvice.advice.CountingAfterReturningAdvice;
import com.mingi.springadvice.advice.CountingBeforeAdvice;
import com.mingi.springadvice.advice.DebugInterceptor;
import com.mingi.springadvice.advice.SimpleThrowsAdvice;
import com.mingi.springadvice.service.SimpleService;

@Configuration
public class AppConfig {

	@Bean
	public SimpleService simpleService() {
		return new SimpleService();
	}
	
	@Bean
	public DebugInterceptor debugInterceptor() {
		return new DebugInterceptor();
	}
	
	@Bean
	public CountingBeforeAdvice countingBeforeAdvice() {
		return new CountingBeforeAdvice();
	}
	
	@Bean
	public CountingAfterReturningAdvice countingAfterReturningAdvice() {
		return new CountingAfterReturningAdvice();
	}
	
	@Bean
	public SimpleThrowsAdvice simpleThrowsAdvice() {
		return new SimpleThrowsAdvice();
	}
	
	@Bean
	public ProxyFactoryBean proxyFactoryBean() {
		ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
		proxyFactoryBean.setTarget(simpleService());
		proxyFactoryBean.setProxyTargetClass(true);
		proxyFactoryBean.addAdvisor(new DefaultPointcutAdvisor(debugInterceptor()));
		proxyFactoryBean.addAdvisor(new DefaultPointcutAdvisor(countingBeforeAdvice()));
		proxyFactoryBean.addAdvisor(new DefaultPointcutAdvisor(countingAfterReturningAdvice()));
		proxyFactoryBean.addAdvisor(new DefaultPointcutAdvisor(simpleThrowsAdvice()));
		
		return proxyFactoryBean;
	}
	 
	
	
	
}
