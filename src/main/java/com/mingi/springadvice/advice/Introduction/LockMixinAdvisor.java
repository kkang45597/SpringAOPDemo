package com.mingi.springadvice.advice.Introduction;

import org.springframework.aop.support.DefaultIntroductionAdvisor;

public class LockMixinAdvisor extends DefaultIntroductionAdvisor {

	public LockMixinAdvisor() {
		
		// 인스턴스별 어드바이스를 생성한다.
		super(new LockMixin(), Lockable.class);
	}
}
