<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="google-signin-client_id" content="415085927923-rlk2denkpna85ffki391opn4br9792f1.apps.googleusercontent.com">
<link rel="stylesheet" href="/resources/css/user/login.css"> 
<link href="/static/bootstrap.min.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
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
			<div class="height-fixed">
			<div class="color-frame">
				<h2>회원로그인</h2>
				<h4>꿈나무, 일반, 사업자 회원</h4>
				<div class="left-inner-box">
					<form id="login-form" action="/login" method="POST">
					<!-- <form action="login.dz" method="POST"> -->
						<div class="input-box"> 
							<input id="user-id" type="text" name="username" placeholder="아이디"> 
							<label for="userid">아이디</label> 
						</div> 
						<div class="input-box"> 
							<input id="user-pw" type="password" name="password" placeholder="비밀번호"> 
							<label for="userpw">비밀번호</label> 
						</div> 
						<input type="submit" id="left-login-bt" value="로그인"> 
						<!-- csrf 보안을 위한 코드 -->
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						<div class="login-bottom-area">
							<!-- <div class="auto-login">
								<input id = "remember_me" name ="remember-me" type = "checkbox"/>&nbsp;로그인유지<br>
							</div> -->
							<a class="find-area" href="findPwView.dz">비밀번호 찾기</a>
							<a class="find-area" href="findIdView.dz">아이디 찾기</a>
						</div>
					</form>
				</div>
			</div>
			<div class="color-frame right-frame">
				<h2>간편로그인</h2>
				<h4>일반회원전용</h4>
				<div class="left-inner-box">
					<div class="input-box"> 
						<a href="javascript:kakaoLogin();">
							<img alt="카카오 회원가입" src="/resources/images/kakaologin-btn.png">
						</a>
							<!-- <div class="g-signin2 google-login" data-onsuccess="onSignIn">구글 로그인</div>
							<div class="fake-glogin"><img alt="구글로그인" src="/resources/images/g-logo.png"><p>구글 로그인</p></div> -->
							<ul>
								<li id="GgCustomLogin">
									<a href="javascript:void(0)">
										<div class="fake-glogin"><img alt="구글로그인" src="/resources/images/g-logo.png"><p>구글 로그인</p></div>
									</a>
								</li>
							</ul>
							<p>꿈나무 및 사업자 회원은 회원로그인을 이용하여 주시기 바랍니다.</p>
					</div>
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
			var userId = $("#user-id");
			var userPw = $("#user-pw");
			
			$.ajax({
				url : "dupLogin.dz",
				data : { "userId" : userId.val(), "userPw" : userPw.val() },
				async: false,
				success : function(result) {
					if(userId.val() == "") {
						alert("아이디를 입력해주세요");
						rtn = false;
					}else if(userPw.val() == ""){
						alert("비밀번호를 입력해주세요");
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
					rtn = false;
				}
			});
			return rtn;
		});
	});//end of function
	
	//구글 처음 실행하는 함수
	function init() {
		gapi.load('auth2', function() {
			gapi.auth2.init();
			options = new gapi.auth2.SigninOptionsBuilder();
			options.setPrompt('select_account');
	        // 추가는 Oauth 승인 권한 추가 후 띄어쓰기 기준으로 추가
			options.setScope('email profile');
	        // 인스턴스의 함수 호출 - element에 로그인 기능 추가
	        // GgCustomLogin은 li태그안에 있는 ID, 위에 설정한 options와 아래 성공,실패시 실행하는 함수들
			gapi.auth2.getAuthInstance().attachClickHandler('GgCustomLogin', options, onSignIn, onSignInFailure);
		})
	}
	//구글
	function onSignIn(googleUser) {
		var access_token = googleUser.getAuthResponse().access_token
		//프로필을 가져온다.
		var profile = googleUser.getBasicProfile();
		console.log(profile);
		$.ajax({
	    	// people api를 이용하여 프로필 및 생년월일에 대한 선택동의후 가져온다.
			url: 'https://people.googleapis.com/v1/people/me'
	        // key에 자신의 API 키를 넣습니다.
			, data: {personFields:'birthdays', key:'AIzaSyA0KoHI1AiMsZKEp1ABWHAcuv0q7oJT9jI', 'access_token': access_token}
			, method:'GET'
		})
		
		.done(function(e){
	        //프로필을 가져온다.
			var profile = googleUser.getBasicProfile();
			//console.log(profile)
			console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
			console.log('Name: ' + profile.getName());
			console.log('Image URL: ' + profile.getImageUrl());
			console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.
			//location.href="googlelogin.dz?googleId="+profile.getEmail()+"&googleName="+profile.getName();
			$.ajax({
	  	    	url : "countSocialUser.dz",
					data : { "socialId" : profile.getEmail(), "socialNickname" : profile.getName() },
					async: false,
					// 없으면 인서트하고 성공 메시지 오면 넘겨주기
					success : function(result) {
						if(result == "success")	{
							document.querySelector("#user-id").value = profile.getEmail();
							document.querySelector("#user-pw").value = profile.getEmail();
							$("#login-form").submit();
						}
					}
	  	    })
			/* $("#login-form").submit(); */
			
		})
		.fail(function(e){
			console.log(e);
		})
	}
	function onSignInFailure(t){		
		console.log(t);
	}
	
	//카카오
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
		        	    
		        	    // 체크하는 function
		        	    $.ajax({
		        	    	url : "countSocialUser.dz",
		    				data : { "socialId" : kakaoId, "socialNickname" : kakaoNickname },
		    				async: false,
		    				success : function(result) {
		    					if(result == "success")	{
		    						// 없으면 인서트하고 성공 메시지 오면 넘겨주기
		    						document.querySelector("#user-id").value = kakaoId;
		    						document.querySelector("#user-pw").value = kakaoId;
		    						$("#login-form").submit();
		    					}
		    				}
		        	    });
		        	    
		        	    
// 		        	    location.href="kakaologin.dz?kakaoId="+kakaoId+"&kakaoNickname="+kakaoNickname;
		        	   
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
<script src="https://apis.google.com/js/platform.js?onload=init" async defer></script>
</html>