package com.donzzul.spring.reservation.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.donzzul.spring.reservation.domain.Reservation;
import com.donzzul.spring.shop.domain.Shop;
import com.donzzul.spring.user.domain.User;

public interface ReservationService {

	// insert / select(by~~) / delete / update 
	public int insertReservation(Reservation reservation); //예약하기
	
	public int updateUserPoint(User user); // 다시하기
	public ArrayList<Reservation> reservaionListByDream(int userNo); // 꿈나무회원별 예약목록 불러오기
	public ArrayList<Reservation> reservationListByMZ(int userNo); // MZ회원별 예약목록 불러오기
	public String reservaionListByShop(int reservationNo, int shopNo); // 가게별 예약목록 불러오기
	public int deleteReservation(int reservationNo); // 예약취소
	public int updateReservation(Reservation reservation);// 예약수정하기
	public int comfirmReservation(int reservationNo);// 예약승인하기(사업자가 예약확인)
	public int cancleReservation(int reservationNo);// 예약취소하기
	public int completeReservation(int reservationNo);// 예약완료하기
}
