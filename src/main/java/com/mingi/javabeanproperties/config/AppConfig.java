package com.mingi.javabeanproperties.config;

import org.aopalliance.aop.Advice;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mingi.javabeanproperties.advice.LoggingBeforeAdvice;
import com.mingi.javabeanproperties.service.SimpleService;
import com.mingi.javabeanproperties.service.SimplerServiceImpl;

@Configuration
public class AppConfig {
	
	@Bean
	public SimpleService simpleService() {
		return new SimplerServiceImpl();
	}
	
	@Bean
	public LoggingBeforeAdvice loggingBeforeAdvice() {
		return new LoggingBeforeAdvice();
	}
	
	@Bean
	public NameMatchMethodPointcut nameMatchMethodPointcut() {
		NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
		pointcut.setMappedName("doSomething");
		return pointcut;
	}

	@Bean
	public DefaultPointcutAdvisor loggingAdvisor() {
		DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
		advisor.setPointcut(nameMatchMethodPointcut());
		advisor.setAdvice(loggingBeforeAdvice());
		return advisor;
	}
	
	@Bean
	public ProxyFactoryBean proxyFacotryBean() {
		ProxyFactoryBean proxyFacotryBean = new ProxyFactoryBean();
		proxyFacotryBean.setTarget(simpleService());
		proxyFacotryBean.setProxyTargetClass(false);
		 // CGLIB용 최적화, false로 설정을 하더라도 setOptimize를 true로 설정하면 setProxyTargetClass도 true가 된다.
		proxyFacotryBean.setFrozen(false);
		proxyFacotryBean.setOptimize(true);
		proxyFacotryBean.setExposeProxy(true);
		proxyFacotryBean.setInterceptorNames("loggingAdvisor");
		proxyFacotryBean.setSingleton(true);
		
//		proxyFacotryBean.setInterfaces(new Class<?>[] {SimpleService.class});
		
		return proxyFacotryBean;
	}
	
	@Bean
	public SimpleService proxySimpleService() {
		return (SimpleService) proxyFacotryBean().getObject();
		
	}
}
