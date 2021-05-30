<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/css/adminpage/ListPagination.css">
<link rel="stylesheet" href="/resources/css/adminpage/listdetail.css">
<title>게시판 목록 페이지</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/mypagemenubar.jsp"></jsp:include>
	<main>
		<div id="main-title">문의사항</div>
		<div class="frame">
			<div class="my-info">
			<jsp:include page="/WEB-INF/views/adminPage/common/listTopNavi.jsp"></jsp:include> <!--  -->
			<jsp:include page="/WEB-INF/views/adminPage/common/boardListNavi.jsp"></jsp:include> <!-- 게시판별 네비 -->
			
				<div class="info-btn-frame">
					<a class="info-btn" href="adminPage.dz">돌아가기</a>
				</div>
			</div>
		</div>
		<div class="my-list reserv-list">
			<div class="frame">
				<table class="list-table">
					<thead>
						<tr>
							<th>No</th>
							<th>제목</th>
							<th>작성자</th>
							<th>날짜</th>
							<th width=150px>답글</th>
							<th width=150px>삭제</th>
						</tr>
					</thead>
					<tbody>
					<c:if test="${ !empty qnaList }">
					<c:forEach items="${qnaList }" var="qna" varStatus="status">
					 <c:set var="num" value="${ pi.listCount - ((pi.currentPage - 1) * 10) - status.index }"/>
						<tr>
							<td>${ num }</td>
							<td style="cursor: pointer;" onclick="location.href='qaDetail.dz?qnaNo=${qna.qnaNo}&qnaId=${ qna.qnaId }'">${qna.qnaTitle }</td>
							<td>${qna.qnaWriter }</td>
							<td>${qna.qanCreateDate }</td>
							<c:if test="${qna.groupLayer eq '0' }">
								<td><a class="delete-btn" href="QnAReplyWrite.dz?qnaNo=${ qna.qnaNo }&userNo=${qna.userNo}&qnaId=${qna.qnaId}">답글</a></td>
							</c:if>
							<c:if test="${qna.groupLayer eq '1' }">
								<td>답변완료</td>
							</c:if>
							<td><div class="delete-btn" onclick="deleteResult('${ qna. qnaNo}')">삭제</div></td>
							
						</tr>
					</c:forEach>
						</c:if>
						<c:if test="${ empty qnaList }">
							<tr>
								<td colspan="6">${ msg }</td>
							</tr>
						</c:if>
					</tbody>
				</table>
				<c:if test="${ !empty qnaList }">
					<table class="page-table">
							<tbody>
								<tr>
								<!-- 이전 -->
									<c:url value="adminQnaList.dz" var="before">
										<c:param name="page" value="${ pi.currentPage-1 }"></c:param>
									</c:url>
									<c:if test="${ pi.currentPage <= 1 }">
									</c:if>
									<c:if test="${ pi.currentPage > 1 }">
										<td class="page-td" width=35px><a href="${ before }">&lt;</a></td>
									</c:if>
									<!-- 이전끝 -->
									<!-- 페이징 -->
									<c:forEach var="p" begin="${ pi.startPage }" end="${ pi.endPage }">
			                    		<c:url var="pagination" value="adminQnaList.dz">
			                    			<c:param name="page" value="${ p }"></c:param>
			                    		</c:url>
			                    		<c:if test="${ p eq pi.currentPage }">
					                        <td class="page-td page-selected" width=35px>${ p }</td>
			                    		</c:if>
										<c:if test="${ p ne pi.currentPage }">
					                        <td class="page-td" width=35px><a href="${ pagination }">${ p }</a></td>
										</c:if>	                    	
			                    	</c:forEach>
									<!-- 페이징 끝 -->
									<c:url var="after" value="adminQnaList.dz">
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
			</div>
		</div>
	</main>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
<script>
	$('.menu-btn').eq(2).css('background','#0160ff').css('color','white');
	$('.board-menu-btn').eq(3).css('background','#0160ff').css('color','white');
	
	function deleteResult(data) {
        var result = confirm('글을 삭제합니다.');
        if(result) {
            location.href='qaDelete.dz?qnaNo=' + data;
			location.href='adminQnaList.dz';
        } else {
			location.href='adminQnaList.dz';
        }
    }
</script>
</html>