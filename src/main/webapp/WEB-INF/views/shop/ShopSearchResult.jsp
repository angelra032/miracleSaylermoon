<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- css -->
<link rel="stylesheet" type="text/css" href="/resources/css/shop/ShopSearchResult.css">
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
			
			<!-- 검색결과 가게 리스트 -->
 			<div class="sub-title second">
 				<c:choose>
 					<c:when test="${ themeNo eq 1 }">
	 					<p>이주의 리뷰왕</p>
 					</c:when>
 					<c:otherwise>
						<p>'${ searchKeyword }' 검색결과</p>
 					</c:otherwise>
 				</c:choose>
			</div>
			
			<div class="searchResult">
				<c:choose>
 					<c:when test="${ themeNo eq 1 }">
	 					<c:forEach var="shop" items="${ rankList }">
							<div class="shopShortInfo">
								<div class="shopShortInfo left">
									<!-- <img src="/resources/images/shopMainImg/${shop.shopFileName}" alt="shopMain"> -->
								</div>
								<div class="shopShortInfo right">
									<input type="hidden" name="shopNo" value="${ shop.shopNo }">
									<input type="hidden" name="shopNo" value="41">
									<span id=shop-title><b><a href="shopDetail.dz?shopNo=41">${ shop.shopName }</a></b></span>&nbsp;&nbsp;
									<span id=shop-type>${ shop.shopType }</span><br><br>
									<span>${ shop.shopShortAddr }</span><br>
									<span>${ shop.startTime }:00 - ${ shop.endTime }:00</span><br>
									<span>매주 일요일</span>&nbsp;&nbsp;
									<span>휴무</span><br>
									<span>${ shop.shopContent }</span> <!-- 글자수 줄이기 -->
								</div>
							</div>
						</c:forEach>
 					</c:when>
 					<c:otherwise>
						<p>'${ searchKeyword }' 검색결과</p>
 					</c:otherwise>
 				</c:choose>
				
			</div>
			
			<div class="searchResult navi">
				<c:url var="before" value="mapSearchShop.dz">
						<c:param name="page" value="${ pi.currentPage - 1 }"></c:param>
						<c:if test="${ !empty location }">
							<c:param name="location" value="${ location }"></c:param>
						</c:if>
					</c:url>
					<c:if test="${ pi.currentPage > 1 }">
						<a href="${ before }"><img src="/resources/images/navi-left.png" alt="이전"/>&nbsp;&nbsp;</a>
					</c:if>
					<c:forEach var="p" begin="${ pi.startPage }" end="${ pi.endPage }">
						<c:url var="pagination" value="mapSearchShop.dz">
							<c:param name="page" value="${ p }"></c:param>
							<c:if test="${ !empty location }">
								<c:param name="location" value="${ location }"></c:param>
							</c:if>
						</c:url>
						<c:if test="${ p eq pi.currentPage }">
							<span id="currentPage">${ p }</span>
						</c:if>
						<c:if test="${ p ne pi.currentPage }">
							<a href="${ pagination }"><span id="otherPage">${ p }</span>&nbsp;&nbsp;</a>
						</c:if>
					</c:forEach>
					<c:url var="after" value="mapSearchShop.dz">
						<c:param name="page" value="${ pi.currentPage + 1 }"></c:param>
						<c:if test="${ !empty location }">
							<c:param name="location" value="${ location }"></c:param>
						</c:if>
					</c:url>
					<c:if test="${ pi.currentPage >= pi.maxPage }">
					</c:if>
					<c:if test="${ pi.currentPage < pi.maxPage }">
						<a href="${ after }"><img src="/resources/images/navi-right.png" alt="다음"/></a>
					</c:if>
			</div>
		</div> 
	</main>
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>


	<script>
/* 		$(function() {
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
			
			$(".theme1").on("click", function(e) {
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
			});
		}); */
	</script>
</body>
</html>