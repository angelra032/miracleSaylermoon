package com.donzzul.spring.notice.store;

import java.util.ArrayList;

import com.donzzul.spring.notice.domain.Notice;

public interface NoticeStore {
	public ArrayList<Notice> selectAllNotice(); // 공지사항 리스트
	public Notice selectOneNotice(int noticeNo); // 공지사항 조회
	public int insertNotice(Notice notice); // 공지사항 등록
	public int updateNotice(Notice notice); // 공지사항 수정
	public int deleteNotice(int noticeNo); // 공지사항 삭제
}
