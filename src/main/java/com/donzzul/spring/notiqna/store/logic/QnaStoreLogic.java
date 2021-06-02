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
		return sqlSession.update("notiQnaMapper.updateGroup", qna);
	}


	@Override
	public int updateQna(Qna qna) {
		return sqlSession.update("notiQnaMapper.updateQnA", qna);
	}

	@Override
	public int deleteQna(int qaNo) {
		return sqlSession.delete("notiQnaMapper.deleteQna", qaNo);
	}
	
	@Override
	public int updateQnaHit(int qaNo) {
		return sqlSession.delete("notiQnaMapper.updateQnaHit", qaNo);
	}

	
	// 드림 마이페이지
	@Override
	public ArrayList<Qna> dreamQnaUpToThree(int userNo) {
		return (ArrayList)sqlSession.selectList("notiQnaMapper.dreamQnaUpToThree",userNo);
	}

	@Override
	public ArrayList<Qna> qnaListBydream(int userNo, PageInfo pi) {
		int offset = (pi.getCurrentPage() -1 ) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		ArrayList<Qna> qna = (ArrayList)sqlSession.selectList("notiQnaMapper.selectAllByDream",userNo, rowBounds);
		return qna;
	}

	@Override
	public int dreamListCount(int userNo) {
		return sqlSession.selectOne("notiQnaMapper.dreamListCount", userNo);
	}


	// 사업자 마이페이지
	@Override
	public ArrayList<Qna> shopQnaUpToThree(int shopNo) {
		return (ArrayList)sqlSession.selectList("notiQnaMapper.shopQnaUpToThree",shopNo);
	}
	
	@Override
	public ArrayList<Qna> qnaListByPartner(int shopNo, PageInfo pi) {
		int offset = (pi.getCurrentPage() -1 ) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		ArrayList<Qna> qna = (ArrayList)sqlSession.selectList("notiQnaMapper.selectAllByPartner", shopNo, rowBounds);
		return qna;
	}

	@Override
	public int partnerListCount(int shopNo) {
		return sqlSession.selectOne("notiQnaMapper.qnaListCountByP",shopNo);
	}
	
	// 관리자 페이지
	@Override
	public ArrayList<Qna> adminQnaList(PageInfo pi) {
		int offset = (pi.getCurrentPage() -1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return (ArrayList)sqlSession.selectList("notiQnaMapper.selectQnaAdminList", null, rowBounds);
	}

	@Override
	public int insertReply(Qna replyqna) {
		return sqlSession.insert("notiQnaMapper.insertReplyAdmin", replyqna);
	}

	@Override
	public int updateQnaReply(int qnaNo) {
		return sqlSession.update("notiQnaMapper.updateOriginQna", qnaNo);
	}

	@Override
	public Qna selectOneReply(int qnaNo) {
		return sqlSession.selectOne("notiQnaMapper.selectOneReply", qnaNo);
	}

}
