package com.donzzul.spring.payment.service;

import com.donzzul.spring.user.domain.User;

public interface PaymentService {

	// 결제, 돈쭐내역 저장, 출력, 룰렛포인트 적립, 
	// 리뷰포인트 적립, 포인트 사용, 포인트 조회
	
					// 포인트 업데이트문 다 계산해서 가져오는 거면 통합할건지 정하고
	public int usePoint(User user); // 포인트 사용
	public String selectMyPoint(User user); // 포인트 조회(string인가? user인가)
	public int saveRoulettePoint(User user); // 룰렛포인트 적립
	public int saveReviewPoint(User user);// 리뷰포인트 적립
	// 결제
	public int insertDonList(User user); // 돈쭐내역 업데이트
	public String selectDonList(User user); // 돈쭐내역 출력
	
}
