<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <!-- css -->
    <link rel="stylesheet" href="/resources/css/mypageheader.css"> 
   <!--  <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100&display=swap" rel="stylesheet"> -->
    <!-- JS --> 
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <!-- F12 / 오른쪽마우스 금지 -->
	<script type="text/javascript"> 
	$(document).ready(function(){ $(document).bind('keydown',function(e){ if ( e.keyCode == 123 /* F12 */) { e.preventDefault(); e.returnValue = false; } }); }); document.onmousedown=disableclick; status="무단 복사를 막기 위해 마우스 드래그가 금지되어 있습니다"; function disableclick(event){ if (event.button==2) { alert(status); return false; } } 
	</script>
    <title>헤더 </title>
</head>
<body>
<!-- F12 / 오른쪽마우스 금지 -->
<body oncontextmenu='return false' onselectstart='return false' ondragstart='return false'>
    <header>
        <div class="fixed-header-navi">
            <div class="header-logo-area" onclick="location.href='/'">
                <!-- visual에서 작업함 경로수정필요!! -->
                <img src="/resources/images/logo-color.png" alt="로고">
            </div>
            <div class="header-menu-area">
                <ul>
                    <li><a href="#">돈쭐소개</a></li>
                    <li><a href="/mapSearchList.dz">지도조회</a></li>
                    <li><a href="/searchShopView.dz">가게검색</a></li>
                    <li><a href="mReviewMain.dz">커뮤니티</a></li>
                </ul>
            </div>
	            <c:if test="${ empty sessionScope.loginUser && empty sessionScope.kakaoId && empty sessionScope.googleId}">
            		<div class="header-submenu-area">
		                <a href="loginView.dz">로그인</a>
		                <a href="enrollView.dz">회원가입</a>
            		</div>
	            </c:if>
	            <c:if test="${ !empty sessionScope.loginUser && sessionScope.loginUser.userId =='admin'}">
	            
	            	<div class="header-submenu-area admin-area">
		                <a href="logout.dz">로그아웃</a>
		                <a href="adminPage.dz">관리자페이지</a>
            		</div>
	            </c:if>
	            <c:if test="${ !empty sessionScope.loginUser && sessionScope.loginUser.userId !='admin'}"> 
	            	<div class="header-submenu-area login-area">
		                <a href="logout.dz">로그아웃</a>
		            <c:if test="${ !empty sessionScope.loginUser && sessionScope.loginUser.userType == '1'}">
	                	<a href="dreamMyPage.dz">마이페이지</a>
	            	</c:if>
		            <c:if test="${ !empty sessionScope.loginUser && (sessionScope.loginUser.userType == '2' || sessionScope.loginUser.userType == '5')}">
	                	<a href="mzMyPage.dz">마이페이지</a>
	            	</c:if>
		            <c:if test="${ !empty sessionScope.loginUser && sessionScope.loginUser.userType == '3'}">
	                	<a href="partnerMyPage.dz">마이페이지</a>
	            	</c:if>
            		</div>
            	</c:if>
 	            <c:if test="${ empty sessionScope.loginUser && !empty sessionScope.kakaoId}"> 
 	            	<div class="header-submenu-area login-area">
 	            		<a href="logout.dz">로그아웃</a>
 	            		<a href="mzMyPage.dz">마이페이지</a>
 	            	</div>
 	            </c:if>
 	            <c:if test="${ empty sessionScope.loginUser && !empty sessionScope.googleId}"> 
 	            	<div class="header-submenu-area login-area">
 	            		<a href="logout.dz" onclick="signOut();">로그아웃</a>
 	            		<a href="GoogleMyPage.dz">마이페이지</a>
 	            	</div>
 	            </c:if>
        </div>
    </header>
    
</body>

</body>
</html>