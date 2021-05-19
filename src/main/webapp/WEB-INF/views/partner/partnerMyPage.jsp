<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사업자 마이페이지</title>
<link rel="stylesheet" href="/resources/css/partner/partnerMyPage.css"> 
<!-- <link href="/static/bootstrap.min.css" rel="stylesheet"> -->
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/menubar.jsp"></jsp:include>
		
		<main>
			<div class="header-background-area">
		        <img src="/resources/images/mapListMain.png" alt="뒷배경이미지">
		    </div>
			<div id="main-title">partner<%-- ${shop.shopName } --%>님, 안녕하세요!</div>
			<div class="frame">
				
				<div id="head-bar">
					<div>
						<button>환급신청</button>
					</div>
				
				</div>
				
				<br>
				
				<div>
					<div>
						<h2>예약 관리</h2>
						<button>더보기</button>
					</div>
					<hr>
					<div>
						
					</div>
				</div>
				
				<br>
				
				<div>
					<div>
						<h2>내가 쓴 글 목록</h2>
						<button>더보기</button>
					</div>
					<hr>
					<div>
						
					</div>
				</div>
				
			</div>
		</main>

	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
</html>