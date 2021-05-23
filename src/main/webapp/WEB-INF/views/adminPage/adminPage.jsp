<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/resources/css/dreammypage/dreammypage.css"> <!-- 임시적으로 붙였습니다. 수정할게요!!!  -->
</head>
<body>
<jsp:include page="/WEB-INF/views/common/mypagemenubar.jsp"></jsp:include>
	<main>
		<div id="main-title">어드민님 안녕하세요!</div>
		<div class="frame">
			<div class="my-info">
				<span>보유포인트 : <b>24243</b>원</span>
				<div class="info-btn-frame">
					<a class="info-btn" href="myINfo.dz ">나의 정보</a>
					<a class="info-btn" href="userWritePwView.dz">회원 탈퇴</a>
				</div>
			</div>
		</div>
		<div class="my-list reserv-list">
			<div class="frame">
				<div class="my-title">
					<span>예약 목록</span>
					<div class="more-btn-frame">
						<a class="more-btn b-btn" href="rListDetailAllByDream.dz">더보기</a>
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
						<tr>
							<td>${status.count }</td>
							<td><a class="table-link-title" href="#"><p></p></a></td>
							<td>${reservation.reserveDate }</td>
							<c:if test="${reservation.rState eq 'O' }">
							<td><a class="reserv-btn" href="#">예약취소</a></td>
							</c:if>
							<c:if test="${reservation.rState eq 'X' }">
							<td><a class="btn btn-secondary">취소완료</a></td>
							</c:if>
							
							<td><a class="reserv-btn" href="#">후기작성</a></td>
						</tr>
						<tr>
							<td>${status.count }</td>
							<td><a class="table-link-title" href="#"><p></p></a></td>
							<td>${reservation.reserveDate }</td>
							<c:if test="${reservation.rState eq 'O' }">
							<td><a class="reserv-btn" href="#">예약취소</a></td>
							</c:if>
							<c:if test="${reservation.rState eq 'X' }">
							<td><a class="btn btn-secondary">취소완료</a></td>
							</c:if>
							
							<td><a class="reserv-btn" href="#">후기작성</a></td>
						</tr>
						<tr>
							<td>${status.count }</td>
							<td><a class="table-link-title" href="#"><p></p></a></td>
							<td>${reservation.reserveDate }</td>
							<c:if test="${reservation.rState eq 'O' }">
							<td><a class="reserv-btn" href="#">예약취소</a></td>
							</c:if>
							<c:if test="${reservation.rState eq 'X' }">
							<td><a class="btn btn-secondary">취소완료</a></td>
							</c:if>
							
							<td><a class="reserv-btn" href="#">후기작성</a></td>
						</tr>
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
						<a class="more-btn b-btn" href="#">더보기</a>
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
						<tr>
							<td>1</td>
							<td><a class="table-link-title" href="#"><p>진짜파스타</p></a></td>
							<td>2021-01-01</td>
							<td>21000원</td>
						</tr>
						<tr>
							<td>1</td>
							<td><a class="table-link-title" href="#"><p>진짜파스타</p></a></td>
							<td>2021-01-01</td>
							<td>21000원</td>
						</tr>
						<tr>
							<td>1</td>
							<td><a class="table-link-title" href="#"><p>진짜파스타</p></a></td>
							<td>2021-01-01</td>
							<td>21000원</td>
						</tr>
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