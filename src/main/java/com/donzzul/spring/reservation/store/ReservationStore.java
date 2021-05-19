package com.donzzul.spring.reservation.store;

import java.util.ArrayList;
import java.util.HashMap;

import com.donzzul.spring.reservation.domain.Reservation;
import com.donzzul.spring.shop.domain.Shop;
import com.donzzul.spring.user.domain.User;

public interface ReservationStore {

	
	public int insertReservation(Reservation reservation); //예약하기
	public int updateUserPoint(User user); // 예약할때 유저 포인트 삭감하기
	
	public ArrayList<Reservation> rListByDreamUpToThree(int userNo); // 꿈나무회원별 상위 3개 예약목록 불러오기
	public ArrayList<Reservation> reservationListByDream(int userNo); // 꿈나무회원별 "전체" 예약목록 불러오기
	
	public ArrayList<Reservation> rListByMZUpToThree(int userNo); // MZ회원별 상위 3개 예약목록 불러오기
	public ArrayList<Reservation> reservationListByMZ(int userNo); // MZ회원별 "전체" 예약목록 불러오기
	
	public ArrayList<Reservation> rListByShopUpToThree(int shopNo); // 가게별 상위 3개 예약목록 불러오기
	public ArrayList<Reservation> reservaionListByShop(int shopNo); // 가게별 "전체" 예약목록 불러오기
	
	public int deleteReservation(int reservationNo); // 예약취소
	public int updateReservation(int reservationNo); // 예약수정하기
	public int comfirmReservation(int reservationNo); //예약승인하기(사업자가예약확인)
	public int cancleReservation(int reservationNo); //예약취소하기
	public int complteReservation(int reservationNo); //예약완료하기
}
