package com.mingi.springadvice.advice;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

public class CountingAfterReturningAdvice implements AfterReturningAdvice {

	private int count;
	
	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) 
			throws Throwable {	
		count++;
		System.out.println("After method : 0" + method.getName() + 
				", return value : " + returnValue + ", count = " + count);
	}
	
	public int getCount() {
		return count;
	}
	
}
