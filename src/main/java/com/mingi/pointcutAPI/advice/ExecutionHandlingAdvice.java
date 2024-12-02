package com.mingi.pointcutAPI.advice;

import static java.lang.System.out;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class ExecutionHandlingAdvice implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		
		try {
			invocation.proceed();
		}
		catch (Exception e){
			out.println("Execution time of " + invocation.getMethod().getName() + 
					", exception : " + e.getMessage());
			throw e;
		}
		
		return null;
	}

}