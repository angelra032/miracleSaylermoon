package com.donzzul.spring.reservation.service;

import java.util.HashMap;

import com.donzzul.spring.reservation.domain.Reservation;

public interface ReservationService {

	// insert / select(by~~) / delete / update 
	public int insertReservation(Reservation reservation); //예약하기
	public HashMap<String, String> reservaionListByUser(int reservationNo, int userNo); // 회원별 예약목록 불러오기
	public HashMap<String, String> reservaionListByShop(int reservationNo, int shopNo); // 가게별 예약목록 불러오기
	public int deleteReservation(int reservationNo); // 예약취소
	public int updateReservation(Reservation reservation);// 예약수정하기
	public int comfirmReservation(int reservationNo);// 예약승인하기(사업자가 예약확인)
	public int cancleReservation(int reservationNo);// 예약취소하기
	public int completeReservation(int reservationNo);// 예약완료하기
}
