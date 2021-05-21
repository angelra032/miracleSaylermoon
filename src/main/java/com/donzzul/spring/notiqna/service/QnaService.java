package com.donzzul.spring.notiqna.service;

import java.util.ArrayList;

import com.donzzul.spring.common.PageInfo;
import com.donzzul.spring.notiqna.domain.Qna;



public interface QnaService {
	public ArrayList<Qna> selectAllQna(); // Qna 리스트
	public ArrayList<Qna> selectAllQna(PageInfo pi);
	public Qna selectOneQna(int qaNo);
	public int insertQna(Qna qna);
	public int updateQna(Qna qna);
	public int deleteQna(int qaNo);
	public int getListCount();
	public int updateGroup(Qna qna); // 답글번호 수정
	
	// qna
}
