<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/css/mzmypage/mzmypage.css">
<title>일반회원 마이페이지</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/mypagemenubar.jsp"></jsp:include>
	<main>
		<c:if test="${ !empty sessionScope.loginUser && sessionScope.loginUser.userType == '2'}">
			<div id="main-title">${ loginUser.userName }님 안녕하세요!</div>
		</c:if>
		<c:if test="${ empty sessionScope.loginUser && !empty sessionScope.kakaoId}">
			<div id="main-title">${ kakaoNickname }님 안녕하세요!</div>
		</c:if>
		<div class="frame">
			<div class="my-info">
				<span>보유포인트 : <b>${ loginUser.userPoint }</b>원</span>
				<div class="info-btn-frame">
					<a class="info-btn" href="myINfo.dz?userNo=${loginUser.userNo }">나의 정보</a>
					
					<a class="info-btn" href="userWritePwView.dz">회원 탈퇴</a>
				</div>
			</div>
		</div>
		<div class="my-list reserv-list">
			<div class="frame">
				<div class="my-title">
					<span>예약 목록</span>
					<div class="more-btn-frame">
						<a class="more-btn b-btn" href="mzReservationList.dz">더보기</a>
					</div>
				</div>
				<table>
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
						<c:if test="${ !empty rList }">
							<c:forEach items="${rList }" var="reservation" varStatus="status">
								<tr>
									<td>${status.count }</td>
									<td><a class="table-link-title" href="#"><p>${reservation.shopName }</p></a></td>
									<td>${reservation.reserveDate }</td>
									<c:if test="${reservation.rState eq 'O' }">
									<td><a class="reserv-btn" href="cancelReservation.dz?reservationNo=${ reservation.reservationNo }">예약취소</a></td>
									</c:if>
									<c:if test="${reservation.rState eq 'X' }">
									<td><a class="btn btn-secondary">취소완료</a></td>
									</c:if>
									<c:if test="${reservation.rState eq 'Y' }">
									<td><a class="confirm-btn" >예약확정</a></td>
									</c:if>
									
									
									<td><a class="btn btn-secondary">후기작성</a></td>
									<c:if test="${reservation.rState eq 'C' }">
									<td><a class="reserv-btn" href="#">후기작성</a></td>
									</c:if>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${ empty rList }">
							<tr>
								<td colspan="5">${ msg }</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
		<div class="my-list w-list wish-list">
			<div class="frame">
				<div class="my-title">
					<span>가고싶다 목록</span>
					<div class="more-btn-frame">
						<a class="more-btn b-btn" href="#">더보기</a>
					</div>
				</div>
				<table>
					<thead>
						<tr>
							<th>No</th>
							<th>가게이름</th>
							<th>가게위치</th>
							<th>삭제</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>1</td>
							<td><a class="table-link-title" href="#"><p>진짜파스타</p></a></td>
							<td>서울시 종로구</td>
							<td><a class="delete-btn" href="#">삭제</a></td>
						</tr>
						<tr>
							<td>1</td>
							<td><a class="table-link-title" href="#"><p>진짜파스타</p></a></td>
							<td>서울시 종로구</td>
							<td><a class="delete-btn" href="#">삭제</a></td>
						</tr>
						<tr>
							<td>1</td>
							<td><a class="table-link-title" href="#"><p>진짜파스타</p></a></td>
							<td>서울시 종로구</td>
							<td><a class="delete-btn" href="#">삭제</a></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="my-list donation-list">
			<div class="frame">
				<div class="my-title">
					<span>돈쭐 목록</span>
					<div class="more-btn-frame">
						<a class="more-btn b-btn" href="printDonAllList.dz">더보기</a>
					</div>
				</div>
				<table>
					<thead>
						<tr>
							<th>No</th>
							<th>가게이름</th>
							<th>돈쭐날짜</th>
							<th>돈쭐금액</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${ !empty dList }">
						<c:forEach items="${dList }" var="donList" varStatus="status">
							<tr>
								<td>${status.count }</td>
								<td><a class="table-link-title" href="#"><p>${donList.shopName }</p></a></td>
								<td>${donList.paymentDate }</td>
								<td>${donList.donPrice }</td>
							</tr>
						</c:forEach>
						</c:if>
						<c:if test="${ empty dList }">
							<tr>
								<td colspan="5">${ msg }</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
		<div class="my-list w-list review-list">
			<div class="frame">
				<div class="my-title">
					<span>내가 쓴 후기</span>
					<div class="more-btn-frame">
						<a class="more-btn b-btn" href="#">더보기</a>
					</div>
				</div>
				<table>
					<thead>
						<tr>
							<th>No</th>
							<th>제목</th>
							<th>가게이름</th>
							<th>작성날짜</th>
							<th>수정</th>
							<th>삭제</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>1</td>
							<td><a class="table-link-title" href="#"><p>진짜파스타 짱맛있습니다</p></a></td>
							<td>진짜파스타</td>
							<td>2021-01-01</td>
							<td><a class="modify-btn" href="#">수정</a></td>
							<td><a class="delete-btn" href="#">삭제</a></td>
						</tr>
						<tr>
							<td>1</td>
							<td><a class="table-link-title" href="#"><p>진짜파스타 짱맛있습니다</p></a></td>
							<td>진짜파스타</td>
							<td>2021-01-01</td>
							<td><a class="modify-btn" href="#">수정</a></td>
							<td><a class="delete-btn" href="#">삭제</a></td>
						</tr>
						<tr>
							<td>1</td>
							<td><a class="table-link-title" href="#"><p>진짜파스타 짱맛있습니다</p></a></td>
							<td>진짜파스타</td>
							<td>2021-01-01</td>
							<td><a class="modify-btn" href="#">수정</a></td>
							<td><a class="delete-btn" href="#">삭제</a></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="my-list recommend-list">
			<div class="frame">
				<div class="my-title">
					<span>내가 쓴 추천</span>
					<div class="more-btn-frame">
						<a class="more-btn b-btn" href="#">더보기</a>
					</div>
				</div>
				<table>
					<thead>
						<tr>
							<th>No</th>
							<th>제목</th>
							<th>작성날짜</th>
							<th>수정</th>
							<th>삭제</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>1</td>
							<td><a class="table-link-title" href="#"><p>여기 추천해요</p></a></td>
							<td>2021-01-01</td>
							<td><a class="modify-btn" href="#">수정</a></td>
							<td><a class="delete-btn" href="#">삭제</a></td>
						</tr>
						<tr>
							<td>1</td>
							<td><a class="table-link-title" href="#"><p>여기 추천해요</p></a></td>
							<td>2021-01-01</td>
							<td><a class="modify-btn" href="#">수정</a></td>
							<td><a class="delete-btn" href="#">삭제</a></td>
						</tr>
						<tr>
							<td>1</td>
							<td><a class="table-link-title" href="#"><p>여기 추천해요</p></a></td>
							<td>2021-01-01</td>
							<td><a class="modify-btn" href="#">수정</a></td>
							<td><a class="delete-btn" href="#">삭제</a></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="my-list w-list recommend-list">
			<div class="frame">
				<div class="my-title">
					<span>내가 쓴 문의글</span>
					<div class="more-btn-frame">
						<a class="more-btn b-btn" href="#">더보기</a>
					</div>
				</div>
				<table>
					<thead>
						<tr>
							<th>No</th>
							<th>제목</th>
							<th>작성날짜</th>
							<th>수정</th>
							<th>삭제</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>1</td>
							<td><a class="table-link-title" href="#"><p>문의합니다</p></a></td>
							<td>2021-01-01</td>
							<td><a class="modify-btn" href="#">수정</a></td>
							<td><a class="delete-btn" href="#">삭제</a></td>
						</tr>
						<tr>
							<td>1</td>
							<td><a class="table-link-title" href="#"><p>문의합니다</p></a></td>
							<td>2021-01-01</td>
							<td><a class="modify-btn" href="#">수정</a></td>
							<td><a class="delete-btn" href="#">삭제</a></td>
						</tr>
						<tr>
							<td>1</td>
							<td><a class="table-link-title" href="#"><p>문의합니다</p></a></td>
							<td>2021-01-01</td>
							<td><a class="modify-btn" href="#">수정</a></td>
							<td><a class="delete-btn" href="#">삭제</a></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		
	</main>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
</html>