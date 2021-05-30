package com.donzzul.spring.reservation.service.logic;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donzzul.spring.common.PageInfo;
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
	public int getReservNo(int userNo) {
		return rStore.getReservNo(userNo);
	}

	@Override
	public int updateUserPoint(Reservation nReservation) {
		return rStore.updateUserPoint(nReservation);
	}
	
	@Override
	public int confirmRCount(Reservation reservation) {
		return rStore.confirmRCount(reservation);
	}

	// 예약번호로 예약 하나 불러오기
	@Override
	public Reservation selectOne(int reservationNo) {
		Reservation reservation = rStore.selectOne(reservationNo);
		return reservation;
	}
	
	@Override
	public ArrayList<Reservation> selectOneBySno(int shopNo) {
		ArrayList<Reservation> reservation = rStore.selectOneBySno(shopNo);
		return reservation;
	}

	@Override
	public int updateRstate(Reservation reservation) {
		int result = rStore.updateRstate(reservation);
		return result;
	}

	@Override
	public int updateShopPoint(Reservation reservation) {
		return rStore.updateShopPoint(reservation);
	}

	@Override
	public int cancleReservation(Reservation reservation) {
		System.out.println("여기 넘어왔니...?");
		int result = rStore.cancleReservation(reservation);
		System.out.println("서비스 임플 "+ result);
		return result;
	}
	
	//-----------------------여기까지가 예약하기
	
	
	
	// 꿈나무회원별 상위 3개 예약목록 불러오기
	@Override
	public ArrayList<Reservation> listByDreamUpToThree(int userNo) {
		ArrayList<Reservation> rList = rStore.listByDreamUpToThree(userNo);
		return rList;
	}
	
	// 꿈나무회원별 "전체" 예약목록 불러오기
	@Override
	public ArrayList<Reservation> reservationListByDream(int userNo, PageInfo pi) {
		ArrayList<Reservation> rList = rStore.reservationListByDream(userNo, pi);
		return rList;
	}
	
	// MZ회원별 상위 3개 예약목록 불러오기
	@Override
	public ArrayList<Reservation> listByMZToThree(int userNo) {
		ArrayList<Reservation> rList = rStore.listByMZToThree(userNo);
		return rList;
	}

	// MZ회원별 "전체" 예약목록 불러오기
	@Override
	public ArrayList<Reservation> reservationListByMZ(int userNo, PageInfo pi) {
		ArrayList<Reservation> rList = rStore.reservationListByMZ(userNo, pi);
		return rList;
	}
	
	// 가게별 상위 3개 예약목록 불러오기
	@Override
	public ArrayList<Reservation> listByShopToThree(int shopNo) {
		ArrayList<Reservation> rList = rStore.listByShopToThree(shopNo);
		return rList;
	}
	
	// 가게별 "전체" 예약목록 불러오기
	@Override
	public ArrayList<Reservation> reservaionListByShop(int shopNo, PageInfo pi) {
		ArrayList<Reservation> rList = rStore.reservaionListByShop(shopNo, pi);
		return rList;
	}

	//=========================페이징 처리
	@Override
	public int getListCount(int userNo) {
		return rStore.selectListCount(userNo);
	}

	@Override
	public int selectShopListCount(int shopNo) {
		return rStore.selectShopListCount(shopNo);
	}

	@Override
	public ArrayList<Reservation> reservationState(Reservation reservation) {
		ArrayList<Reservation> list = rStore.reservationState(reservation);
		return list;
	}

}
