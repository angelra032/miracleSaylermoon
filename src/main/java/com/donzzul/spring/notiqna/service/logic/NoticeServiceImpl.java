package com.donzzul.spring.notiqna.service.logic;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donzzul.spring.common.PageInfo;
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
		return nStore.insertNotice(notice);
	}

	@Override
	public int updateNotice(Notice notice) {
		return nStore.updateNotice(notice);
	}

	@Override
	public int deleteNotice(int noticeNo) {
		return nStore.deleteNotice(noticeNo);
	}

	@Override
	public ArrayList<Notice> selectAllNotice(PageInfo pi) {
		return nStore.selectAllNotice(pi);
	}

	@Override
	public int getListCount() {
		return nStore.getListCount();
	}


}
