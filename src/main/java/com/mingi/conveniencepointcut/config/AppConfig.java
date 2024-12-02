package com.mingi.conveniencepointcut.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.TransactionProxyFactoryBean;

import com.mingi.conveniencepointcut.service.MyServiceImpl;
import com.mingi.conveniencepointcut.service.MySpecialServiceImpl;
import com.mingi.conveniencepointcut.transaction.SimpleTransactionManager;

@Configuration
@EnableTransactionManagement
public class AppConfig {

	@Bean
	public PlatformTransactionManager transactionManager() {
		return new SimpleTransactionManager();
	}
	
	private TransactionProxyFactoryBean createProxy(
			PlatformTransactionManager transactionManager,
			Object target,
			Properties transactionAttributes) {
		TransactionProxyFactoryBean proxyFactoryBean = 
				new TransactionProxyFactoryBean();
		
		proxyFactoryBean.setTarget(target);
		proxyFactoryBean.setTransactionManager(transactionManager);
		proxyFactoryBean.setTransactionAttributes(transactionAttributes);
		proxyFactoryBean.setProxyTargetClass(true);
		return proxyFactoryBean;
	}
	
	@Bean
	public TransactionProxyFactoryBean myService (PlatformTransactionManager transactinManger) {
		Properties properties = new Properties();
		properties.setProperty("*", "PROPAGATION_REQUIRED");
		return createProxy (transactinManger, new MyServiceImpl(), properties);
	}
	
	@Bean
	public TransactionProxyFactoryBean mySpecialService (PlatformTransactionManager transactinManger) {
		Properties properties = new Properties();
//		properties.setProperty("*", "PROPAGATION_REQUIRED");
		properties.setProperty("get", "PROPAGATION_REQUIRED");
		properties.setProperty("find", "PROPAGATION_REQUIRED");
		properties.setProperty("load", "PROPAGATION_REQUIRED");
		properties.setProperty("store", "PROPAGATION_REQUIRED");
		
		return createProxy (transactinManger, new MySpecialServiceImpl(), properties);
	}
}
