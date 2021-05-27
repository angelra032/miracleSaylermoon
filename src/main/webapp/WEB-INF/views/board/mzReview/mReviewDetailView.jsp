<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="resources/css/board/common/detailView.css">
<title>맛집후기</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/menubar.jsp"></jsp:include>
	<main>
		<div class="header-background-area">
        	<img src="/resources/images/board/board-banner.png" alt="뒷배경이미지">
	   	</div>
		<div id="main-title">
			<span class="top-sub-title">${ mReview.mReviewTitle }</span>
			<span class="top-sub-shopName">어디어디어디 후기</span>
		</div>
		
        <div class="frame">
            <div class="title-nick-date-area">
                <div class="title-bottom-area">
                    <div class="title-bottom-nick-area">
                        <span class="userNick">${ mReview.mReviewWriter }</span>
                    </div>
                    <div class="title-bottom-date-area">
                        <span class="createDate">${ mReview.mReviewCreateDate }</span>
                    </div>
                </div>
            </div>
            <div class="content-area">
                ${ mReview.mReviewContent }
            </div>

            <div class="bottom-btn-area">
			<c:if test="${ mReview.userNo eq loginUser.userNo}">
	            <div class="modify-btn-area">
	                <button onclick="location.href='mReviewMain.dz'" style="cursor: pointer;">수정하기</button>
	            </div>
	            <div class="modify-btn-area">
	                <button onclick="location.href='mReviewDelete.dz?mReviewNo=${ mReview.mReviewNo }'" style="cursor: pointer;">삭제하기</button>
	            </div>
	            <div class="user-back-btn-area">
	                <button onclick="location.href='recommendMain.dz'" style="cursor: pointer;">목록으로</button>
	            </div>
			</c:if>
			<c:if test="${ mReview.userNo ne loginUser.userNo }">
			 	<div class="back-btn-area">
	                <button onclick="location.href='mReviewMain.dz'" style="cursor: pointer;">목록으로</button>
	            </div>
			</c:if>
			</div>
        </div>
       </main>
      <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
</html>