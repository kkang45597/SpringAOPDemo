package com.mingi.springadvice.advice.Introduction;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(AppConfig.class);
		
		MyTargetClass myObject = context.getBean(MyTargetClass.class);
		Lockable lockable = (Lockable) myObject;
		
		if(!lockable.locked()) {
			myObject.setName("Hello");
			lockable.lock();
			String name = myObject.getName();
			System.out.println(name + ", " + lockable.locked());
		}
		else {
			try {
				myObject.setName("Good bye");
			}
			catch(LockedException e) {
				System.out.println(e.getMessage());
			}
		}
		context.close();
	}
}
