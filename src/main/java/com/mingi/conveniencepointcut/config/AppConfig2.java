package com.mingi.conveniencepointcut.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.TransactionProxyFactoryBean;

import com.mingi.conveniencepointcut.advice.SimpleAdvice;
import com.mingi.conveniencepointcut.service.MySpecialServiceImpl;
import com.mingi.conveniencepointcut.service.SimpleService;

import java.util.Properties;

import org.aopalliance.aop.Advice;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.aop.support.RegexpMethodPointcutAdvisor;

@Configuration
public class AppConfig2 {

	@Bean
	public SimpleService simpleService() {
		return new SimpleService();
	}
	
	@Bean
	public SimpleAdvice simpleAdvice() {
		return new SimpleAdvice();
	}
	
	@Bean
	public JdkRegexpMethodPointcut jdkRegexMethodPointcut() {
		JdkRegexpMethodPointcut pointcut = new JdkRegexpMethodPointcut();
		pointcut.setPatterns(".*get*.");
		
		return pointcut;
	}
	
	@Bean
	public DefaultPointcutAdvisor defaultPointcutAdvisor() {
		return new DefaultPointcutAdvisor(jdkRegexMethodPointcut(), simpleAdvice());
	}
	
	@Bean
	public RegexpMethodPointcutAdvisor setterAndAbsquatulatingAdvisor() {
		RegexpMethodPointcutAdvisor advisor = new RegexpMethodPointcutAdvisor();
		advisor.setAdvice(simpleAdvice());
		advisor.setPatterns(".*set*.", ".*.Absquatulating.*");
		return advisor;
	}
	
	@Bean
	public ProxyFactoryBean proxyFactoryBean() {
		ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
		proxyFactoryBean.setTarget(simpleService());
		proxyFactoryBean.addAdvisor(setterAndAbsquatulatingAdvisor());
		proxyFactoryBean.addAdvisor(defaultPointcutAdvisor());
		return proxyFactoryBean;
	}
}
