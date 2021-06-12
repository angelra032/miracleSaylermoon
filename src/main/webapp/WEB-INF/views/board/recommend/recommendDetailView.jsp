<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="resources/css/board/common/detailView.css">
<title>가게추천</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/menubar.jsp"></jsp:include>
	<main>
		<div class="header-background-area">
        	<img src="/resources/images/board/board-banner.png" alt="뒷배경이미지">
	   	</div>
		<div id="main-title">
			<span class="top-sub-title">${ recommendBoard.recommendTitle }</span>
			<span class="top-sub-shopName">가게추천</span>
		</div>
        <div class="frame">
            <div class="title-nick-date-area">
                <div class="title-bottom-area">
                    <div class="title-bottom-nick-area">
                        <span class="userNick"><img src="/resources/images/board/user.png">&nbsp;${ recommendBoard.recommendWriter }</span>
                    </div>
                    <div class="title-bottom-date-area">
                        <span class="createDate"><img src="/resources/images/board/clock.png">&nbsp;${ recommendBoard.recommendCreateDate }</span>
                        <span class="boardHit"><img src="/resources/images/board/visibility.png">&nbsp;${ recommendBoard.recommendHit }</span>
                    </div>
                </div>
            </div>
            <div class="content-area">
                ${ recommendBoard.recommendContent }
            </div>
            
			
			
			<div class="bottom-btn-area">
			<c:if test="${ recommendBoard.userNo eq loginUser.userNo}">
	            <div class="modify-btn-area">
	                <button onclick="location.href='recommendUpdateForm.dz?recommendNo=${recommendBoard.recommendNo}'" style="cursor: pointer;">수정하기</button>
	            </div>
	            <div class="modify-btn-area">
	                <button onclick="deleteResult(${recommendBoard.recommendNo})"  style="cursor: pointer;">삭제하기</button>
	            </div> 
				<div class="user-back-btn-area">
	                <button onclick="location.href='recommendMain.dz'" style="cursor: pointer;">목록으로</button>
	            </div>
			</c:if>
			<c:if test="${ recommendBoard.userNo ne loginUser.userNo }">
			 	<div class="back-btn-area">
	                <button onclick="location.href='recommendMain.dz'" style="cursor: pointer;">목록으로</button>
	            </div>
			</c:if>
			</div>
        </div>
       </main>
      <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
// onclick="location.href='recommendDelete.dz?recommendNo=${recommendBoard.recommendNo}'"

	function deleteResult(recommendNo) {
	    var result = confirm('게시글을 삭제합니다');
	    if(result) {
	    	$.ajax({
	    		url : "recommendDelete.dz",
				data : { "recommendNo" : recommendNo },
				success : function(data){ 
					if(data == "success"){
						alert("게시글을 삭제했습니다");
					}else { // 남은 데이터 없을때
						alert("게시글 삭제를 실패했습니다");
					}
					location.href='recommendMain.dz';
				}, //end of success
				error : function() {
					console.log("전송실패");
				}
	    	});
	    } else {
			location.href='recommendMain.dz';
	    }
	}
</script>
</html>