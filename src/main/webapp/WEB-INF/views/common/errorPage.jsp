<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/css/user/errorpage.css"> 
<title>에러페이지</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/menubar.jsp"></jsp:include>
	<main>
		<div class="header-background-area">
        	<img src="/resources/images/mapListMain.png" alt="뒷배경이미지">
   		</div>	
   		<div id="main-title">에러페이지</div>
   		<div class="frame">
	   		<div class="tabcontent">
				<h4>${ msg }</h4>
				<a  class="submit-btn"  href="#" onclick="back()">돌아가기</a>
			</div>
		</div>
	</main>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
<script>
	function back() {
		history.back();
	}
</script>
</html>