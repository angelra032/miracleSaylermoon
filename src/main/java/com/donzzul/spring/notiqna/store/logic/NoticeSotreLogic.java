package com.donzzul.spring.notiqna.store.logic;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.donzzul.spring.common.PageInfo;
import com.donzzul.spring.notiqna.domain.Notice;
import com.donzzul.spring.notiqna.store.NoticeStore;


@Repository
public class NoticeSotreLogic implements NoticeStore {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public ArrayList<Notice> selectAllNotice() {
		return (ArrayList)sqlSession.selectList("notiQnaMapper.selectNoticePublicAllList");
	}

	@Override
	public Notice selectOneNotice(int noticeNo) {
		return sqlSession.selectOne("notiQnaMapper.selectNoticeOneDetail", noticeNo);
	}

	@Override
	public int insertNotice(Notice notice) {
		return sqlSession.insert("notiQnaMapper.insertNotice", notice);
	}

	@Override
	public int updateNotice(Notice notice) {
		return sqlSession.update("notiQnaMapper.updateNotice", notice);
	}

	@Override
	public int deleteNotice(int noticeNo) {
		return sqlSession.delete("notiQnaMapper.deleteNotice", noticeNo);
	}
	
	//페이징
	@Override
	public int getListCount() {
		return sqlSession.selectOne("notiQnaMapper.selectNoticeListCount");
	}

	@Override
	public ArrayList<Notice> selectAllNotice(PageInfo pi) {
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return (ArrayList)sqlSession.selectList("notiQnaMapper.selectNoticeAllList", null, rowBounds);
	}


}
