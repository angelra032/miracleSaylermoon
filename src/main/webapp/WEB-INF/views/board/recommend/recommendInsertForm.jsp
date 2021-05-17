<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
	<link rel="stylesheet" href="resources/css/summernote/summernote-lite.css">
	<link rel="stylesheet" href="resources/css/board/recommend/recommendInsertForm.css">
	<!--  -->
	<title>가게추천 글 등록</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/menubar.jsp"></jsp:include>
	
	<main align="center">
		<div class="header-background-area">
        	<img src="/resources/images/mapListMain.png" alt="뒷배경이미지">
	   	</div>
		<div id="main-title">타이틀!</div>
		<h1>가게추천</h1>
		
		<form action="" method="post">
			<!-- <input type="hidden"> -->
			<input type="text" name="recommendTitle" id="recommendTitle" placeholder="제목">
			<p>닉네임위치</p>
			<!-- <textarea name="recommendContent" placeholder="내용"></textarea> -->
			<br>
		 	<textarea id="summernote" name="eventContent"></textarea>  
			
		</form>
		
			<div class="col-md-3">
				<button class="btn" id="saveBtn">작성</button>
			</div>
	  
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
				          callbacks: {
					        	onImageUpload: function(files, editor, welEditable) {
					        		for(var i = files.length -1; i>=0; i--) {
					        			/* sendFile(files[i], this); */
					        			uploadSummernoteImageFile(files[i],this);
					        		}
					        	}
					        },
					        onPaste: function (e) {
								var clipboardData = e.originalEvent.clipboardData;
								if (clipboardData && clipboardData.items && clipboardData.items.length) {
									var item = clipboardData.items[0];
									if (item.kind === 'file' && item.type.indexOf('image/') !== -1) {
										e.preventDefault();
									}
								}
							},
				          toolbar: [
				               ['style', ['style']],
				               ['font', ['bold', 'italic', 'underline', 'clear']],
				               ['fontname', ['fontname']],
				               ['color', ['color']],
				               ['para', ['ul', 'ol', 'paragraph']],
				               ['height', ['height']],
				               ['insert', ['picture', 'link', 'hr']],
				               ['view', ['fullscreen', 'codeview']]
				             ]
				         });
				   
				   // $('#summernote').summernote('code', '<p>가나다</p><p>마바사</p><p>아자차카타파하</p>');
				   
				   // 저장버튼
				   $('#saveBtn').on('click', function() {
					   	var recommendContent = $("#summernote").summernote('code', recommendContent);
						var recommendTitle = $("#recommendTitle").val();
					    $.ajax({
						   url : "recommendInsertForm.dz",
						   type : "POST",
						   data : {"recommendTitle" : recommendTitle, "recommendContent" : recommendContent},
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