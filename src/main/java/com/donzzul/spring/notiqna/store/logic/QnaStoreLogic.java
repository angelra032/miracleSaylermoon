package com.donzzul.spring.notiqna.store.logic;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.donzzul.spring.common.PageInfo;
import com.donzzul.spring.notiqna.domain.Qna;
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
	public ArrayList<Qna> selectAllQna(PageInfo pi) {
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return (ArrayList)sqlSession.selectList("notiQnaMapper.selectQnaAllList", null, rowBounds);
	}
//	, 

	@Override
	public Qna selectOneQna(int qaNo) {
		return sqlSession.selectOne("notiQnaMapper.selectQnaOneDetail", qaNo);
	}

	@Override
	public int insertQna(Qna qna) {
		System.out.println(qna.toString());
		return sqlSession.insert("notiQnaMapper.inserQna", qna);
	}
	
	@Override
	public int updateGroup(Qna qna) {
		return sqlSession.update("notiQnaMapper.updateGroup", qna.getQnaNo());
	}


	@Override
	public int updateQna(Qna qna) {
		return sqlSession.update("notiQnaMapper.updateQnA", qna);
	}

	@Override
	public int deleteQna(int qaNo) {
		// TODO Auto-generated method stub
		return sqlSession.delete("notiQnaMapper.deleteQna", qaNo);
	}




}
