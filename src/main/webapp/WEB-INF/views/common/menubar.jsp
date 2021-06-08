<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="google-signin-client_id"
	content="415085927923-rlk2denkpna85ffki391opn4br9792f1.apps.googleusercontent.com">
<!-- css -->
<link rel="stylesheet" href="/resources/css/header.css">
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100&display=swap"
	rel="stylesheet">
<!-- chatting css -->
<link rel="stylesheet" href="/resources/css/chatting/chat.css">
<!-- JS -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://apis.google.com/js/platform.js?onload=onLoad" async
	defer></script>
<script src="https://apis.google.com/js/api:client.js"></script>
<!-- modal -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />
<title>헤더</title>
</head>
<body>
	<header>
		<div class="fixed-header-navi">
			<div class="header-logo-area" onclick="location.href='/'">
				<!-- visual에서 작업함 경로수정필요!! -->
				<img src="/resources/images/logo.png" alt="로고">
			</div>
			<div class="header-menu-area">
				<ul>
					<li><a href="#">돈쭐소개</a></li>
					<li><a href="/mapSearchList.dz">지도조회</a></li>
					<li><a href="/searchShopView.dz">가게검색</a></li>
					<li><a href="mReviewMain.dz">커뮤니티</a></li>
				</ul>
			</div>
			<c:if
				test="${ empty sessionScope.loginUser && empty sessionScope.kakaoId && empty sessionScope.googleId }">
				<div class="header-submenu-area">
					<a href="loginView.dz">로그인</a> <a href="enrollView.dz">회원가입</a>
				</div>
			</c:if>
			<c:if
				test="${ !empty sessionScope.loginUser && sessionScope.loginUser.userId =='admin'}">
				<div class="header-submenu-area admin-area">
					<a href="logout.dz">로그아웃</a> <a href="adminPage.dz">관리자페이지</a>
				</div>
			</c:if>
			<c:if
				test="${ !empty sessionScope.loginUser && sessionScope.loginUser.userId !='admin'}">
				<div class="header-submenu-area login-area">
					<a href="logout.dz">로그아웃</a>
					<c:if
						test="${ !empty sessionScope.loginUser && sessionScope.loginUser.userType == '1'}">
						<a href="dreamMyPage.dz">마이페이지</a>
					</c:if>
					<c:if
						test="${ !empty sessionScope.loginUser && sessionScope.loginUser.userType == '2'}">
						<a href="mzMyPage.dz">마이페이지</a>
					</c:if>
					<c:if
						test="${ !empty sessionScope.loginUser && sessionScope.loginUser.userType == '3'}">
						<a href="partnerMyPage.dz">마이페이지</a>
					</c:if>
				</div>
			</c:if>
			<c:if
				test="${ empty sessionScope.loginUser && !empty sessionScope.kakaoId}">
				<div class="header-submenu-area login-area">
					<a href="logout.dz" onclick="kakaoLogout()">로그아웃</a> <a
						href="KakaoMyPage.dz">마이페이지</a>
				</div>
			</c:if>
			<c:if
				test="${ empty sessionScope.loginUser && !empty sessionScope.googleId}">
				<div class="header-submenu-area login-area">
					<a href="#" onclick="signOut();">로그아웃</a> <a href="GoogleMyPage.dz">마이페이지</a>
				</div>
			</c:if>
		</div>
	</header>
	
	
	
	<!-- chatting button-->
	<c:if
		test="${ !empty sessionScope.loginUser || !empty sessionScope.kakaoId || !empty sessionScope.googleId }">
		<a id="modal" href="#container" rel="modal:open"> 
			<div id="chatting">
			<img alt="chattingIcon" src="/resources/images/chatting/chat.png">
			<p class="pTag">문의하기</p>
			</div>
		</a>
	</c:if>
	<!-- modal section -->
	<c:choose>
		<c:when test="${loginUser.userType eq '4'}">
		<!-- 관리자용  -->
			<script type="text/javascript">
			$( document ).ready(function() {
				$('#modal').click(function(){
					getChatList();
				});
				
				$("#backHistory").click(function(){
					$(".chatList").show(); //채팅방 리스트숨기기
					$(".userChat").hide();
					getChatList();
				});
			});
			
			//채팅방 리스트 가져오기
			function getChatList(){
				$.ajax({
					url: "/getChatList",
					data: {},
					type: "post",
					success: function (data) {
						if(data != null){
							var tag = "<div></div>";
							if(data.length > 0){
								data.forEach(function(d, idx){
									var userId = d.userId.trim();
									tag += "<div class='roomList' onclick='moveChating(\""+userId+"\")'>"+
												"<div class='num'>"+userId+"</div>"+
												"<div class='room'>"+ "2021-06-07 14:34" +"</div>"+
												"<div class='go'>"+ "상담원과 대화한 내용이 출력.."+"</div>" +
											"</div>";	
								});
							}else{
								tag += "<tr><td colspan='3'><p id='emptyMsg'>상담할 내용이 없습니다.</p></td></tr>";	
							}
							$("#roomList").empty().append(tag);
						}
					},
					error : function(err){
						console.log('error');
					}
				});
			}
			
			//해당 채팅 연길하기
			function moveChating(userId){
				$.ajax({
					url: "/moveChating",
					data: {"userId":userId},
					type: "post",
					success: function (data) {
						if(data != null){
							if(data.status == 'success'){
								$("#userId").val(data.userId);
								wsOpen(data.userId);
								$(".chatList").hide(); //채팅방 리스트숨기기
								$(".userChat").show();
							}else{
								alert('채팅방 연결이 실패하였습니다.');
								return false;
							}
						}else{
							alert('채팅방 연결이 실패하였습니다.');
							return false;
						}
					},
					error : function(err){
						console.log('error');
					}
				});
			}
			</script>
			<div id="container" class="container modal">
				<div id="header">
					<div class="header-icon">
						<img alt="header-icon" src="/resources/images/chatting/logo.png">
					</div>
					<div class="header-text">
						<h4>실시간 상담 리스트</h4>
						<input type="hidden" id="sessionId" value="">
					</div>
				</div>
				<div class="chatList">
					<div class="center-table-area" id="shop-list-table">
						<div id="roomList" class="roomList"></div>
					</div>
					<div class="refreshIcon"  onclick="getChatList();">
						<img alt="refreshIcon" id="refreshBtn" src="/resources/images/chatting/refreshing@2x.png">
					</div>
				</div>
				<div class="userChat" style="display: none">
					<div id="chating" class="chating">
						<div><button id="backHistory">뒤로가기</button></div>
					</div>
					<div id="yourMsg2">
						<input type="hidden" name="userName" value="관리자" id="userName">
						<input type="hidden" id="userId" value="">
						<div class="inputTable">
							<div>
								<div><input id="chatting-text" class="chattingText sendingText" placeholder="보내실 메시지를 입력하세요"></div>
								<div><button onclick="send()" id="sendBtn">보내기</button></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</c:when>
		<c:otherwise>
			<div id="container" class="container modal">
				<div id="header"><div class="header-icon">
						<img alt="header-icon" src="/resources/images/chatting/logo.png">
					</div>
					<div class="header-text">
						<h4  id="listH4">실시간 상담</h4>
						<input type="hidden" id="sessionId" value="">
						<input type="hidden" id="userId" value="${sessionScope.loginUser.userId }">
					</div>
				</div>
				<div id="chating" class="chating">
					<div id='startDiv'>
						<div id='imgDiv'>
							<img src='/resources/images/chatting/operator-1.png'>
						</div>
						<div class="msgBox">
							<input type="hidden" class="userNo"
								value="${sessionScope.loginUser.userNo }"> 
								<span>안녕하십니까?
									<br> 돈쭐 고객센터 담당자입니다.
									<br> 무엇을 도와드릴까요?
								</span>
							<button id="requestBtn">실시간 상담하기</button>
						</div>
					</div>
				</div>
		
				<div id="yourName">
					<table class="inputTable">
						<tr>
							<td></td>
							<td><input type="text" name="userName"
								value="${sessionScope.loginUser.userName }" id="userName"></td>
						</tr>
					</table>
				</div>
		
				<div id="yourMsg">
					<table class="inputTable">
						<tr>
							<th></th>
							<th><input id="chatting-text" class="chattingText" placeholder="보내실 메시지를 입력하세요"></th>
							<th><button onclick="send()" id="sendBtn">보내기</button></th>
						</tr>
					</table>
				</div>
			</div>
		</c:otherwise>
	</c:choose>
