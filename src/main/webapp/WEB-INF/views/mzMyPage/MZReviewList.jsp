<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/css/mzmypage/mzpaymentlist.css">
<title>일반회원 마이페이지</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/mypagemenubar.jsp"></jsp:include>
	<main>
		<div id="main-title">돈쭐 목록</div>
		<div class="frame">
			<div class="my-info">
				<div class="info-btn-frame">
					<a class="info-btn" href="mzMyPage.dz">돌아가기</a>
				</div>
			</div>
		</div>
		<div class="my-list reserv-list w-list">
			<div class="frame">
				<table>
					<thead>
						<tr>
							<th>No</th>
							<th>가게이름</th>
							<th>돈쭐날짜</th>
							<th>돈쭐금액</th>
							<th>사용포인트</th>
						</tr>
					</thead>
					<c:if test="${ !empty dList }">
					<tbody class="don-tbody">
					<c:forEach items="${dList }" var="donList" varStatus="status">
						<tr>
							<td>${status.count }</td>
							<td><a class="table-link-title" href="shopDetail.dz?shopNo=${donList.shopNo }"><p>${donList.shopName }</p></a></td> <!-- 가게 상세페이지 -->
							<td>${donList.paymentDate }</td>
							<td>${donList.donPrice }</td>
							<td>${donList.usePoint }</td>
						</tr>
					</c:forEach>
					</tbody>
				
				
						<!-- 페이징 처리 -->
							<tbody>
							
						<tr align="center" height="20">
							<td colspan="5">
								<!-- 이전 -->
								<c:url var="before" value="printDonAllList.dz">
									<c:param name="page" value="${pi.currentPage - 1 }"></c:param>
								</c:url>
								<c:if test="${pi.currentPage <= 1 }">
									[이전]&nbsp;
								</c:if>
								<c:if test = "${pi.currentPage > 1 }">
									<a href="${before }">[이전]</a>&nbsp;
								</c:if>
								<!-- 페이지 -->
								<c:forEach var="p" begin="${pi.startPage }" end="${pi.endPage }">
									<c:url var="pagination" value="printDonAllList.dz">
										<c:param name="page" value="${p }"></c:param>
									</c:url>
									<c:if test="${p eq pi.currentPage }">
										<font color="red" size="4">[${p }]</font>								
									</c:if>
									<c:if test="${p ne pi.currentPage }">
										<a href="${pagination }">${p }</a>&nbsp;
									</c:if>
								</c:forEach>
								<!-- 다음 -->
								<c:url var="after" value="printDonAllList.dz">
									<c:param name="page" value="${pi.currentPage + 1 }"></c:param>
								</c:url>
								<c:if  test="${pi.currentPage >= pi.maxPage }">
									[다음]&nbsp;
								</c:if>
								<c:if test="${pi.currentPage < pi.maxPage }">
									<a href="${after }">[다음]</a>&nbsp;
								</c:if>
							</td>
						</tr> 
							</tbody>
						</c:if>
						<c:if test="${ empty dList }">
							<tbody>
								<tr>
									<td colspan="6">${ msg }</td>
								</tr>
							</tbody>
						</c:if>
						</table>
						
			<%-- <!-- 페이징 -->
            <table class="board-page-table">
                <tbody>
                    <tr>
                    <!-- 이전 -->
                    	<c:url var="before" value="printDonAllList.dz">
                    		<c:param name="page" value="${ pi.currentPage -1 }"></c:param>
                    	</c:url>
                    	<c:if test="${ pi.currentPage <= 1 }">
		                        
                    	</c:if>
                    	<c:if test="${ pi.currentPage >1 }">
	                        <td class="page-td" width=35px><a href="${ before }">&lt;</a></td>
                    	</c:if>
                   	<!-- 이전끝 -->
                   	<!-- 페이징 -->
                   		<c:forEach var="p" begin="${ pi.startPage }" end="${ pi.endPage }">
                   			<c:url var="pagination" value="printDonAllList.dz">
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
                   	<!-- 다음 -->
                   		<c:url var="after" value="printDonAllList.dz">
                    		<c:param name="page" value="${ pi.currentPage +1 }"></c:param>
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
        	 --%>
        	
        	
			</div>
		</div>
	</main>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
</html>