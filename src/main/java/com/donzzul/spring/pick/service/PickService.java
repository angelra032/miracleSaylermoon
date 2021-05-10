package com.donzzul.spring.pick.service;

import com.donzzul.spring.pick.domain.Pick;

public interface PickService {

	public int insertPick(Pick pick); // 찜 등록
	public int deletePick(Pick pick); // 찜 해제
}
