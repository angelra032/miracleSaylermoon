<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/css/main.css"> 
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/menubar.jsp"></jsp:include>
	
	<main>
		<div class="header-background-area">
        	<img src="/resources/images/main/main-banner.jpg" alt="뒷배경이미지">
   		</div>	
   		<div class="main-banner-subtitle">Meaningful consumption, valuable life.</div>
		<div id="main-title">누구나 마음편히<br><span>밥먹는 세상</span>을 위해</div>
		<div class="search-box">
			<form action="">
				<input type="text" placeholder="검색하고자 하는 식당명을 입력해주세요">
				<button type="submit"><img alt="검색" src="/resources/images/search.png"></button>
			</form>
		</div>
		<div class="circle-btn-area">
			<a href="#"><img alt="지도" src="/resources/images/main/main-bt-map.png"><p>지도조회</p></a>
			<a href="#"><img alt="지도" src="/resources/images/main/main-bt-recommended.png"><p>테마추천</p></a>
			<a href="#"><img alt="지도" src="/resources/images/main/main-bt-thankyou.png"><p>감사후기</p></a>
			<a href="#"><img alt="지도" src="/resources/images/main/main-bt-review.png"><p>맛집후기</p></a>
		</div>
		<div class="review-area">
			<div class="main-sub-title"><span>100% 솔직후기</span></div>
			<div class="main-content-title">이미 수많은 분들이 돈쭐과 함께<br>가치있는 소비를 경험하고 계십니다.</div>
			<div class="review-contents-area">
			
			</div>
		</div>
	</main>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
</html>