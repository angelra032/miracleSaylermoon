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
	<!--  -->
	<title>공지사항 등록</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/menubar.jsp"></jsp:include>
	
	<main> <!-- 인라인스타일 나중에 지워주기 -->
		<div class="header-background-area">
        	<img src="/resources/images/mapListMain.png" alt="뒷배경이미지">
	   	</div>
		<div id="main-title">공지사항</div>
		
		<div class="form-group">
			<!-- <input type="hidden"> -->
			<div class="title-area">
				<label for="noticeTitle">제목</label>
				<input type="text" name="noticeTitle" id="noticeTitle" class="form-control"" placeholder="공지사항 제목">
			</div>
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
				<c:if test="${ !empty loginUser }">
				</c:if>
				<%-- <c:if test="${ empty loginUser }">
					<div class="text-center col-sm-3">
						<button class="btn btn-lg" onclick="location.href='/loginView.dz'">로그인</button>
					</div>
				</c:if> --%>
				<div class="text-center col-sm-3">
					<button class="btn btn-lg" onclick="location.href='/notiQnaMain.dz'">목록보기</button>
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
				          callbacks: {
					        	onImageUpload: function(files, editor, welEditable) {
					        		for(var i = files.length -1; i>=0; i--) {
					        			/* sendFile(files[i], this); */
					        			uploadSummernoteImageFile(files[i],this);
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
				               ['insert', ['link', 'hr']],
				               ['view', ['codeview']]
				             ]
				         });
				   
				   // $('#summernote').summernote('code', '<p>가나다</p><p>마바사</p><p>아자차카타파하</p>');
				   
				   // 저장버튼
				   $('#saveBtn').on('click', function() {
					   	var noticeContent = $("#summernote").summernote('code', noticeContent);
						var noticeTitle = $("#noticeTitle").val();
					    $.ajax({
						   url : "noticeInsertForm.dz",
						   type : "POST",
						   data : {"noticeTitle" : noticeTitle, "noticeContent" : noticeContent},
						   success : function(data){
							   if(data == "success") {
								   location.href="notiQnaMain.dz";
								} else {
									alert('게시글 올리기 실패');
									location.href="notiQnaMain.dz";
								}
						   },
						   error : function() {
						   }
					   });
					    
				   });
				   
				   
				 }); 
		}); 
	</script>
</html>