<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
	<link rel="stylesheet" href="resources/css/summernote/summernote-lite.css">
	<!--  -->
	<title>가게추천 글 등록</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/menubar.jsp"></jsp:include>
	
	<main align="center" style="height:700px;"> <!-- 인라인스타일 나중에 지워주기 -->
		<!-- <div id="main-title">타이틀!</div> -->
		<h1>가게추천</h1>
		
		<form action="recommendInsertForm.dz" method="post">
			<!-- <input type="hidden"> -->
			<input type="text" name="recommendTitle" placeholder="제목">
			<p>닉네임위치</p>
			<textarea name="recommendContent" placeholder="내용"></textarea>
			<br>
			
			<input type="submit" value="작성">
		</form>
		
		<!-- <textarea id="summernote" name="eventContent"></textarea>  -->
	  
	</main>
	
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.0/jquery.js"></script> 
	<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
	<!-- 섬머노트를 위해 추가해야할 부분 -->
	<script src="resources/js/summernote/summernote-lite.js"></script>
	<script src="resources/js/summernote/lang/summernote-ko-KR.js"></script>
	<script type="text/javascript">
		/* jQuery(function ($) {
			$(document).ready(function() { 
				   $('#summernote').summernote({
				         width: 750,
				          height: 500,                // 에디터 높이
				          minHeight: null,            // 최소 높이
				          maxHeight: null,            // 최대 높이
				          focus: true,                // 에디터 로딩후 포커스를 맞출지 여부
				          lang: "ko-KR",            // 한글 설정
				          placeholder: '내용을 입력해주세요.', //placeholder 설정
				          toolbar: [
				               ['style', ['style']],
				               ['font', ['bold', 'italic', 'underline', 'clear']],
				               ['fontname', ['fontname']],
				               ['color', ['color']],
				               ['para', ['ul', 'ol', 'paragraph']],
				               ['height', ['height']],
				               ['table', ['table']],
				               ['insert', ['link', 'hr']],
				               ['view', ['fullscreen', 'codeview']],
				               ['help', ['help']]
				             ]
				         });
				 });
		}); */
	</script>
</html>