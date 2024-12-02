package com.mingi.mainpulatingadvisedobjects.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class Debuginterceptor implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		
		System.out.println("Debuginterceptor : Before method " + invocation.getMethod().getName());
		Object retVal = invocation.proceed();
		System.out.println("Debuginterceptor : After method " + invocation.getMethod().getName());
		return retVal;
	}

}
