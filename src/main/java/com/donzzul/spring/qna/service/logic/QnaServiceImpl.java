package com.donzzul.spring.qna.service.logic;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donzzul.spring.qna.domain.Qna;
import com.donzzul.spring.qna.service.QnaService;
import com.donzzul.spring.qna.store.QnaStore;

@Service
public class QnaServiceImpl implements QnaService{

	@Autowired
	private QnaStore qnaStore;

	@Override
	public ArrayList<Qna> selectAllQna() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Qna selectOneQna(int qaNo) {
		// TODO Auto-generated method stub
		return null;
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
