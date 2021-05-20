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
	<title>맛집후기 글쓰기 등록</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/menubar.jsp"></jsp:include>
	
	<main align="center" style="height:700px;"> <!-- 인라인스타일 나중에 지워주기 -->
		<div class="header-background-area">
        	<img src="/resources/images/mapListMain.png" alt="뒷배경이미지">
	   	</div>
		<div id="main-title">맛집후기</div>
	
		<h1>맛집(mz예약)후기</h1>
		
		<form action="" method="post">
		
			<input type="text" name="mReviewTitle" id="mReviewTitle" placeholder="제목">
			<p>${ loginUser.userNick }</p>
			<br>
			<textarea id="summernote" name="eventContent"></textarea> 
			
		</form>
			<div class="col-md-3">
				<button class="btn" id="saveBtn">작성</button>
			</div>
		
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
	jQuery(function ($) {
			$(document).ready(function() { 
				   $('#summernote').summernote({
				         width: 1000,
				          height: 500,                // 에디터 높이
				          minHeight: null,            // 최소 높이
				          maxHeight: null,            // 최대 높이
				          focus: true,                // 에디터 로딩후 포커스를 맞출지 여부
				          lang: "ko-KR",            // 한글 설정
				          placeholder: '자유롭게 글을 작성할 수 있습니다. 명예훼손이나 상대방을 비방, 불쾌감을 주는 글, 욕설, 남을 모욕하는 글은 임의로 제제가 있을 수 있습니다.', //placeholder 설정
				          toolbar: [
				               ['style', ['style']],
				               ['font', ['bold', 'italic', 'underline', 'clear']],
				               ['fontname', ['fontname']],
				               ['color', ['color']],
				               ['para', ['ul', 'ol', 'paragraph']],
				               ['height', ['height']],
				               ['insert', ['picture', 'link', 'hr']],
				               ['view', ['fullscreen', 'codeview']],
				             ]
				         });
				   
				   $('#saveBtn').on('click', function() {
					   	var mReviewContent = $("#summernote").summernote('code', mReviewContent);
						var mReviewTitle = $("#mReviewTitle").val();
					    $.ajax({
						   url : "mReviewInsertForm.dz",
						   type : "POST",
						   data : {"mReviewTitle" : mReviewTitle, "mReviewContent" : mReviewContent},
						   success : function(){
							   // console.log('성공');
								alert('성공');							   
						   },
						   error : function() {
								alert('실패');							   
						   }
					   });
				   });
				   
				   
				 });
		});
	
	 function uploadSummernoteImageFile(file, editor) {
			data = new FormData();
			data.append("file", file);
			$.ajax({
				data : data,
				type : "POST",
				url : "uploadSummernoteImageFile",
				contentType : false,
				enctype : 'multipart/form-data',
				processData : false,
				success : function(data) {
					$(editor).summernote('insertImage', data.url);
				}
			});
		}
	</script>
</html>