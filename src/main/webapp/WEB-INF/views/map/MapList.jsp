<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/resources/css/shop/MapList.css">
<title>지도조회</title>
</head>
<body>

	<jsp:include page="/WEB-INF/views/common/menubar.jsp">
	
	<main>
		<div id="main-title">지도조회</div>
		<div>
			<h2>찾을 지역을 선택하세요</h2>
		</div>
		<div>
			<table border="1">
				<tr>
					<td><a href="">전체</a></td>
					<td>서울</td>
					<td>부산</td>
					<td>광주</td>
					<td>대구</td>
					<td>대전</td>
				</tr>
				<tr>
					<td>세종</td>
					<td>울산</td>
					<td>인천</td>
					<td>제주</td>
					<td>강원</td>
					<td>경기</td>
				</tr>
				<tr>
					<td>경남</td>
					<td>경북</td>
					<td>전남</td>
					<td>전북</td>
					<td>충남</td>
					<td>충북</td>
				</tr>
			</table>
		</div>
	</main>
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp">
	
</body>
</html>