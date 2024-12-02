package com.mingi.springadvice.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class DebugInterceptor implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		System.out.println("Before : invocation = [" + invocation + "]");
		Object retVal = invocation.proceed();
		System.out.println("Invocation returned with value : " + retVal);
		return retVal;
	}

}
