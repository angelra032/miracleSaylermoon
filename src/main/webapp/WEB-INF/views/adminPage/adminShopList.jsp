<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/css/adminpage/ListPagination.css">
<link rel="stylesheet" href="/resources/css/adminpage/listdetail.css">
<title>사업자 목록 페이지</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/mypagemenubar.jsp"></jsp:include>
	<main>
		<div id="main-title">사업자 목록</div>
		<div class="frame">
			<div class="my-info">
				<jsp:include page="/WEB-INF/views/adminPage/common/listTopNavi.jsp"></jsp:include>
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
							<th width=60>No</th>
							<th width=130px>업체명</th>
							<th width=60px>아이디</th>
							<th width=130px>업체등록번호</th>
							<th width=130px>전화번호</th>
							<th width=80px>승인상태</th>
							<th width=80px>탈퇴요청</th>
							<th width=120px>승인</th>
							<th width=120px>탈퇴</th>
						</tr>
					</thead>
					<tbody>
					<c:if test="${ !empty shopList }">
					<c:forEach items="${shopList }" var="shop" varStatus="status">
						<c:set var="num" value="${ pi.listCount - ((pi.currentPage - 1) * 10) - status.index }"/>
						<tr>
							<td>${num }</td>
							<td>${shop.shopName }</td>
							<td>${shop.userId }</td>
							<td>${shop.partnerVerify }</td>
							<td>${shop.shopPhone }</td>
							<c:if test="${shop.showShopYN eq 'y' or shop.showShopYN eq 'Y' }">
								<td>승인</td>
							</c:if>
							<c:if test="${shop.showShopYN eq 'n' or shop.showShopYN eq 'N' }">
								<td>미승인</td>
							</c:if>
							<td>${shop.partnerWithdraw }</td>
							<td><a class="delete-btn" href="#">승인</a></td>
							<td><a class="delete-btn" href="#">탈퇴</a></td>
							
						</tr>
					</c:forEach>
						</c:if>
						<c:if test="${ empty shopList }">
							<tr>
								<td colspan="6">${ msg }</td>
							</tr>
						</c:if>
					</tbody>
				</table>
				<c:if test="${ !empty shopList }">
					<table class="page-table">
							<tbody>
								<tr>
								<!-- 이전 -->
									<c:url value="adminShopList.dz" var="before">
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
			                    		<c:url var="pagination" value="adminShopList.dz">
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
									<c:url var="after" value="adminShopList.dz">
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
</html>