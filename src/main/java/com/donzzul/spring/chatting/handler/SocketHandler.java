package com.donzzul.spring.chatting.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


@Component
public class SocketHandler extends TextWebSocketHandler {

   HashMap<String, WebSocketSession> sessionMap = new HashMap<>(); //웹소켓 세션을 담아둘 맵
   List<HashMap<String, Object>> rls = new ArrayList<>(); //웹소켓 세션을 담아둘 리스트 ---roomListSessions
   List<HashMap<String, Object>> list = new ArrayList<>();

   @Override
   public void handleTextMessage(WebSocketSession session, TextMessage message) {
      System.out.println("=======================================handleTextMessage");
      //메시지 발송
      String msg = message.getPayload();
      System.out.println("===========msg"+msg.toString());
      JSONObject obj = jsonToObjectParser(msg);
      System.out.println("=====obj"+obj.toJSONString());

      String rN = (String) obj.get("userId");
      HashMap<String, Object> temp = new HashMap<String, Object>();
      if(rls.size() > 0) {
         for(int i=0; i<rls.size(); i++) {
            String userId = (String) rls.get(i).get("userId"); //세션리스트의 저장된 방번호를 가져와서
            if(userId.equals(rN)) { //같은값의 방이 존재한다면
               temp = rls.get(i); //해당 방번호의 세션리스트의 존재하는 모든 object값을 가져온다.
               break;
         }
      }

      //해당 방의 세션들만 찾아서 메시지를 발송해준다.
      for(String k : temp.keySet()) {
         if(k.equals("userId") || k.equals("userName")) { //다만 방번호일 경우에는 건너뛴다.
            continue;
         }
         WebSocketSession wss = (WebSocketSession) temp.get(k);
         if(wss != null) {
            try {
                  wss.sendMessage(new TextMessage(obj.toJSONString()));
            } catch (IOException e) {
               e.printStackTrace();
            }
         }
      }
      }
   }

   @SuppressWarnings("unchecked")
   @Override
   public void afterConnectionEstablished(WebSocketSession session) throws Exception {
      //소켓 연결
      String userName = session.getPrincipal().getName();
      String url = session.getUri().toString();
      System.out.println(url);
      String userId = url.split("/chatting/")[1];

      /*
       * for (int i = 0; i < list.size(); i++) {
       * if(list.get(i).get("userId").equals(userId)) {
       * if(list.get(i).get("userName").equals(userName)) { session =
       * sessionMap.get(list.get(i).get("sessionId")); } } }
       */


      super.afterConnectionEstablished(session);
      if(sessionMap.get(session.getId()) == null) {
         //세션맵에 널일경우만 추가
         sessionMap.put(session.getId(), session);
      }
      boolean flag = false;
      int idx = rls.size(); //방의 사이즈를 조사한다.
      if(rls.size() > 0) {
         for(int i=0; i<rls.size(); i++) {
            String rN = (String) rls.get(i).get("userId");
               if(rN.equals(userId)) {
               flag = true;
               idx = i;
               break;
            }
         }
      }
      if(flag) { //존재하는 방이라면 세션만 추가한다.
         HashMap<String, Object> map = rls.get(idx);
         map.put(session.getId(), session);
         map.put("userName", userName);
      }else { //최초 생성하는 방이라면 방번호와 세션을 추가한다.
         HashMap<String, Object> map = new HashMap<String, Object>();
         map.put("userId", userId);
         map.put(session.getId(), session);
         map.put("userName", userName);
         rls.add(map);
      }


      /*for (int i = 0; i < list.size(); i++) {
         System.out.println("list["+i+"]"+list.get(i).toString());
      }*/
      //세션등록이 끝나면 발급받은 세션ID값의 메시지를 발송한다.
      JSONObject obj = new JSONObject();
      obj.put("type", "getId");
      obj.put("sessionId", session.getId());
      session.sendMessage(new TextMessage(obj.toJSONString()));


      //최초 진입시 연결되었습니다. 관리자와 연결중입니다 표시해주기
      //해당 방 유저 찾기
      HashMap<String, Object> temp = new HashMap<String, Object>();
      if(rls.size() > 0) {
          for(int i=0; i<rls.size(); i++) {
             String findUserId = (String) rls.get(i).get("userId"); //세션리스트의 저장된 방번호를 가져와서
             if(findUserId.equals(userId)) { //같은값의 방이 존재한다면
                temp = rls.get(i); //해당 방번호의 세션리스트의 존재하는 모든 object값을 가져온다.
                break;
          }
       }
      }
      obj.put("type", "message");
      obj.put("userName", "system");
      obj.put("userId", userId);
      if(!userName.equals("admin")) {
         if(flag) {
            return;
         }
      }else {
         //관리자가 재진입시 계속 연결되었습니다.가 표출된다 막기
         if(flag) {
            boolean chk = false;
            for (int i = 0; i < list.size(); i++) {
             System.out.println("======userId1"+userId);
             System.out.println("======userId2"+list.get(i).get("userId"));
             System.out.println("======userName1"+userName);
             System.out.println("======userName2"+list.get(i).get("userName"));
            if(list.get(i).get("userId") == userId && list.get(i).get("userName") == userName){
               chk = true;
            }
            }
            if(chk) {
               return;
            }
         }
      }

      for(String k : temp.keySet()) {
          if(k.equals("userId") || k.equals("userName")) { //다만 userId,userName 일 경우에는 건너뛴다.
             continue;
          }
          if(flag) {
               //존재하는 방이라면 세션만 추가한다.
               //여기는 관리자에 해당된다.
               obj.put("msg", "연결되었습니다.....");
            }else {
               //최초 생성시 여기는 사용자에 해당된다.
                 obj.put("msg", "관리자와 연결중입니다...");
            }
               System.out.println("=========temp.get(k)"+temp.get(k));
             WebSocketSession wss = (WebSocketSession) temp.get(k);
             if(wss != null) {
                try {
                      wss.sendMessage(new TextMessage(obj.toJSONString()));
                } catch (IOException e) {
                   e.printStackTrace();
                }
             }
       }

      //유저정보 기록
      HashMap<String, Object> map2 = new HashMap<String, Object>();
      map2.put("userId", userId);
      map2.put(session.getId(), session);
      map2.put("sessionId", session.getId());
      map2.put("userName", userName);
      list.add(map2);
   }

   @Override
   public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
      //소켓 종료
      if(rls.size() > 0) { //소켓이 종료되면 해당 세션값들을 찾아서 지운다.
         for(int i=0; i<rls.size(); i++) {
            rls.get(i).remove(session.getId());
         }
      }
      super.afterConnectionClosed(session, status);
   }

   private static JSONObject jsonToObjectParser(String jsonStr) {
      JSONParser parser = new JSONParser();
      JSONObject obj = null;
      try {
         obj = (JSONObject) parser.parse(jsonStr);
      } catch (ParseException e) {
         e.printStackTrace();
      }
      return obj;
   }
}