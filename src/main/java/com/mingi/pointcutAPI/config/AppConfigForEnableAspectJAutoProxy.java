package com.mingi.pointcutAPI.config;

import org.springframework.aop.Pointcut;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.mingi.pointcutAPI.advice.ExecutionHandlingAdvice;
import com.mingi.pointcutAPI.advice.ExecutionTimeAdvice;
import com.mingi.pointcutAPI.advice.LoggingAdvice;
import com.mingi.pointcutAPI.pointcut.CustomPointCut;
import com.mingi.pointcutAPI.service.AnotherService;
import com.mingi.pointcutAPI.service.MyService;

@Configuration
@EnableAspectJAutoProxy // ProxyFactoryBean을 만들지 않아도 된다.
public class AppConfigForEnableAspectJAutoProxy {
	
	@Bean
	public MyService myService() {
		return new MyService();
	}
	
	@Bean
	public AnotherService anotherService() {
		return new AnotherService();
	}
	
	@Bean
	public Pointcut customPointCut() {
		return new CustomPointCut();
	}
	
	@Bean
	public Pointcut apsetJPointcut() {
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression("execution(* com.mingi.pointcutAPI.service.MyService.myMethod(..))");
		
		return pointcut;
	}
	
	@Bean
	public DefaultPointcutAdvisor aspectJloggingAdvisor(
			@Qualifier("customPointCut") Pointcut pointcut) {
		return new DefaultPointcutAdvisor(pointcut, new LoggingAdvice());
	}
	
	@Bean
	public DefaultPointcutAdvisor loggingAdvisor(
			@Qualifier("customPointCut") Pointcut pointcut) {
		return new DefaultPointcutAdvisor(pointcut, new LoggingAdvice());
	}
	
	@Bean
	public DefaultPointcutAdvisor excutionTimeAdvisor(
			@Qualifier("customPointCut") Pointcut pointcut) {
		return new DefaultPointcutAdvisor(pointcut, new ExecutionTimeAdvice());
	}
	
	@Bean
	public DefaultPointcutAdvisor excutionHadlingAdvisor(
			@Qualifier("customPointCut") Pointcut pointcut) {
		return new DefaultPointcutAdvisor(pointcut, new ExecutionHandlingAdvice());
	}
}
