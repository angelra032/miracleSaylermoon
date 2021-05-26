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
			
			<!-- 테마 메인!!!!!!!!! -->
			<div class="sub-title second">
				<p>에디터 추천</p>
			</div>
			
			<div class="searchTheme">
				<div class="theme one">
					<input type=hidden name="themeNo" value="1">
					<span>이주의 리뷰왕</span>
					<img class="themeOne" src="/resources/images/theme/shoptheme1.jpeg" alt="theme1">
				</div>
				<div class="theme two">
					<input type=hidden name="themeNo" value="2">
					<span>천안 맛집 추천</span>
					<img class="themeOne" src="/resources/images/theme/shoptheme2.jpeg" alt="theme2">
				</div>
				<div class="theme three">
					<input type=hidden id="searchNum" value="3">
					<span>이달의 신규가게</span>
					<a href=""><img src="/resources/images/theme/shoptheme3.jpeg" alt="theme3"></a>
				</div>
				<div class="theme four">
					<input type=hidden id="searchNum" value="4">
					<span>파스타 맛집 추천</span>
					<a href=""><img src="/resources/images/theme/shoptheme4.jpeg" alt="theme4"></a>
				</div>
				<div class="theme five">
					<input type=hidden id="searchNum" value="5">
					<span>중식 맛집 추천</span>
					<a href=""><img src="/resources/images/theme/shoptheme5.jpeg" alt="theme5"></a>
				</div>
				<div class="theme six">
					<input type=hidden id="searchNum" value="6">
					<span>명동 맛집 추천</span>
					<a href="#"><img src="/resources/images/theme/shoptheme6.jpeg" alt="theme6"></a>
				</div>
				<div class="theme seven">
					<input type=hidden id="searchNum" value="7">
					<span>햄버거 맛집 추천</span>
					<a href=""><img src="/resources/images/theme/shoptheme7.jpeg" alt="theme7"></a>
				</div>
				<div class="theme eight">
					<input type=hidden id="searchNum" value="8">
					<span>제주 맛집 추천</span>
					<a href=""><img src="/resources/images/theme/shoptheme8.jpeg" alt="theme8"></a>
				</div>
				<div class="theme nine">
					<input type=hidden id="searchNum" value="9">
					<span>백반 맛집 추천</span>
					<a href=""><img src="/resources/images/theme/shoptheme9.jpeg" alt="theme9"></a>
				</div>
				<div class="theme ten">
					<input type=hidden id="searchNum" value="10">
					<span>도심 속<br>작은 유럽</span>
					<a href=""><img src="/resources/images/theme/shoptheme10.jpeg" alt="theme10"></a>
				</div>
				<div class="theme eleven">
					<input type=hidden id="searchNum" value="11">
					<span>분식이 땡길 때</span>
					<a href=""><img src="/resources/images/theme/shoptheme11.jpeg" alt="theme11"></a>
				</div>
				<div class="theme twelve">
					<input type=hidden id="searchNum" value="12">
					<span>초밥 맛집 추천</span>
					<a href=""><img src="/resources/images/theme/shoptheme12.jpeg" alt="theme12"></a>
				</div>
			</div> 
			
			
			<!-- 검색결과 가게 리스트 -->
			<!-- AJAX -->
<%-- 			<div class="sub-title second">
				<p>'${ searchKeyword }' 검색결과</p>
			</div>
			
			<div class="searchResult">
				<div class="shopShortInfo">
					<div class="shopShortInfo left">
						<!-- <img src="/resources/images/shopMainImg/realPasta.jpeg" alt="shopMain"> -->
					</div>
					<div class="shopShortInfo right">
						<input type="hidden" name="shopNo" value="${ shop.shopNo }">
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
			
			</div> --%>
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
							consloe.log("서버에 연결할 수 없습니다.");
							return false;
						}
					});
				}
			});
			
			$(".themeOne").on("click", function() {
				var themeNo = $("input[name=themeNo]").val();
 				$.ajax({
					url: "searchTheme.dz",
					type: "get",
					data: { "themeNo" : themeNo },
					dataType: "json",
					success: function(data) {
						
					},
					error: function() {
						consloe.log("서버에 연결할 수 없습니다.");
						return false;
					}
				});
			});
		});
	</script>
</body>
</html>