<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="resources/css/board/common/detailView.css">
<title>QnA</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/menubar.jsp"></jsp:include>
	<main>
		<div class="header-background-area">
        	<img src="/resources/images/board/board-banner.png" alt="뒷배경이미지">
	   	</div>
		<div id="main-title">
			<span class="top-sub-title">${ qna.qnaTitle }</span>
			<span class="top-sub-shopName">QnA</span>
		</div>
		
        <div class="frame">
            <div class="title-nick-date-area">
                  <div class="title-bottom-nick-area">
                      <span class="userNick">${ qna.qnaWriter }</span>
                  </div>
                  <div class="title-bottom-date-area">
                      <span class="createDate">${ qna.qanCreateDate }</span>
                      <span class="boardHit">조회수 ${ qna.qnaHit }</span>
                  </div>
            </div>
            </div>
            <div class="content-area">
                ${ qna.qnaContent }
            </div>
            <c:if test="${ qna.groupLayer == 1 &&  qna.groupOrder == 0}">
	            <div class="reply-area">
	            	답변 : <a href="qaDetail.dz?qnaNo=${reply.qnaNo }"><span>${ reply.qnaTitle }</span></a>
	            </div>
            </c:if>
            <c:if test="${ qna.groupLayer == 1 &&  qna.groupOrder == 1}">
	            <%-- <div class="reply-area">
	            	문의 : <a href="qaDetail.dz?qnaNo=${ reply.qnaNo }"><span>${ reply.qnaTitle }</span></a>
	            </div> --%>
            </c:if>

            <div class="bottom-btn-area">
			<c:if test="${ qna.userType eq loginUser.userType }">
	            <div class="modify-btn-area">
	                <button onclick="location.href='qaUpdateForm.dz?qnaNo=${ qna.qnaNo }'" style="cursor: pointer;">수정하기</button>
	            </div>
	            <div class="modify-btn-area">
	                <button onclick="deleteResult(${ qna.qnaNo })" style="cursor: pointer;">삭제하기</button>
	            </div>
	            <div class="user-back-btn-area">
	                <button onclick="location.href='notiQnaMain.dz'" style="cursor: pointer;">목록으로</button>
	            </div>
			</c:if>
			<c:if test="${  qna.userType ne loginUser.userType }">
			 	<div class="back-btn-area">
	                <button onclick="location.href='notiQnaMain.dz'" style="cursor: pointer;">목록으로</button>
	            </div>
			</c:if>
			</div>
        </div>
       </main>
      <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
	function deleteResult(qnaNo) {
	    var result = confirm('게시글을 삭제합니다');
	    if(result) {
	    	$.ajax({
	    		url : "qaDelete.dz",
				data : { "qnaNo" : qnaNo },
				success : function(data){ 
					if(data == "success"){
						alert("게시글을 삭제했습니다");
					}else { // 남은 데이터 없을때
						alert("삭제 실패했습니다");
					}
					location.href='notiQnaMain.dz';
				}, //end of success
				error : function() {
					console.log("전송실패");
				}
	    	});
	    } else {
			location.href='notiQnaMain.dz';
	    }
	}
</script>
</html>