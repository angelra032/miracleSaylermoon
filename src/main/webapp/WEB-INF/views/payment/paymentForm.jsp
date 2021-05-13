<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/css/payment/paymentForm.css"> 
<link href="/static/bootstrap.min.css" rel="stylesheet">
<title>돈쭐내기</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/menubar.jsp"></jsp:include>
	
	<main>
		<div class="header-background-area">
	        <img src="/resources/images/mapListMain.png" alt="뒷배경이미지">
	    </div>
		<div id="main-title">돈쭐내기</div>
		<div class="frame">
			<h1>진짜파스타</h1>
			<div id="payment-form">
				<form action="" method="post">
					<div id="payment-choice">
						<div class="choice menu">
							<h3>메뉴 선택</h3>
							<select id="select-box">
								<option selected>후라이드치킨(25,000원)</option>
							</select>
						</div>
						<div class="choice amount">
							<h3>수량 선택</h3>
							<input type="button" class="amount-btn" value="X 2">&nbsp;&nbsp;
							<input type="button" class="amount-btn" value="X 3">&nbsp;&nbsp;
							<input type="button" class="amount-btn" value="X 4">
						</div>
					</div>
					<br>
					<div id="payment-point">
						<h2>포인트 사용</h2>
						<h4>사용된 포인트만큼 할인 적용됩니다.</h4>
						<br><br>
						<div id="point-div">
							<div class="point">보유포인트 300포인트</div>
							<div class="point">가용포인트 0포인트</div>
							<div class="point"><input type="text" placeholder="사용할 포인트 입력"/> 원 사용하기</div> 
						</div>
					
					</div>
					
					<input type="submit" id="payment-btn" value="돈쭐내러 가기">
				</form>
			</div>
		</div>
	</main>
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
</html>