</body>
<script type="text/javascript">
	var click = true;
	$("#requestBtn").on("click", function() {
		if(click){
			var userName = $("#userName").val();
			$("#yourName").hide();
			$("#yourMsg").show();
			chatName();
			var userType = '${loginUser.userType }';
			click = !click
		}else{
			alert("상담원과 연결중입니다.")
		}
	});
	
	function clickSubmit(){
		if(submitCheck()){return;}
		    //폼서밋
	}

	function chatName() {
		//1.방생성 후 소켓연결
		createRoom();
	}
	
	//유저 몰래 방생성하기
	function createRoom(){
		$.ajax({
			url: "/createRoom",
			data: {"userId":"${loginUser.userId }"},
			type: "post",
			success: function (data) {
				wsOpen("${loginUser.userId }");
			},
			error : function(err){
				console.log('error');
			}
		});
	}
	
	var ws;

	function wsOpen(userId) {
		ws = new WebSocket("ws://" + location.host + "/chatting/"+userId);
		wsEvt();
	}

	function wsEvt() {
		ws.onopen = function(data) {
			//소켓이 열리면 동작!
		}

		ws.onmessage = function(data) {
			var msg = data.data;
			if (msg != null && msg.trim() != '') {
				var d = JSON.parse(msg);
				if (d.type == "getId") {
					var si = d.sessionId != null ? d.sessionId : "";
					if (si != "") {
						$("#sessionId").val(si);
					}
				} else if (d.type == "message") {
					//관리자 일경우
					if("${loginUser.userType}" == "4"){
						if (d.sessionId == $("#sessionId").val()) {
							$("#chating").append("<div class='sendDiv'><span>"+d.msg+"</span></div>");
						} else {
							$("#chating").append(
									"<div class='reciveDiv'><span style='margin-left: 10px;'>" + d.userName + " :" + d.msg + "</span></div>");
						}
					//사용자 일경우
					}else{
						if (d.sessionId == $("#sessionId").val()) {
							$("#chating").append("<div class='sendDiv'><span>"+d.msg+"</span></div>");
						} else {
							$("#chating").append(
									"<div class='reciveDiv'><div class='imgDiv'><img src='/resources/images/chatting/operator-1@2x.png'></div><span>" + d.userName + " :"
									+ d.msg + "</span></div>");
						}	
					}
				} else {
					console.log("unknown type!")
				}
			}
		}
	}
	var sendmsg = "";
	function send() {
		var option = {
			type : "message",
			sessionId : $("#sessionId").val(),
			userName : $("#userName").val(),
			msg : $("#chatting-text").val(),
			userId : $("#userId").val()
		}
		sendmsg = ws.send(JSON.stringify(option));
	}
	$(document).on("keypress", function(e) {
		if (e.keyCode == 13) { //enter press
			e.preventDefault();
		}
	});
	var sendmsg = "";

	
 	/*  $(document).on("click", "#sendBtn", function() {
		if(messageContent !== ""){
			var userNo = $('input[class=userNo]').val();
			var messageContent =  $("#chatting-text").val();
			var messageTime = new Date();
			$('#chatting-text').val("");
		}else if(messageContent === ""){
			alert("내용을 입력해주세요");
			return false;
		} */
		/* $.ajax({
			url:"insertChatContents.dz",
			type:"GET",
			data:{ "userNo" : userNo, "messageContent" : messageContent, "messageTime" : messageTime },
			success : function(){
			}
		}); */
	});  
