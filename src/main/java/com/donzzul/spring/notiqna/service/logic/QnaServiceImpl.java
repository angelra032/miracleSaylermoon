package com.donzzul.spring.notiqna.service.logic;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donzzul.spring.notiqna.domain.Qna;
import com.donzzul.spring.notiqna.domain.QnaPage;
import com.donzzul.spring.notiqna.service.QnaService;
import com.donzzul.spring.notiqna.store.QnaStore;

@Service
public class QnaServiceImpl implements QnaService{

	@Autowired
	private QnaStore qnaStore;

	@Override
	public int getListCount() {
		return qnaStore.getListCount();
	}
	
	@Override
	public ArrayList<Qna> selectAllQna() {
		return qnaStore.selectAllQna();
	}
	
	@Override
	public ArrayList<Qna> selectAllQna(QnaPage pi) {
		return qnaStore.selectAllQna(pi);
	}

	@Override
	public Qna selectOneQna(int qaNo) {
		return qnaStore.selectOneQna(qaNo);
	}

	@Override
	public int insertQna(Qna qna) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateQna(Qna qna) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteQna(int qaNo) {
		// TODO Auto-generated method stub
		return 0;
	}




	
	
}
