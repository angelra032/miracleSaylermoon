<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사업자 마이페이지</title>
<link rel="stylesheet" href="/resources/css/partnermypage/partnerMyPage.css">
<!-- <link rel="stylesheet" href="/resources/css/mzmypage/mzmypage.css">  -->
<!-- <link href="/static/bootstrap.min.css" rel="stylesheet"> -->
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/mypagemenubar.jsp"></jsp:include>
	<main>
		<div id="main-title">${ loginUser.userName }님 안녕하세요!</div>
		<div class="frame">
			<div class="my-info">
				<span>보유포인트 : <b>${ loginUser.userPoint }</b>원</span>
				<div class="info-btn-frame">
					<a class="info-btn" href="#">나의 정보</a>
					<a class="info-btn" href="#">회원 탈퇴</a>
				</div>
			</div>
		</div>
		<div class="my-list reserv-list">
			<div class="frame">
				<div class="my-title">
					<span>예약 관리</span>
					<div class="more-btn-frame">
						<a class="more-btn b-btn" href="#">더보기</a>
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
							<td>1${reservation.reservationNo }</td>
							<td><a class="table-link-title" href="#"><p>진짜파스타</p></a></td>
							<td>2021-01-01</td>
							<td><a class="reserv-btn" href="#">예약취소</a></td>
							<td><a class="reserv-btn" href="#">후기작성</a></td>
						</tr>
						<tr>
							<td>1</td>
							<td><a class="table-link-title" href="#"><p>진짜파스타</p></a></td>
							<td>2021-01-01</td>
							<td><a class="reserv-btn" href="#">예약취소</a></td>
							<td><a class="reserv-btn" href="#">후기작성</a></td>
						</tr>
						<tr>
							<td>1</td>
							<td><a class="table-link-title" href="#"><p>진짜파스타</p></a></td>
							<td>2021-01-01</td>
							<td><a class="reserv-btn" href="#">예약취소</a></td>
							<td><a class="reserv-btn" href="#">후기작성</a></td>
						</tr>
						
						<c:forEach items="${rList }" var="reservation">
						
						</c:forEach>
						
					</tbody>
				</table>
			</div>
		</div>
		
		<div class="my-list w-list recommend-list">
			<div class="frame">
				<div class="my-title">
					<span>내가 쓴 글</span>
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
	
	
<%-- 	
	<jsp:include page="/WEB-INF/views/common/menubar.jsp"></jsp:include>
		
		<main>
			<div class="header-background-area">
		        <img src="/resources/images/mapListMain.png" alt="뒷배경이미지">
		    </div>
			<div id="main-title">partner${shop.shopName }님, 안녕하세요!</div>
			<div class="frame">
				
				<div id="head-bar">
					<div>
						<button>환급신청</button>
					</div>
				
				</div>
				
				<br>
				
				<div>
					<div>
						<h2>예약 관리</h2>
						<button>더보기</button>
					</div>
					<hr>
					<div>
						
					</div>
				</div>
				
				<br>
				
				<div>
					<div>
						<h2>내가 쓴 글 목록</h2>
						<button>더보기</button>
					</div>
					<hr>
					<div>
						
					</div>
				</div>
				
			</div>
		</main>

	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include> --%>
	
	
</body>
</html>