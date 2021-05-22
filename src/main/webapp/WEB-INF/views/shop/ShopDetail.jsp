<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가게 상세 페이지</title>
</head>
<body>

	<jsp:include page="/WEB-INF/views/common/menubar.jsp"/>
	 <div class="header-background-area">
        	<img src="/resources/images/searchShop-bg.png" alt="뒷배경이미지">
   	</div>
	<div id="main-title" class="mainTitle">진짜파스타</div>
	<h1>세션 확인 필수!!!</h1>
	<h3>'예약하기','돈쭐내기','목록으로'</h3>
	<h3>1. 일반회원 = '예약하기','돈쭐내기','목록으로'</h3>
	<h3>2. 비회원 = '예약하기','돈쭐내기','목록으로' 클릭시 로그인 화면 연결</h3>
	<h3>3. 꿈나무 = '예약하기','목록으로'</h3>
	<h3>4. 사업자회원 = '목록으로'</h3>
	
	<h1>리뷰 목록 조회 - Ajax</h1>
	<h3>더보기 버튼을 누르면 계속해서 후기글 보이는 형태</h3>
	<h3>최신순 정렬</h3>
	
	<main>
		<div class=frame>
			
		</div>
	</main>
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>