package com.mingi.mainpulatingadvisedobjects.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class AnotherInterceptor implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {

		System.out.println("AnotherInterceptor : Before method " + invocation.getMethod().getName());
		Object retVal = invocation.proceed();
		System.out.println("AnotherInterceptor : After method " + invocation.getMethod().getName());
		return null;
	}

}
