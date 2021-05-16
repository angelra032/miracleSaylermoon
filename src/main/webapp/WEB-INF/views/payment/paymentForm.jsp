<%@page import="com.donzzul.spring.user.domain.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% User loginUser = (User)request.getAttribute("loginUser"); %>
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
						
					
					</div>
					
					<input type="submit" id="payment-btn" value="돈쭐내러 가기">
				</form>
				
				<div id="point-div">
							<div class="point">보유포인트 300포인트</div>
							<div class="point">가용포인트 <span id="useablePoint">300</span>포인트</div>
							<div class="point">
								<input type="text" id="pCount" placeholder="사용할 포인트 입력"/>원 
								<button id="pSubmit">사용하기</button>
							</div> 
						</div>
				
			</div>
		</div>
	</main>
	
	<!-- 포인트 ajax -->
	<script>
		// 버튼 클릭 시 input에 적힌 값이 컨트롤로 전해져서
		// 컨트롤에서 값을 받아서 로그인유저(세션)의 포인트 값에서 차감(디비)
					// 유저(userNo?)도 넘겨야 하나?,,,
		// 가용포인트 값 바뀜 - 이것도 컨트롤 처리,,
		$(function(){
			$("#pSubmit").on("click", function(){
				//alert("나오냐");
				var pCount = $("#pCount").val(); // input의 포인트값
				console.log(pCount); // 폼안에 있음 리로드 됨 버튼..
				
				// 에이젝스
				$.ajax({
					url : "usePoint.dz",
					type: "post",
					data : {"usePoint" : pCount}, // 넘길 데이터(사용할 포인터) - 유저의 포인트(유저vo)
					success : function(data){ // 성공했을 때 - 가용포인트 변경(성공한 값) | 사용가능한 선 안 넘었으면 실패(alert)
						if(data == "success"){
							// 가용포인트 변경 함수 작성
							printMyPoint();
							$("#pCount").val(""); // 입력값 비우기
						}else{
							// 포인트 사용 불가(얼마 이상)
							alert("포인트는 100원 단위로 500원 이상일 때 사용 가능합니다. 이런식");
						}
					},
					error : function(){ // 실패했을 때
						return "fail"; // ?? 무슨 의미야?
					}
				});
			});
		});
		
		// 가용포인트 출력
		function printMyPoint(){
			//var userPoint = '${loginUser.userPoint }'
			var userPointSpan = $("#useablePoint"); // 유저(가용)포인트 span
			userPointSpan.val(loginUser.userPoint); // user 포인트 출력해보기,,
			
			
			$.ajax({
				url : "printMyPoint.dz",
				type : "get",
				data : {"userPoint" : userPoint }, // 뭘 갖고 가야하나? userNo? user의 포인트? | 아무것도 가져갈 필요 없을 듯? - 세션의 포인트니까,,
				//dataType : 
				success : function(data){
					var useablePoint = $("#useablePoint").text(userPoint);
				},
				error: function(){
					
				}
			});
		}
	</script>
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
</html>