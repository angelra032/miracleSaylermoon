<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet" href="/resources/css/dreammypage/dreammypage.css">
<title>꿈나무회원 마이페이지</title>
</head>
<body>

	<!-- 헤더시작 -->
	<jsp:include page="/WEB-INF/views/common/menubar.jsp"></jsp:include>
	<!-- 헤더 끝 -->
	
	
	
	
	
	<!-- 본문 시작 -->
	<main>
		<div class="header-background-area">
        	<img src="/resources/images/mapListMain.png" alt="뒷배경이미지">
   		</div>	
		<div id="main-title">꿈나무님 안녕하세요 </div>
		<div class = "frame">
			<div class="firstSection">회원정보수정</div>
			
			
			
			
			<!-- 예약목록 -->
			<div class="secondSection">
				<div class="sSectionTop">
					<div class="secondTitle">
						<h1>예약목록</h1>
					</div>
					<div class="secondButton">
						<button class="btn btn-light float-right" id="rListButton">더보기</button>
					</div>
				</div>
				<div class="sSectionBottom">
					<table align="center" width="800" border="1" cellspacing="0" style="clear:right">
						<tr>
							<th>No</th>
							<th>가게이름</th>
							<th>예약날짜</th>
							<th>예약취소</th>
							<th>후기작성</th>
						</tr>

						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>

					</table>
				</div>
			</div>
			<!-- 예약목록 끝 -->
			
			
			
			
			<div class="thirdSection">가고싶다목록</div>
			
			<div class="fourthSection">내가 쓴 후기 목록</div>
			
			<div class="fifthSection">내가 쓴 글 목록</div>
		</div>
	</main>
	<!-- 본문 끝 -->
	
	
	
	
	
	
	
	<!-- 푸터 시작 -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
	<!-- 푸터 끝 -->
</body>
</html>