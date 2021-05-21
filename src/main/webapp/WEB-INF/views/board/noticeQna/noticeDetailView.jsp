<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="resources/css/board/common/detailView.css">
<title>공지사항 상세페이지</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/menubar.jsp"></jsp:include>
	<main>
		<div class="header-background-area">
        	<img src="/resources/images/mapListMain.png" alt="뒷배경이미지">
	   	</div>
		<div id="main-title">공지사항 상세페이지</div>
		
        <div class="frame">
            <div class="title-nick-date-area">
                <div class="title-top-area">
                    <span class="title">${ notice.noticeTitle }</span>
                </div>
                <div class="title-bottom-area">
                    <div class="title-bottom-shopName-area">
                        <span class="shopName"></span>
                    </div>
                    <div class="title-bottom-date-area">
                        <span class="createDate">${ notice.noticeCreateDate }</span>
                    </div>
                    <div class="title-bottom-nick-area">
                        <span class="userNick">관리자</span>
                    </div>
                </div>
            </div>
            <div class="content-area">
               ${ notice.noticeContent }
            </div>

            <div class="back-btn-area">
                <button onclick="location.href='notiQnaMain.dz'" style="cursor: pointer;">목록으로</button>
            </div>
        </div>
       </main>
      <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
</html>