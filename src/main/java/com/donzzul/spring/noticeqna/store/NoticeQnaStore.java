package com.donzzul.spring.noticeqna.store;

import java.sql.Connection;

import com.donzzul.spring.noticeqna.domain.NoticeQna;

public interface NoticeQnaStore {
	public NoticeQna selectOneNotice(Connection conn, int noticeNo);
}
