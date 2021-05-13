package com.donzzul.spring.notice.store.logic;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.donzzul.spring.notice.domain.Notice;
import com.donzzul.spring.notice.store.NoticeStore;

@Repository
public class NoticeSotreLogic implements NoticeStore {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public ArrayList<Notice> selectAllNotice() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Notice selectOneNotice(int noticeNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertNotice(Notice notice) {
		int result = sqlSession.insert("notiQnaMapper.insertNotice", notice);
		return result;
	}

	@Override
	public int updateNotice(Notice notice) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteNotice(int noticeNo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
