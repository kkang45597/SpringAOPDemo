package com.mingi.pointcutAPI.config;

import org.springframework.aop.Pointcut;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import com.mingi.pointcutAPI.advice.ExecutionHandlingAdvice;
import com.mingi.pointcutAPI.advice.ExecutionTimeAdvice;
import com.mingi.pointcutAPI.advice.LoggingAdvice;
import com.mingi.pointcutAPI.pointcut.CustomPointCut;
import com.mingi.pointcutAPI.service.AnotherService;
import com.mingi.pointcutAPI.service.MyService;

@Configuration
public class AppConfig {
	
	@Lazy
	@Bean
	public MyService myService() {
		return new MyService();
	}
	
	@Lazy
	@Bean
	public AnotherService anotherService() {
		return new AnotherService();
	}
	
	@Lazy
	@Bean
	public Pointcut customPointCut() {
		return new CustomPointCut();
	}
	
	@Lazy
	@Bean
	public Pointcut apsetJPointcut() {
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression("execution(* com.mingi.pointcutAPI.service.MyService.myMethod(..))");
		
		return pointcut;
	}
	
	@Lazy
	@Bean
	public DefaultPointcutAdvisor aspectJloggingAdvisor(
			@Qualifier("customPointCut") Pointcut pointcut) {
		return new DefaultPointcutAdvisor(pointcut, new LoggingAdvice());
	}
	
	@Lazy
	@Bean
	public DefaultPointcutAdvisor loggingAdvisor(
			@Qualifier("customPointCut") Pointcut pointcut) {
		return new DefaultPointcutAdvisor(pointcut, new LoggingAdvice());
	}
	
	@Lazy
	@Bean
	public DefaultPointcutAdvisor excutionTimeAdvisor(
			@Qualifier("customPointCut") Pointcut pointcut) {
		return new DefaultPointcutAdvisor(pointcut, new ExecutionTimeAdvice());
	}
	
	@Lazy
	@Bean
	public DefaultPointcutAdvisor excutionHadlingAdvisor(
			@Qualifier("customPointCut") Pointcut pointcut) {
		return new DefaultPointcutAdvisor(pointcut, new ExecutionHandlingAdvice());
	}
	
	@Lazy
	@Bean
	public ProxyFactoryBean myServiceProxy(MyService myService, 
			@Qualifier("loggingAdvisor") DefaultPointcutAdvisor loggingAdvisor,
			@Qualifier("excutionTimeAdvisor") DefaultPointcutAdvisor excutionTimeAdvisor,
			@Qualifier("excutionHadlingAdvisor") DefaultPointcutAdvisor excutionHadlingAdvisor) {
			
		ProxyFactoryBean proxyFacotryBean = new ProxyFactoryBean();
		proxyFacotryBean.setTarget(myService);
		proxyFacotryBean.setInterceptorNames(
				"loggingAdvisor",
				"excutionTimeAdvisor",
				"excutionHadlingAdvisor");
		
		return proxyFacotryBean;
	}
	
	@Lazy
	@Bean
	public ProxyFactoryBean anotherServiceProxy(AnotherService anotherService, 
			@Qualifier("loggingAdvisor") DefaultPointcutAdvisor loggingAdvisor,
			@Qualifier("excutionTimeAdvisor") DefaultPointcutAdvisor excutionTimeAdvisor,
			@Qualifier("excutionHadlingAdvisor") DefaultPointcutAdvisor excutionHadlingAdvisor) {
			
		ProxyFactoryBean proxyFacotryBean = new ProxyFactoryBean();
		proxyFacotryBean.setTarget(anotherService);
		proxyFacotryBean.setInterceptorNames(
				"loggingAdvisor",
				"excutionTimeAdvisor",
				"excutionHadlingAdvisor");
		
		return proxyFacotryBean;
	}
}
