package com.donzzul.spring.chatting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.donzzul.spring.chatting.domain.Chat;
import com.donzzul.spring.chatting.service.ChatService;

@Controller
public class ChatController {

	
	@Autowired
	private ChatService service;
	
	
	@RequestMapping(value="/chat.dz")
	public ModelAndView chat() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("chatting/chatting");
		return mv;	
	}
	
	@ResponseBody
	@RequestMapping(value="insertChatContents.dz", method = RequestMethod.GET)
	public void insertChatContents(@ModelAttribute Chat chat) {
		System.out.println("여기에는 들어오니"+chat.toString());
		int result = service.insertChatContents(chat);
		
		
	}
}
