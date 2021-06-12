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
	<link rel="preconnect" href="https://fonts.gstatic.com">
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100&display=swap" rel="stylesheet">
	<!--  -->
	<title>맛집후기</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/menubar.jsp"></jsp:include>
	
	<main>
		<div class="header-background-area">
        	<img src="/resources/images/board/board-banner.png" alt="뒷배경이미지">
	   	</div>
		<div id="main-title">
			맛집후기
			<div class="shop-name">
				<span>${ shop.shopName }</span>
			</div>
			<input type="hidden" class="shopNo" value="${ shopNo }">
		</div>
		
		
		
	<div class="form-group-area">
			<div class="title-area">
				<label for="mReviewTitle">제목</label>
				<input type="text" name="mReviewTitle" id="mReviewTitle" class="form-control" placeholder="제목" >
			</div>
			<div class="nick-area">
				<label>이름</label>
				<c:if test="${loginUser.userType eq '1' or loginUser.userType eq '2' }">
					<div class="user-nick-area">${ loginUser.userNick }</div>
				</c:if>
				<c:if test="${ loginUser.userType eq '3' or loginUser.userType eq '5' }">
					<div class="user-nick-area">${ loginUser.userName }</div>
				</c:if>
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
				<c:if test="${ !empty loginUser }">
				</c:if>
				<div class="text-center col-sm-3">
					<button class="btn btn-lg gotolist-btn">목록보기</button>
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
				         width: 930,
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
				               ['view', ['codeview']],
				             ],
				             callbacks: {	//여기 부분이 이미지를 첨부하는 부분
									onImageUpload : function(files) {
										uploadSummernoteImageFile(files[0],this);
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
				   
				   $('#saveBtn').on('click', function() {
					   	var mReviewContent = $("#summernote").summernote('code', mReviewContent);
					   	var content = $('.note-editable').val();
						var mReviewTitle = $("#mReviewTitle").val();
						var shopNo = '${shopNo}';
						var reservationNo = '${reservationNo}';
						
						if(mReviewTitle != "" && mReviewContent != "<p><br></p>") {
						    $.ajax({
							   url : "mReviewInsertForm.dz",
							   type : "POST",
							   data : {"mReviewTitle" : mReviewTitle, "mReviewContent" : mReviewContent, "shopNo" : shopNo, "reservationNo" : reservationNo},
							   success : function(data){
								   if(data == "success") {
									   alert('게시글을 올렸습니다');
									   location.href="mReviewMain.dz";
								   } else if (data == 'fail') {
									   alert('게시글 올리기 실패');
									   location.href="mReviewMain.dz";
								   }
								   	//location.href='mReviewMain.dz';						   
							   },
							   error : function() {
									alert('게시글 올리기 에러');							   
									history.back();					   
							   }
						   });
						} else {
							alert('제목과 내용을 입력해주세요');
							return false;
						}
				   });
				   
				   // 목록으로 버튼 클릭시 '작성된 내용은 저장되지 않습니다. 계속하시겠습니까?'출력
					$('.gotolist-btn').on('click', function() {
						var result = confirm('작성된 내용은 저장되지 않습니다. 계속하시겠습니까?');
						if(result) {
							history.back();
						}else {
						};
					}); //end of $('.gotolist-btn').on('click', function(){});
				   
				 });
		});
	
	 function uploadSummernoteImageFile(file, editor) {
			data = new FormData();
			data.append("file", file);
			$.ajax({
				data : data,
				type : "POST",
				url : "uploadMReviewImg",
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