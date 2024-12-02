package com.mingi.usingautoproxyfacility.bean;

import org.springframework.transaction.annotation.Transactional;

import com.mingi.usingautoproxyfacility.bean.service.BusinessService;

public class BusinessObject2  implements BusinessService {

	@Transactional // 선언적 트랜잭션
	public void process() {
		System.out.println("BusinessObject2 클래스에서 process 메서드 동작");
		
	}
}
