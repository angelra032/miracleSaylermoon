package com.donzzul.spring.reservation.service.logic;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donzzul.spring.reservation.domain.Reservation;
import com.donzzul.spring.reservation.service.ReservationService;
import com.donzzul.spring.reservation.store.ReservationStore;
import com.donzzul.spring.shop.domain.Shop;
import com.donzzul.spring.user.domain.User;

@Service
public class ReservationServiceImpl implements ReservationService{

	
	@Autowired
	private ReservationStore rStore;

	@Override
	public int insertReservation(Reservation reservation) {
		return rStore.insertReservation(reservation);
	}

	@Override
	public int updateUserPoint(User user) {
		return rStore.updateUserPoint(user);
	}
	//-----------------------여기까지가 예약하기
	
	// 꿈나무회원별 상위 3개 예약목록 불러오기
	@Override
	public ArrayList<Reservation> rListByDreamUpToThree(int userNo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	// 꿈나무회원별 "전체" 예약목록 불러오기
	@Override
	public ArrayList<Reservation> reservationListByDream(int userNo) {
		ArrayList<Reservation> rList = rStore.reservationListByDream(userNo);
		System.out.println(rList);
		return rList;
	}
	
	// MZ회원별 상위 3개 예약목록 불러오기
	@Override
	public ArrayList<Reservation> rListByMZUpToThree(int userNo) {
		// TODO Auto-generated method stub
		return null;
	}

	// MZ회원별 "전체" 예약목록 불러오기
	@Override
	public ArrayList<Reservation> reservationListByMZ(int userNo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	// 가게별 상위 3개 예약목록 불러오기
	@Override
	public ArrayList<Reservation> rListByShopUpToThree(int shopNo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	// 가게별 "전체" 예약목록 불러오기
	@Override
	public ArrayList<Reservation> reservaionListByShop(int shopNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteReservation(int reservationNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateReservation(Reservation reservation) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int comfirmReservation(int reservationNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int cancleReservation(int reservationNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int completeReservation(int reservationNo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
