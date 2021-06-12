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
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="/resources/js/data.js"></script>
<title>가게 찾기 리스트</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/menubar.jsp"/>

	<main>
		<div class="header-background-area">
	       	<img src="/resources/images/shop/searchShop-bg.png" alt="뒷배경이미지">
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
					<div class="scale">
						<a href="searchTheme.dz?themeNo=1"><span class="themeTitle">이 주의 리뷰왕</span>
						<input type=hidden name="theme1" value="1">
						<img class="theme1" src="/resources/images/shop/theme/shoptheme1.jpeg" alt="theme1"></a>
					</div>
				</div>
				<div class="theme two">
					<div class="scale">
						<a href="searchTheme.dz?themeNo=2"><span class="themeTitle">천안 맛집 추천</span>
						<input type=hidden name="theme2" value="2">
						<img class="theme1" src="/resources/images/shop/theme/shoptheme2.jpeg" alt="theme2"></a>
					</div>
				</div>
				<div class="theme three">
					<div class="scale">
						<a href="searchTheme.dz?themeNo=3"><span class="themeTitle">이 달의 신규가게</span>
						<input type=hidden name="theme3" value="3">
						<img class="theme1" src="/resources/images/shop/theme/shoptheme3.jpeg" alt="theme3"></a>
					</div>
				</div>
				<div class="theme four">
					<div class="scale">
						<a href="searchTheme.dz?themeNo=4"><span class="themeTitle">파스타 맛집 추천</span>
						<input type=hidden name="theme4" value="4">
						<img class="theme1" src="/resources/images/shop/theme/shoptheme4.jpeg" alt="theme4"></a>
					</div>
				</div>
				<div class="theme five">
					<div class="scale">
						<a href="searchTheme.dz?themeNo=5"><span class="themeTitle">중식 맛집 추천</span>
						<input type=hidden name="theme5" value="5">
						<img class="theme1" src="/resources/images/shop/theme/shoptheme5.jpeg" alt="theme5"></a>
					</div>
				</div>
				<div class="theme six">
					<div class="scale">
						<a href="searchTheme.dz?themeNo=6"><span class="themeTitle">명동 맛집 추천</span>
						<input type=hidden name="theme6" value="6">
						<img class="theme1" src="/resources/images/shop/theme/shoptheme6.jpeg" alt="theme6"></a>
					</div>
				</div>
				<div class="theme seven">
					<div class="scale">
						<a href="searchTheme.dz?themeNo=7"><span class="themeTitle">햄버거 맛집 추천</span>
						<input type=hidden name="theme7" value="7">
						<img class="theme1" src="/resources/images/shop/theme/shoptheme7.jpeg" alt="theme7"></a>
					</div>
				</div>
				<div class="theme eight">
					<div class="scale">
						<a href="searchTheme.dz?themeNo=8"><span class="themeTitle">제주 맛집 추천</span>
						<input type=hidden name="theme8" value="8">
						<img class="theme1" src="/resources/images/shop/theme/shoptheme8.jpeg" alt="theme8"></a>
					</div>
				</div>
				<div class="theme nine">
					<div class="scale">
						<a href="searchTheme.dz?themeNo=9"><span class="themeTitle">카페 추천</span>
						<input type=hidden name="theme9" value="9">
						<img class="theme1" src="/resources/images/shop/theme/shoptheme9.jpeg" alt="theme9"></a>
					</div>
				</div>
				<div class="theme ten">
					<div class="scale">
						<a href="searchTheme.dz?themeNo=10"><span class="themeTitle">도심 속 작은 유럽</span>
						<input type=hidden name="theme10" value="10">
						<img class="theme1" src="/resources/images/shop/theme/shoptheme10.jpeg" alt="theme10"></a>
					</div>
				</div>
				<div class="theme eleven">
					<div class="scale">
						<a href="searchTheme.dz?themeNo=11"><span class="themeTitle">분식이 땡길 때</span>
						<input type=hidden name="theme11" value="11">
						<img class="theme1" src="/resources/images/shop/theme/shoptheme11.jpeg" alt="theme11"></a>
					</div>
				</div>
				<div class="theme twelve">
					<div class="scale">
						<a href="searchTheme.dz?themeNo=12"><span class="themeTitle">초밥 맛집 추천</span>
						<input type=hidden name="theme12" value="12">
						<img class="theme1" src="/resources/images/shop/theme/shoptheme12.jpeg" alt="theme12"></a>
					</div>
				</div>
			</div> 
		</div> 
	</main>
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>


	<script>
		$(function() {
			
			$("#searchBox").autocomplete({  //오토 컴플릿트 시작
				source: List,	// source는 data.js파일 내부의 List 배열
				focus : function(event, ui) { // 방향키로 자동완성단어 선택 가능하게 만들어줌	
					return false;
				},
				minLength: 1,// 최소 글자수
				delay: 100,	//autocomplete 딜레이 시간(ms)
				//disabled: true, //자동완성 기능 끄기
			});
				
			$("#btn-search").on("click", function() {
				var searchKeyword = $("#searchBox").val();
				if(searchKeyword == "") {
					alert("검색하실 식당명, 음식 종류를 입력해주세요.");
					return false;
				}else {
					location.href = 'searchShop.dz?searchKeyword=' + searchKeyword;
				}
			});
			
			$("#searchBox").keypress(function(event){
			     if ( event.which == 13 ) { // 키코드
			         $('#btn-search').click();
			         return false;
			     }
			});
				
			/* $(".theme1").on("click", function(e) {
				console.log(e.target);
				console.log($(e.target).prev().val());
				var themeNo = $(e.target).prev().val();
 				$.ajax({
					url: "searchTheme.dz",
					type: "get",
					data: { "themeNo" : themeNo },
					dataType: "json",
					success: function(data) {
						console.log(data.sList);
					},
					error: function() {
						console.log("서버에 연결할 수 없습니다.");
						return false;
					}
				});
			});*/
		});
	</script>
</body>
</html>