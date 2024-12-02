package com.mingi.pointcutAPI.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import static java.lang.System.out;

public class ExecutionTimeAdvice implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		
		long startTimeMills = System.currentTimeMillis();
		
		try {
			invocation.proceed();
		}
		finally {
			long timeTakeMills = System.currentTimeMillis() - startTimeMills;
			out.println("Execution time of " + invocation.getMethod().getName() + 
					" :: " + timeTakeMills + "ms");
		}
		
		return null;
	}

}
