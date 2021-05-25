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
	public int updateUserPoint(Reservation nReservation);  // 예약할때 유저 포인트 삭감하기
	public int confirmRCount(Reservation reservation); // 예약할때 가능한 인원 체크하기
	public Reservation selectOne(int reservationNo); // 예약번호로 예약 하나 불러오기
	public ArrayList<Reservation> selectOneBySno(int shopNo); // 샵번호로 모든 예약 불러오기
	public int updateRstate(Reservation reservation);
	public int updateShopPoint(Reservation reservation); // 사업자가 예약확인 시 사업자 포인트 업데이트
	
	public ArrayList<Reservation> rListByDreamUpToThree(int userNo); // 꿈나무회원별 상위 3개 예약목록 불러오기
	public ArrayList<Reservation> reservationListByDream(int userNo, PageInfo pi); // 꿈나무회원별 "전체" 예약목록 불러오기
	
	public ArrayList<Reservation> rListByMZUpToThree(int userNo); // MZ회원별 상위 3개 예약목록 불러오기
	public ArrayList<Reservation> reservationListByMZ(int userNo, PageInfo pi); // MZ회원별 "전체" 예약목록 불러오기
	
	public ArrayList<Reservation> rListByShopUpToThree(int shopNo); // 가게별 상위 3개 예약목록 불러오기
	public ArrayList<Reservation> reservaionListByShop(int shopNo, PageInfo pi); // 가게별 "전체" 예약목록 불러오기
	
	// 페이징
	public int getListCount(int userNo); // 페이징처리
	public int addReadCount(int reservationNo);
}
