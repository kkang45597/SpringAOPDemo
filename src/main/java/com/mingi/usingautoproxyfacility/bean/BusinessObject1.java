package com.mingi.usingautoproxyfacility.bean;

import org.springframework.transaction.annotation.Transactional;

import com.mingi.usingautoproxyfacility.bean.service.BusinessService;

public class BusinessObject1 implements BusinessService {

	@Transactional // 선언적 트랜잭션
	public void process() {
		System.out.println("BusinessObject1 클래스에서 process 메서드 동작");
		
	}
}
