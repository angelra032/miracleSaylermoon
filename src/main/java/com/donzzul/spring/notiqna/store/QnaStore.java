package com.donzzul.spring.notiqna.store;

import java.util.ArrayList;

import com.donzzul.spring.common.PageInfo;
import com.donzzul.spring.notiqna.domain.Qna;
import com.donzzul.spring.reservation.domain.Reservation;


public interface QnaStore {
	public ArrayList<Qna> selectAllQna(); // Qna 리스트
	public ArrayList<Qna> selectAllQna(PageInfo pi);
	public Qna selectOneQna(int qaNo);
	public int insertQna(Qna qna);
	public int updateQna(Qna qna);
	public int deleteQna(int qaNo);
	public int getListCount();
	public int updateGroup(Qna qna);
	
	// 꿈나무 마이페이지
	public ArrayList<Qna> dreamQnaUpToThree(int userNo);
	public ArrayList<Qna> qnaListBydream(int userNo, PageInfo pi);
	public int dreamListCount(int userNo); 
	
	// 사업자 마이페이지
	public ArrayList<Qna> shopQnaUpToThree(int shopNo);
	
	// 관리자 페이지
	public ArrayList<Qna> adminQnaList(PageInfo pi);
	public int insertReply(Qna replyqna);
	public int updateQnaReply(int qnaNo);
	
	
}
