package com.donzzul.spring.chatting.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.socket.WebSocketSession;

import com.donzzul.spring.chatting.domain.Chat;
import com.donzzul.spring.chatting.service.ChatService;

@Controller
public class ChatController {

	List<HashMap<String, String>> roomList = new ArrayList<HashMap<String,String>>();
	
	@Autowired
	private ChatService service;
	
	
	@RequestMapping(value="/chat.dz")
	public ModelAndView chat() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("chatting/chatting");
		return mv;	
	}
	
	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping(value="insertChatContents.dz", method = RequestMethod.GET)
	 * public void insertChatContents(@ModelAttribute Chat chat) {
	 * System.out.println("여기에는 들어오니"+chat.toString()); int result =
	 * service.insertChatContents(chat); }
	 */
	
	/*사용자가 실시간 상담 눌렀을경우 
	 * list에 쌓는다
	 * */
	@ResponseBody
	@RequestMapping("/createRoom")
	public List<HashMap<String, String>> createRoom(@RequestParam HashMap<Object, Object> params){
		String userId = (String) params.get("userId");
		if(userId != null && !userId.trim().equals("")) {
			List<HashMap<String, String>> new_list = roomList.stream().filter(o->o.get("userId").equals(userId)).collect(Collectors.toList());
			//해당 아이디의 방이 없을경우만 방데이터 쌓기
			if(new_list.size() == 0) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("userId", userId);
				roomList.add(map);
			}
		}
		return roomList;
	}
	
	/*관리자 방 리스트 가져오기*/
	@ResponseBody
	@RequestMapping("/getChatList")
	public List<HashMap<String, String>> getChatList(@RequestParam HashMap<Object, Object> params){
		return roomList;
	}
	
	@ResponseBody
	@RequestMapping("/moveChating")
	public HashMap<String, String> moveChating(@RequestParam HashMap<Object, Object> params) {
		HashMap<String, String> map = new HashMap<String, String>();
		String userId = (String) params.get("userId");
		//채팅방 리스트에 해당 user 데이터가 있는지 체크
		List<HashMap<String, String>> new_list = roomList.stream().filter(o->o.get("userId").equals(userId)).collect(Collectors.toList());
		if(new_list != null && new_list.size() > 0) {
			//user 채팅방이 있을경우
			map.put("userId", userId);
			map.put("status", "success");
		}else {
			//user 채팅방이 없을경우
			map.put("status", "error");
		}
		return map;
	}
	
	
	
	
}
