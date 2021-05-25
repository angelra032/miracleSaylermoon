<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- css -->
<link rel="stylesheet" type="text/css" href="/resources/css/shop/ShopSearchList.css">
<!-- JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>가게 찾기 리스트</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/menubar.jsp"/>

	<main>
		
		<div class="header-background-area">
	       	<img src="/resources/images/searchShop-bg.png" alt="뒷배경이미지">
	   	</div>
		<div id="main-title">가게검색</div>
		
		<div class=frame>

			<div class="sub-title first">
				<p>식당명, 음식 종류를 검색하세요</p>
			</div>
						
			<div class="searchBar">
					<input type="text" id="searchBox" name="searchKeyword" placeholder="검색하고자 하는 식당명, 음식 종류를 입력해주세요">
					<button id="btn-search"><img src="/resources/images/search.png"></button>
			</div>
			
			<!-- <a href="shopDetail.dz?shopNo=41">진짜 파스타</a> -->
			
			<!-- 테마 메인!!!!!!!!! -->
<!--			<div class="sub-title second">
				<p>에디터 추천</p>
			</div>
			
			<div class="searchTheme">
				<div class="theme one">
					<img src="" alt="theme1">
					<a href="">이주의 리뷰왕</a>
				</div>
				<div class="theme two">
					<img src="" alt="theme2">
					<a href="">천안 맛집 추천</a>
				</div>
				<div class="theme three">
					<img src="" alt="theme3">
					<a href="">이달의 신규가게</a>
				</div>
				<div class="theme four">
					<img src="" alt="theme4">
					<a href="">백반 맛집 추천</a>
				</div>
				<div class="theme five">
					<img src="" alt="theme5">
					<a href="">중식 맛집 추천</a>
				</div>
				<div class="theme six">
					<img src="" alt="theme6">
					<a href="">명동 맛집 추천</a>
				</div>
				<div class="theme seven">
					<img src="" alt="theme7">
					<a href="">햄버거 맛집 추천</a>
				</div>
				<div class="theme eight">
					<img src="" alt="theme8">
					<a href="">제주 맛집 추천</a>
				</div>
				<div class="theme nine">
					<img src="" alt="theme9">
					<a href="">파스타 맛집 추천</a>
				</div>
				<div class="theme ten">
					<img src="" alt="theme10">
					<a href="">도심 속 작은 유럽</a>
				</div>
				<div class="theme eleven">
					<img src="" alt="theme11">
					<a href="">분식이 땡길 때</a>
				</div>
				<div class="theme twelve">
					<img src="" alt="theme12">
					<a href="">초밥 맛집 추천</a>
				</div>
			</div> -->
			
			
			<!-- 검색결과 가게 리스트 -->
			<!-- AJAX -->
			<div class="sub-title second">
				<p>'${ searchKeyword }' 검색결과</p>
			</div>
			
			<div class="searchResult">
				<div class="shopShortInfo">
					<div class="shopShortInfo left">
						<!-- <img src="/resources/images/shopMainImg/realPasta.jpeg" alt="shopMain"> -->
					</div>
					<div class="shopShortInfo right">
						<%-- <input type="hidden" name="shopNo" value="${ shop.shopNo }"> --%>
						<input type="hidden" name="shopNo" value="41">
						<span id=shop-title><b><a href="shopDetail.dz?shopNo=41">진짜 파스타</a></b></span>&nbsp;&nbsp;
						<span id=shop-type>양식</span><br><br>
						<span>홍대 놀이터</span><br>
						<span>12:00 - 22:00</span><br>
						<span>매주 일요일</span>&nbsp;&nbsp;
						<span>휴무</span><br>
						<span>홍대 진짜 파스타</span> <!-- 글자수 줄이기 -->
					</div>
				</div>
			</div>
			
			<div class="searchResult navi">
			
			</div>
		</div> 
	</main>
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>


	<script>
		$(function() {
			$("#btn-search").on("click", function() {
				var searchKeyword = $("#searchBox").val();
				if(searchKeyword == "") {
					alert("검색어를 입력해주세요.");
					return false;
				}else {
					$.ajax({
						url: "",
						type: "get",
						data: "",
						dataType: "json",
						success: function(data) {
							
								
						},
						error: function() {
							alert("서버에 연결할 수 없습니다.");
							return false;
						}
					});
				}
			});
		});
	</script>
</body>
</html>