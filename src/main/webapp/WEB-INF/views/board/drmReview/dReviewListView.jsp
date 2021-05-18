<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- <link rel="stylesheet" href="resources/css/board/drmReview/dReviewListView.css"> -->
<link rel="stylesheet" href="resources/css/board/mainList.css">
<title>게시글</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/menubar.jsp"></jsp:include>
	<main align="center">
		<div class="header-background-area">
        	<img src="/resources/images/mapListMain.png" alt="뒷배경이미지">
	   	</div>
		<div id="main-title">감사후기</div>
		<div class="frame">
			<jsp:include page="/WEB-INF/views/board/boardNavi.jsp"></jsp:include>
            <!-- <div class="top-board-menu-area">
                <ul id="top-board-menu">
                    <li><a href="#" class='menu-btn'>맛집후기</a></li>
                    <li><a href="#" class='menu-btn'>감사후기</a></li>
                    <li><a href="#" class='menu-btn'>가게추천</a></li>
                    <li><a href="#" class='menu-btn'>문의 및 공지</a></li>
                </ul>
            </div> -->
    
            <table class="board-list-table">
                <thead>
                    <tr>
                        <td width=110>No</td>
                        <td width=450 style="word-break: break-all;">제목</td>
                        <td width=160>작성자</td>
                        <td width=180>날짜</td>
                    </tr>
                </thead>
                <tbody>
                    <tr onclick="" style="cursor: pointer;">
                        <td>8</td>
                        <td>너무 감사합니다 사장님</td>
                        <td>순둥순둥순둥순둥</td>
                        <td>2021-02-21</td>
                    </tr>
                    <tr onclick="" style="cursor: pointer;">
                        <td>6</td>
                        <td>감사합니다</td>
                        <td>순둥</td>
                        <td>2021-02-21</td>
                    </tr>
                    <tr onclick="" style="cursor: pointer;">
                        <td>6</td>
                        <td>감사합니다</td>
                        <td>순둥</td>
                        <td>2021-02-21</td>
                    </tr>
                    <tr onclick="" style="cursor: pointer;">
                        <td>6</td>
                        <td>감사합니다</td>
                        <td>순둥</td>
                        <td>2021-02-21</td>
                    </tr>
                    <tr onclick="" style="cursor: pointer;">
                        <td>6</td>
                        <td>감사합니다</td>
                        <td>순둥</td>
                        <td>2021-02-21</td>
                    </tr>
                    <tr onclick="" style="cursor: pointer;">
                        <td>6</td>
                        <td>감사합니다 감사합니다 감사합니다</td>
                        <td>순둥</td>
                        <td>2021-02-21</td>
                    </tr>
                </tbody>
            </table>
    
            <!-- 페이징 -->
            <table class="board-page-table">
                <tbody>
                    <tr>
                        <td><a href="#">&ne;</a></td>
                        <td><a href="#">1</a></td>
                        <td><a href="#">2</a></td>
                        <td><a href="#">3</a></td>
                        <td><a href="#">4</a></td>
                        <td><a href="#">5</a></td>
                        <td><a href="#">&ne;</a></td>
                    </tr>
                </tbody>

	        </table>
        </div>
	</main>
</body>
<script type="text/javascript">
	$('.menu-btn').eq(1).css('background','#0160ff').css('color','white');
</script>
</html>