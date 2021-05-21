<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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

			<div class="sub-title">
				<p>식당명, 음식 종류를 검색하세요</p>
			</div>
						
			<div class="searchBar">
				<form action="#" method="get">
					<input type="text" id="searchBox" name="searchKeyword" placeholder="검색하고자 하는 식당명, 음식 종류를 입력해주세">
					<button id="btn-search"><img src="/resources/images/search.png"></button>
				</form>
			</div>
		
			<h1>Ajax 로 검색 결과 나타내기</h1>
			<h3>1. 테마</h3>
			<h3>2. 테마 or 키워드 검색 결과 출력 리스트</h3>
			<a href="shopDetail.dz?shopNo=41">진짜 파스타</a>
			
			<%-- <c:if test="${ shop.shopParking eq 'Y' }">
				<span>주차가능</span>
			</c:if>
			<c:if test="${ shop.shopParking eq 'N' }">
				<span>주차불가</span>
			</c:if> --%>
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
						url: "mapSearchKey.dz",
						type: "get",
						data: { "searchKeyword": searchKeyword },
						dataType: "json",
						success: function(data) {
							/* for(int i in d) */
							
								
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