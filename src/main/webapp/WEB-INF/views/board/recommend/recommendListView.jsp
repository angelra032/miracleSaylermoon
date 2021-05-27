<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="resources/css/board/common/mainList.css">
<title>가게추천</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/menubar.jsp"></jsp:include>
	<main align="center">
		<div class="header-background-area">
        	<img src="/resources/images/board/board-banner.png" alt="뒷배경이미지">
	   	</div>
		<div id="main-title">가게추천</div>
		<div class="frame">
			<jsp:include page="/WEB-INF/views/board/common/boardNavi.jsp"></jsp:include>
    		
    		<c:if test="${ rList.size() > 0 }">
	            <table class="board-list-table">
	                <thead>
	                    <tr>
	                        <td width=110>No</td>
	                        <td width=450>제목</td>
	                        <td width=160>작성자</td>
	                        <td width=180>날짜</td>
	                    </tr>
	                </thead>
	                <tbody>
	                	<c:forEach items="${ rList }" var="recommend" varStatus="status">
   		            	<c:set var="num" value="${ pi.listCount - ((pi.currentPage - 1) * 10) - status.index }"/>
		                	<tr onclick="location.href='recommendDetail.dz?recommendNo=${recommend.recommendNo}'" style="cursor: pointer;">
		                        <td>${ num }</td>
		                        <td>${ recommend.recommendTitle }</td>
		                        <td>${ recommend.recommendWriter }</td>
		                        <td>${ recommend.recommendCreateDate }</td>
		                    </tr>
	                	</c:forEach>
	                </tbody>
	            </table>
	    
	            <!-- 페이징 -->
	            <table class="board-page-table">
	                <tbody>
	                    <tr>
 						<!-- 이전시작 -->
	                    	<c:url var="before" value="recommendMain.dz"> 
	                    		<c:param name="page" value="${ pi.currentPage -1 }"></c:param>
	                    	</c:url>
	                    	<c:if test="${ pi.currentPage <= 1 }">
		                        
	                    	</c:if>
	                    	<c:if test="${ pi.currentPage >1 }">
		                        <td class="page-td" width=35px><a href="${ before }">&lt;</a></td>
	                    	</c:if>
	                    <!-- 이전끝 -->
	                    <!-- 페이지 -->
	                    	<c:forEach var="p" begin="${ pi.startPage }" end="${ pi.endPage }">
	                    		<c:url var="pagination" value="recommendMain.dz">
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
	                    <!-- 다음 -->
	                    	<c:url var="after" value="recommendMain.dz">
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
    		
    		<c:if test="${ empty rList.size() }">
    			<h1>${ msg }</h1>
    		</c:if>
    		
			<c:if test="${ loginUser.userType eq '2' }">
	            <div id="write-btn-area">
	                <button>글쓰기</button>
	            </div>
			</c:if>
			
        </div>
	</main>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
	$('.menu-btn').eq(2).css('background','#0160ff').css('color','white');
	$('#write-btn-area').on("click", function() {
		location.href='recommendWriteView.dz'
	});
</script>
</html>