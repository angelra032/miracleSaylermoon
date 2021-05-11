package com.donzzul.spring.qna.store.logic;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.donzzul.spring.qna.domain.Qna;
import com.donzzul.spring.qna.store.QnaStore;

@Repository
public class QnaStoreLogic implements QnaStore {

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
