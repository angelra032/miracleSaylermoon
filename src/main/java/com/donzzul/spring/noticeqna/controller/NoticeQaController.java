package com.donzzul.spring.noticeqna.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.donzzul.spring.noticeqna.domain.NoticeQa;
import com.donzzul.spring.noticeqna.service.NoticeQaService;

public class NoticeQaController {

	
	private NoticeQaService service;
	
	
	@RequestMapping(value="/notice/write", method=RequestMethod.POST)
	public String test(HttpServletRequest request) {
		
		return null;
	}
	
}