</script>



<script>
	//구글로그아웃
	function signOut() {
		var auth2 = gapi.auth2.getAuthInstance();
		auth2.signOut().then(function() {
			console.log('User signed out.');
			auth2.disconnect();
			if (!gapi.auth2) {
				gapi.load('auth2', function() {
					gapi.auth2.init();
				});
			}
			location.href = "logout.dz";
		});
	}
	function onLoad() {
		gapi.load('auth2', function() {
			gapi.auth2.init();
		});
	}
	$(document).ready(
			function() {
				$(window).scroll(function() {
					var scroll = $(window).scrollTop();
					if (scroll > 1) {
						scrollDownEvent();

					} else {
						scrollUpEvent();
					}
				});

				// 스크롤아래로
				function scrollDownEvent() {
					$('.fixed-header-navi').css("background", "#ffffff");
					$('.fixed-header-navi').css("box-shadow",
							"0 0 20px rgba(0,0,0,.5)");
					$('.fixed-header-navi').css("position", "fixed");
					$('.header-menu-area a').css('color', '#333333');
					$('.header-submenu-area a').css('color', '#333333');
					$(".header-logo-area img").attr("src",
							'/resources/images/logo-color.png');
				}
				// 스크롤 위로
				function scrollUpEvent() {
					$(".fixed-header-navi").css("background", "");
					$('.fixed-header-navi').css("box-shadow", "");
					$('.header-menu-area a').css('color', '#ffffff');
					$('.header-submenu-area a').css('color', '#ffffff');
					$(".header-logo-area img").attr("src",
							'/resources/images/logo.png');
				}

				function kakaoLogout() {
					if (Kakao.Auth.getAccessToken()) {
						Kakao.API.request({
							url : '/v1/user/unlink',
							success : function(response) {
								console.log(response)
							},
							fail : function(error) {
								console.log(error)
							},
						});
						Kakao.Auth.setAccessToken(undefined)
					}
				}
			});

</script>
</body>
</html>