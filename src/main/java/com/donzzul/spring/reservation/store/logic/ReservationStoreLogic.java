package com.donzzul.spring.reservation.store.logic;

import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.donzzul.spring.reservation.domain.Reservation;
import com.donzzul.spring.reservation.store.ReservationStore;
import com.donzzul.spring.shop.domain.Shop;
import com.donzzul.spring.user.domain.User;

@Repository
public class ReservationStoreLogic implements ReservationStore {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insertReservation(Reservation reservation) {
		return sqlSession.insert("reservationMapper.insertRservation", reservation);
	}

	@Override
	public int updateUserPoint(User user) {
		return sqlSession.update("userMapper.updateUserPoint",user);
	}
	
	// ======================여기까지가 예약 끝

	// 꿈나무회원별 상위 3개 예약목록 불러오기
	@Override
	public ArrayList<Reservation> rListByDreamUpToThree(int userNo) {
		ArrayList<Reservation> reserve = (ArrayList)sqlSession.selectList("reservationMapper.rListByDreamUpToThree", userNo);
		return reserve;
	}
	
	// 꿈나무회원별 "전체" 예약목록 불러오기
	@Override
	public ArrayList<Reservation> reservationListByDream(int userNo) {
		ArrayList<Reservation> reserve = (ArrayList)sqlSession.selectList("reservationMapper.selectAllByDream",userNo);
		return reserve;
	}

	//// MZ회원별 상위 3개 예약목록 불러오기
	@Override
	public ArrayList<Reservation> rListByMZUpToThree(int userNo) {
		ArrayList<Reservation> reserve = (ArrayList)sqlSession.selectList("reservationMapper.rListByMZToThree", userNo);
		return reserve;
	}
	
	// MZ회원별 "전체" 예약목록 불러오기
	@Override
	public ArrayList<Reservation> reservationListByMZ(int userNo) {
		return null;
	}

	// 가게별 상위 3개 예약목록 불러오기
	@Override
	public ArrayList<Reservation> rListByShopUpToThree(int shopNo) {
		ArrayList<Reservation> reserve = (ArrayList)sqlSession.selectList("reservationMapper.rListByShopToThree", shopNo);
		return reserve;
	}

	// 가게별 "전체" 예약목록 불러오기
	@Override
	public ArrayList<Reservation> reservaionListByShop(int shopNo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//=======================여기까지가 예약 목록

	
	// 예약번호로 예약 하나 불러오기
	@Override
	public Reservation selectOne(int reservationNo) {
		Reservation reservation = sqlSession.selectOne("reservationMapper.selectOneByRno",reservationNo);
		System.out.println("스토어"+reservation);
		return reservation;
	}
	
	// 예약상태 취소로 변경하기
	@Override
	public int cancleReservation(int reservationNo) {
		int result = sqlSession.update("reservationMapper.cancleReservation", reservationNo);
		return result;
	}
	
	@Override
	public int deleteReservation(int reservationNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateReservation(int reservationNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int comfirmReservation(int reservationNo) {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public int complteReservation(int reservationNo) {
		// TODO Auto-generated method stub
		return 0;
	}



}
