package com.donzzul.spring.notiqna.store;

import java.util.ArrayList;

import com.donzzul.spring.notiqna.domain.Qna;
import com.donzzul.spring.notiqna.domain.QnaPage;


public interface QnaStore {
	public ArrayList<Qna> selectAllQna(); // Qna 리스트
	public ArrayList<Qna> selectAllQna(QnaPage pi);
	public Qna selectOneQna(int qaNo);
	public int insertQna(Qna qna);
	public int updateQna(Qna qna);
	public int deleteQna(int qaNo);
	public int getListCount();
}
