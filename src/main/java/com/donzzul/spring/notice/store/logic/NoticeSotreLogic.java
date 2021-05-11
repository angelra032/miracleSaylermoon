package com.donzzul.spring.notice.store.logic;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.donzzul.spring.notice.domain.Notice;
import com.donzzul.spring.notice.store.NoticeStore;

@Repository
public class NoticeSotreLogic implements NoticeStore {

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
		// TODO Auto-generated method stub
		return 0;
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
