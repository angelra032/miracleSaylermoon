<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/css/dreammypage/dreampickdetail.css">
<link rel="stylesheet" href="/resources/css/adminpage/ListPagination.css">
<title>꿈나무회원 찜목록 페이지</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/mypagemenubar.jsp"></jsp:include>
	<main>
		<div id="main-title">찜 목록</div>
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
							<th>가게위치</th>
							<th>삭제</th>
						</tr>
					</thead>
					<tbody>
					<c:if test="${ !empty pList }">
							<c:forEach items="${pList }" var="pick" varStatus="status">
								<tr>
									<td>${status.count }</td>
									<td><a class="table-link-title" href="shopDetail.dz?shopNo=${pick.shopNo }"><p>${pick.shopName }</p></a></td>
									<td>${ pick.shopShortAddr}</td>
									<td><a class="delete-btn" href="removePick.dz?shopNo=${pick.shopNo }">삭제</a></td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${ empty pList }">
							<tr>
								<td colspan="4">${msg }</td>
							</tr>
						</c:if>
						<c:if test="${ !empty pList }">
						
							<table class="page-table">
							<tbody>
								<tr>
								<!-- 이전 -->
									<c:url value="pickListByDream.dz" var="before">
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
			                    		<c:url var="pagination" value="pickListByDream.dz">
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
									<c:url var="after" value="pickListByDream.dz">
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
				<!-- 왜 안먹히냐 -->
			</div>
		</div>
	</main>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
</html>