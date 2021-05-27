package com.donzzul.spring.reservation.store.logic;

import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.donzzul.spring.common.PageInfo;
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
	public int getReservNo(int userNo) {
		return sqlSession.selectOne("reservationMapper.getReserveNo", userNo);
	}
	
	@Override
	public int updateUserPoint(Reservation nReservation) {
		return sqlSession.update("userMapper.updateUserPoint",nReservation);
	}
	
	@Override
	public int confirmRCount(Reservation reservation) {
		return sqlSession.selectOne("reservationMapper.confirmRCount",reservation);
	}

	@Override
	public Reservation selectOne(int reservationNo) {
		Reservation reservation = sqlSession.selectOne("reservationMapper.selectOneByRno",reservationNo);
		System.out.println("selectOne스토어"+reservation);
		return reservation;
	}
	
	@Override
	public ArrayList<Reservation> selectOneBySno(int shopNo) {
		ArrayList<Reservation> reservation = (ArrayList)sqlSession.selectList("reservationMapper.selectOneBySno",shopNo);
		return reservation;
	}
	
	
	@Override
	public int updateRstate(Reservation reservation) {
		int result = sqlSession.update("reservationMapper.updateRstate",reservation);
		return result;
	}
	
	// 사업자 포인트 업데이트
	@Override
	public int updateShopPoint(Reservation reservation) {
		return sqlSession.update("reservationMapper.updateShopPoint",reservation);
	}
	
	@Override
	public int cancleReservation(Reservation reservation) {
		System.out.println("스토어에 들어왔니..?");
		int result = sqlSession.update("reservationMapper.cancleReservation",reservation);
		System.out.println("스토어"+result);
		return result;
	}
	// ======================여기까지가 예약 끝

	
	
	// 꿈나무회원별 상위 3개 예약목록 불러오기
	@Override
	public ArrayList<Reservation> listByDreamUpToThree(int userNo) {
		ArrayList<Reservation> reserve = (ArrayList)sqlSession.selectList("reservationMapper.listByDreamUpToThree", userNo);
		return reserve;
	}
	
	// 꿈나무회원별 "전체" 예약목록 불러오기
	@Override
	public ArrayList<Reservation> reservationListByDream(int userNo, PageInfo pi) {
		int offset = (pi.getCurrentPage() -1 ) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		ArrayList<Reservation> reserve = (ArrayList)sqlSession.selectList("reservationMapper.selectAllByDream",userNo, rowBounds);
		return reserve;
	}

	//// MZ회원별 상위 3개 예약목록 불러오기
	@Override
	public ArrayList<Reservation> listByMZToThree(int userNo) {
		ArrayList<Reservation> reserve = (ArrayList)sqlSession.selectList("reservationMapper.listByMZToThree", userNo);
		return reserve;
	}
	
	// MZ회원별 "전체" 예약목록 불러오기
	@Override
	public ArrayList<Reservation> reservationListByMZ(int userNo, PageInfo pi) {
		int offset = (pi.getCurrentPage() -1 ) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		ArrayList<Reservation> reserve = (ArrayList)sqlSession.selectList("reservationMapper.selectAllByMZ",userNo, rowBounds);
		return reserve;
	}

	// 가게별 상위 3개 예약목록 불러오기
	@Override
	public ArrayList<Reservation> listByShopToThree(int shopNo) {
		ArrayList<Reservation> reserve = (ArrayList)sqlSession.selectList("reservationMapper.listByShopToThree", shopNo);
		return reserve;
	}

	// 가게별 "전체" 예약목록 불러오기
	@Override
	public ArrayList<Reservation> reservaionListByShop(int shopNo, PageInfo pi) {
		int offset = (pi.getCurrentPage() -1 ) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		ArrayList<Reservation> reserve = (ArrayList)sqlSession.selectList("reservationMapper.selectAllByShop",shopNo, rowBounds);
		return reserve;
	}
	
	

	@Override
	public int updateReservation(int reservationNo) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int complteReservation(int reservationNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	// ======================페이징 처리
	
	@Override
	public int selectListCount(int userNo) {
		return sqlSession.selectOne("reservationMapper.selectListCount", userNo);
	}

}
