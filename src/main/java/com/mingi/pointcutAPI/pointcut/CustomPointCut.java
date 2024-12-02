package com.mingi.pointcutAPI.pointcut;

import java.lang.reflect.Method;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import static java.lang.System.out;

import com.mingi.pointcutAPI.service.CustomAnnotation;

public class CustomPointCut implements Pointcut {

	@Override
	public ClassFilter getClassFilter() {
		// boolean matches(Class<?> clazz);
		// 람다식의 파라미터 인퍼런스
		return clazz -> clazz.getName().startsWith("com.mingi.pointcutAPI.service"); // qualified name
	}

	@Override
	public MethodMatcher getMethodMatcher() {
		return new MethodMatcher() {

			@Override
			public boolean matches(Method method, Class<?> targetClass) {
				return method.isAnnotationPresent(CustomAnnotation.class) || 
						"differentMethod".equals(method.getName());
			}

			@Override
			public boolean isRuntime() {
				out.println("called isRuntime Method");
//				return true;
				return false;
			}

			@Override
			public boolean matches(Method method, Class<?> targetClass, Object... args) {
				if(args.length > 0 && args[0] instanceof String) {
					out.println("called matches [" + method.getName() + "] : arg is String");
					return true;
				}
				else {
					out.println("called matches [" + method.getName() + "] : arg is not String");
					return false;
				}
			}
		};
	}

}
