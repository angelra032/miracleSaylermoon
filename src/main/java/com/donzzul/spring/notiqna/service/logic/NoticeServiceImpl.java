package com.donzzul.spring.notiqna.service.logic;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donzzul.spring.notiqna.domain.Notice;
import com.donzzul.spring.notiqna.service.NoticeService;
import com.donzzul.spring.notiqna.store.NoticeStore;


@Service
public class NoticeServiceImpl implements NoticeService {
	
	@Autowired
	private NoticeStore nStore;

	@Override
	public ArrayList<Notice> selectAllNotice() {
		return nStore.selectAllNotice();
	}

	@Override
	public Notice selectOneNotice(int noticeNo) {
		return nStore.selectOneNotice(noticeNo);
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
