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
					<a class="info-btn" href="adminPage.dz">돌아가기</a>
				</div>
			</div>
		</div>
		<div class="my-list reserv-list">
			<div class="frame">
				<table class="list-table">
					<thead>
						<tr>
							<th width=60>No</th>
							<th width=300px>업체명</th>
							<th width=60px>아이디</th>
							<th width=130px>업체등록번호</th>
							<th width=130px>전화번호</th>
							<th width=120px>공개상태</th>
							<th width=120px>환급</th>
							<th width=120px>탈퇴</th>
						</tr>
					</thead>
					<tbody>
					<c:if test="${ !empty shopList }">
					<c:forEach items="${shopList }" var="shop" varStatus="status">
						<c:set var="num" value="${ pi.listCount - ((pi.currentPage - 1) * 10) - status.index }"/>
						<tr>
							<td>${num }</td>
							<td style="cursor: pointer;" onclick="location.href='shopDetail.dz?shopNo=${shop.shopNo}'">${shop.shopName }</td>
							<td>${shop.userId }</td>
							<td>${shop.partnerVerify }</td>
							<td>${shop.shopPhone }</td>
							<c:if test="${ shop.showShopYN eq 'Y' or shop.showShopYN eq 'y' }">
								<td>승인완료</td>
							</c:if>
							<c:if test="${ shop.showShopYN eq 'N' or shop.showShopYN eq 'n' }">
								<td><a class="delete-btn" href="#">승인</a></td>
							</c:if>
							<c:if test="${ shop.shopPointYn eq 'y' or shop.shopPointYn eq 'Y' }">
								<td><a class="delete-btn" href="#">환급</a></td>
							</c:if>
							<c:if test="${ shop.shopPointYn eq 'n' or shop.shopPointYn eq 'N' }">
								<td><div class="done-btn">환급</div></td>
							</c:if>
							<c:if test="${ shop.partnerWithdraw eq 'Y' or shop.partnerWithdraw eq 'y' }">
								<td><div class="delete-btn" onclick="deleteShop('${shop.userNo}')">탈퇴</div></td>
							</c:if>
							<c:if test="${ shop.partnerWithdraw eq 'N' or shop.partnerWithdraw eq 'n' }">
								<td><div class="done-btn">탈퇴</div></td>
							</c:if>
							
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
<script type="text/javascript">
	$('.menu-btn').eq(1).css('background','#0160ff').css('color','white');
	
	function deleteShop(data) {
        var result = confirm('회원을 탈퇴시킵니다.');
        if(result) {
            location.href='adminUserDelete.dz?userNo=' + data;
			location.href='adminShopList.dz';
        } else {
			location.href='adminShopList.dz';
        }
    }
</script>
</html>