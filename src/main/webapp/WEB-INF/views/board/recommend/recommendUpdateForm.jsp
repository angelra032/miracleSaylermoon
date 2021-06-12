<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
	<link rel="stylesheet" href="resources/css/summernote/summernote-lite.css">
	<link rel="stylesheet" href="resources/css/board/common/insertForm.css">
	<!-- <link rel="stylesheet" href="resources/css/board/recommend/recommendInsertForm.css"> -->
	<!--  -->
	<title>가게추천</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/menubar.jsp"></jsp:include>
	
	<main>
		<div class="header-background-area">
        	<img src="/resources/images/board/board-banner.png" alt="뒷배경이미지">
	   	</div>
		<div id="main-title">가게추천</div>
		
		<div class="form-group-area">
			<!-- <input type="hidden"> -->
			<div class="title-area">
				<label for="recommendTitle">제목</label>
				<input type="text" name="recommendTitle" id="recommendTitle" class="form-control"" placeholder="제목" value="${ recommendBoard.recommendTitle}">
			</div>
			<div class="nick-area">
				<label>이름</label>
				<div class="user-nick-area">${ recommendBoard.recommendWriter }</div>
			</div>
			<br>
			<div class="editor-area">
				<label>내용</label>
				<div class="summernote-edit-area">
				 	<textarea id="summernote" name="eventContent"></textarea>  
				</div>
			</div>
			<div class="btn-area">
				<div class="text-center col-sm-3">
					<button class="btn btn-lg" id="saveBtn">등록하기</button>
				</div>
				<div class="text-center col-sm-3">
					<button class="btn btn-lg" id="saveBtn">목록보기</button>
				</div>
			</div>
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
				          toolbar: [
				               ['style', ['style']],
				               ['font', ['bold', 'italic', 'underline', 'clear']],
				               ['fontname', ['fontname']],
				               ['color', ['color']],
				               ['para', ['ul', 'ol', 'paragraph']],
				               ['height', ['height']],
				               ['insert', ['picture', 'link', 'hr']],
				               ['view', ['codeview']]
				             ],
			             callbacks: {	//여기 부분이 이미지를 첨부하는 부분
								onImageUpload : function(files) {
									uploadImage(files[0],this);
								},
								onPaste: function (e) {
									var clipboardData = e.originalEvent.clipboardData;
									if (clipboardData && clipboardData.items && clipboardData.items.length) {
										var item = clipboardData.items[0];
										if (item.kind === 'file' && item.type.indexOf('image/') !== -1) {
											e.preventDefault();
										}
									}
								}
							}
				         });
				   
				   $('#summernote').summernote('code', '${ recommendBoard.recommendContent }');
				   
				   // 저장버튼
				   $('#saveBtn').on('click', function() {
					   	var recommendContent = $("#summernote").summernote('code', recommendContent);
						var recommendTitle = $("#recommendTitle").val();
						var recommendNo = '${recommendBoard.recommendNo}';
						if(recommendTitle != "" && recommendContent != "<p><br></p>") {
						    $.ajax({
							   url : "recommendModify.dz",
							   type : "POST",
							   data : {"recommendTitle" : recommendTitle, "recommendContent" : recommendContent, "recommendNo" : recommendNo},
							   success : function(data){
								   if(data == "success") {
									   alert('게시글이 수정되었습니다');
									   location.href="recommendDetail.dz?recommendNo="+recommendNo;
									} else {
										alert('게시글 수정 실패');
										location.href="recommendMain.dz";
									}
							   },
							   error : function() {
							   }
						   });
						} else {
							alert('제목과 내용을 입력해주세요');
							return false;
						}
				   });
				   
				   
				 }); 
		}); 
		 
	 /**
		* 이미지 파일 업로드
		*/
		function uploadImage(file, editor) {
			var data = new FormData();
			data.append("file", file);
			
			$.ajax({
				data : data,
				type : "post",
				contentType : false,
				processData : false,
				enctype : "multipart/form-data",
				url : "/uploadRecommendImg",
				success : function(data) {
	            	//항상 업로드된 파일의 url이 있어야 한다.
					$(editor).summernote('insertImage', data.url);
				}
			});
		}
		 
		 
		 
	</script>
</html>