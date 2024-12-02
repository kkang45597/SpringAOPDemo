package com.mingi.mainpulatingadvisedobjects.pointcut;

import java.lang.reflect.Method;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.StaticMethodMatcher;

public class MySpecialPointcut extends StaticMethodMatcher implements Pointcut {

	@Override
	public boolean matches(Method method, Class<?> targetClass) {
		
		return "performOperation".equals(method.getName());
	}

	@Override
	public ClassFilter getClassFilter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MethodMatcher getMethodMatcher() {
		// TODO Auto-generated method stub
		return null;
	}

}
