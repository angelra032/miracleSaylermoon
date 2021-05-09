package com.donzzul.spring.reservation.store;

import java.util.HashMap;

import com.donzzul.spring.reservation.domain.Reservation;

public interface ReservationStrore {

	
	public int insertReservation(Reservation reserveReservation); //예약하기
	public HashMap<String, String> reservationListByUser(int reservationNo, int userNo); // 회원별 예약목록 불러오기
	public HashMap<String, String> reservationListByShop(int reservationNo, int ShopNo); // 가게별 예약목록 불러오기
	public int deleteReservation(int reservationNo); // 예약취소
	public int updateReservation(int reservationNo); // 예약수정하기
	public int comfirmReservation(int reservationNo); //예약승인하기(사업자가예약확인)
	public int cancleReservation(int reservationNo); //예약취소하기
	public int complteReservation(int reservationNo); //예약완료하기
}
