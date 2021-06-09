<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="resources/css/board/common/mainList.css">
<title>공지 문의사항</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/menubar.jsp"></jsp:include>
	<main>
		<div class="header-background-area">
        	<img src="/resources/images/board/board-banner.png" alt="뒷배경이미지">
	   	</div>
		<div id="main-title">공지사항</div>
		<div class="frame">
			<jsp:include page="/WEB-INF/views/board/common/boardNavi.jsp"></jsp:include>
			<c:if test="${ qList.size() > 0 }">
	            <table class="board-list-table">
	                <thead class="board-thead">
	                    <tr class="board-thead-tr">
	                        <td width=110>No</td>
	                        <td width=110>구분</td>
	                        <td width=450>제목</td>
	                        <td width=160>작성자</td>
	                        <td width=160>날짜</td>
                         	<td width=100>조회수</td>
	                    </tr>
	                   <c:forEach items="${ nList }" var="notice" varStatus="status">
	                    	<c:set var="num" value="${ status.index }"/>
		                    <tr onclick="location.href='noticeDetail.dz?noticeNo=${notice.noticeNo}'" style="cursor: pointer;"> <!-- 공지사항위치 -->
		                        <td>${ num }</td>
		                        <td>관리자</td>
		                        <td>${ notice.noticeTitle }</td>
		                        <td>관리자</td>
		                        <td>${ notice.noticeCreateDate }</td>
		                        <td>${ notice.noticeHit }</td>
		                    </tr>
	                    </c:forEach>
	                </thead>
	                <tbody>
	                	<c:forEach items="${ qList }" var="qna" varStatus="status">
	                		 <c:set var="num" value="${ pi.listCount - ((pi.currentPage - 1) * 10) - status.index }"/>
		                    <tr onclick="location.href='qaDetail.dz?qnaNo=${qna.qnaNo}'" style="cursor: pointer;">
		                        <td>${ num }</td>
		                        <c:if test="${ qna.userType eq '1' }">
			                        <td>꿈나무</td>
		                        </c:if>
             					<c:if test="${ qna.userType eq '2' }">
			                        <td>일반</td>
		                        </c:if>
		                        <c:if test="${ qna.userType eq '3' }">
			                        <td>사업자</td>
		                        </c:if>
		                        <c:if test="${ qna.userType eq '4' }">
			                        <td>관리자</td>
		                        </c:if>
		                        <td>${ qna.qnaTitle }</td>
		                        <td>${ qna.qnaWriter }</td>
		                        <td>${ qna.qanCreateDate }</td>
		                        <td>${ qna.qnaHit }</td>
		                    </tr>
	                		
	                	</c:forEach>
	                </tbody>
	            </table>
    
	            <!-- 페이징 -->
	            <table class="board-page-table">
	                <tbody>
	                    <tr>
	                    <!-- 이전 시작 -->
	                   	 	<c:url var="before" value="notiQnaMain.dz"> 
	                    		<c:param name="page" value="${ pi.currentPage -1 }"></c:param>
	                    	</c:url>
	                    	<c:if test="${ pi.currentPage <= 1 }">
		                        
	                    	</c:if>
	                    	<c:if test="${ pi.currentPage >1 }">
		                        <td class="page-td" width=35px><a href="${ before }">&lt;</a></td>
	                    	</c:if>
	                    <!-- 이전 끝 -->
	                    <!-- 페이지 -->
	                    	<c:forEach var="p" begin="${ pi.startPage }" end="${ pi.endPage }">
	                    		<c:url var="pagination" value="notiQnaMain.dz">
	                    			<c:param name="page" value="${ p }"></c:param>
	                    		</c:url>
	                    		<c:if test="${ p eq pi.currentPage }">
			                        <td class="page-td page-selected" width=35px>${ p }</td>
	                    		</c:if>
								<c:if test="${ p ne pi.currentPage }">
			                        <td class="page-td" width=35px><a href="${ pagination }">${ p }</a></td>
								</c:if>	                    	
	                    	</c:forEach>
	                    <!-- 페이지 끝 -->
	                    <!-- 다음 시작 -->
	                    	<c:url var="after" value="notiQnaMain.dz">
	                    		<c:param name="page" value="${ pi.currentPage+1 }"></c:param>
	                    	</c:url>
	                    	<c:if test="${ pi.currentPage >= pi.maxPage }">
	                    	</c:if>
	                    	<c:if test="${ pi.currentPage < pi.maxPage }">
	                    		<td class="page-td" width=35px><a href="${ after }">&gt;</a></td>
	                    	</c:if>
	                    <!-- 다음 끝 -->
	                    </tr>
	                </tbody>
	            </table>
			
			</c:if>   
			
			<c:if test="${ empty qList.size() }">
				<h1>${ msg }</h1>
			</c:if> 
			
			<c:if test="${ loginUser.userType eq '2' or loginUser.userType eq '1' or loginUser.userType eq '3'}">
	            <div id="write-btn-area">
	                <button onclick="location.href='/qaWriteView.dz'">문의하기</button>
	            </div>
			</c:if>
			<c:if test="${ loginUser.userType eq '4' }">
				<div id="write-btn-area">
	                <button onclick="location.href='/noticeWriteView.dz'">공지쓰기</button>
	            </div>
			</c:if>
        </div>
	</main>
</body>
</html>