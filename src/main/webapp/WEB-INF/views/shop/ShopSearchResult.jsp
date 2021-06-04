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
			<div class="searchShopContent">
				<div class="sub-title first">
					<p>식당명, 음식 종류를 검색하세요</p>
				</div>
				<div class="searchBar">
					<c:if test="${ !empty searchKeyword }">
						<input type="text" id="searchBox" name="searchKeyword" placeholder="검색하고자 하는 식당명, 음식 종류를 입력해주세요" value="${ searchKeyword }">
					</c:if>
					<c:if test="${ empty searchKeyword }">
						<input type="text" id="searchBox" name="searchKeyword" placeholder="검색하고자 하는 식당명, 음식 종류를 입력해주세요">
					</c:if>
					<button id="btn-search"><img src="/resources/images/search.png"></button>
				</div>
				
				<!-- 검색결과 가게 리스트 -->
	 			<div class="sub-title second">
	 				<c:choose>
	 					<c:when test="${ themeNo eq 1 }">
		 					<p>이 주의 리뷰왕</p>
	 					</c:when>
	 					<c:when test="${ themeNo eq 2 }">
		 					<p>천안 맛집 추천</p>
	 					</c:when>
	 					<c:when test="${ themeNo eq 3}">
		 					<p>이 달의 신규가게</p>
	 					</c:when>
	 					<c:when test="${ themeNo eq 4 }">
		 					<p>파스타 맛집 추천</p>
	 					</c:when>
	 					<c:when test="${ themeNo eq 5 }">
		 					<p>중식 맛집 추천</p>
	 					</c:when>
	 					<c:when test="${ themeNo eq 6 }">
		 					<p>명동 맛집 추천</p>
	 					</c:when>
	 					<c:when test="${ themeNo eq 7 }">
		 					<p>햄버거 맛집 추천</p>
	 					</c:when>
	 					<c:when test="${ themeNo eq 8 }">
		 					<p>제주 맛집 추천</p>
	 					</c:when>
	 					<c:when test="${ themeNo eq 9 }">
		 					<p>카페 추천</p>
	 					</c:when>
	 					<c:when test="${ themeNo eq 10 }">
		 					<p>도심 속 작은 유럽</p>
	 					</c:when>
	 					<c:when test="${ themeNo eq 11 }">
		 					<p>분식이 땡길 때</p>
	 					</c:when>
	 					<c:when test="${ themeNo eq 12 }">
		 					<p>초밥 맛집 추천</p>
	 					</c:when>
	 					<c:otherwise>
							<p>'${ searchKeyword }' 검색결과</p>
	 					</c:otherwise>
	 				</c:choose>
				</div>
				<div class="searchResult">
					<c:choose>
	 					<c:when test="${ !empty searchKeyword }">
	 						<c:forEach var="search" items="${ sList }" varStatus="status">
								<div class="shopShortInfo">
									<div class="shopShortInfo left">
										<!-- <img src="/resources/images/shopMainImg/${reviewShop.shopFileName}" alt="shopMain"> -->
									</div>
									<div class="shopShortInfo right">
										<span id="shop-title"><b><a href="shopDetail.dz?shopNo=${ search.shopNo }">${ search.shopName }</a></b></span>&nbsp;&nbsp;
										<span id="shop-type">${ search.shopType }</span><br><br>
										<span>${ search.shopShortAddr }</span><br>
										<span>${ search.startTime }:00 - ${ search.endTime }:00&nbsp;&nbsp;매주 일요일&nbsp;휴무</span><br>
										<c:choose>
											<c:when test="${ search.shopContent != null }">
												<span class="shopContent">${ search.shopContent }</span> 
											</c:when>
											<c:when test="${ search.shopContent == null }">
												<c:forEach var="reviewOne" items="${ reviewOneList }">
													<c:if test="${ reviewOne.drmReviewContent ne 'EMPTY' }">
														<span class="shopContent">${ reviewOne.drmReviewContent }</span> 
													</c:if>
													<c:if test="${ reviewOne.drmReviewContent eq 'EMPTY' }">
														<span class="shopContent">${ search.shopName }</span> 
													</c:if>
												</c:forEach>
											</c:when>
										</c:choose>
									</div>
								</div>
							</c:forEach>
	 					</c:when>
	 					<c:when test="${ themeNo eq 1 }">
		 					<c:forEach var="reviewShop" items="${ rankList }" varStatus="status">
								<div class="shopShortInfo">
									<div class="shopShortInfo rank"> <!-- 1 부터 차례대로 인덱스 출력 -->
										${ status.count }
									</div>
									<div class="shopShortInfo left">
										<!-- <img src="/resources/images/shopMainImg/${reviewShop.shopFileName}" alt="shopMain"> -->
									</div>
									<div class="shopShortInfo right">
										<span id="shop-title"><b><a href="shopDetail.dz?shopNo=${ reviewShop.shopNo }">${ reviewShop.shopName }</a></b></span>&nbsp;&nbsp;
										<span id="shop-type">${ reviewShop.shopType }</span><br><br>
										<span>${ reviewShop.shopShortAddr }</span><br>
										<span>${ reviewShop.startTime }:00 - ${ reviewShop.endTime }:00&nbsp;&nbsp;매주 일요일&nbsp;휴무</span><br>
										<c:choose>
											<c:when test="${ reviewShop.shopContent != null }">
												<span class="shopContent">${ reviewShop.shopContent }</span> 
											</c:when>
											<c:when test="${ reviewShop.shopContent == null }">
												<c:forEach var="reviewOne" items="${ reviewOneList }">
													<c:if test="${ reviewOne.drmReviewContent ne 'EMPTY' }">
														<span class="shopContent">${ reviewOne.drmReviewContent }</span> 
													</c:if>
													<c:if test="${ reviewOne.drmReviewContent eq 'EMPTY' }">
														<span class="shopContent">${ reviewShop.shopName }</span> 
													</c:if>
												</c:forEach>
											</c:when>
										</c:choose>
									</div>
								</div>
							</c:forEach>
	 					</c:when>
	 					<c:when test="${ themeNo eq 3 }">
		 					<c:forEach var="newShop" items="${ newSList }" varStatus="status">
								<div class="shopShortInfo">
									<div class="shopShortInfo rank">
										${ status.count }
									</div>
									<div class="shopShortInfo left">
										<!-- <img src="/resources/images/shopMainImg/${newShop.shopFileName}" alt="shopMain"> -->
									</div>
									<div class="shopShortInfo right">
										<span id="shop-title"><b><a href="shopDetail.dz?shopNo=${ newShop.shopNo }">${ newShop.shopName }</a></b></span>&nbsp;&nbsp;
										<span id="shop-type">${ newShop.shopType }</span><br><br>
										<span>${ newShop.shopShortAddr }</span><br>
										<span>${ newShop.startTime }:00 - ${ newShop.endTime }:00&nbsp;&nbsp;매주 일요일&nbsp;휴무</span><br>
										<c:choose>
											<c:when test="${ newShop.shopContent != null }">
												<span class="shopContent">${ newShop.shopContent }</span> 
											</c:when>
											<c:when test="${ newShop.shopContent == null }">
												<c:forEach var="reviewOne" items="${ reviewOneList }">
													<c:if test="${ reviewOne.drmReviewContent ne 'EMPTY' }">
														<span class="shopContent">${ reviewOne.drmReviewContent }</span> 
													</c:if>
													<c:if test="${ reviewOne.drmReviewContent eq 'EMPTY' }">
														<span class="shopContent">${ newShop.shopName }</span> 
													</c:if>
												</c:forEach>
											</c:when>
										</c:choose>
									</div>
								</div>
							</c:forEach>
	 					</c:when>
	 					<c:otherwise>
	 						<c:forEach var="themeShop" items="${ themeList }">
								<div class="shopShortInfo">
									<div class="shopShortInfo left">
										<!-- <img src="/resources/images/shopMainImg/${themeShop.shopFileName}" alt="shopMain"> -->
									</div>
									<div class="shopShortInfo right">
										<span id="shop-title"><b><a href="shopDetail.dz?shopNo=${ themeShop.shopNo }">${ themeShop.shopName }</a></b></span>&nbsp;&nbsp;
										<span id="shop-type">${ themeShop.shopType }</span><br><br>
										<span>${ themeShop.shopShortAddr }</span><br>
										<span>${ themeShop.startTime }:00 - ${ themeShop.endTime }:00&nbsp;&nbsp;매주 일요일&nbsp;휴무</span><br>
										<c:choose>
											<c:when test="${ themeShop.shopContent != null }">
												<span class="shopContent">${ themeShop.shopContent }</span> 
											</c:when>
											<c:when test="${ themeShop.shopContent == null }">
												<c:forEach var="reviewOne" items="${ reviewOneList }">
													<c:if test="${ reviewOne.drmReviewContent ne 'EMPTY' }">
														<span class="shopContent">${ reviewOne.drmReviewContent }</span> 
													</c:if>
													<c:if test="${ reviewOne.drmReviewContent eq 'EMPTY' }">
														<span class="shopContent">${ themeShop.shopName }</span> 
													</c:if>
												</c:forEach>
											</c:when>
										</c:choose>
									</div>
								</div>
							</c:forEach>
	 					</c:otherwise>
	 				</c:choose>
				</div>
				
				<!-- 네비 -->
				<div class="result-list-navi">
					<c:choose>
						<c:when test="${ !empty searchKeyword }">
								<div class="searchResult navi">
									<c:url var="before" value="searchShop.dz">
										<c:param name="page" value="${ pi.currentPage - 1 }"></c:param>
										<c:param name="searchKeyword" value="${ searchKeyword }"></c:param>
									</c:url>
									<c:if test="${ pi.currentPage > 1 }">
										<a href="${ before }"><img src="/resources/images/navi-left.png" alt="이전"/>&nbsp;&nbsp;</a>
									</c:if>
									<c:forEach var="p" begin="${ pi.startPage }" end="${ pi.endPage }">
										<c:url var="pagination" value="searchShop.dz">
											<c:param name="page" value="${ p }"></c:param>
											<c:param name="searchKeyword" value="${ searchKeyword }"></c:param>
										</c:url>
										<c:if test="${ p eq pi.currentPage }">
											<span id="currentPage">${ p }</span>
										</c:if>
										<c:if test="${ p ne pi.currentPage }">
											<a href="${ pagination }"><span id="otherPage">${ p }</span>&nbsp;&nbsp;</a>
										</c:if>
									</c:forEach>
									<c:url var="after" value="searchShop.dz">
										<c:param name="page" value="${ pi.currentPage + 1 }"></c:param>
										<c:param name="searchKeyword" value="${ searchKeyword }"></c:param>
									</c:url>
									<c:if test="${ pi.currentPage >= pi.maxPage }">
									</c:if>
									<c:if test="${ pi.currentPage < pi.maxPage }">
										<a href="${ after }"><img src="/resources/images/navi-right.png" alt="다음"/></a>
									</c:if>
								</div>
						</c:when>
						<c:when test="${ themeNo eq 1 || themeNo eq 3 }"></c:when>
						<c:when test="${ themeNo ne 1 && themeNo ne 3 }">
								<div class="searchResult navi">
									<c:url var="before" value="searchTheme.dz">
										<c:param name="page" value="${ pi.currentPage - 1 }"></c:param>
										<c:param name="themeNo" value="${ themeNo }"></c:param>
									</c:url>
									<c:if test="${ pi.currentPage > 1 }">
										<a href="${ before }"><img src="/resources/images/navi-left.png" alt="이전"/>&nbsp;&nbsp;</a>
									</c:if>
									<c:forEach var="p" begin="${ pi.startPage }" end="${ pi.endPage }">
										<c:url var="pagination" value="searchTheme.dz">
											<c:param name="page" value="${ p }"></c:param>
											<c:param name="themeNo" value="${ themeNo }"></c:param>
										</c:url>
										<c:if test="${ p eq pi.currentPage }">
											<span id="currentPage">${ p }</span>
										</c:if>
										<c:if test="${ p ne pi.currentPage }">
											<a href="${ pagination }"><span id="otherPage">${ p }</span>&nbsp;&nbsp;</a>
										</c:if>
									</c:forEach>
									<c:url var="after" value="searchTheme.dz">
										<c:param name="page" value="${ pi.currentPage + 1 }"></c:param>
										<c:param name="themeNo" value="${ themeNo }"></c:param>
									</c:url>
									<c:if test="${ pi.currentPage >= pi.maxPage }">
									</c:if>
									<c:if test="${ pi.currentPage < pi.maxPage }">
										<a href="${ after }"><img src="/resources/images/navi-right.png" alt="다음"/></a>
									</c:if>
								</div>
						</c:when>
					</c:choose>
				</div>
			</div>
		</div> 
	</main>
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>


	<script>
	$(function() {
		$("#btn-search").on("click", function() {
			var searchKeyword = $("#searchBox").val();
			if(searchKeyword == "") {
				alert("검색하실 식당명, 음식 종류를 입력해주세요.");
				return false;
			}else {
				location.href = 'searchShop.dz?searchKeyword=' + searchKeyword;
			}
		});
	});
	</script>
</body>
</html>