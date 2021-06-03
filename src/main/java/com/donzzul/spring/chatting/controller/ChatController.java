package com.donzzul.spring.chatting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ChatController {

	@RequestMapping(value="/chat.dz")
	public ModelAndView chat() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("chatting/chatting");
		return mv;	
	}
	
	@ResponseBody
	@RequestMapping(value="insertChatContents.dz", method = RequestMethod.GET)
	public void insertChatContents() {
		
		
		
	}
}
