<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <!-- css -->
    <link rel="stylesheet" href="/resources/css/header.css"> 
    <!-- JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <title>헤더 </title>
</head>
<body>
    <header>
        <div class="fixed-header-navi">
            <div class="header-logo-area" onclick="location.href='#'">
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
	            <c:if test="${ empty sessionScope.loginUser }">
            		<div class="header-submenu-area">
		                <a href="loginView.dz">로그인</a>
		                <a href="enrollView.dz">회원가입</a>
            		</div>
	            </c:if>
	            <c:if test="${ !empty sessionScope.loginUser && sessionScope.loginUser.userId =='admin'}">
	            	<div class="header-submenu-area admin-area">
		                <a href="logout.dz">로그아웃</a>
		                <a href="#">관리자페이지</a>
            		</div>
	            </c:if>
	            <c:if test="${ !empty sessionScope.loginUser && sessionScope.loginUser.userId !='admin'}">
	            	<div class="header-submenu-area login-area">
		                <a href="logout.dz">로그아웃</a>
		            <c:if test="${ !empty sessionScope.loginUser && sessionScope.loginUser.userType == '1'}">
	                	<a href="dreamMyPage.dz">마이페이지</a>
	            	</c:if>
		            <c:if test="${ !empty sessionScope.loginUser && sessionScope.loginUser.userType == '2'}">
	                	<a href="mzMyPage.dz">마이페이지</a>
	            	</c:if>
		            <c:if test="${ !empty sessionScope.loginUser && sessionScope.loginUser.userType == '3'}">
	                	<a href="#">마이페이지</a>
	            	</c:if>
            		</div>
            	</c:if>
        </div>
    </header>
    
</body>
<script>
    $(document).ready(function(){
        $(window).scroll(function(){
            var scroll = $(window).scrollTop();
            if (scroll > 1) {
                scrollDownEvent();
                
            }
            else{
                scrollUpEvent();
            }
        });
        
        // 스크롤아래로
        function scrollDownEvent() {
            $('.fixed-header-navi').css("background" , "#ffffff");
            $('.fixed-header-navi').css("box-shadow" , "0 0 20px rgba(0,0,0,.5)");
            $('.fixed-header-navi').css("position" , "fixed");
            $('.header-menu-area a').css('color', '#333333');
            $('.header-submenu-area a').css('color', '#333333');
            $(".header-logo-area img").attr("src", '/resources/images/logo-color.png');
        }
        // 스크롤 위로
        function scrollUpEvent() {
            $(".fixed-header-navi").css("background" , "");
            $('.fixed-header-navi').css("box-shadow" , "");
            $('.header-menu-area a').css('color', '#ffffff');
            $('.header-submenu-area a').css('color', '#ffffff');
            $(".header-logo-area img").attr("src", '/resources/images/logo.png');
        }

    });
</script>
</body>
</html>