package com.donzzul.spring.notiqna.store.logic;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.donzzul.spring.notiqna.domain.Qna;
import com.donzzul.spring.notiqna.domain.QnaPage;
import com.donzzul.spring.notiqna.store.QnaStore;


@Repository
public class QnaStoreLogic implements QnaStore {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int getListCount() {
		return sqlSession.selectOne("notiQnaMapper.selectQnaListCount");
	}

	@Override
	public ArrayList<Qna> selectAllQna() {
		return (ArrayList)sqlSession.selectList("notiQnaMapper.selectQnaAllList");
	}
	
	@Override
	public ArrayList<Qna> selectAllQna(QnaPage pi) {
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getPageLimit());
		return (ArrayList)sqlSession.selectList("notiQnaMapper.selectQnaAllList", null, rowBounds);
	}
//	, 

	@Override
	public Qna selectOneQna(int qaNo) {
		return sqlSession.selectOne("notiQnaMapper.selectQnaOneDetail", qaNo);
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
