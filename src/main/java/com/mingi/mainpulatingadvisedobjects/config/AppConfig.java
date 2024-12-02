package com.mingi.mainpulatingadvisedobjects.config;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mingi.mainpulatingadvisedobjects.advice.AnotherInterceptor;
import com.mingi.mainpulatingadvisedobjects.advice.Debuginterceptor;
import com.mingi.mainpulatingadvisedobjects.pointcut.MySpecialPointcut;
import com.mingi.mainpulatingadvisedobjects.service.MyService;
import com.mingi.mainpulatingadvisedobjects.service.MyServiceImpl;

@Configuration
public class AppConfig {
	
	@Bean
	public MyService myService() {
		return new MyServiceImpl();
	}

	@Bean
	public Debuginterceptor debugInterceptor() {
		return new Debuginterceptor();
	}
	
	@Bean
	public AnotherInterceptor anotherInterceptor() {
		return new AnotherInterceptor();
	}
	
	@Bean
	public ProxyFactoryBean myServiceProxy(MyService myService, Debuginterceptor debugInterceptor) {
		ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
		
		proxyFactoryBean.setTarget(myService);
//		proxyFactoryBean.addAdvice(debugInterceptor);
		proxyFactoryBean.addAdvisor(new DefaultPointcutAdvisor((Advice) new MySpecialPointcut()));
		proxyFactoryBean.setProxyTargetClass(true);
		
		return proxyFactoryBean;
	}
	
	@Bean
	public MySpecialPointcut mySpecialPointcut() {
		return new MySpecialPointcut();
	}
	
	@Bean
	public DefaultPointcutAdvisor myAdvisor(MySpecialPointcut mySpecialPointcut,
			Debuginterceptor debugInterceptor) {
		return new DefaultPointcutAdvisor(mySpecialPointcut, debugInterceptor);
	}
	
	@Bean
	public DefaultPointcutAdvisor anotherAdvisor(MySpecialPointcut mySpecialPointcut,
			AnotherInterceptor anotherIntercetor) {
		return new DefaultPointcutAdvisor(mySpecialPointcut, anotherIntercetor);
	}
}
