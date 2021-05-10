package com.donzzul.spring.payment.service;

import com.donzzul.spring.user.domain.User;

public interface PaymentService {

	public int updatePoint(User user); // 포인트 사용 (사용, 적립)
	public int updateDonList(User user); // 돈쭐내역 업데이트
	public String selectDonList(User user); // 돈쭐내역 출력
	
}
