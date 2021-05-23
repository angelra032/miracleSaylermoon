package com.donzzul.spring.reservation.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.donzzul.spring.common.PageInfo;
import com.donzzul.spring.reservation.domain.Reservation;
import com.donzzul.spring.shop.domain.Shop;
import com.donzzul.spring.user.domain.User;

public interface ReservationService {

	// insert / select(by~~) / delete / update 
	public int insertReservation(Reservation reservation); //예약하기
	public int getReservNo(int userNo); // 예약 시퀀스 번호 가져오기
	public int updateUserPoint(int sequenceNo);  // 예약할때 유저 포인트 삭감하기
	public int confirmRCount(Reservation reservation); // 예약할때 가능한 인원 체크하기
	
	public ArrayList<Reservation> rListByDreamUpToThree(int userNo); // 꿈나무회원별 상위 3개 예약목록 불러오기
	public ArrayList<Reservation> reservationListByDream(int userNo, PageInfo pi); // 꿈나무회원별 "전체" 예약목록 불러오기
	
	public ArrayList<Reservation> rListByMZUpToThree(int userNo); // MZ회원별 상위 3개 예약목록 불러오기
	public ArrayList<Reservation> reservationListByMZ(int userNo, PageInfo pi); // MZ회원별 "전체" 예약목록 불러오기
	
	public ArrayList<Reservation> rListByShopUpToThree(int shopNo); // 가게별 상위 3개 예약목록 불러오기
	public ArrayList<Reservation> reservaionListByShop(int shopNo, PageInfo pi); // 가게별 "전체" 예약목록 불러오기
	
	public Reservation selectOne(int reservationNo); // 예약번호로 예약 하나 불러오기
	public int cancleReservation(int reservationNo);// 예약취소하기
	
	public int updateReservation(Reservation reservation);// 예약수정하기
	public int comfirmReservation(int reservationNo);// 예약승인하기(사업자가 예약확인)
	public int deleteReservation(int reservationNo); // 예약삭제
	public int completeReservation(int reservationNo);// 예약완료하기
	
	public int getListCount(int userNo); // 페이징처리
	public int addReadCount(int reservationNo);
}
