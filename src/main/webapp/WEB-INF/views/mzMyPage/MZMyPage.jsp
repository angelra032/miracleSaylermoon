<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/css/mzmypage/mzmypage.css">
<title>마이페이지</title>
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
		<c:if test="${ empty sessionScope.loginUser && !empty sessionScope.googleId}">
			<div id="main-title">${ googleName }님 안녕하세요!</div>
		</c:if>
		<div class="frame">
			<div class="my-info">
				<span>보유포인트 : <b>${ loginUser.userPoint }</b>원</span>
				<c:if test="${ !empty sessionScope.loginUser && sessionScope.loginUser.userType == '2'}">
				<div class="info-btn-frame">
					<a class="info-btn" href="myINfo.dz?userNo=${ loginUser.userNo }">나의 정보</a>
					<a class="info-btn" href="userWritePwView.dz">회원 탈퇴</a>
				</div>
				</c:if>
			</div>
		</div>
		<div class="my-list reserv-list">
			<div class="frame">
				<div class="my-title">
					<span>예약 목록</span>
					<div class="more-btn-frame">
						<c:if test="${ !empty rList }">
							<a class="more-btn b-btn" href="mzReservationList.dz">더보기</a>
						</c:if>
					</div>
				</div>
				<table>
					<thead>
						<tr>
							<th>No</th>
							<th>가게이름</th>
							<th>예약일시</th>
							<th>예약인원</th>
							<th>예약취소</th>
							<th>후기작성</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${ !empty rList }">
							<c:forEach items="${rList }" var="reservation" varStatus="status">
								<tr>
									<td>${status.count }</td>
									<td><a class="table-link-title" href="shopDetail.dz?shopNo=${reservation.shopNo }"><p>${reservation.shopName }</p></a></td>
									<td>${reservation.reserveDate } / ${reservation.reserveTime }시</td>
									<td class="reserv-membercount${ reservation.reservationNo }">${reservation.reserveCount }명</td>
									
									<c:if test="${reservation.rState eq 'O' }">
										<td class="reserv-stay${ reservation.reservationNo }"><a class="reserv-btn reserv-btn${ reservation.reservationNo }" href="#" >예약취소</a></td>
										<input type="hidden" class="reservationNo" value="${ reservation.reservationNo }">
										<td><a class="reserv-btn disable-btn">후기작성</a></td>
									</c:if>
									<c:if test="${reservation.rState eq 'X' }">
										<td class="reserv-cancle">취소완료</td>
										<td><a class="reserv-btn disable-btn">후기작성</a></td>
									</c:if>
									<c:if test="${reservation.rState eq 'Y' }">
										<td class="reserv-confirm">예약확정</td>
										<td><a class="reserv-btn disable-btn">후기작성</a></td>
									</c:if>
									<c:if test="${reservation.rState eq 'C' }"><!-- 방문완료/후기작성가능 -->
										<td class="reserv-confirm">방문완료</td>
										<td><a class="reserv-btn review-btn" href="mReviewWriteView.dz?shopNo=${reservation.shopNo }&reservationNo=${reservation.reservationNo }">후기작성</a></td>
									</c:if>
									<c:if test="${reservation.rState eq 'H' }"><!-- 방문완료/후기작성완료 -->
										<td class="reserv-confirm">방문완료</td>
										<td>후기작성완료</td>
									</c:if>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${ empty rList }">
							<tr>
								<td colspan="5">${ Rmsg }</td>
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
								<td colspan="5">${ Dmsg }</td>
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
<script type="text/javascript">
	$(document).ready(function() {
		// a href='#' 클릭 무시 스크립트
		$('a[href="#"]').click(function(ignore) {
            ignore.preventDefault();
        });
		
		// 예약취소 aJax
		$('.reserv-btn').on('click', function() {
			var reservationNo = $(this).parent().next().val();
			$.ajax({
				url : "cancelMZReservation.dz",
				data : {"reservationNo" : reservationNo},
				success : function(result) {
					if(result == 'ok'){
						$('.reserv-stay'+reservationNo).remove();
						$('.reserv-membercount'+reservationNo).after('<td class="reserv-cancle">취소완료</td>');
					}
				},
				error : function() {
					console.log("전송실패");
				}
			});
		}); //end of $('.reserv-btn').click
		
		// 후기작성버튼 비활성화 되어있을때 누르면 alert창 띄우기
		$('.disable-btn').on('click', function() {
			alert('작성 가능한 기간이 아닙니다.');
		});

	});
</script>
</html>