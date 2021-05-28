<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/css/main.css"> 
<title>돈쭐</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/menubar.jsp"></jsp:include>
	
	<main>
		<div class="header-background-area">
        	<img src="/resources/images/main/main-banner.jpg" alt="뒷배경이미지">
   		</div>	
   		<div class="main-banner-subtitle">Meaningful consumption, valuable life.</div>
		<div id="main-title">누구나 마음편히<br><span>밥먹는 세상</span>을 위해</div>
		<div class="search-box">
			<form action="">
				<input type="text" placeholder="검색하고자 하는 식당명을 입력해주세요">
				<button type="submit"><img alt="검색" src="/resources/images/search.png"></button>
			</form>
		</div>
		<div class="circle-btn-area">
			<a href="#"><img alt="지도" src="/resources/images/main/main-bt-map.png"><p>지도조회</p></a>
			<a href="#"><img alt="지도" src="/resources/images/main/main-bt-recommended.png"><p>테마추천</p></a>
			<a href="#"><img alt="지도" src="/resources/images/main/main-bt-thankyou.png"><p>감사후기</p></a>
			<a href="#"><img alt="지도" src="/resources/images/main/main-bt-review.png"><p>맛집후기</p></a>
		</div>
		<div class="review-area">
			<div class="main-sub-title"><span>100% 솔직후기</span></div>
			<div class="main-content-title">이미 수많은 분들이 돈쭐과 함께<br>가치있는 소비를 경험하고 계십니다.</div>
			<div class="review-contents-area">
				<c:if test="${ !empty mList }">
					<c:forEach items="${ mList }" var="mReview" varStatus="status">
						<div class="review-content">${ mReview.mReviewContent }</div>
					</c:forEach>
				</c:if>
				<c:if test="${ empty mList }">
						<div class="review-content">${ msg }</div>
				</c:if>
			</div>
		</div>
		<div class="thanks-area">
			<div class="main-sub-title more-paddingtop"><span>감사후기 전달</span></div>
			<div class="main-content-title">그동안 전하지 못했던 감사한 마음을<br>돈쭐이 전달해 드립니다.</div>
			<div class="content-area">
				<div class="thanks-content">
					<div class="thanks-title">사장님 정말정말 감사합니다!</div>
					<div class="thanks-writer">게으른달팽이</div>
					<div class="thanks-con">글글 아주긴 후기글 정성스러운 후기글입니다 아주 맛있고 짱이고 또오고싶고그래요 아무튼 아무 깉 후기글글글 아주긴 후기글 정성스러운 후기글입니다 아주 맛있고 짱이고 또오고싶고그래요 아무튼 아무 깉 후기글글글 아주긴 후기글 정성스러운 후
또오고싶고그래요 아무튼 아무 깉 후기글글글 아주긴 후기글 정성스러운 후기글입니다 아주 맛있고 짱이고 또오고싶고그래요 아무튼 아무 깉 후기글글글 아주긴 후기글 정성스러운 후기글입니다 아주 맛있고 짱이고 또오고싶고그래요 아무튼
 아무 깉 후기글글글 아주긴 후기글 정성스러운 후기글입니다 아주 맛있고 짱이고 또오고싶고그래요 아무튼 아무 깉 후기글
후기글글글 아주긴 후기글 정성스러운 후기글입니다 아주 맛있고 짱이고 또오고싶고그래요 아무튼 아무 깉 후기글글글 아주긴 후기글 정성스러운 후기글입니다 아주 맛있고 짱이고 또오고싶고그래요 아무튼 아무 깉 후기글글글 아주긴 후기글 정성스러운 후기글입니다 아주 맛있고 짱이고 또오고싶고그래요 아무튼 아무 깉 후기글글글 아주긴 후기글 정성스러운 후기글입니다 아주 맛있고 짱이고 또오고싶고그래요 아무튼 아무 깉 후기글글글 아주긴 후기글 정성스러운 후기글입니다 아주 맛있고 짱이고 또오고싶고그래요 아무튼 아무
고 짱이고 또오고싶고그래요 아무튼 아무 깉 후기글글글 아주긴 후기글 정성스러운 후기글입니다 아주 맛있고 짱이고 또오</div>
				</div>
			</div>
		</div>
		<div class="system-area">
			<div class="main-sub-title more-paddingtop less-margintop"><span>돈쭐만의 시스템</span></div>
			<div class="main-content-title white-text">최적화된 구조로 기부가 쉬워집니다.</div>
			<img alt="기부시스템" src="/resources/images/main/donationsystem.png">
		</div>
		<div class="money-area">
			<div class="main-sub-title more-paddingtop less-margintop-blue "><span>선한 영향력</span></div>
			<div class="main-content-title"><span>당신도</span> 늦지 않았습니다.<br><span>돈쭐과 함께하면</span> 쉽습니다.</div>
			<div class="money-intro">
				기부문화는 창조하는 자와 공감하는 자에 의해 태어나고 계속됩니다.<br>
				의미있는 소비의 즐거움으로
				우리 <br>사회의 기부적 토대는 더욱 단단해지고 있습니다.<br>
				함께 나눌수록 깊어지고 넓어지는 돈쭐의 힘!<br>
				의식있는 소비를 원하는 누구에게나<br>
				대한민국의 꿈나무를 응원할 수 있는 무대는 늘 열려있습니다.
			</div>
			<div class="money-count">
				<div class="donation-title-area">
					<div></div>
				</div>
			</div>
		</div>
	</main>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
</html>