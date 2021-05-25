<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- css -->
<link rel="stylesheet" type="text/css" href="/resources/css/shop/ShopDetail.css">
<title>가게 상세 페이지</title>
</head>
<body>

	<jsp:include page="/WEB-INF/views/common/menubar.jsp"/>
		
		 <div class="header-background-area">
		 	<!-- 가게 메인 이미지 가져오기 -->
		 	<img src="/resources/images/shopMainImg/realPasta.jpeg" alt="shopMain">
	   	</div>
		<div id="shop-header">
			<div id="shop-main-title">
				<span>${ shop.shopName }</span>
				<span id="shop-main-title-type">${ shop.shopType }</span><br>
				<span id="shop-main-title-provide">${ shop.shopProduct }</span>
			</div>
			
			<!-- 사업자 회원 제외 찜버튼 활성화 -->
			<c:if test="${ loginUser.userType != 3 }">		
				<div id="pick-zone">
					<!-- 세션 체크 하여 동작 -->
					<!-- 세션 없을시 로그인 연결 -->
					찜버튼!!
					<input type="hidden" name="shopNo" value="${ shop.shopNo }">
					<span id="pick-button" onclick=""><img src="" alt="pick-button"></span>
				</div>
			</c:if>
		</div>
		
	<main>
		<div class=frame>
			<div class="shop-detail">
				<div class="detailAll line1">
					<div class="detail-left">휴무일</div>
					<div class="detail-right">${ shop.businessDay }</div>
				</div>
				
				<div class="detailAll line2">
					<div class="detail-left">영업시간</div>
					<div class="detail-right">${ shop.startTime }:00 - ${ shop.endTime }:00</div>
				</div>
				
				<div class="detailAll line3">
					<div class="detail-left">대표메뉴</div>
					<div class="detail-right">
						<div class="detail-right menu-list">${ mainMenu.mainMenuName }&nbsp;&nbsp;${ mainMenu.mainMenuPrice }</div>
						<div class="detail-right menu-img"> <!-- 이미지파일 여러개 생성 ( 미리보기 가능 ) ( 최대 몇개 ? ) -->
							<img src="" alt="menuImg">
							<%-- ${ mPhoto.menuFileName } --%>
						</div> 
					</div>
				</div>
				
				<div class="detailAll line4">
					<div class="detail-left">주차여부</div>
					<div class="detail-right">
						<c:if test="${ shop.shopParking eq 'Y' }">
							주차가능
						</c:if>
						<c:if test="${ shop.shopParking eq 'N' }">
							주차불가
						</c:if>
					</div>
				</div>
				
				<div class="detailAll line5">
					<div class="detail-left">상세내용</div>
					<div class="detail-right">${ shop.shopContent }</div> <!-- 최대 몇글자? -->
				</div>
				
				<div class="detailAll line6">
					<div class="detail-left">연락처</div>
					<div class="detail-right">${ shop.shopPhone }</div>
				</div>
				
				<div class="detailAll line7">
					<div class="detail-left">주소</div>
					<div class="detail-right">${ shop.shopAddr }</div>
				</div>
				
				<div id="map"></div>
				
				<div class="detailAll buttons">
					<c:url var="reservation" value="reservationView.dz">
						<c:param name="shopNo" value="${ shop.shopNo }"/>
						<c:param name="shopName" value="${ shop.shopName }"/>
						<c:param name="startTime" value="${ shop.startTime }"/>
						<c:param name="endTime" value="${ shop.endTime }"/>
						<c:param name="businessDay" value="${ shop.businessDay }"/>
						<c:param name="shopMaxReserv" value="${ shop.shopMaxReserv }"/>
					</c:url>
					<c:url var="donation" value="reservationView.dz">
						<c:param name="shopNo" value="${ shop.shopNo }"/>
						<c:param name="shopName" value="${ shop.shopName }"/>
						<c:param name="startTime" value="${ shop.startTime }"/>
						<c:param name="endTime" value="${ shop.endTime }"/>
						<c:param name="businessDay" value="${ shop.businessDay }"/>
						<c:param name="shopMaxReserv" value="${ shop.shopMaxReserv }"/>
					</c:url>
					
					<!-- 회원별 버튼 생성 -->
					<!-- 비회원 : 로그인 연결 -->
					<c:if test="${ empty loginUser }">
						<ul>
							<li><a href="javascript:void(0);" onclick="goLogin()">예약하기</a></li>
							<li><a href="javascript:void(0);" onclick="goLogin()">돈쭐내기</a></li>
							<li><a href="javascript:history.back();">목록으로</a></li>
						</ul>
					</c:if>
					
					<!-- 회원 -->
					<c:if test="${ !empty loginUser }">
						
						<!-- 꿈나무회원 -->
						<c:if test="${ loginUser.userType == 1 }">
							<ul>
								<li><a href="${ reservation }">예약하기</a></li>
								<li><a href="javascript:history.back();">목록으로</a></li>
							</ul>
						</c:if>
						
						<!-- mz -->
						<c:if test="${ loginUser.userType == 2 }">
							<ul>
								<li><a href="${ reservation }">예약하기</a></li>
								<li><a href="${ donation }">돈쭐내기</a></li>
								<li><a href="javascript:history.back();">목록으로</a></li>
							</ul>
						</c:if>
						
						<!-- 사업자 -->
						<c:if test="${ loginUser.userType == 3 }">
							<ul>
								<li><a href="javascript:history.back();">목록으로</a></li>
							</ul>
						</c:if>
					
					</c:if>
					
				</div>

				
				<!-- 예약후기 -->
				<!-- <div class="header-background-area">
				 	<img src="/resources/images/review-title.png" alt="shopMain">
			   	</div> -->
				
				<div id="review-title" class="reviewTitle">
					<span>예약후기</span>&nbsp;&nbsp;
					<span>솔직하고 따뜻한 후기</span>
				</div>
				
				<div id="review-tab">
					<ul>
						<li><a href="javascript:void(0);" onclick="shopReviewAll()">전체후기</a></li>
						<li><a href="javascript:void(0);" onclick="drReviewAll()">감사후기</a></li>
						<li><a href="javascript:void(0);" onclick="mzReviewAll()">맛집후기</a></li>
					</ul>
				</div>
				
				<div class="review-list"> <!-- 처음에 보여질 후기 갯수 / 작성날짜, 닉네임 안들어가도 되는지 확인 -->
				 	<c:forEach items="${ drList }" var="reviewAll">
						<div class="review-list rContent">
							<div class="rContent left">
								<!-- <img src="/resources/images/shopMainImg/realPasta.jpeg" alt="shopMain"> -->
							</div>
							<div class="rContent right">
								<hr>
								<span><b>후기제목</b></span>&nbsp;&nbsp;
								<span>${ reviewAll.drmReviewTitle }</span>&nbsp;&nbsp;&nbsp;&nbsp;
								<span><b>/&nbsp;&nbsp;후기타입</b></span>&nbsp;&nbsp;
								<span>감사후기</span><br> <!-- 감사후기 공개 여부 확인해서 가져오기 -->
								<span><b>후기내용</b></span><br>
								<span>${ reviewAll.drmReviewContent }</span><br>
								<hr>
							</div>
						</div>
					</c:forEach> 
					<div class="review-list showMoreReply">
						<!-- 클릭시 나올 후기 갯수 -->
						<input type="button" id="moreReply" onclick="">더보기
					</div>
				</div>
			</div>
		</div>
	</main>
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	
	<script>
	
	</script>
</body>
</html>