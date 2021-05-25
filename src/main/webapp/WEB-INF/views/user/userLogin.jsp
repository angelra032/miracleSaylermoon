<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="google-signin-client_id" content="donjjul.apps.googleusercontent.com">
<link rel="stylesheet" href="/resources/css/user/login.css"> 
<link href="/static/bootstrap.min.css" rel="stylesheet">
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script src="https://apis.google.com/js/platform.js" async defer></script>
<title>돈쭐 로그인</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/menubar.jsp"></jsp:include>
	
	<main>
		<div class="header-background-area">
        	<img src="/resources/images/mapListMain.png" alt="뒷배경이미지">
   		</div>	
		<div id="main-title">로그인</div>
		<div class="frame">
			<div class="color-frame">
				<h2>회원로그인</h2>
				<h4>꿈나무, 일반, 사업자 회원</h4>
				<div class="left-inner-box">
					<form action="login.dz" method="POST"> 
						<div class="input-box"> 
							<input id="userid" type="text" name="userId" placeholder="아이디"> 
							<label for="userid">아이디</label> 
						</div> 
						<div class="input-box"> 
							<input id="userpw" type="password" name="userPw" placeholder="비밀번호"> 
							<label for="userpw">비밀번호</label> 
						</div> 
						<input type="submit" id="left-login-bt" value="로그인"> 
						<a href="findIdView.dz">아이디 찾기</a>
						<a href="findPwView.dz">비밀번호 찾기</a>
					</form>
				</div>
			</div>
			<div class="color-frame">
				<h2>간편로그인</h2>
				<h4>일반회원전용</h4>
				<div class="left-inner-box">
					<div class="input-box"> 
						<a href="javascript:kakaoLogin();">
							<img alt="카카오 회원가입" src="/resources/images/kakaologin-btn.png">
						</a>
						<div class="googlelogin" data-onsuccess="onSignIn">구글 로그인</div>
					</div>
				</div>
			</div> 

		</div>
	</main>
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
<script>
	$(function() {
		$('#left-login-bt').click(function() {
			var rtn = true;
			var userId = $("#userid");
			var userPw = $("#userpw");
			/* if(userId.val() == "") {
				alert("아이디를 입력해주세요")
				rtn = false;
			}else if(userPw.val() == ""){
				alert("비밀번호를 입력해주세요")
				rtn = false;
			} */
			$.ajax({
				url : "dupLogin.dz",
				data : { "userId" : userId.val(), "userPw" : userPw.val() },
				async: false,
				success : function(result) {
					if(userId.val() == "") {
						alert("아이디를 입력해주세요")
						rtn = false;
					}else if(userPw.val() == ""){
						alert("비밀번호를 입력해주세요")
						rtn = false;
					}else if(result == 1){
						alert("아이디 또는 비밀번호가 일치하지 않습니다. 다시 입력해주세요.");
						userId.focus();
						rtn = false;
					}else {
						rtn = true;
					}
				},
				error : function() {
					console.log("전송실패");
				}
			});
			return rtn;
		});
	});
	window.Kakao.init('25454baf7b7c333b7ced28bdce84084a'); //발급받은 키 중 javascript키를 사용해준다.
	console.log(Kakao.isInitialized()); // sdk초기화여부판단
	//카카오로그인
	function kakaoLogin() {
	    window.Kakao.Auth.login({
	    	scope: 'profile, account_email',
	        success: function (result) {
	        	console.log(result);
	        	window.Kakao.API.request({
	          		url: '/v2/user/me', // 현재 로그인한 사용자정보
	          		success: function(res) {
		        	    const kakao_account = res.kakao_account;
		        	    console.log(kakao_account);
		        	    console.log(res);
		        	    
		        	    var kakaoId = res.id;
		        	    var kakaoNickname = res.properties.nickname;
		        	    location.href="kakaologin.dz?kakaoId="+kakaoId+"&kakaoNickname="+kakaoNickname;
	          		},
	          		fail: function (error) {
	            		console.log(error)
	          		}
	        	});
	      	},
		    fail: function (error) {
		    	console.log(error)
		    }
	    });
    }
</script>
</html>