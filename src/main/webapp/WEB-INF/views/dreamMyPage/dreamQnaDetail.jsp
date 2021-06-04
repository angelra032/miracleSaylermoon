<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/css/adminpage/ListPagination.css">
<link rel="stylesheet" href="/resources/css/dreammypage/dreamqnadetail.css">
<title>꿈나무회원 문의목록 페이지</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/mypagemenubar.jsp"></jsp:include>
	<main>
		<div id="main-title">문의 목록</div>
		<div class="frame">
			<div class="my-info">
				<div class="info-btn-frame">
					<a class="info-btn" href="javascript:history.back();">돌아가기</a>
				</div>
				
			</div>
		</div>
		<div class="my-list reserv-list">
			<div class="frame">
				<table class="list-table">
					<thead>
						<tr>
							<th>No</th>
							<th>가게이름</th>
							<th>예약날짜</th>
							<th>예약취소</th>
							<th>후기작성</th>
						</tr>
					</thead>
					<tbody>
					<c:if test="${ !empty qList }">
					<c:forEach items="${qList }" var="qna" varStatus="status">
						<tr>
							<td>${status.count }</td>
							<td><a class="table-link-title" href="qaDetail.dz?qnaNo=${qna.qnaNo }">${qna.qnaTitle }</a></td>
							<td class="qnaDate">${qna.qanCreateDate }</td>
							
							
							<td><a class="modify-btn" href="qaUpdateForm.dz?qnaNo=${qna.qnaNo}">수정</a></td>
							<td><a class="delete-btn" href="#">삭제</a></td>
						</tr>
					</c:forEach>
						</c:if>
						<c:if test="${ empty qList }">
							<tr>
								<td colspan="5">${ msg }</td>
							</tr>
						</c:if>
						<c:if test="${ !empty qList }">
							<table class="page-table">
							<tbody>
								<tr>
								<!-- 이전 -->
									<c:url value="allQnaListByDream.dz" var="before">
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
			                    		<c:url var="pagination" value="allQnaListByDream.dz">
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
									<c:url var="after" value="allQnaListByDream.dz">
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
					</tbody>
				</table>
			</div>
		</div>
	</main>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
	var qnaDate = document.querySelectorAll.('.qnaDate');
	console.log("qnaDate"+qnaDate);

	$(document).on('click','.delete-btn',function(){
		console.log("여기 들어오니");
		return false;
	});
</script>
</html>