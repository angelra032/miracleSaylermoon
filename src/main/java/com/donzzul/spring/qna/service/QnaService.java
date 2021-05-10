package com.donzzul.spring.qna.service;

import java.util.ArrayList;

import com.donzzul.spring.qna.domain.Qna;


public interface QnaService {
	public ArrayList<Qna> selectAllQna(); // Qna 리스트
	public Qna selectOneQna(int qaNo);
	public int insertQna(Qna qna);
	public int updateQna(Qna qna);
	public int deleteQna(int qaNo);
}
