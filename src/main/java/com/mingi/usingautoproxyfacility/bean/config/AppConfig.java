package com.mingi.usingautoproxyfacility.bean.config;

import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.aop.interceptor.SimpleTraceInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.TransactionAttributeSourceAdvisor;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import com.mingi.usingautoproxyfacility.bean.BusinessObject1;
import com.mingi.usingautoproxyfacility.bean.BusinessObject2;
import com.mingi.usingautoproxyfacility.bean.MyBean;

@Configuration
@EnableTransactionManagement// ProxyFactoryBean을 만들지 않아도 된다.
public class AppConfig { // Java 기반 Configuration Metadata: 빈으로 등록됨

	@Bean
	public SimpleTraceInterceptor myInterceptor() {
		return new SimpleTraceInterceptor();
	}
	
	@Bean
	public MyBean jdkBean() {
		return new MyBean();
	}
	
	@Bean
	public MyBean onlyBean() {
		return new MyBean();
	}
	
	@Bean
	@Order(1)
    public static BeanNameAutoProxyCreator beanNameAutoProxyCreator() {
        BeanNameAutoProxyCreator creator = new BeanNameAutoProxyCreator();
        creator.setBeanNames("jdk*", "onlyBean");
        creator.setInterceptorNames("myInterceptor");
        return creator;
	}
	
	@Bean
	@Order(2)
	public static DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		return new DefaultAdvisorAutoProxyCreator();
	}

	@Bean
	public TransactionAttributeSourceAdvisor transactionAdvisor(
			TransactionInterceptor transactionInterceptor) {
		TransactionAttributeSourceAdvisor advisor = new TransactionAttributeSourceAdvisor();
		advisor.setTransactionInterceptor(transactionInterceptor);
		return advisor;
	}
	
	@Bean
    public BusinessObject1 businessObject1() {
        return new BusinessObject1();
    }

    @Bean
    public BusinessObject2 businessObject2() {
        return new BusinessObject2();
    }
}
